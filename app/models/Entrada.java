package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

public class Entrada extends Model {
	
	@Id
	@GeneratedValue
	private Long id;
	private Integer butaca;
	private Long codigo;
	@ManyToOne
	private Sesion sesion;
	@ManyToOne
	private Cliente cliente;
	

}
