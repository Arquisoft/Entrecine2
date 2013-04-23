package business.impl;

import business.BusinessFactory;
import business.services.ClienteService;
import business.services.EntradaService;
import business.services.PeliculaService;
import business.services.SalaService;
import business.services.SesionService;
import business.services.TipoSesionService;
import business.services.impl.ClienteServiceImpl;
import business.services.impl.EntradaServiceImpl;
import business.services.impl.PeliculaServiceImpl;
import business.services.impl.SalaServiceImpl;
import business.services.impl.SesionServiceImpl;
import business.services.impl.TipoSesionServiceImpl;

public class BusinessFactoryImpl implements BusinessFactory {

	@Override
	public ClienteService getClienteService() {
		return new ClienteServiceImpl();
	}

	@Override
	public EntradaService getEntradaService() {
		return new EntradaServiceImpl();
	}

	@Override
	public PeliculaService getPeliculaService() {
		return new PeliculaServiceImpl();
	}

	@Override
	public SalaService getSalaService() {
		return new SalaServiceImpl();
	}

	@Override
	public SesionService getSesionService() {
		return new SesionServiceImpl();
	}

	@Override
	public TipoSesionService getTipoSesionService() {
		return new TipoSesionServiceImpl();
	}

}
