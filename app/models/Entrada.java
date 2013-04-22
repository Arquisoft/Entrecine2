package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

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

}
