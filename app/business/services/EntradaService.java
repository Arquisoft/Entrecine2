package business.services;

import java.util.List;

import models.Entrada;

public interface EntradaService {

	public Entrada findById(Long id);

	public List<Entrada> findAll();

	public void update(Entrada e);

	public void save(Entrada e);

	public void delete(Entrada e);
}
