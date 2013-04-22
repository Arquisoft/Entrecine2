package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Pelicula extends Model {

	@Id
	public Long id;
	public String titulo;
	@OneToMany(mappedBy = "pelicula")
	public Set<Sesion> sesiones = new HashSet<Sesion>();
	public int año;
	public String genero;


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