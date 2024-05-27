package usuarios.modelo;
import java.util.ArrayList;
import inventario.modelo.Pieza;

public class Cliente extends Usuario
{
	
	private static String CLIENTE = "Cliente";
	private int telefono;
	private String email;
	private double monto;
	private ArrayList<Pieza> piezasCompradas;
	private boolean verificar;
	
	public Cliente(String _nombre, String _contrasenia, String _tipo, int telefono, String email, Integer _Id) {
		super(_nombre, _contrasenia, _tipo = "Cliente", _Id = AdministradorUsuarios.crearID());
		
		this.telefono = telefono;
		this.email = email;
		this.monto = 0;
		this.piezasCompradas = new ArrayList<Pieza>();
		this.verificar = false;
	}

	public static String getCLIENTE() {
		return CLIENTE;
	}

	public int getTELEFONO() {
		return telefono;
	}

	public String getEMAIL() {
		return email;
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
	
	public boolean getVerfificar () {
		return this.verificar;
	}
	
	public void setVerificar() {
		verificar = true;
	}

}
