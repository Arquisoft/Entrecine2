package persistence.dao.impl;

import java.util.List;

import models.Pelicula;
import persistence.dao.PeliculaDAO;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.Ebean;

public class PeliculaDaoEbean implements PeliculaDAO {

	private Finder<Long, Pelicula> find = new Finder<Long, Pelicula>(
			Long.class, Pelicula.class);

	@Override
	public Pelicula findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Pelicula> findAll() {
		return find.all();
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
