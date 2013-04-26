package controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import views.html.admin;
import views.html.adminSesionesDePelicula;
import views.html.adminSesionesDeSala;
import views.html.adminTipoSesion;
import views.html.adminlogin;
import controllers.filters.FiltroAdministrador;
import controllers.forms.SesionForm;

public class Administracion extends Controller {

	private static Form<Pelicula> formPelicula = Form.form(Pelicula.class);

	private static Form<Empleado> formEmpleado = Form.form(Empleado.class);

	private static Form<TipoSesion> formTipoSesion = Form
			.form(TipoSesion.class);

	private static Form<Sala> formSala = Form.form(Sala.class);

	private static Form<SesionForm> formSesion = Form.form(SesionForm.class);

	// NAVEGACION MENU SUPERIOR

	@With(FiltroAdministrador.class)
	public static Result index() {
		return ok(admin.render(Pelicula.findAll(), Sala.findAll(),
				formPelicula, formSala));
	}

	@With(FiltroAdministrador.class)
	public static Result adminTipoSesion() {
		return ok(adminTipoSesion.render(TipoSesion.findAll(), formTipoSesion));
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
			return badRequest(admin.render(Pelicula.findAll(), Sala.findAll(),
					formularioRecibido, formSala));
		} else {
			Pelicula p = formularioRecibido.get();
			String id = formularioRecibido.data().get("id");
			if (!id.isEmpty())
				p.setId(Long.parseLong(id));
			p.save();
			return redirect(routes.Administracion.index());
		}
	}

	@With(FiltroAdministrador.class)
	public static Result editarPelicula(Long id) {
		Pelicula p = Pelicula.findById(id);
		Form<Pelicula> f = Form.form(Pelicula.class).fill(p);
		return badRequest(admin.render(Pelicula.findAll(), Sala.findAll(), f,
				formSala));
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

	// CRUD SESIONES
	@With(FiltroAdministrador.class)
	public static Result borrarSesion(Long id) {
		Sesion s = Sesion.findById(id);
		Sala sala = s.getSala();
		s.delete();
		return redirect(routes.Administracion.index());
	}

	@With(FiltroAdministrador.class)
	public static Result editarSesion(Long id) {
		Sesion s = Sesion.findById(id);
		DynamicForm formularioRecibido = Form.form();
		Map<String, String> datos = new HashMap<String, String>();
		datos.put("hora", s.getHora().toString());
		datos.put("tipo", s.getTipo().getId().toString());
		formularioRecibido.bind(datos);
		return badRequest(adminSesionesDeSala.render(s.getSala(), s.getSala().getSesiones()));
	}

	@Transactional
	@With(FiltroAdministrador.class)
	public static Result nuevaSesion(Long s, String fecha_s) {
		Date fecha = Date.valueOf(fecha_s);
		List<Sesion> sesiones = Sesion.findByFecha(fecha);
		Sala sala = Sala.findById(s);
		DynamicForm formularioRecibido = Form.form().bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(adminSesionesDeSala.render(sala, sesiones));
		} else {
			Map<String, String> form = formularioRecibido.get().getData();
			Sesion sesion = new Sesion();
			sesion.setTipo(TipoSesion.findById(Long.parseLong(form.get("tipo"))));
			sesion.setSala(sala);
			sesion.setDia(fecha);
			sesion.setHora(Time.valueOf(form.get("hora") + ":00"));
			sesion.save();
			return redirect(routes.Administracion.index());
		}
	}

	// CRUD SALAS

	@With(FiltroAdministrador.class)
	public static Result nuevaSala() {
		Form<Sala> formularioRecibido = formSala.bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(admin.render(Pelicula.findAll(), Sala.findAll(),
					formPelicula, formSala));
		} else {
			Sala s = formularioRecibido.get();
			String id = formularioRecibido.data().get("id");
			if (!id.isEmpty())
				s.setId(Long.parseLong(id));
			s.save();
			return redirect(routes.Administracion.index());
		}
	}
	
	@With(FiltroAdministrador.class)
	public static Result editarSala(Long id) {
		Sala s = Sala.findById(id);
		Form<Sala> f = Form.form(Sala.class).fill(s);
		return badRequest(admin.render(Pelicula.findAll(), Sala.findAll(), formPelicula,
				f));
	}
	
	@With(FiltroAdministrador.class)
	public static Result borrarSala(Long id) {
		Sala.findById(id).delete();
		return redirect(routes.Administracion.index());
	}


	// VISTA SESIONES

	@With(FiltroAdministrador.class)
	public static Result getSesionesDePelicula(Long id) {
		Pelicula peli = Pelicula.findById(id);
		DynamicForm formularioRecibido = Form.form().bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(admin.render(Pelicula.findAll(), Sala.findAll(),
					formPelicula, formSala));
		} else {
			try {
				Date fecha = Date.valueOf(formularioRecibido.data().get(
						"fechaPeli"));
				List<Sesion> sesiones = Sesion.findByFecha(fecha);
				if (sesiones == null)
					sesiones = new ArrayList<Sesion>();
				return ok(adminSesionesDePelicula.render(peli, sesiones));
			} catch (IllegalArgumentException e) {
				return badRequest(admin.render(Pelicula.findAll(),
						Sala.findAll(), formPelicula, formSala));
			}
		}
	}

	@With(FiltroAdministrador.class)
	public static Result getSesionesDeSala(Long id) {
		Sala sala = Sala.findById(id);
		DynamicForm formularioRecibido = Form.form().bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(admin.render(Pelicula.findAll(), Sala.findAll(),
					formPelicula, formSala));
		} else {
			try {
				Date fecha = Date.valueOf(formularioRecibido.data().get(
						"fechaSala"));
				List<Sesion> sesiones = Sesion.findByFecha(fecha);
				if (sesiones == null)
					sesiones = new ArrayList<Sesion>();
				return ok(adminSesionesDeSala.render(sala, sesiones));
			} catch (IllegalArgumentException e) {
				return badRequest(admin.render(Pelicula.findAll(),
						Sala.findAll(), formPelicula, formSala));
			}
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
