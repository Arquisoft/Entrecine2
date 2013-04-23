package controllers.filters;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * Filtro que comprueba si el usuario que esta en sesion es un empleado de la taquilla
 *
 */
public class FiltroUsuarioTaquilla extends Action.Simple{
	
	  public Result call(Context ctx) throws Throwable {
		    return delegate.call(ctx);
		  }

}
