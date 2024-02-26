package ejercicioLogin;

public class Usuario {

	private static int idActual = 0;

	private int id = 0;
	private String nombre;
	private String departamento;
	private int edad;

	public Usuario(String nombre, String departamento, int edad) {
		this.nombre = nombre;
		this.departamento = departamento;
		this.edad = edad;
		this.id = idActual;
		idActual++;
	}

	@Override
	public String toString() {
		return "Usuario [ID = " + id + ", Nombre = " + nombre + ", Edad = " + edad + ", Departamento = " + departamento + " ]";
	}

}
