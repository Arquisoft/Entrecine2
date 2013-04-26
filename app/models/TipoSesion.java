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
public class TipoSesion extends Model {

	private static final long serialVersionUID = 7766152580895748217L;
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private Double precio;
	@OneToMany(mappedBy = "tipo")
	private List<Sesion> sesiones = new ArrayList<Sesion>();

	public static TipoSesion findById(Long id) {
		return Factories.business.getTipoSesionService().findById(id);
	}

	public static List<TipoSesion> findAll() {
		return Factories.business.getTipoSesionService().findAll();
	}

	public void update() {
		Factories.business.getTipoSesionService().update(this);
	}

	public void save() {
		TipoSesion tipoSesion = null;
		if (id != null) {
			tipoSesion = TipoSesion.findById(id);
			if (tipoSesion == null)
				Factories.business.getTipoSesionService().save(this);
			else
				update();
		} else
			Factories.business.getTipoSesionService().save(this);
	}

	public void delete() {
		Factories.business.getTipoSesionService().delete(this);
	}

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
	
	public void setId(Long id) {
		this.id = id;
	}

	public List<Sesion> getSesiones() {
		return Collections.unmodifiableList(sesiones);
	}

	public void addSesion(Sesion sesion) {
		this.sesiones.add(sesion);
		sesion._setTipo(this);
	}

	public void removeSesion(Sesion sesion) {
		this.sesiones.remove(sesion);
		sesion._setTipo(null);
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
		TipoSesion other = (TipoSesion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoSesion [id=" + id + ", nombre=" + nombre + ", precio="
				+ precio + "]";
	}

}
