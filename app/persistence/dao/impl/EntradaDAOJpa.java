package persistence.dao.impl;

import java.util.List;

import models.Entrada;
import persistence.dao.EntradaDAO;
import play.db.jpa.JPA;

public class EntradaDAOJpa implements EntradaDAO {

	@Override
	public Entrada findById(Long id) {
		return JPA.em().find(Entrada.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entrada> findAll() {
		return JPA.em().createQuery("SELECT e FROM Entrada e").getResultList();
	}

	@Override
	public void update(Entrada e) {
		JPA.em().merge(e);

	}

	@Override
	public void save(Entrada e) {
		JPA.em().persist(e);
	}

	@Override
	public void delete(Entrada e) {
		JPA.em().remove(e);
	}

}
