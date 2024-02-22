package ejercicioFormulario;

public class Pregunta {

	private String enunciado;
	private String respuesta;

	private int contadorCorrectas = 0;
	private int contadorIncorrectas = 0;

	public Pregunta(String enunciado, String respuesta) {
		this.enunciado = enunciado;
		this.respuesta = respuesta;
	}

	public String printEnunciado() {
		return enunciado;
	}

	///////////////////////////

	public int getContadorCorrectas() {
		return contadorCorrectas;
	}

	public void aumentarContadorCorrectas() {
		this.contadorCorrectas ++;
	}

	public int getContadorIncorrectas() {
		return contadorIncorrectas;
	}

	public void aumentarContadorIncorrectas() {
		this.contadorIncorrectas ++;
	}

	//////////////////
	public String getEnunciado() {
		return enunciado;
	}

	public String getRespuesta() {
		return respuesta.toLowerCase();
	}

}
