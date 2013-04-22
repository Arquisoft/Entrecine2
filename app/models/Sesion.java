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
	
	private static final long serialVersionUID = -1969188576839253301L;
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
	
	public Sesion() {
		super();
	}

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Long getId() {
		return id;
	}
	
	public Pelicula getPelicula() {
		return pelicula;
	}

	void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public TipoSesion getTipo() {
		return tipo;
	}

	void setTipo(TipoSesion tipo) {
		this.tipo = tipo;
	}
	
	
}
