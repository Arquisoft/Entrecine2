package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

//Usamos un controller diferente para cuando los usuarios estan logeados para asi poder filtrar en un metodo con la anotacion @Before si estos estan logeados o no
public class UsuariosLogeados extends Controller {

	// Con la notacion before este metodo se ejecuta antes de cada llamada a uno
	// de los metodos de este controller
	@Before
	private static void isUsuarioLogeado() {

	}

}