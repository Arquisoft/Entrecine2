package business.services.impl;

import java.sql.Date;
import java.util.List;

import models.Pelicula;
import models.Sala;
import models.Sesion;
import play.db.ebean.Model.Finder;
import business.services.SesionService;

import com.avaje.ebean.Ebean;

public class SesionServiceImpl implements SesionService {

	private Finder<Long, Sesion> find = new Finder<Long, Sesion>(Long.class,
			Sesion.class);

	@Override
	public Sesion findById(Long id) {
		return find.byId(id);
	}

	@Override
	public List<Sesion> findAll() {
		return find.all();
	}

	@Override
	public List<Sesion> findByFecha(Date fecha) {
		return find.where().eq("fecha", fecha).findList();
	}

	@Override
	public List<Sesion> findBySalaAndFecha(Sala sala, Date fecha) {
		return find.where().eq("fecha", fecha).eq("sala", sala).findList();
	}
	
	@Override
	public List<Sesion> findByPeliculaAndFecha(Pelicula pelicula, Date fecha) {
		return find.where().eq("pelicula", pelicula).eq("fecha", fecha).findList();
	}
	
	@Override
	public List<Sesion> findByFechaAndDisponible(Date fecha) {
		return find.where().eq("fecha", fecha).eq("disponible", true).findList();
	}

	@Override
	public void update(Sesion s) {
		Ebean.update(s);
	}

	@Override
	public void save(Sesion s) {
		Ebean.save(s);
	}

	@Override
	public void delete(Sesion s) {
		Ebean.delete(s);
	}

	

}
