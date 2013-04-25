package controllers;

import models.Cliente;
import models.Empleado;
import models.Entrada;
import models.Pelicula;
import models.Sesion;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.taquilla;
import views.html.taquillaVerSesiones;
import views.html.taquillalogin;
import views.html.taquillaVerSesion;
import views.html.reservaRealizadaTaquilla;
import controllers.filters.FiltroTaquilla;;

public class Taquilla extends Controller {
	private static Form<Entrada> formEntrada = Form.form(Entrada.class);
	private static Form<Empleado> formEmpleado = Form.form(Empleado.class);
	
	@With(FiltroTaquilla.class)
	public static Result index() {
		return ok(taquilla.render(Pelicula.findAll()));
	}
	
	@With(FiltroTaquilla.class)
	public static Result verSesiones(Long id) {
		Pelicula pelicula = Pelicula.findById(id);	

		return ok(taquillaVerSesiones.render(pelicula));
	}
	
	@With(FiltroTaquilla.class)
	public static Result verSesionTaquilla(Long id) {
		Sesion sesion = Sesion.findById(id);
			return ok(taquillaVerSesion.render(sesion));
		}
	@With(FiltroTaquilla.class)
	public static Result reservarButacaTaquilla() {
		  Form<Entrada> formularioRecibido = formEntrada.bindFromRequest();
		  
		  String butaca = formularioRecibido.field("butaca").value();
		  String idSesion = formularioRecibido.field("id_sesion").value();
		  
		  Sesion sesion = Sesion.findById(Long.parseLong(idSesion));
		  Entrada entrada = new Entrada();
		  entrada.setButaca(Integer.parseInt(butaca));
		  entrada.setSesion(sesion);
		  entrada.setCliente(Cliente.findByLogin("taquilla"));
		  entrada.save();
		  
		  return redirect(routes.Taquilla.reservaRealizada());
		}
	
	@With(FiltroTaquilla.class)
	public static Result reservaRealizada() {
	 return ok(reservaRealizadaTaquilla.render());
	}
	// LOGIN Y REDIRECCIONES EN SEGUNDO PLANO

	public static Result irALogin() {
		return ok(taquillalogin.render(formEmpleado));
	}

	public static Result doLogin() {
		Form<Empleado> formularioRecibido = formEmpleado.bindFromRequest();

		String login = formularioRecibido.field("login").value();
		String pass = formularioRecibido.field("password").value();

		Empleado empleado = Empleado.findByLogin(login);

		if (empleado == null ||!empleado.getPassword().equals(pass)) {
			// Solamente mostramos el error en login, asi no se sabe si el error
			// lo dio porque no existe el usuario o porque la contraseña no
			// coincide
			formularioRecibido.reject("login",
					"El usuario o contraseña no es correcto");
		 	return badRequest(taquillalogin.render(formularioRecibido));
		}

		// Lo metemos en sesion
		session().put("empleado", login);

		// Si todo ha sido correcto vamos al index
		return redirect(routes.Taquilla.index());
	}
}