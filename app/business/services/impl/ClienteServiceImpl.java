package business.services.impl;

import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import models.Entrada;
import models.Pelicula;
import play.db.ebean.Model.Finder;
import business.services.ClienteService;

import com.avaje.ebean.Ebean;

public class ClienteServiceImpl implements ClienteService {

	private Finder<Long, Cliente> find = new Finder<Long, Cliente>(Long.class,
			Cliente.class);

	@Override
	public Cliente findById(Long id) {
		return find.byId(id);
	}

	@Override
	public Cliente findByLogin(String login) {
		Cliente cliente = find.where().eq("login", login).findUnique();
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		return find.all();
	}

	@Override
	public void update(Cliente c) {
		Ebean.update(c);
	}

	@Override
	public void save(Cliente c) {
		Ebean.save(c);
	}

	@Override
	public void delete(Cliente c) {
		Ebean.delete(c);
	}

	@Override
	public List<Pelicula> findPeliculasVistas(Cliente c) {
		//Busco las peliculas que vio el cliente a partir de las entradas que compro
		List<Pelicula> peliculasVistas = new ArrayList<Pelicula>();
		
		//Trabajo con Cliente directamente, al estar en la transaccion
		Ebean.beginTransaction();
			for(Entrada entrada : c.getEntradas())
				peliculasVistas.add(entrada.getSesion().getPelicula());
		
		Ebean.endTransaction();
		
		return peliculasVistas;
	}

}
