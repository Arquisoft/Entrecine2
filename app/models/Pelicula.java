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
	private Integer duracion;
	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	private String genero;
	private String imagenCartelera;
	private String sinopsis;
	private Boolean enCartelera;

	public static Pelicula findById(Long id) {
		return Factories.business.getPeliculaService().findById(id);
	}

	public static List<Pelicula> findAll() {
		return Factories.business.getPeliculaService().findAll();
	}

	public static List<Pelicula> findAllEnCartelera() {
		return Factories.business.getPeliculaService().findAllEnCartelera();
	}

	public void update() {
		Factories.business.getPeliculaService().update(this);
	}

	public void save() {
		Pelicula peli = null;
		if (id != null) {
			peli = Pelicula.findById(id);
			if (peli == null)
				Factories.business.getPeliculaService().save(this);
			else
				update();
		} else
			Factories.business.getPeliculaService().save(this);
	}

	public void delete() {
		Factories.business.getPeliculaService().delete(this);
	}
	
	public List<Cliente> getEspectadores(){
		return Factories.business.getPeliculaService().getEspectadores(this);
	}

	public Pelicula() {
		super();
		imagenCartelera = "http://img526.imageshack.us/img526/1449/nodisponible.jpg";
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

	public void setId(Long id){
		this.id = id;
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
	public List<Sesion> getSesionesFuturas() {
		java.util.Date today = new java.util.Date();
		List<Sesion> nuevaLista = sesiones;
		List<Sesion> listaEliminados = new ArrayList<Sesion>();

		for(Sesion sesion: nuevaLista){
			if(sesion.getDia().getTime() < today.getTime()){
				listaEliminados.add(sesion);
			}
		}
		for(Sesion sesion: listaEliminados){
			nuevaLista.remove(sesion);
		}
		return nuevaLista;

	}

	public void addSesion(Sesion sesion) {
		sesion._setPelicula(this);
		sesiones.add(sesion);
	}

	public void removeSesion(Sesion sesion) {
		sesion._setPelicula(null);
		sesiones.remove(sesion);
	}

	public String getImagenCartelera() {
		return imagenCartelera;
	}

	public void setImagenCartelera(String imagenCartelera) {
		this.imagenCartelera = imagenCartelera;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
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

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", sesiones="
				+ sesiones + ", anio=" + anio + ", duracion=" + duracion
				+ ", genero=" + genero + ", imagenCartelera=" + imagenCartelera
				+ ", sinopsis=" + sinopsis + ", enCartelera=" + enCartelera
				+ "]";
	}


}