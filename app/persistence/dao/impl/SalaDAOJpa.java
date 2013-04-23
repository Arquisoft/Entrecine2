package persistence.dao.impl;

import java.util.List;

import models.Sala;
import persistence.dao.SalaDAO;
import play.db.jpa.JPA;

public class SalaDAOJpa implements SalaDAO {

	@Override
	public Sala findById(Long id) {
		return JPA.em().find(Sala.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> findAll() {
		return JPA.em().createQuery("SELECT s FROM Sala s").getResultList();
	}

	@Override
	public void update(Sala s) {
		JPA.em().merge(s);

	}

	@Override
	public void save(Sala s) {
		JPA.em().persist(s);
	}

	@Override
	public void delete(Sala s) {
		JPA.em().remove(s);
	}

}
