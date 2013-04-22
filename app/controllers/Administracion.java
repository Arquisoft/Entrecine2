package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Administracion extends Controller {
  

    public static Result adminIndex() {
    	return ok(admin.render());
    }
  
}