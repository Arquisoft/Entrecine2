package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

public class Cliente extends Model {

	private static final long serialVersionUID = 2768482032925131180L;
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String login;
	private String password;
	@OneToMany(mappedBy = "cliente")
	private List<Entrada> entradas = new ArrayList<Entrada>();

	public Cliente() {
		super();
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

	public void addEntrada(Entrada entrada) {
		entrada.setCliente(this);
		entradas.add(entrada);
	}

	public void removeEntrada(Entrada entrada) {
		entrada.setCliente(null);
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
