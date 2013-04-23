package business.services.impl;

import java.util.List;

import models.Sala;
import play.db.ebean.Model.Finder;

import business.services.SalaService;

import com.avaje.ebean.Ebean;

public class SalaServiceImpl implements SalaService {

	private Finder<Long, Sala> find = new Finder<Long, Sala>(Long.class,
			Sala.class);

	@Override
	public Sala findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Sala> findAll() {
		return find.all();
	}

	@Override
	public void update(Sala s) {
		Ebean.update(s);
	}

	@Override
	public void save(Sala s) {
		Ebean.save(s);
	}

	@Override
	public void delete(Sala s) {
		Ebean.delete(s);
	}

}
