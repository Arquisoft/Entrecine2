package persistence.dao.impl;

import java.util.List;

import models.Sesion;
import persistence.dao.SesionDAO;
import play.db.jpa.JPA;

public class SesionDAOJpa implements SesionDAO {

	@Override
	public Sesion findById(Long id) {
		return JPA.em().find(Sesion.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sesion> findAll() {
		return JPA.em().createQuery("SELECT s FROM Sesion s").getResultList();
	}

	@Override
	public void update(Sesion s) {
		JPA.em().merge(s);

	}

	@Override
	public void save(Sesion s) {
		JPA.em().persist(s);
	}

	@Override
	public void delete(Sesion s) {
		JPA.em().remove(s);
	}

}
