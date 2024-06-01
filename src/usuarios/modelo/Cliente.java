package usuarios.modelo;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	public void crearRastro (String nombre, String fechaNacimiento, String tipoDocumento, String numeroDocumento, String direccion, String numeroTarjeta, boolean credito, String nombreBanco) throws IOException {
		
		String pathProyecto = Paths.get("").toAbsolutePath().toString();
		String path = pathProyecto.replaceFirst("/usuarios/modelo/Cliente.java", "data/Rastros/");
		String tipoTarjeta = "Debito";
		if (credito) {
			tipoTarjeta = "Credito";
		}
		LocalDateTime dateTime =  LocalDateTime.now();
		DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String finalDateTime = dateTime.format(formatter);
		String contenido = "Registro aumento monto \n" + "=".repeat(32) + "\n" + "Fecha y Hora: " + finalDateTime + "\n" + "Nombre: " + nombre + "\n" + "Email: " + email + "\n" + "Numero telefonico/celular: " + telefono + "\n" + "Fecha de Nacimiento: " + fechaNacimiento + "\n" + "Tipo de Documento: " + tipoDocumento + "\n" + "Numero de Documento: " + numeroDocumento + "\n" + "Direccion: " + direccion + "\n" + "Numero de Tarjeta: " + numeroTarjeta + "\n" + "Tipo de Tarjeta: " + tipoTarjeta + "\n" + "Banco: " + nombreBanco; 
		String fileName = nombre + "_DT_" + finalDateTime;
		
		File file = new File(path, fileName);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(contenido);
		writer.close();
		
		
		
	}
}
