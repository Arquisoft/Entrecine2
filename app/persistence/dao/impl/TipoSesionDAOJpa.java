package persistence.dao.impl;

import java.util.List;

import models.TipoSesion;
import persistence.dao.TipoSesionDAO;
import play.db.jpa.JPA;

public class TipoSesionDAOJpa implements TipoSesionDAO {

	@Override
	public TipoSesion findById(Long id) {
		return JPA.em().find(TipoSesion.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoSesion> findAll() {
		return JPA.em().createQuery("SELECT tp FROM TipoSesion tp").getResultList();
	}

	@Override
	public void update(TipoSesion ts) {
		JPA.em().merge(ts);

	}

	@Override
	public void save(TipoSesion ts) {
		JPA.em().persist(ts);
	}

	@Override
	public void delete(TipoSesion ts) {
		JPA.em().remove(ts);
	}

}
