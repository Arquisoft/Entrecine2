package models;

import infrastructure.Factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Sala extends Model {

	private static final long serialVersionUID = -5960605356904138910L;
	@Id
	@GeneratedValue
	private Long id;
	private Integer numButacas;
	@Column(unique = true)
	private Integer numero;
	@OneToMany(mappedBy = "sala")
	private List<Sesion> sesiones = new ArrayList<Sesion>();

	public static Sala findById(Long id) {
		return Factories.business.getSalaService().findById(id);
	}

	public static List<Sala> findAll() {
		return Factories.business.getSalaService().findAll();
	}

	public void update() {
		Factories.business.getSalaService().update(this);
	}

	public void save() {
		Factories.business.getSalaService().save(this);
	}

	public void delete() {
		Factories.business.getSalaService().delete(this);
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
		sesion._setSala(this);
		sesiones.add(sesion);
	}

	public void removeSesion(Sesion sesion) {
		sesion._setSala(null);
		sesiones.remove(sesion);
	}

	public List<Sesion> getSesiones() {
		return Collections.unmodifiableList(sesiones);
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", numButacas=" + numButacas + ", numero="
				+ numero + "]";
	}

}
