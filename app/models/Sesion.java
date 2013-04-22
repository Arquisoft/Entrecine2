package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

public class Sesion extends Model {
	
	@Id 
	@GeneratedValue
	private Long id;
	@ManyToOne
	private TipoSesion tipo;
	@ManyToOne
	private Sala sala;
	@ManyToOne
	private Pelicula pelicula;
	@OneToMany(mappedBy = "sesion")
	private List<Entrada> entradas = new ArrayList<Entrada>();
	private int inicio;
	private Date dia;
	

}
