package controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.Random;

import models.Cliente;
import models.Empleado;
import models.Entrada;
import models.Pelicula;
import models.Sala;
import models.Sesion;
import models.TipoSesion;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.confirmacionReserva;
import views.html.index;
import views.html.tpvVirtual;
import views.html.vistaPelicula;
import views.html.vistaSesion;

public class Clientes extends Controller {

	private static Form<Cliente> formCliente = Form.form(Cliente.class);
	private static Form<Entrada> formEntrada = Form.form(Entrada.class);

	public static Result index() {
		return ok(index.render(Pelicula.findAll(), formCliente));
	}

	public static Result login() {
		Form<Cliente> formularioRecibido = formCliente.bindFromRequest();

		String login = formularioRecibido.field("login").value();
		String password = formularioRecibido.field("password").value();

		Cliente cliente = Cliente.findByLogin(login);

		if (cliente == null || !password.equals(cliente.getPassword())) {
			// Solamente mostramos el error en login, asi no se sabe si el error
			// lo dio porque no existe el usuario o porque la contraseÃ±a no
			// coincide
			formularioRecibido.reject("login",
					"El usuario o contraseÃ±a no es correcto");
			return badRequest(index.render(Pelicula.findAll(),
					formularioRecibido));
		} else {
			session().put("cliente", login);
			return redirect(routes.Clientes.index());
		}
	}

	public static Result logout() {
		// Sacamos a cliente de sesion
		session().remove("cliente");
		return redirect(routes.Clientes.index());
	}

	public static Result verPelicula(Long id) {
		Pelicula pelicula = Pelicula.findById(id);

		if (pelicula == null) {
			return badRequest(index.render(Pelicula.findAll(), formCliente));
		} else {
			return ok(vistaPelicula.render(pelicula, formCliente));
		}
	}

	public static Result reservarButaca() {
		Form<Entrada> formularioRecibido = formEntrada.bindFromRequest();

		String butaca = formularioRecibido.field("butaca").value();
		String idSesion = formularioRecibido.field("id_sesion").value();

		Sesion sesion = Sesion.findById(Long.parseLong(idSesion));
		Entrada entrada = new Entrada();
		entrada.setButaca(Integer.parseInt(butaca));
		entrada.setSesion(sesion);
		entrada.setCliente(Cliente.findByLogin(session().get("cliente")));
		entrada.setCodigo(new Random().nextLong());
		entrada.save();
		return ok(confirmacionReserva.render(entrada, formCliente));
	}

	public static Result cancelarReserva() {
		Form<Entrada> formRecibido = formEntrada.bindFromRequest();

		String idEntrada = formRecibido.field("entrada").value();

		Entrada entrada = Entrada.findById(Long.parseLong(idEntrada));
		entrada.delete();

		return redirect(routes.Clientes.verSesion(entrada.getSesion().getId()));
	}

	public static Result verSesion(Long id) {
		Sesion sesion = Sesion.findById(id);

		if (sesion == null) {
			return redirect(routes.Clientes.index());
		} else {
			return ok(vistaSesion.render(sesion, formCliente));
		}
	}

	public static Result tpvVirtual() {
		return ok(tpvVirtual.render());
	}

