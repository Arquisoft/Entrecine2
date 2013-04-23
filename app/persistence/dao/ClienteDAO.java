package persistence.dao;

import java.util.List;

import models.Cliente;

public interface ClienteDAO {

	public Cliente findById(Long id);

	public List<Cliente> findAll();

	public void update(Cliente c);

	public void save(Cliente c);

	public void delete(Cliente c);
}
