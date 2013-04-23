package persistence.impl;

import persistence.PersistenceService;
import persistence.dao.ClienteDAO;
import persistence.dao.EntradaDAO;
import persistence.dao.PeliculaDAO;
import persistence.dao.SalaDAO;
import persistence.dao.SesionDAO;
import persistence.dao.TipoSesionDAO;
import persistence.dao.impl.ClienteDAOJpa;
import persistence.dao.impl.EntradaDAOJpa;
import persistence.dao.impl.PeliculaDAOJpa;
import persistence.dao.impl.SalaDAOJpa;
import persistence.dao.impl.SesionDAOJpa;
import persistence.dao.impl.TipoSesionDAOJpa;

public class PersistenceServiceJpa implements PersistenceService {

	@Override
	public ClienteDAO getClienteDAO() {
		return new ClienteDAOJpa();
	}

	@Override
	public EntradaDAO getEntradaDAO() {
		return new EntradaDAOJpa();
	}

	@Override
	public PeliculaDAO getPeliculaDAO() {
		return new PeliculaDAOJpa();
	}

	@Override
	public SalaDAO getSalaDAO() {
		return new SalaDAOJpa();
	}

	@Override
	public SesionDAO getSesionDAO() {
		return new SesionDAOJpa();
	}

	@Override
	public TipoSesionDAO getTipoSesionDAO() {
		return new TipoSesionDAOJpa();
	}

}