	@SuppressWarnings("deprecation")
	public static Result rellenarDb() {

		// Añadir Clientes
		Cliente cliente = new Cliente();
		cliente.setPassword("pass");
		cliente.setNombre("Pepito");
		cliente.setLogin("cliente");
		cliente.save();

		Cliente clienteTaquilla = new Cliente();
		clienteTaquilla.setPassword("pass");
		clienteTaquilla.setNombre("Taquilla");
		clienteTaquilla.setLogin("taquilla");
		clienteTaquilla.save();

		// Añadir empleados
		Empleado empleado = new Empleado();
		empleado.setPassword("pass");
		empleado.setLogin("empleado");
		empleado.setAdmin(false);
		empleado.save();

		// Añadir admin
		Empleado admin = new Empleado();
		admin.setPassword("pass");
		admin.setLogin("admin");
		admin.setAdmin(true);
		admin.save();

		// Añadimos salas
		Sala sala = new Sala();
		sala.setNumButacas(200);
		sala.setNumero(1);
		sala.save();
		Sala sala1 = new Sala();
		sala1.setNumButacas(400);
		sala1.setNumero(2);
		sala1.save();

		Sala sala2 = new Sala();
		sala2.setNumButacas(600);
		sala2.setNumero(3);
		sala2.save();

		// Añadimos Tipos
		TipoSesion tipoOrdinaria = new TipoSesion();
		tipoOrdinaria.setNombre("Ordinaria");
		tipoOrdinaria.setPrecio(8.60);
		tipoOrdinaria.save();

		TipoSesion tipoMatutina = new TipoSesion();
		tipoMatutina.setNombre("Matutina");
		tipoMatutina.setPrecio(7.60);
		tipoMatutina.save();

		TipoSesion tipoEspectador = new TipoSesion();
		tipoEspectador.setNombre("Día del Espectador");
		tipoEspectador.setPrecio(5.60);
		tipoEspectador.save();

		TipoSesion tipoNoche = new TipoSesion();
		tipoNoche.setNombre("Noche");
		tipoNoche.setPrecio(7.80);
		tipoNoche.save();

		// Añadimos Películas
		Pelicula peli = new Pelicula();
		peli.setTitulo("Oblivion");
		peli.setAnio(2013);
		peli.setGenero("Accion");
		peli.setEnCartelera(true);
		peli.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/oblivion/cartelera.jpg");
		peli.setSinopsis("En un planeta Tierra espectacular que ha evolucionado hasta ser irreconocible, un hombre se enfrenta al pasado y toma el camino de la redención mientras lucha para salvar a la raza humana");
		peli.save();

		Pelicula peli1 = new Pelicula();
		peli1.setTitulo("Los Croods");
		peli1.setAnio(2013);
		peli1.setGenero("Animación");
		peli1.setEnCartelera(true);
		peli1.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/loscroods/cartelera.jpg");
		peli1.setSinopsis("LOS CROODS es una aventura cÃ³mica en 3D que sigue los pasos de la primera familia moderna del mundo durante el viaje de su vida.");
		peli1.save();

		Pelicula peli2 = new Pelicula();
		peli2.setTitulo("Memorias de un Zombie Adolescente");
		peli2.setAnio(2013);
		peli2.setGenero("Comedia");
		peli2.setEnCartelera(true);
		peli2.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/memoriasdeunzombieadolescente/cartelera.jpg");
		peli2.setSinopsis("R (Nicholas Hoult) es un zombie que vaga perdido, solo y sin rumbo tras una plaga mundial que ha convertido a casi toda la población en muertos vivientes.");
		peli2.save();

		Pelicula peli3 = new Pelicula();
		peli3.setTitulo("On the Road");
		peli3.setAnio(2013);
		peli3.setGenero("Drama");
		peli3.setEnCartelera(true);
		peli3.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/ontheroadenlacarretera/cartelera.jpg");
		peli3.setSinopsis("Nada mÃ¡s morir su padre, Sal Paradise, un neoyorkino aspirante a escritor, conoce a Dean Moriarty, un ex-convicto de un encanto arrollador y casado con la super liberada y seductora Marylou.");
		peli3.save();

		// Añadimos Sesiones

		Sesion sesion = new Sesion();
		sesion.setDia(new Date(2013, 04, 29));
		sesion.setHora(new Time(22, 30, 00));
		sesion.setTipo(tipoNoche);
		sesion.setPelicula(peli);
		sesion.setSala(sala);
		sesion.save();

		Sesion sesion2 = new Sesion();
		sesion2.setDia(new Date(2013, 04, 29));
		sesion2.setHora(new Time(22, 50, 00));
		sesion2.setTipo(tipoNoche);
		sesion2.setPelicula(peli2);
		sesion2.setSala(sala2);
		sesion2.save();

		Sesion sesion3 = new Sesion();
		sesion3.setDia(new Date(2013, 04, 30));
		sesion3.setHora(new Time(12, 15, 00));
		sesion3.setTipo(tipoMatutina);
		sesion3.setPelicula(peli);
		sesion3.setSala(sala);
		sesion3.save();

		Sesion sesion4 = new Sesion();
		sesion4.setDia(new Date(2013, 04, 30));
		sesion4.setHora(new Time(12, 30, 00));
		sesion4.setTipo(tipoMatutina);
		sesion4.setPelicula(peli2);
		sesion4.setSala(sala2);
		sesion4.save();

		Sesion sesion5 = new Sesion();
		sesion5.setDia(new Date(2013, 04, 30));
		sesion5.setHora(new Time(13, 30, 00));
		sesion5.setTipo(tipoMatutina);
		sesion5.setPelicula(peli3);
		sesion5.setSala(sala1);
		sesion5.save();

		Sesion sesion6 = new Sesion();
		sesion6.setDia(new Date(2013, 04, 30));
		sesion6.setHora(new Time(17, 30, 00));
		sesion6.setTipo(tipoEspectador);
		sesion6.setPelicula(peli1);
		sesion6.setSala(sala);
		sesion6.save();

		return redirect(routes.Clientes.index());
	}

}
