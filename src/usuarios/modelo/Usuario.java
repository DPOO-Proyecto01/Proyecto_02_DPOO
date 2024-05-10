package usuarios.modelo;

public abstract class Usuario 
{
	private String nombre;
	private String contrasenia;
	protected String tipo;
	private Integer Id;
	
	
	Usuario(String _nombre, String _contrasenia, String _tipo, Integer _Id) {
		this.nombre = _nombre;
		this.contrasenia = _contrasenia;
		this.tipo = _tipo;
		this.Id = _Id;
		
	}


	public String getNombre() {
		return nombre;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public String getTipo() {
		return tipo;
	}
	
	public Integer getID() {
		return Id;
	}
}


