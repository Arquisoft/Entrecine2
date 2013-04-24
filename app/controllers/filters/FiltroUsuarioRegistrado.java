package controllers.filters;

import models.Cliente;
import controllers.routes;
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
		String login = ctx.session().get("cliente");
		Cliente cliente = null;
		if(login != null)
			cliente = Cliente.findByLogin(login);
		if(cliente == null)
			// Si no esta logeado redirijimos al inicio
			return redirect(routes.Clientes.index());
		return delegate.call(ctx);
	}
	
	

}
