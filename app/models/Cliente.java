package models;

import infrastructure.Factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Cliente extends Model {

	private static final long serialVersionUID = 2768482032925131180L;
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	@Column(unique = true)
	private String login;
	private String password;
	@OneToMany(mappedBy = "cliente")
	private List<Entrada> entradas = new ArrayList<Entrada>();

	public static Cliente findById(Long id) {
		return Factories.business.getClienteService().findById(id);
	}

	public static List<Cliente> findAll() {
		return Factories.business.getClienteService().findAll();
	}

	public static Cliente findByLogin(String login) {
		return Factories.business.getClienteService().findByLogin(login);
	}

	public void update() {
		Factories.business.getClienteService().update(this);
	}

	public void save() {
		Factories.business.getClienteService().save(this);
	}

	public void delete() {
		Factories.business.getClienteService().delete(this);
	}

	public List<Pelicula> getPeliculasVistas() {
		return Factories.business.getClienteService().findPeliculasVistas(this);
	}

	public List<Pelicula> getSugerencias() {
		Map<Cliente, Integer> usuariosParecidos = getUsuariosParecidos();

		List<Pelicula> sugerencias = new ArrayList<Pelicula>();

		// Solo hacemos esto si tiene algun usuario parecido
		if (usuariosParecidos.size() > 0) {
			// Sacamos 6 peliculas solo, con mayor probabilidad que sean de los
			// usuarios que mas comparten, y tambien las pelis que mas se
			// repitan ya que las tienen mas usuarios
			Random rnd = new Random();

			int peliculasSacadas = 0;

			while (peliculasSacadas < 6) {
				for (Entry<Cliente, Integer> cliente : usuariosParecidos
						.entrySet()) {
					// No seguimos si ya sacamos 6
					if (peliculasSacadas < 6) {
						// Siempre añado 10. Cuantas menos pelis comparta mas
						// dificil sera(es mas dificil que salga un 1 entre 11
						// posibilidades que un numero del 1 al 100 entre 111
						// posibilidades)
						int random = rnd.nextInt(cliente.getValue() + 10);

						if (random < cliente.getValue()) {
							// Sacamos una de las que tiene al azar
							random = rnd.nextInt(cliente.getKey()
									.getPeliculasVistas().size());
							Pelicula pelicula = cliente.getKey()
									.getPeliculasVistas().get(random);
							// La metemos solo si no la teniamos ya
							if (!sugerencias.contains(pelicula))
								sugerencias.add(pelicula);
							// La metamos o no la contamos como sacada(a veces
							// nos dara mas sugerencias de peliculas)
							peliculasSacadas++;
						}
					}
				}
			}
		}

		return sugerencias;

	}

	private Map<Cliente, Integer> getUsuariosParecidos() {

		// Con Map Reduce se sacan los usuarios a los que nos parecemos
		// Lo que haremos sera sacar los que hayan visto el mayor numero de
		// peliculas que nosotros tambien hayamos visto

		// El primer par es (Cliente, peliculasVistas), siendo Cliente el
		// cliente del que queremos sacar los usuarios parecidos
		List<Pelicula> peliculasCliente = getPeliculasVistas();

		// Mapeo, sacando los pares (Cliente, 1) para cada cliente que haya
		// visto la peli
		// Como el 1 no es nada, simplemente uso una lista
		List<Cliente> clientes = new ArrayList<Cliente>();

		// Esta parte es clave, ya que ademas de mapear, ya estamos mezclando
		// Si quisiesmo realizarlo con varios servidores esta seria la parte que
		// deberiamos de tener en cuenta para repartir el map entre los
		// servidores esclavos
		// Lo bueno es que este metodo mapPelicula no tiene efectos colaterales
		// (una de las cualidades de la transparencia referencial), lo que la
		// hace propicio para ejecutarse en varios hilos
		// en diferentes servidores esclavos
		for (Pelicula pelicula : peliculasCliente)
			clientes.addAll(mapPelicula(pelicula));

		// Ordeno, es decir, ahora lo ordeno por cliente(aqui no nos haria mucha
		// falta mezclar y ordenar, ya que es un poco absurdo ir metiendo 1s
		// pudiendo hacer el reduce de frente)
		// Esta parte la hacemos con los datos ya mezclados
		Map<Cliente, List<Integer>> vecesCliente = new HashMap<Cliente, List<Integer>>();

		for (Cliente cliente : clientes) {
			// Si ya estaba metido el cliente le metemos otro 1
			if (vecesCliente.containsKey(cliente)) {
				List<Integer> veces = vecesCliente.get(cliente);
				veces.add(1);
				vecesCliente.put(cliente, veces);
			} else {
				// Si no estaba metido le creamos uno(con un uno metido ya ya
				// que hay 1)
				List<Integer> veces = new ArrayList<Integer>();
				veces.add(1);
				vecesCliente.put(cliente, veces);
			}
		}

		// Reduzco, simpelmente cuento los 1s
		Map<Cliente, Integer> numeroPeliculasVistasPorCliente = new HashMap<Cliente, Integer>();

		for (Entry<Cliente, List<Integer>> entrada : vecesCliente.entrySet())
			// El numero de 1s es simplemente el tamaño de la lista
			numeroPeliculasVistasPorCliente.put(entrada.getKey(), entrada
					.getValue().size());

		return numeroPeliculasVistasPorCliente;
	}

	private List<Cliente> mapPelicula(Pelicula pelicula) {
		// Saco los clientes que han visto esa pelicula
		return pelicula.getEspectadores();
	}

	public Cliente() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addEntrada(Entrada entrada) {
		entrada._setCliente(this);
		entradas.add(entrada);
	}

	public void removeEntrada(Entrada entrada) {
		entrada._setCliente(null);
		entradas.remove(entrada);
	}

	public List<Entrada> getEntradas() {
		return Collections.unmodifiableList(entradas);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", login=" + login
				+ ", password=" + password + ", entradas=" + entradas + "]";
	}

}
