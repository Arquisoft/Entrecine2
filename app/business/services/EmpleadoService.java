package business.services;

import java.util.List;

import models.Empleado;

public interface EmpleadoService {

	public Empleado findById(Long id);
	
	public Empleado findByLogin(String login);

	public List<Empleado> findAll();

	public void update(Empleado e);

	public void save(Empleado e);

	public void delete(Empleado e);
}
