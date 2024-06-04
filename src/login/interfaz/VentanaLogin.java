package login.interfaz;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import administrador.interfaz.VentanaAdministrador;
import cajero.interfaz.VentanaCajero;
import cliente.interfaz.VentanaCliente;
import consola.ConsolaAdministrador;
import consola.ConsolaCajero;
import consola.ConsolaCliente;
import consola.ConsolaOperador;
import galeria.persistencia.CentralPersistencia;
import inventario.modelo.Galeria;
import inventario.modelo.Inventario;
import operador.interfaz.VentanaOperador;
import procesos.modelo.AdministradorProcesos;
import usuarios.modelo.Administrador;
import usuarios.modelo.AdministradorUsuarios;
import usuarios.modelo.Cajero;
import usuarios.modelo.Cliente;
import usuarios.modelo.Operador;

public class VentanaLogin extends JFrame
{
	private PanelSuperior panelSup;
	private PanelCentral panelC;
	private PanelInferior panelI;
	
	HashMap<Integer, Cliente> clientes;
	HashMap<Integer, Cajero> cajeros;
	HashMap<Integer, Operador> operadores;
	HashMap<Integer, Administrador> administradores;
	static Inventario inventario;
	static AdministradorProcesos adminProcesos;
	static AdministradorUsuarios adminUsuarios;
	static String ruta;
	
	
	private Galeria galeria;

	
	
	
	public VentanaLogin() 
	{
		setSize(500,600);
        setTitle( "Galeria" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible(true);
		setLocationRelativeTo(null);
		
        setLayout( new BorderLayout( ) );
        
        panelSup = new PanelSuperior();
        add(panelSup, BorderLayout.NORTH);
        
        panelI = new PanelInferior(this );
        add(panelI, BorderLayout.SOUTH);
        
        panelC = new PanelCentral(this);
        add(panelC, BorderLayout.CENTER);
        
        clientes = new HashMap<Integer, Cliente>();
		cajeros = new HashMap<Integer, Cajero>();
		operadores = new HashMap<Integer, Operador>();
		administradores = new HashMap<Integer, Administrador>();
		
		try 
		{
			galeria = cargaDeDatos();
		} 
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog( this, "Error al cargar la galeria" );
		}
        
	}
	
	private static Galeria cargaDeDatos() throws IOException 
	{
		
		inventario = new Inventario();
		adminProcesos = new AdministradorProcesos();
		adminUsuarios = new AdministradorUsuarios();
		Galeria galeria = new Galeria(inventario, adminProcesos, adminUsuarios);
		
		CentralPersistencia.getPersistenciaUsuarios().cargarUsuarios("/Users/elica/git/Proyecto_02_DPOO/src/data/usuarios.json", galeria);
		CentralPersistencia.getPersistenciaInventario().cargarInventario("/Users/elica/git/Proyecto_02_DPOO/src/data/inventario.json", galeria);
		CentralPersistencia.getPersistenciaProcesos().cargarProcesos("/Users/elica/git/Proyecto_02_DPOO/src/data/procesos.json", galeria);

		
		return galeria;
	}
	
	public void guardarAplicacion() throws FileNotFoundException 
	{
		CentralPersistencia.getPersistenciaInventario().guardarInventario("C:/Users/elica/git/Proyecto_02_DPOO/src/data/inventario.json", galeria);
		CentralPersistencia.getPersistenciaProcesos().guardarProcesos("C:/Users/elica/git/Proyecto_02_DPOO/src/data/procesos.json", galeria);
		CentralPersistencia.getPersistenciaUsuarios().guardarUsuarios("C:/Users/elica/git/Proyecto_02_DPOO/src/data/usuarios.json", galeria);
		
	}
	
	
	public void IniciarSesion(String username, String contrasena, String tipo) 
	{
		if (galeria.getAdminUsuarios().logIn(username, contrasena, tipo)) 
		{
			if (tipo.equalsIgnoreCase("Cliente")) 
			{
				Cliente usuario = galeria.getAdminUsuarios().buscarCliente(username);
				new VentanaCliente(galeria, usuario, this);
				this.setVisible(false);
				
			} 
			else if (tipo.equalsIgnoreCase("Cajero")) 
			{
				Cajero cajero = galeria.getAdminUsuarios().buscarCajero(username);
				new VentanaCajero(galeria, cajero, this);
				this.setVisible(false);
			} 
			else if (tipo.equalsIgnoreCase("Operador")) 
			{
				Operador operador = galeria.getAdminUsuarios().buscarOperador(username);
				new VentanaOperador(galeria, operador, this);
				this.setVisible(false);
			} 
			else if (tipo.equalsIgnoreCase("Administrador")) 
			{
				Administrador administrador = galeria.getAdminUsuarios().buscarAdmin(username);
				new VentanaAdministrador(galeria, administrador, this);
				this.setVisible(false);
			}
		}
	}
	
	public void btnregistrarse() 
	{
		new RegisterDialog(this);
	}
	
	public void RegistroCliente(String username, String contrasenia, String telefono, String email ) 
	{
	
		Integer telefono1 = Integer.valueOf(telefono);
		
		Cliente cliente = new Cliente(username, contrasenia, "", telefono1, email, null);
		galeria.getAdminUsuarios().agregarCliente(cliente);
			
		
	}
	
	public void RegistroEmpleados(String tipo, String username, String contrasenia) 
	{
		if (tipo.equalsIgnoreCase("Cajero")) 
		{
			Cajero cajero = new Cajero(username, contrasenia, "", "Cajero", null);
			galeria.getAdminUsuarios().agregarCajero(cajero);
			
		} 
		else if (tipo.equalsIgnoreCase("Operador")) 
		{
			Operador operador = new Operador(username, contrasenia, "", "Operador", null);
			galeria.getAdminUsuarios().agregarOperador(operador);
			
		} 
		else if (tipo.equalsIgnoreCase("Administrador")) 
		{
			Administrador admin = new Administrador(username, contrasenia, "", null);
			galeria.getAdminUsuarios().agregarAdministrador(admin);
		}
	}
	
	public static void main(String[] args) 
	{
		new VentanaLogin();
	}
	
}
