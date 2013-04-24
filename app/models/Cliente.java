package models;

import infrastructure.Factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
