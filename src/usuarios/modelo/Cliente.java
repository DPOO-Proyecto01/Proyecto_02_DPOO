package usuarios.modelo;
import java.util.ArrayList;
import inventario.modelo.Pieza;

public class Cliente extends Usuario
{
	
	private static String CLIENTE = "Cliente";
	private static int TELEFONO;
	private static String EMAIL;
	private double monto;
	private ArrayList<Pieza> piezasCompradas;
	
	public Cliente(String _nombre, String _contrasenia, String _tipo, int telefono, String email, Integer _Id) {
		super(_nombre, _contrasenia, _tipo = "Cliente", _Id = AdministradorUsuarios.crearID());
		
		Cliente.TELEFONO = telefono;
		Cliente.EMAIL = email;
		this.monto = 0;
		this.piezasCompradas = new ArrayList<Pieza>();
	}

	public static String getCLIENTE() {
		return CLIENTE;
	}

	public int getTELEFONO() {
		return TELEFONO;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public double getMonto() {
		return monto;
	}

	public ArrayList<Pieza> getPiezasCompradas() {
		return piezasCompradas;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public void setPiezasCompradas(ArrayList<Pieza> piezasCompradas) {
		this.piezasCompradas = piezasCompradas;
		
	}
	
	public void añadirPiezaComprada(Pieza pieza)
	{
		this.piezasCompradas.add(pieza);
	}

}
