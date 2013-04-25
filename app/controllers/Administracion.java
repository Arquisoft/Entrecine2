package controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import models.Empleado;
import models.Pelicula;
import models.Sala;
import models.Sesion;
import models.TipoSesion;
import play.data.DynamicForm;
import play.data.Form;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.*;
import controllers.filters.FiltroAdministrador;
import controllers.forms.SesionForm;

public class Administracion extends Controller {

	private static Form<Pelicula> formPelicula = Form.form(Pelicula.class);

	private static Form<Empleado> formEmpleado = Form.form(Empleado.class);
	
	private static Form<TipoSesion> formTipoSesion = Form.form(TipoSesion.class);

	private static Form<SesionForm> formSesion = Form.form(SesionForm.class);

	// NAVEGACION MENU SUPERIOR

	@With(FiltroAdministrador.class)
	public static Result index() {
		return ok(adminPeliculas.render(Pelicula.findAll(), formPelicula));
	}

	@With(FiltroAdministrador.class)
	public static Result adminPeliculas() {
		return ok(adminPeliculas.render(Pelicula.findAll(), formPelicula));
	}
	
	@With(FiltroAdministrador.class)
	public static Result adminTipoSesion() {
		return ok(adminTipoSesion.render(TipoSesion.findAll(), formTipoSesion));
	}

	@With(FiltroAdministrador.class)
	public static Result adminSesiones() {
		return ok(adminSesiones.render(Pelicula.findAll()));
	}

	// CRUD PELICULAS

	@With(FiltroAdministrador.class)
	public static Result borrarPelicula(Long id) {
		Pelicula.findById(id).delete();
		return redirect(routes.Administracion.adminPeliculas());
	}

	@With(FiltroAdministrador.class)
	public static Result nuevaPelicula() {
		Form<Pelicula> formularioRecibido = formPelicula.bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(adminPeliculas.render(Pelicula.findAll(),
					formularioRecibido));
		} else {
			formularioRecibido.get().save();
			return redirect(routes.Administracion.adminPeliculas());
		}
	}

	@With(FiltroAdministrador.class)
	public static Result editarPelicula(Long id) {
		Pelicula p = Pelicula.findById(id);
		Form<Pelicula> f = Form.form(Pelicula.class).fill(p);
		return badRequest(adminPeliculas.render(Pelicula.findAll(), f));
	}
	
	// CRUD TIPOS DE SESION
	@With(FiltroAdministrador.class)
	public static Result borrarTipoSesion(Long id) {
		TipoSesion.findById(id).delete();
		return redirect(routes.Administracion.adminTipoSesion());
	}

	@With(FiltroAdministrador.class)
	public static Result nuevoTipoSesion() {
		Form<TipoSesion> formularioRecibido = formTipoSesion.bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(adminTipoSesion.render(TipoSesion.findAll(),
					formularioRecibido));
		} else {
			formularioRecibido.get().save();
			return redirect(routes.Administracion.adminTipoSesion());
		}
	}

	@With(FiltroAdministrador.class)
	public static Result editarTipoSesion(Long id) {
		TipoSesion t = TipoSesion.findById(id);
		Form<TipoSesion> f = Form.form(TipoSesion.class).fill(t);
		return badRequest(adminTipoSesion.render(TipoSesion.findAll(), f));
	}

	// ADMIN SESIONES
	
	@With(FiltroAdministrador.class)
	public static Result getSesionesDePelicula(Long id) { //TODO falta mirar la fecha
		Pelicula peli = Pelicula.findById(id);
		DynamicForm formularioRecibido = Form.form().bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(adminSesiones.render(Pelicula.findAll()));
		} else {
			return ok(adminSesionesDePelicula.render(peli));
		}
	}
	
	@With(FiltroAdministrador.class)
	public static Result borrarSesion(Long id) {
		Sesion.findById(id).delete();
		return redirect(routes.Administracion.adminSesiones());
	}
	
	@With(FiltroAdministrador.class)
	public static Result editarSesion(Long id) {
		Sesion s = Sesion.findById(id);
		SesionForm sesionForm = new SesionForm();
		sesionForm.setPelicula(s.getPelicula().getId());
		sesionForm.setSala(s.getSala().getId());
		sesionForm.setTipo(s.getTipo().getId());
		sesionForm.setDia(s.getDia().toString());
		sesionForm.setHora(s.getHora().toString());
		Form<SesionForm> f = Form.form(SesionForm.class).fill(sesionForm);
		return badRequest(adminSesiones.render(Pelicula.findAll()));
	}

	@Transactional
	@With(FiltroAdministrador.class)
	public static Result nuevaSesion() {
		Form<SesionForm> formularioRecibido = formSesion.bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(adminSesiones.render(Pelicula.findAll()));
		} else {
			SesionForm form = formularioRecibido.get();
			Sesion sesion = new Sesion();
			sesion.setPelicula(Pelicula.findById(form.getPelicula()));
			sesion.setTipo(TipoSesion.findById(form.getTipo()));
			sesion.setSala(Sala.findById(form.getSala()));
			sesion.setDia(Date.valueOf(form.getDia()));
			sesion.setHora(Time.valueOf(form.getHora() + ":00"));
			sesion.save();
			return redirect(routes.Administracion.adminSesiones());
		}
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
