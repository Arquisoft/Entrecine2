package persistence.dao.impl;

import java.util.List;

import models.Cliente;
import persistence.dao.ClienteDAO;
import play.db.jpa.JPA;

public class ClienteDAOJpa implements ClienteDAO {

	@Override
	public Cliente findById(Long id) {
		return JPA.em().find(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		return JPA.em().createQuery("SELECT c FROM Cliente c").getResultList();
	}

	@Override
	public void update(Cliente c) {
		JPA.em().merge(c);

	}

	@Override
	public void save(Cliente c) {
		JPA.em().persist(c);
	}

	@Override
	public void delete(Cliente c) {
		JPA.em().remove(c);
	}

}
