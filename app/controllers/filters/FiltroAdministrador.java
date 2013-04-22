package controllers.filters;

import play.mvc.*;


public class FiltroAdministrador extends Action.Simple {

  public Result call(Http.Context ctx) throws Throwable {
    return delegate.call(ctx);
  }
}