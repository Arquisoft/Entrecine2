package business.services;

import java.sql.Date;
import java.util.List;

import models.Pelicula;
import models.Sala;
import models.Sesion;

public interface SesionService {

	public Sesion findById(Long id);

	public List<Sesion> findAll();

	public List<Sesion> findBySalaAndFecha(Sala sala, Date fecha);

	public List<Sesion> findByPeliculaAndFecha(Pelicula pelicula, Date fecha);

	public void update(Sesion s);

	public void save(Sesion s);

	public void delete(Sesion s);

	public List<Sesion> findByFecha(Date fecha);

	public List<Sesion> findByFechaAndDisponible(Date fecha);

}
