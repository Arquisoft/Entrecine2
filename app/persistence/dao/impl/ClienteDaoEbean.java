package persistence.dao.impl;

import java.util.List;

import models.Cliente;
import persistence.dao.ClienteDAO;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.Ebean;

public class ClienteDaoEbean implements ClienteDAO {
	
	//TODO FINDBYLOGIN

	private Finder<Long, Cliente> find = new Finder<Long, Cliente>(Long.class,
			Cliente.class);

	@Override
	public Cliente findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Cliente> findAll() {
		return find.all();
	}

	@Override
	public void update(Cliente c) {
		Ebean.update(c);
	}

	@Override
	public void save(Cliente c) {
		Ebean.save(c);
	}

	@Override
	public void delete(Cliente c) {
		Ebean.delete(c);
	}

}
