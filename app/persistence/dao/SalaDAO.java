package persistence.dao;

import java.util.List;

import models.Sala;

public interface SalaDAO {

	public Sala findById(Long id);

	public List<Sala> findAll();

	public void update(Sala s);

	public void save(Sala s);

	public void delete(Sala s);
}
