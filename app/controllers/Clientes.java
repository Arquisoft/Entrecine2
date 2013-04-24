package controllers;

import models.Cliente;
import models.Empleado;
import models.Pelicula;
import models.Sesion;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.vistaPelicula;
import views.html.vistaSesion;

public class Clientes extends Controller {

	private static Form<Cliente> formCliente = Form.form(Cliente.class);

	public static Result index() {
		Cliente cliente = Cliente.findByLogin(session().get("cliente"));
		return ok(index.render(Pelicula.findAll(), formCliente, cliente));
	}

	public static Result login() {
		Form<Cliente> formularioRecibido = formCliente.bindFromRequest();

		String login = formularioRecibido.field("login").value();
		String password = formularioRecibido.field("password").value();

		Cliente cliente = Cliente.findByLogin(login);

		if (cliente == null || !password.equals(cliente.getPassword())) {
			//Solamente mostramos el error en login, asi no se sabe si el error lo dio porque no existe el usuario o porque la contraseña no coincide
			formularioRecibido.reject("login", "El usuario o contraseña no es correcto");
			return badRequest(index.render(Pelicula.findAll(), formularioRecibido, cliente));
		} else {
			session().put("cliente", login);
			return redirect(routes.Clientes.index());
		}
	}

	public static Result verPelicula(Long id) {
		Pelicula pelicula = Pelicula.findById(id);
		Cliente cliente = Cliente.findByLogin(session().get("cliente"));
		

		if (pelicula == null) {
			return badRequest(index.render(Pelicula.findAll(), formCliente, cliente));
		} else {
			return ok(vistaPelicula.render(pelicula, cliente));
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

	public static Result rellenarDb() {

		Cliente cliente = new Cliente();
		cliente.setPassword("pass");
		cliente.setLogin("login");
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
		peli.setTitulo("Pelicula 1");
		peli.setAnio(2013);
		peli.setGenero("Accion");
		peli.save();
		Pelicula peli2 = new Pelicula();
		peli2.setTitulo("Pelicula 2");
		peli2.setAnio(2013);
		peli2.setGenero("Accion");
		peli2.save();

		return redirect(routes.Clientes.index());
	}
	
}
