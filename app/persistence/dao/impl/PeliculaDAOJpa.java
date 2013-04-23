package persistence.dao.impl;

import java.util.List;

import models.Pelicula;
import persistence.dao.PeliculaDAO;
import play.db.jpa.JPA;

public class PeliculaDAOJpa implements PeliculaDAO {

	@Override
	public Pelicula findById(Long id) {
		return JPA.em().find(Pelicula.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pelicula> findAll() {
		return JPA.em().createQuery("SELECT p FROM Pelicula p").getResultList();
	}

	@Override
	public void update(Pelicula p) {
		JPA.em().merge(p);

	}

	@Override
	public void save(Pelicula p) {
		JPA.em().persist(p);
	}

	@Override
	public void delete(Pelicula p) {
		JPA.em().remove(p);
	}

}
