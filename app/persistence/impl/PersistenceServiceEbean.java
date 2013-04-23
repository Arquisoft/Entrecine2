package persistence.impl;

import persistence.PersistenceService;
import persistence.dao.ClienteDAO;
import persistence.dao.EntradaDAO;
import persistence.dao.PeliculaDAO;
import persistence.dao.SalaDAO;
import persistence.dao.SesionDAO;
import persistence.dao.TipoSesionDAO;
import persistence.dao.impl.ClienteDaoEbean;
import persistence.dao.impl.EntradaDaoEbean;
import persistence.dao.impl.PeliculaDaoEbean;
import persistence.dao.impl.SalaDaoEbean;
import persistence.dao.impl.SesionDaoEbean;
import persistence.dao.impl.TipoSesionDaoEbean;

public class PersistenceServiceEbean implements PersistenceService {

	@Override
	public ClienteDAO getClienteDAO() {
		return new ClienteDaoEbean();
	}

	@Override
	public EntradaDAO getEntradaDAO() {
		return new EntradaDaoEbean();
	}

	@Override
	public PeliculaDAO getPeliculaDAO() {
		return new PeliculaDaoEbean();
	}

	@Override
	public SalaDAO getSalaDAO() {
		return new SalaDaoEbean();
	}

	@Override
	public SesionDAO getSesionDAO() {
		return new SesionDaoEbean();
	}

	@Override
	public TipoSesionDAO getTipoSesionDAO() {
		return new TipoSesionDaoEbean();
	}

}
