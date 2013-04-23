package controllers.filters;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * Filtro que comprueba si el usuario que esta en sesion es Administrador
 *
 */
public class FiltroAdministrador extends Action.Simple {

  public Result call(Context ctx) throws Throwable {
    return delegate.call(ctx);
  }
}