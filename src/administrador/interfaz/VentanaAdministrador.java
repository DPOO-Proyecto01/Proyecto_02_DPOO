package administrador.interfaz;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import inventario.modelo.Galeria;
import login.interfaz.VentanaLogin;
import usuarios.modelo.Administrador;
import usuarios.modelo.Cliente;

public class VentanaAdministrador extends JFrame
{
	private PanelSuperiorAdmin panelS;
	private PanelCentralAdmin panelC;
	private PanelInferiorAdmin panelI;
	
	private Galeria galeria;
	private Administrador admin;
	private VentanaLogin ventanaL;
	
	public VentanaAdministrador(Galeria galeria, Administrador admin, VentanaLogin ventanaL) 
	{
		this.galeria = galeria;
		this.admin = admin;
		this.ventanaL = ventanaL;
		
		setTitle("Galeria");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setLayout( new BorderLayout( ) );
		
		panelS = new PanelSuperiorAdmin();
		add(panelS, BorderLayout.NORTH);
		
		panelC = new PanelCentralAdmin(this);
		add(panelC, BorderLayout.CENTER);

		panelI = new PanelInferiorAdmin(this);
		add(panelI, BorderLayout.SOUTH);
	}
	
	public void cerrarSesion()
	{
		try 
		{
			ventanaL.guardarAplicacion();
		} 
		catch (FileNotFoundException e) 
		{
			JOptionPane.showMessageDialog( this, "Error al guardar los datos" );
		}
		this.dispose();
		ventanaL.setVisible(true);
		
	}
	
	public void verificarUsuarios(String username) 
	{
		Cliente cliente = galeria.getAdminUsuarios().buscarCliente(username);
		cliente.setVerificar();
	}
	
	public void confirmarProcesos() 
	{
		
	}
}
