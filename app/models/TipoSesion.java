package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

public class TipoSesion extends Model {
	
	private static final long serialVersionUID = 7766152580895748217L;
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private Double precio;
	@OneToMany(mappedBy = "tipo")
	private List<Sesion> sesiones = new ArrayList<Sesion>();
	
	public TipoSesion() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}
	
	public List<Sesion> getSesiones() {
		return Collections.unmodifiableList(sesiones);
	}
	
	public void addSesion(Sesion sesion) {
		this.sesiones.add(sesion);
		sesion.setTipo(this);
	}
	
	public void removeSesion(Sesion sesion) {
		this.sesiones.remove(sesion);
		sesion.setTipo(null);
	}
	
}
