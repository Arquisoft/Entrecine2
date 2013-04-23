package models;

import infrastructure.Factories;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class Entrada extends Model {

	private static final long serialVersionUID = 4990936969506463676L;
	@Id
	@GeneratedValue
	private Long id;
	private Integer butaca;
	private Long codigo;
	@ManyToOne
	private Sesion sesion;
	@ManyToOne
	private Cliente cliente;

	public static Entrada findById(Long id) {
		return Factories.persistence.getEntradaDAO().findById(id);
	}

	public static List<Entrada> findAll() {
		return Factories.persistence.getEntradaDAO().findAll();
	}

	public void update() {
		Factories.persistence.getEntradaDAO().update(this);
	}

	public void save() {
		Factories.persistence.getEntradaDAO().save(this);
	}

	public void delete() {
		Factories.persistence.getEntradaDAO().delete(this);
	}

	public Entrada() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Integer getButaca() {
		return butaca;
	}

	public void setButaca(Integer butaca) {
		this.butaca = butaca;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	Sesion getSesion() {
		return sesion;
	}

	void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	Cliente getCliente() {
		return cliente;
	}

	void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Entrada other = (Entrada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
