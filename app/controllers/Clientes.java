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
import play.mvc.With;
import views.html.clienteRegistro;
import views.html.confirmacionReserva;
import views.html.index;
import views.html.tpvVirtual;
import views.html.vistaPelicula;
import views.html.vistaSesion;
import controllers.filters.FiltroCliente;
import controllers.filters.FiltroNoLogueado;

public class Clientes extends Controller {

	private static Form<Cliente> formCliente = Form.form(Cliente.class);
	private static Form<Cliente> formRegistro = Form.form(Cliente.class);
	private static Form<Entrada> formEntrada = Form.form(Entrada.class);

	public static Result index() {
		// Si hay un cliente logeado le vamos a mostrar sugerencias
		if(session().get("cliente") != null){
			return ok(index.render(Pelicula.findAll(), Cliente.findByLogin(session().get("cliente")).getSugerencias(), formCliente));
		}else{		
		//Si no hay cliente logeado no pasamos sugerencias
		return ok(index.render(Pelicula.findAll(), null, formCliente));
		}
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
			return badRequest(index.render(Pelicula.findAll(), null,
					formularioRecibido));
		} else {
			session().put("cliente", login);
			return redirect(routes.Clientes.index());
		}
	}

	@With(FiltroCliente.class)
	public static Result logout() {
		// Sacamos a cliente de sesion
		session().remove("cliente");
		return redirect(routes.Clientes.index());
	}

	public static Result verPelicula(Long id) {
		Pelicula pelicula = Pelicula.findById(id);

		if (pelicula == null) {
			String cliente = session().get("cliente");
			if(cliente != null)
				return badRequest(index.render(Pelicula.findAll(), Cliente.findByLogin(cliente).getSugerencias(), formCliente));
			else
				return badRequest(index.render(Pelicula.findAll(), null, formCliente));
		} else {
			return ok(vistaPelicula.render(pelicula, formCliente));
		}
	}

	@With(FiltroCliente.class)
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

	@With(FiltroCliente.class)
	public static Result cancelarReserva() {
		Form<Entrada> formRecibido = formEntrada.bindFromRequest();

		String idEntrada = formRecibido.field("entrada").value();

		Entrada entrada = Entrada.findById(Long.parseLong(idEntrada));
		entrada.delete();

		return redirect(routes.Clientes.verSesion(entrada.getSesion().getId()));
	}

	@With(FiltroCliente.class)
	public static Result verSesion(Long id) {
		Sesion sesion = Sesion.findById(id);

		if (sesion == null) {
			return redirect(routes.Clientes.index());
		} else {
			return ok(vistaSesion.render(sesion, formCliente));
		}
	}

	@With(FiltroCliente.class)
	public static Result tpvVirtual() {
		return ok(tpvVirtual.render());
	}
	
	@With(FiltroNoLogueado.class)
	public static Result doRegister() {
		Form<Cliente> formularioRecibido = formRegistro.bindFromRequest();

		String nombre = formularioRecibido.field("nombre").value();
		String login = formularioRecibido.field("login").value();
		String password = formularioRecibido.field("password").value();
		String confirmPassword = formularioRecibido.field("confirmPassword").value();
		
		if(Cliente.findByLogin(login) != null) {
			formularioRecibido.reject("login", "Ya existe un usuario" +
					" registrado con el mismo nombre.");
		    return badRequest(clienteRegistro.render(formularioRecibido, formCliente));
		} else if (!password.equals(confirmPassword)) {
			formularioRecibido.reject("confirmPassword", "Las contraseñas no coinciden.");
		    return badRequest(clienteRegistro.render(formularioRecibido, formCliente));
		} else {
		    Cliente c = new Cliente();
		    c.setLogin(login);
		    c.setNombre(nombre);
		    c.setPassword(password);
		    c.save();
		    session().put("cliente", login);
			return redirect(routes.Clientes.index());
		}
	}
	
	@With(FiltroNoLogueado.class)
	public static Result vistaRegistro() {
		return ok(clienteRegistro.render(formRegistro, formCliente));
	}

	@SuppressWarnings({ "deprecation", "unused" })
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
				
		Cliente oblivion = new Cliente();
		oblivion.setPassword("pass");
		oblivion.setNombre("oblivion");
		oblivion.setLogin("oblivion");
		oblivion.save();

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
		peli.setDuracion(120);
		peli.save();

		Pelicula peli1 = new Pelicula();
		peli1.setTitulo("Los Croods");
		peli1.setAnio(2013);
		peli1.setGenero("Animación");
		peli1.setEnCartelera(true);
		peli1.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/loscroods/cartelera.jpg");
		peli1.setSinopsis("LOS CROODS es una aventura cómica en 3D que sigue los pasos de la primera familia moderna del mundo durante el viaje de su vida.");
		peli1.setDuracion(130);

		peli1.save();

		Pelicula peli2 = new Pelicula();
		peli2.setTitulo("Memorias de un Zombie Adolescente");
		peli2.setAnio(2013);
		peli2.setGenero("Comedia");
		peli2.setEnCartelera(true);
		peli2.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/memoriasdeunzombieadolescente/cartelera.jpg");
		peli2.setSinopsis("R (Nicholas Hoult) es un zombie que vaga perdido, solo y sin rumbo tras una plaga mundial que ha convertido a casi toda la población en muertos vivientes.");
		peli2.setDuracion(180);
		peli2.save();

		Pelicula peli3 = new Pelicula();
		peli3.setTitulo("On the Road");
		peli3.setAnio(2013);
		peli3.setGenero("Drama");
		peli3.setEnCartelera(true);
		peli3.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/ontheroadenlacarretera/cartelera.jpg");
		peli3.setSinopsis("Nada más morir su padre, Sal Paradise, un neoyorkino aspirante a escritor, conoce a Dean Moriarty, un ex-convicto de un encanto arrollador y casado con la super liberada y seductora Marylou.");
		peli3.setDuracion(125);
		peli3.save();
		
		Pelicula peli4 = new Pelicula();
		peli4.setTitulo("Iron Man 3");
		peli4.setAnio(2013);
		peli4.setGenero("Accion");
		peli4.setEnCartelera(true);
		peli4.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/ironman3/cartelera.jpg");
		peli4.setSinopsis("En Iron Man 3 de Marvel, el descarado pero brillante empresario Tony Stark/Iron Man se enfrentará a un enemigo cuyo poder no conoce límites. ");
		peli4.setDuracion(130);
		peli4.save();
		
		Pelicula peli5 = new Pelicula();
		peli5.setTitulo("Combustión");
		peli5.setAnio(2013);
		peli5.setGenero("Thriller");
		peli5.setEnCartelera(true);
		peli5.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/combustin/cartelera.jpg");
		peli5.setSinopsis("Mikel (Alex Gonzalez) está a punto de casarse con Julia (María Castro), dueña de una importante joyería heredada de sus padres.  ");
		peli5.setDuracion(114);
		peli5.save();

		// Añadimos Sesiones

		Sesion sesion = new Sesion();
		sesion.setFecha(new Date(113, 04, 29));
		sesion.setHora(new Time(22, 30, 00));
		sesion.setTipo(tipoNoche);
		sesion.setPelicula(peli);
		sesion.setSala(sala);
		sesion.save();

		Sesion sesion2 = new Sesion();
		sesion2.setFecha(new Date(113, 04, 4));
		sesion2.setHora(new Time(22, 50, 00));
		sesion2.setTipo(tipoNoche);
		sesion2.setPelicula(peli2);
		sesion2.setSala(sala2);
		sesion2.save();

		Sesion sesion3 = new Sesion();
		sesion3.setFecha(new Date(113, 04, 5));
		sesion3.setHora(new Time(12, 15, 00));
		sesion3.setTipo(tipoMatutina);
		sesion3.setPelicula(peli);
		sesion3.setSala(sala);
		sesion3.save();

		Sesion sesion4 = new Sesion();
		sesion4.setFecha(new Date(113, 04, 5));
		sesion4.setHora(new Time(12, 30, 00));
		sesion4.setTipo(tipoMatutina);
		sesion4.setPelicula(peli2);
		sesion4.setSala(sala2);
		sesion4.save();

		Sesion sesion5 = new Sesion();
		sesion5.setFecha(new Date(113, 04, 5));
		sesion5.setHora(new Time(13, 30, 00));
		sesion5.setTipo(tipoMatutina);
		sesion5.setPelicula(peli3);
		sesion5.setSala(sala1);
		sesion5.save();

		Sesion sesion6 = new Sesion();
		sesion6.setFecha(new Date(113, 04, 6));
		sesion6.setHora(new Time(17, 30, 00));
		sesion6.setTipo(tipoEspectador);
		sesion6.setPelicula(peli4);
		sesion6.setSala(sala);
		sesion6.save();
		
		Sesion sesion7 = new Sesion();
		sesion7.setFecha(new Date(113, 04, 7));
		sesion7.setHora(new Time(10, 30, 00));
		sesion7.setTipo(tipoMatutina);
		sesion7.setPelicula(peli4);
		sesion7.setSala(sala1);
		sesion7.save();
		
		Sesion sesion8 = new Sesion();
		sesion8.setFecha(new Date(113, 04, 6));
		sesion8.setHora(new Time(16, 30, 00));
		sesion8.setTipo(tipoEspectador);
		sesion8.setPelicula(peli4);
		sesion8.setSala(sala);
		sesion8.save();
		
		Sesion sesion9 = new Sesion();
		sesion9.setFecha(new Date(113, 04, 5));
		sesion9.setHora(new Time(22, 30, 00));
		sesion9.setTipo(tipoNoche);
		sesion9.setPelicula(peli4);
		sesion9.setSala(sala2);
		sesion9.save();
		
		Sesion sesion10 = new Sesion();
		sesion10.setFecha(new Date(113, 04, 5));
		sesion10.setHora(new Time(17, 30, 00));
		sesion10.setTipo(tipoEspectador);
		sesion10.setPelicula(peli5);
		sesion10.setSala(sala2);
		sesion10.save();
		
		Sesion sesion11 = new Sesion();
		sesion11.setFecha(new Date(113, 03, 5));
		sesion11.setHora(new Time(17, 30, 00));
		sesion11.setTipo(tipoEspectador);
		sesion11.setPelicula(peli5);
		sesion11.setSala(sala1);
		sesion11.save();
		
		// Añadimos entradas
		// El cliente Oblivion tiene 3 entradas a la peli Oblivion
		Entrada entrada1 = new Entrada();
		entrada1.setSesion(sesion);
		entrada1.setCliente(oblivion);
		entrada1.setButaca(1);
		entrada1.setCodigo(1L);
		entrada1.save();
		Entrada entrada2 = new Entrada();
		entrada2.setSesion(sesion);
		entrada2.setCliente(oblivion);
		entrada2.setButaca(2);
		entrada2.setCodigo(11L);
		entrada2.save();
		Entrada entrada3 = new Entrada();
		entrada3.setSesion(sesion);
		entrada3.setCliente(oblivion);
		entrada3.setButaca(3);
		entrada3.setCodigo(12L);
		entrada3.save();
		
		//El cliente Cliente tiene 2 entradas  Oblivion y 1 a Croods
		Entrada entrada4 = new Entrada();
		entrada4.setSesion(sesion);
		entrada4.setCliente(cliente);
		entrada4.setButaca(16);
		entrada4.setCodigo(13L);
		entrada4.save();
		Entrada entrada5 = new Entrada();
		entrada5.setSesion(sesion);
		entrada5.setCliente(cliente);
		entrada5.setButaca(21);
		entrada5.setCodigo(14L);
		entrada5.save();
		Entrada entrada6 = new Entrada();
		entrada6.setSesion(sesion7);
		entrada6.setCliente(cliente);
		entrada6.setButaca(21);
		entrada6.setCodigo(15L);
		entrada6.save();
		
		//El cliente Taquilla tiene 2 entradas a The Croods y una a The Road
		Entrada entrada7 = new Entrada();
		entrada4.setSesion(sesion7);
		entrada4.setCliente(clienteTaquilla);
		entrada4.setButaca(19);
		entrada4.setCodigo(16L);
		entrada4.save();
		Entrada entrada8 = new Entrada();
		entrada5.setSesion(sesion7);
		entrada5.setCliente(clienteTaquilla);
		entrada5.setButaca(23);
		entrada5.setCodigo(17L);
		entrada5.save();
		Entrada entrada9 = new Entrada();
		entrada6.setSesion(sesion5);
		entrada6.setCliente(clienteTaquilla);
		entrada6.setButaca(21);
		entrada6.setCodigo(18L);
		entrada6.save();
		

		return redirect(routes.Clientes.index());
	}

}
