package controllers;

import models.Empleado;
import models.Pelicula;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.admin;
import views.html.adminlogin;
import controllers.filters.FiltroAdministrador;

public class Administracion extends Controller {

	private static Form<Pelicula> formPelicula = Form.form(Pelicula.class);

	private static Form<Empleado> formEmpleado = Form.form(Empleado.class);

	// @Marcos: Index muestra una lista con las películas ->
	// render(List<Pelicula>)
	@With(FiltroAdministrador.class)
	public static Result index() {
		return ok(admin.render(Pelicula.findAll(), formPelicula));
	}

	// @Marcos: Acción de borrar presente en la tabla de películas
	@With(FiltroAdministrador.class)
	public static Result borrarPelicula(Long id) {
		Pelicula.findById(id).delete();
		return redirect(routes.Administracion.index());
	}

	// @Marcos:
	@With(FiltroAdministrador.class)
	public static Result nuevaPelicula() {
		Form<Pelicula> formularioRecibido = formPelicula.bindFromRequest();
		if (formularioRecibido.hasErrors()) {
			return badRequest(admin.render(Pelicula.findAll(),
					formularioRecibido));
		} else {
			formularioRecibido.get().save();
			return redirect(routes.Administracion.index());
		}
	}

	public static Result irALogin() {
		return ok(adminlogin.render(formEmpleado));
	}

	public static Result login() {
		Form<Empleado> formularioRecibido = formEmpleado.bindFromRequest();

		// En este caso no devolveremos los errores correspondientes a si fallo
		// el login o el password, si no que devolveremos un error generico para
		// que un posible atacante malicioso no sepa si lo que ha fallado ha
		// sido la contraseña o el usuario
		String login = formularioRecibido.field("login").value();
		String pass = formularioRecibido.field("password").value();

		Empleado empleado = Empleado.findByLogin(login);
		

		if (empleado == null || !empleado.getAdmin()
				|| !empleado.getPassword().equals(pass))
			// No devolvemos el mismo formulario ya que no queremos devolver la
			// contraseña otra vez
			return badRequest(adminlogin.render(formEmpleado));
		
		// Lo metemos en sesion
		session().put("usuario", login);
		
		// Si todo ha sido correcto vamos al index
		session().put("usuario", login);
		return redirect(routes.Administracion.index());
	}

}