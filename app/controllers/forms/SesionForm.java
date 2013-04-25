package controllers.forms;

public class SesionForm {

	private Long pelicula;
	private Long tipo;
	private Long sala;
	private String dia;
	private String hora;

	public Long getPelicula() {
		return pelicula;
	}

	public void setPelicula(Long pelicula) {
		this.pelicula = pelicula;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public Long getSala() {
		return sala;
	}

	public void setSala(Long sala) {
		this.sala = sala;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "SesionForm [pelicula=" + pelicula + ", tipo=" + tipo
				+ ", sala=" + sala + ", dia=" + dia + ", hora=" + hora + "]";
	}

}
