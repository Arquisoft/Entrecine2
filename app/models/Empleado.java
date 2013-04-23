package models;

import infrastructure.Factories;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Empleado extends Model{
	
	private static final long serialVersionUID = -1974384819366278040L;
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	@Column(unique=true)
	private String login;
	private String password;
	private Boolean admin;

	public static Empleado findById(Long id) {
		return Factories.business.getEmpleadoService().findById(id);
	}

	public static List<Empleado> findAll() {
		return Factories.business.getEmpleadoService().findAll();
	}
	
	public static Empleado findByLogin(String login){
		return Factories.business.getEmpleadoService().findByLogin(login);
	}

	public void update() {
		Factories.business.getEmpleadoService().update(this);
	}

	public void save() {
		Factories.business.getEmpleadoService().save(this);
	}

	public void delete() {
		Factories.business.getEmpleadoService().delete(this);
	}

	public Empleado() {
		super();
		this.admin = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", login=" + login
				+ ", password=" + password + ", admin=" + admin + "]";
	}

	

}
