package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Pelicula extends Model {

	private static final long serialVersionUID = -990873777041332443L;
	@Id @GeneratedValue
	private Long id;
	private String titulo;
	@OneToMany(mappedBy = "pelicula")
	private List<Sesion> sesiones = new ArrayList<Sesion>();
	private Integer año;
	private String genero;
	
	public Pelicula() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Finder<Long,Pelicula> find = new Finder(Long.class, Pelicula.class);

	public static List<Pelicula> all() {
		return find.all();
	}

	public static void create(Pelicula peli) {
		peli.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAño() {
		return año;
	}

	public void setAño(Integer año) {
		this.año = año;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}
	
	public List<Sesion> getSesiones() {
		return Collections.unmodifiableList(sesiones);
	}
	
	public void addSesion(Sesion sesion) {
		sesion.setPelicula(this);
		sesiones.add(sesion);
	}
	
	public void removeSesion(Sesion sesion) {
		sesion.setPelicula(null);
		sesiones.remove(sesion);
	}

}