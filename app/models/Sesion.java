package models;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

public class Sesion extends Model {
	
	@Id
	private Long id;
	private TipoSesion tipo;
	@ManyToOne
	private Sala sala;
	@ManyToOne
	private Pelicula pelicula;
	

}
