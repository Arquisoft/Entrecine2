package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;


public class Sala extends Model {
	
	@Id
	@GeneratedValue
	private Long id;
	private Integer numButacas;
	@OneToMany(mappedBy = "sala")
	private List<Sesion> sesiones = new ArrayList<Sesion>();
	

}
