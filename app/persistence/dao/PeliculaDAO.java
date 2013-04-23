package persistence.dao;

import java.util.List;

import models.Pelicula;

public interface PeliculaDAO {

	public Pelicula findById(Long id);

	public List<Pelicula> findAll();

	public void update(Pelicula p);

	public void save(Pelicula p);

	public void delete(Pelicula p);

}
