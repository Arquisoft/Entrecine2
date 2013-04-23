package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.admin;
import controllers.filters.FiltroAdministrador;

public class Administracion extends Controller {

	
	//Ejecutamos el filtro antes de la llamada a este metodo
	@With(FiltroAdministrador.class)
	public static Result index() {
		return ok(admin.render());
	}

}