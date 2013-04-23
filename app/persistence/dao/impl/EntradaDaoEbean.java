package persistence.dao.impl;

import java.util.List;

import models.Entrada;
import persistence.dao.EntradaDAO;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.Ebean;

public class EntradaDaoEbean implements EntradaDAO {

	private Finder<Long, Entrada> find = new Finder<Long, Entrada>(Long.class,
			Entrada.class);

	@Override
	public Entrada findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Entrada> findAll() {
		return find.all();
	}

	@Override
	public void update(Entrada e) {
		Ebean.update(e);
	}

	@Override
	public void save(Entrada e) {
		Ebean.save(e);
	}

	@Override
	public void delete(Entrada e) {
		Ebean.delete(e);
	}

}
