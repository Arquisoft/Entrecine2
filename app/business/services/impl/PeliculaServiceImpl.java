package business.services.impl;

import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import models.Entrada;
import models.Pelicula;
import models.Sesion;
import play.db.ebean.Model.Finder;
import business.services.PeliculaService;

import com.avaje.ebean.Ebean;

public class PeliculaServiceImpl implements PeliculaService {

	private Finder<Long, Pelicula> find = new Finder<Long, Pelicula>(
			Long.class, Pelicula.class);

	@Override
	public Pelicula findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Pelicula> findAll() {
		return find.all();
	}

	@Override
	public List<Pelicula> findAllEnCartelera() {
		List<Pelicula> peliculas = find.where().eq("enCartelera", true)
				.findList();
		return peliculas;
	}

	@Override
	public void update(Pelicula p) {
		Ebean.update(p);
	}

	@Override
	public void save(Pelicula p) {
		Ebean.save(p);
	}

	@Override
	public void delete(Pelicula p) {
		Ebean.delete(p);
	}

	@Override
	public List<Cliente> getEspectadores(Pelicula p) {
		// Busco todos los clientes que alguna vez vieron esta pelicula
		List<Cliente> espectadores = new ArrayList<Cliente>();		
		Ebean.beginTransaction();
			for(Sesion sesion : p.getSesiones())
				for(Entrada entrada : sesion.getEntradas())
					espectadores.add(entrada.getCliente());
		
		Ebean.endTransaction();
		return espectadores;
	}

}
