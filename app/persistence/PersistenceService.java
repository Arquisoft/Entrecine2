package persistence;

import persistence.dao.ClienteDAO;
import persistence.dao.EntradaDAO;
import persistence.dao.PeliculaDAO;
import persistence.dao.SalaDAO;
import persistence.dao.SesionDAO;
import persistence.dao.TipoSesionDAO;

public interface PersistenceService {

	public ClienteDAO getClienteDAO();
	
	public EntradaDAO getEntradaDAO();
	
	public PeliculaDAO getPeliculaDAO();

	public SalaDAO getSalaDAO();

	public SesionDAO getSesionDAO();

	public TipoSesionDAO getTipoSesionDAO();

}
