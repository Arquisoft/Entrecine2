package business;

import business.services.ClienteService;
import business.services.EmpleadoService;
import business.services.EntradaService;
import business.services.PeliculaService;
import business.services.SalaService;
import business.services.SesionService;
import business.services.TipoSesionService;

public interface BusinessFactory {

	public ClienteService getClienteService();

	public EmpleadoService getEmpleadoService();

	public EntradaService getEntradaService();

	public PeliculaService getPeliculaService();

	public SalaService getSalaService();

	public SesionService getSesionService();

	public TipoSesionService getTipoSesionService();

}
