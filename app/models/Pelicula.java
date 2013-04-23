package models;

import infrastructure.Factories;

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
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	@OneToMany(mappedBy = "pelicula")
	private List<Sesion> sesiones = new ArrayList<Sesion>();
	private Integer anio;
	private String genero;
	private Boolean enCartelera;

	public static Pelicula findById(Long id) {
		return Factories.persistence.getPeliculaDAO().findById(id);
	}

	public static List<Pelicula> findAll() {
		return Factories.persistence.getPeliculaDAO().findAll();
	}

	public void update() {
		Factories.persistence.getPeliculaDAO().update(this);
	}

	public void save() {
		Factories.persistence.getPeliculaDAO().save(this);
	}

	public void delete() {
		Factories.persistence.getPeliculaDAO().delete(this);
	}

	public Pelicula() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
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

	public Boolean getEnCartelera() {
		return enCartelera;
	}

	public void setEnCartelera(Boolean enCartelera) {
		this.enCartelera = enCartelera;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}