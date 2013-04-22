package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
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

	Sala getSala() {
		return sala;
	}

	void setSala(Sala sala) {
		this.sala = sala;
	}

	public TipoSesion getTipo() {
		return tipo;
	}

	void setTipo(TipoSesion tipo) {
		this.tipo = tipo;
	}

	public void addEntrada(Entrada entrada) {
		entrada.setSesion(this);
		entradas.add(entrada);
	}

	public void removeEntrada(Entrada entrada) {
		entrada.setSesion(null);
		entradas.remove(entrada);
	}

	public List<Entrada> getEntradas() {
		return Collections.unmodifiableList(entradas);
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
		Sesion other = (Sesion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
