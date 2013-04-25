package business.services;

import java.util.List;

import models.Cliente;
import models.Pelicula;

public interface ClienteService {

	public Cliente findById(Long id);
	
	public Cliente findByLogin(String login);

	public List<Cliente> findAll();

	public void update(Cliente c);

	public void save(Cliente c);

	public void delete(Cliente c);
	
	public List<Pelicula> findPeliculasVistas(Cliente c);
}
