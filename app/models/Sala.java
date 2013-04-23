package models;

import infrastructure.Factories;

import java.util.ArrayList;
import java.util.Collections;
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

	public static Sala findById(Long id) {
		return Factories.persistence.getSalaDAO().findById(id);
	}

	public static List<Sala> findAll() {
		return Factories.persistence.getSalaDAO().findAll();
	}

	public void update() {
		Factories.persistence.getSalaDAO().update(this);
	}

	public void save() {
		Factories.persistence.getSalaDAO().save(this);
	}

	public void delete() {
		Factories.persistence.getSalaDAO().delete(this);
	}

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

	public void addSesion(Sesion sesion) {
		sesion.setSala(this);
		sesiones.add(sesion);
	}

	public void removeSesion(Sesion sesion) {
		sesion.setSala(null);
		sesiones.remove(sesion);
	}

	public List<Sesion> getSesiones() {
		return Collections.unmodifiableList(sesiones);
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
		Sala other = (Sala) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
