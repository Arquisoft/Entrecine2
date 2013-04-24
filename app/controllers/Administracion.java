package controllers;

import models.Empleado;
import models.Pelicula;
import models.Sesion;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.adminPeliculas;
import views.html.adminlogin;
import views.html.adminSesiones;
import controllers.filters.FiltroAdministrador;

public class Administracion extends Controller {

	private static Form<Pelicula> formPelicula = Form.form(Pelicula.class);

	private static Form<Empleado> formEmpleado = Form.form(Empleado.class);
	
	private static Form<Sesion> formSesion = Form.form(Sesion.class);

	//	NAVEGACION MENU SUPERIOR
	
	@With(FiltroAdministrador.class)
	public static Result index() {
		return ok(adminPeliculas.render(Pelicula.findAll(), formPelicula));
	}

	// @Marcos: Acción de borrar presente en la tabla de películas
	@With(FiltroAdministrador.class)
	public static Result borrarPelicula(Long id) {
		Pelicula.findById(id).delete();
		return redirect(routes.Administracion.adminPeliculas());
	}

	@With(FiltroAdministrador.class)
	public static Result adminPeliculas() {
		return ok(adminPeliculas.render(Pelicula.findAll(), formPelicula));
	}


	@With(FiltroAdministrador.class)
	public static Result adminSesiones() {
		return TODO;
	}
	
	
	// CRUD PELICULAS

	@With(FiltroAdministrador.class)
	public static Result borrarPelicula(Long id) {
		Pelicula.findById(id).delete();
		return redirect(routes.Administracion.index());
	}
	

	@With(FiltroAdministrador.class)
	public static Result nuevaPelicula() {
		Form<Pelicula> formularioRecibido = formPelicula.bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(adminPeliculas.render(Pelicula.findAll(),
					formularioRecibido));
		} else {
			formularioRecibido.get().save();
			return redirect(routes.Administracion.index());
		}
	}

	@With(FiltroAdministrador.class)
	public static Result adminSesiones() {
		return ok(adminSesiones.render(Sesion.findAll(), formSesion));
	}

	@With(FiltroAdministrador.class)
	public static Result borrarSesion(Long id) {
		Pelicula.findById(id).delete();
		return redirect(routes.Administracion.adminSesiones());
	}

	@With(FiltroAdministrador.class)
	public static Result nuevaSesion() {
		Form<Sesion> formularioRecibido = formSesion.bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(adminSesiones.render(Sesion.findAll(),
					formularioRecibido));
		} else {
			formularioRecibido.get().save();
			return redirect(routes.Administracion.index());
		}
	}
	
	@With(FiltroAdministrador.class)
	public static Result editarPelicula(Long id) {
		Pelicula p = Pelicula.findById(id);
		Form<Pelicula> f = Form.form(Pelicula.class).fill(p);
		return badRequest(adminPeliculas.render(Pelicula.findAll(), f));
	}

	
	// LOGIN Y REDIRECCIONES EN SEGUNDO PLANO
	
	public static Result irALogin() {
		return ok(adminlogin.render(formEmpleado));
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
			return badRequest(adminlogin.render(formularioRecibido));
		}

		// Lo metemos en sesion
		session().put("empleado", login);

		// Si todo ha sido correcto vamos al index
		return redirect(routes.Administracion.index());
	}

}
