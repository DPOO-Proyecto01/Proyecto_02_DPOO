package usuarios.modelo;

public abstract class Empleado extends Usuario
{
	private String rol;
	Empleado(String _nombre, String _contrasenia, String _tipo, String _rol, Integer _Id) {
		super(_nombre, _contrasenia, _tipo, _Id);
		this.rol = _rol;
		
		
		// TODO Auto-generated constructor stub
	}

}
