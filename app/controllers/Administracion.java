package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Administracion extends Controller {

	// Con la notacion before este metodo se ejecuta antes de cada llamada a uno
	// de los metodos de este controller
	@Before
	private static void isAdminLogeado() {
		
	}

	public static Result index() {
		return ok(admin.render());
	}

}