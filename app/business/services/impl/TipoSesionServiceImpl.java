package business.services.impl;

import java.util.List;

import models.TipoSesion;
import play.db.ebean.Model.Finder;

import business.services.TipoSesionService;

import com.avaje.ebean.Ebean;

public class TipoSesionServiceImpl implements TipoSesionService {

	private Finder<Long, TipoSesion> find = new Finder<Long, TipoSesion>(
			Long.class, TipoSesion.class);

	@Override
	public TipoSesion findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<TipoSesion> findAll() {
		return find.all();
	}

	@Override
	public void update(TipoSesion ts) {
		Ebean.update(ts);
	}

	@Override
	public void save(TipoSesion ts) {
		Ebean.save(ts);
	}

	@Override
	public void delete(TipoSesion ts) {
		Ebean.delete(ts);
	}

}
