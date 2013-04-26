import static org.junit.Assert.*;

import java.util.List;

import models.Cliente;
import models.Entrada;
import models.Pelicula;
import models.Sala;
import models.Sesion;

import org.junit.Before;
import org.junit.Test;


public class TestReserva {
	
	private Cliente cliente;
	private Sesion sesion;
	private Pelicula pelicula;
	private Entrada entrada;
	private Sala sala;

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente();
		sesion = new Sesion();
		pelicula = new Pelicula();
		entrada = new Entrada();
		sala = new Sala();
		
		entrada.setCliente(cliente);
		entrada.setSesion(sesion);
		entrada.setButaca(1);
		sesion.setPelicula(pelicula);
		sesion.setSala(sala);
		sala.setNumButacas(10);
	}

	// Cuando un cliente compra una entrada, la butaca debe dejar de figurar
	// como disponible para dicha sesión.
	@Test
	public void test() {
		List<Integer> libres = sesion.getButacasLibres();
		assertFalse(libres.contains(entrada.getButaca()));
	}

}
