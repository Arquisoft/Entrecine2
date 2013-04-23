package controllers.filters;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * Filtro que comprueba si el usuario que esta en sesion es un usuario registrado
 *
 */
public class FiltroUsuarioRegistrado extends Action.Simple{

	@Override
	public Result call(Context ctx) throws Throwable {
		// El usuario en sesion es el login del usuario
		String login = ctx.session().get("usuario");
		if(login == null)
			// Si no esta logeado redirijimos al inicio
			redirect("/");
		return null;
	}
	
	

}
