package business.services.impl;

import java.util.List;

import models.Sesion;
import play.db.ebean.Model.Finder;

import business.services.SesionService;

import com.avaje.ebean.Ebean;

public class SesionServiceImpl implements SesionService {

	private Finder<Long, Sesion> find = new Finder<Long, Sesion>(Long.class,
			Sesion.class);

	@Override
	public Sesion findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Sesion> findAll() {
		return find.all();
	}

	@Override
	public void update(Sesion s) {
		Ebean.update(s);
	}

	@Override
	public void save(Sesion s) {
		Ebean.save(s);
	}

	@Override
	public void delete(Sesion s) {
		Ebean.delete(s);
	}

}
