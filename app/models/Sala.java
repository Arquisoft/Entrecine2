package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;


public class Sala extends Model {
	
	@Id
	private Long id;
	private int numButacas;
	@OneToMany(mappedBy = "sala")
	private Set<Sesion> sesiones = new HashSet<Sesion>();
	

}
