package controllers;

import models.Pelicula;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.admin;
import controllers.filters.FiltroAdministrador;

public class Administracion extends Controller {

	private static Form<Pelicula> formPelicula = Form.form(Pelicula.class);
	
	// @Marcos: Index muestra una lista con las películas -> render(List<Pelicula>)
	@play.db.jpa.Transactional
	@With(FiltroAdministrador.class)
	public static Result index() {
		return ok(admin.render(Pelicula.findAll(), formPelicula));
	}
	
	// @Marcos: Acción de borrar presente en la tabla de películas
	@play.db.jpa.Transactional
	@With(FiltroAdministrador.class)
	public static Result borrarPelicula(Long id) {
		Pelicula.findById(id).delete();
		return redirect(routes.Administracion.index());
	}
	
	// @Marcos: 
	@play.db.jpa.Transactional
	@With(FiltroAdministrador.class)
	public static Result nuevaPelicula() {
		Form<Pelicula> formularioRecibido = formPelicula.bindFromRequest();
		  if(formularioRecibido.hasErrors()) {
		    return badRequest(
		      admin.render(Pelicula.findAll(), formularioRecibido)
		    );
		  } else {
		    formularioRecibido.get().save();
		    return redirect(routes.Administracion.index());  
		  }
	}

}