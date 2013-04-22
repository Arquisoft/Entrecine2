package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;


public class Sala extends Model {
	
	private static final long serialVersionUID = -5960605356904138910L;
	@Id
	@GeneratedValue
	private Long id;
	private Integer numButacas;
	@OneToMany(mappedBy = "sala")
	private List<Sesion> sesiones = new ArrayList<Sesion>();

	public Sala() {
		super();
	}

	public Integer getNumButacas() {
		return numButacas;
	}

	public void setNumButacas(Integer numButacas) {
		this.numButacas = numButacas;
	}

	public Long getId() {
		return id;
	}
	

}
