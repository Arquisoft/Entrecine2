package models;

import infrastructure.Factories;

import java.util.List;

import javax.persistence.Column;
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
	@Column(unique = true)
	@GeneratedValue
	private Long codigo;
	@ManyToOne
	private Sesion sesion;
	@ManyToOne
	private Cliente cliente;

	public static Entrada findById(Long id) {
		return Factories.business.getEntradaService().findById(id);
	}

	public static List<Entrada> findAll() {
		return Factories.business.getEntradaService().findAll();
	}

	public void update() {
		Factories.business.getEntradaService().update(this);
	}

	public void save() {
		Factories.business.getEntradaService().save(this);
	}

	public void delete() {
		Factories.business.getEntradaService().delete(this);
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

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		if (this.sesion != null)
			this.sesion.removeEntrada(this);
		sesion.addEntrada(this);
	}
	
	void _setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente){
		if (this.cliente != null)
			this.cliente.removeEntrada(this);
		cliente.addEntrada(this);
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrada [id=" + id + ", butaca=" + butaca + ", codigo="
				+ codigo + ", sesion=" + sesion + ", cliente=" + cliente + "]";
	}

}
