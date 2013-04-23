package controllers.filters;

import models.Empleado;
import controllers.routes;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * Filtro que comprueba si el usuario que esta en sesion es Administrador
 *
 */
public class FiltroAdministrador extends Action.Simple {

  public Result call(Context ctx) throws Throwable {
		// El usuario en sesion es el login del usuario
		String login = ctx.session().get("usuario");
		Empleado empleado = null;
		
		if(login != null)
			empleado = Empleado.findByLogin(login);

		if (empleado == null) 
			redirect(routes.Administracion.irALogin());
		
		if(!empleado.getAdmin() )
			// Si no esta logeado  o no es admin redirijimos a la pantalla de login
			redirect(routes.Administracion.irALogin());
		return null;
  }
}