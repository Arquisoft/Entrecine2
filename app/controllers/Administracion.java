package controllers;

import controllers.filters.FiltroAdministrador;
import play.*;
import play.mvc.*;
import views.html.*;

public class Administracion extends Controller {

	
	//Ejecutamos el filtro antes de la llamada a este metodo
	@With(FiltroAdministrador.class)
	public static Result index() {
		return ok(admin.render());
	}

}