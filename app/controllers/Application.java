package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result adminIndex() {
    	return ok(admin.render());
    }

    public static Result pelisMgmnt() {
    	return ok(pelisMgmnt.render());
    }
  
}
