package business.services;

import java.util.List;

import models.Cliente;
import models.Pelicula;

public interface PeliculaService {

	public Pelicula findById(Long id);

	public List<Pelicula> findAll();

	public List<Pelicula> findAllEnCartelera();
	
	public void update(Pelicula p);

	public void save(Pelicula p);

	public void delete(Pelicula p);
	
	public List<Cliente> getEspectadores(Pelicula p);


}
