package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Pelicula extends Model {

	@Id @GeneratedValue
	private Long id;
	private String titulo;
	@OneToMany(mappedBy = "pelicula")
	private List<Sesion> sesiones = new ArrayList<Sesion>();
	private Integer año;
	private String genero;


	public static Finder<Long,Pelicula> find = new Finder(Long.class, Pelicula.class);

	public static List<Pelicula> all() {
		return find.all();
	}

	public static void create(Pelicula peli) {
		peli.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

}