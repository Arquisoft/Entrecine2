package business.services.impl;

import java.util.List;

import models.Empleado;
import play.db.ebean.Model.Finder;
import business.services.EmpleadoService;

import com.avaje.ebean.Ebean;

public class EmpleadoServiceImpl implements EmpleadoService {

	private Finder<Long, Empleado> find = new Finder<Long, Empleado>(
			Long.class, Empleado.class);

	@Override
	public Empleado findById(Long id) {
		return find.byId(id);
	}

	@Override
	public Empleado findByLogin(String login) {
		Empleado empleado = find.fetch(
				"SELECT e FROM Empleado e WHERE e.login = ?", login)
				.findUnique();
		return empleado;
	}

	@Override
	public List<Empleado> findAll() {
		return find.all();
	}

	@Override
	public void update(Empleado e) {
		Ebean.update(e);
	}

	@Override
	public void save(Empleado e) {
		Ebean.save(e);
	}

	@Override
	public void delete(Empleado e) {
		Ebean.delete(e);
	}

}
