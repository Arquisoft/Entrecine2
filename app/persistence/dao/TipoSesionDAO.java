package persistence.dao;

import java.util.List;

import models.TipoSesion;

public interface TipoSesionDAO {

	public TipoSesion findById(Long id);

	public List<TipoSesion> findAll();

	public void update(TipoSesion ts);

	public void save(TipoSesion ts);

	public void delete(TipoSesion ts);
}
