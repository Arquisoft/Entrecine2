package business.services.impl;

import java.util.List;

import models.Pelicula;
import play.db.ebean.Model.Finder;
import business.services.PeliculaService;

import com.avaje.ebean.Ebean;

public class PeliculaServiceImpl implements PeliculaService {

	private Finder<Long, Pelicula> find = new Finder<Long, Pelicula>(
			Long.class, Pelicula.class);
	
	private Finder<Long, String> findString = new Finder<Long, String>(
			Long.class, String.class);

	@Override
	public Pelicula findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Pelicula> findAll() {
		return find.all();
	}

	@Override
	public List<Pelicula> findAllEnCartelera() {
		List<Pelicula> peliculas = find.where().eq("enCartelera", true)
				.findList();
		return peliculas;
	}

	@Override
	public List<String> findAllTitulos() {
		
		//findString.fe
		return null;
	}

	@Override
	public void update(Pelicula p) {
		Ebean.update(p);
	}

	@Override
	public void save(Pelicula p) {
		Ebean.save(p);
	}

	@Override
	public void delete(Pelicula p) {
		Ebean.delete(p);
	}

}
