package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

public class Cliente extends Model {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String login;
	private String password;
	@OneToMany(mappedBy = "cliente")
	private List<Entrada> entradas = new ArrayList<Entrada>();
	
	

}
