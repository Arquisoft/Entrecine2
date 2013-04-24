package controllers.filters;

import controllers.routes;
import models.Empleado;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * Filtro que comprueba si el usuario que esta en sesion es un empleado de la taquilla
 *
 */
public class FiltroTaquilla extends Action.Simple{
	
	  public Result call(Context ctx) throws Throwable {
			// El usuario en sesion es el login del usuario
			String login = ctx.session().get("empleado");
			Empleado empleado = null;
			
			if(login != null)
				empleado = Empleado.findByLogin(login);

			if (empleado == null) {
				return redirect(routes.Taquilla.irALogin());
			}
					

			
			return delegate.call(ctx);
		  }

}
