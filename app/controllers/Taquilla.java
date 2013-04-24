package controllers;

import models.Empleado;
import models.Pelicula;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.taquilla;
import views.html.taquillalogin;
import controllers.filters.FiltroTaquilla;;

public class Taquilla extends Controller {
	private static Form<Empleado> formEmpleado = Form.form(Empleado.class);
	
	@With(FiltroTaquilla.class)
	public static Result index() {
		return ok(taquilla.render(Pelicula.findAll()));
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

		if (empleado == null || !empleado.getAdmin()
				|| !empleado.getPassword().equals(pass)) {
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