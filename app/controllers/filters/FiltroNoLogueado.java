package controllers.filters;

import controllers.routes;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;

public class FiltroNoLogueado extends Simple {

	@Override
	public Result call(Context context) throws Throwable {
		// El usuario no debe estar en sesion
		if (context.session().get("cliente") != null)
			return redirect(routes.Clientes.index());
		// Si no esta en sesion continuamos	
		return delegate.call(context);
	}

}
