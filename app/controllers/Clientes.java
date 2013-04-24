package controllers;

import java.sql.Date;

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
import views.html.index;
import views.html.vistaPelicula;
import views.html.vistaSesion;

public class Clientes extends Controller {

	private static Form<Cliente> formCliente = Form.form(Cliente.class);
	private static Form<Entrada> formEntrada = Form.form(Entrada.class);

	public static Result index() {
		Cliente cliente = Cliente.findByLogin(session().get("cliente"));
		return ok(index.render(Pelicula.findAll(), formCliente));
	}

	public static Result login() {
		Form<Cliente> formularioRecibido = formCliente.bindFromRequest();

		String login = formularioRecibido.field("login").value();
		String password = formularioRecibido.field("password").value();

		Cliente cliente = Cliente.findByLogin(login);

		if (cliente == null || !password.equals(cliente.getPassword())) {
			//Solamente mostramos el error en login, asi no se sabe si el error lo dio porque no existe el usuario o porque la contraseña no coincide
			formularioRecibido.reject("login", "El usuario o contraseña no es correcto");
			return badRequest(index.render(Pelicula.findAll(), formularioRecibido));
		} else {
			session().put("cliente", login);
			return redirect(routes.Clientes.index());
		}
	}
	
	public static Result logout(){
		//Simplemente ponemos cliente a null(que es lo mismo que que no este metido, y si no estaba metido nos da igual)
		session().put("cliente", null);
		return redirect(routes.Clientes.index());
	}

	public static Result verPelicula(Long id) {
		Pelicula pelicula = Pelicula.findById(id);
		Cliente cliente = Cliente.findByLogin(session().get("cliente"));
		

		if (pelicula == null) {
			return badRequest(index.render(Pelicula.findAll(), formCliente));
		} else {
			return ok(vistaPelicula.render(pelicula, cliente,formCliente));
		}
	}
	
	public static Result verSesion(Long id) {
		Sesion sesion = Sesion.findById(id);
		
		if (sesion == null) {
			return redirect(routes.Clientes.index());
		} else {
			return ok(vistaSesion.render(sesion));
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
		entrada.save();
		
		return redirect(routes.Clientes.index());
	}

	public static Result rellenarDb() {

		Cliente cliente = new Cliente();
		cliente.setPassword("pass");
		cliente.setLogin("cliente");
		cliente.save();
		cliente = new Cliente();
		cliente.setPassword("pass");
		cliente.setLogin("null");
		cliente.save();
		
		Empleado empleado = new Empleado();
		empleado.setPassword("pass");
		empleado.setLogin("empleado");
		empleado.save();
		
		Empleado admin = new Empleado();
		admin.setPassword("pass");
		admin.setLogin("admin");
		admin.setAdmin(true);
		admin.save();
		
		Pelicula peli = new Pelicula();
		peli.setTitulo("Oblivion");
		peli.setAnio(2013);
		peli.setGenero("Accion");
		peli.setEnCartelera(true);
		peli.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/oblivion/cartelera.jpg");
		peli.setSinopsis("En un planeta Tierra espectacular que ha evolucionado hasta ser irreconocible, un hombre se enfrenta al pasado y toma el camino de la redención mientras lucha para salvar a la raza humana");
		Sala sala = new Sala();
		sala.setNumButacas(200);
		sala.setNumero(1);
		TipoSesion tipo = new TipoSesion();
		tipo.setNombre("Ordinaria");
		tipo.setPrecio(8.60);
		Sesion sesion = new Sesion();
		sesion.setDia(new Date(2013,04,29));
		sesion.setInicio(22);
		sesion.setTipo(tipo);
		sesion.setPelicula(peli);
		sesion.setSala(sala);
		peli.save();
		sala.save();
		tipo.save();
		sesion.save();
		
		peli = new Pelicula();
		peli.setTitulo("Los Croods");
		peli.setAnio(2013);
		peli.setGenero("Animación");
		peli.setEnCartelera(true);
		peli.setImagenCartelera("http://www.cinesa.es/Manager/peliculas/loscroods/cartelera.jpg");
		peli.setSinopsis("LOS CROODS es una aventura cómica en 3D que sigue los pasos de la primera familia moderna del mundo durante el viaje de su vida.");
		sala = new Sala();
		sala.setNumButacas(100);
		sala.setNumero(2);
		tipo = new TipoSesion();
		tipo.setNombre("Matutina");
		tipo.setPrecio(5.90);
		sesion = new Sesion();
		sesion.setDia(new Date(2013,04,29));
		sesion.setInicio(10);
		sesion.setTipo(tipo);
		sesion.setPelicula(peli);
		sesion.setSala(sala);
		peli.save();
		sala.save();
		tipo.save();
		sesion.save();
		
		return redirect(routes.Clientes.index());
	}
	
}
