package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.taquilla;

public class Taquilla extends Controller {

	public static Result index() {
		return ok(taquilla.render());
	}

}