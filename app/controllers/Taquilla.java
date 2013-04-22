package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Taquilla extends Controller {

	public static Result index() {
		return ok(taquilla.render());
	}

}