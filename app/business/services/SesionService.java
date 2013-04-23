package business.services;

import java.util.List;

import models.Sesion;

public interface SesionService {

	public Sesion findById(Long id);

	public List<Sesion> findAll();

	public void update(Sesion s);

	public void save(Sesion s);

	public void delete(Sesion s);
}