package controllers.customForms;

public class SesionForm {

	private Long sesionId;
	private String hora;
	private String tipoSesionId;

	public Long getSesionId() {
		return sesionId;
	}

	public void setSesionId(Long sesionId) {
		this.sesionId = sesionId;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTipoSesionId() {
		return tipoSesionId;
	}

	public void setTipoSesionId(String tipoSesionId) {
		this.tipoSesionId = tipoSesionId;
	}

}
