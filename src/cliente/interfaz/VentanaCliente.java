package cliente.interfaz;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import inventario.modelo.Galeria;
import login.interfaz.VentanaLogin;
import usuarios.modelo.Cliente;

public class VentanaCliente extends JFrame
{
	private PanelSuperiorCliente panelS;
	private PanelCentralCliente panelC;
	private PanelInferiorCliente panelI;
	
	private Galeria galeria;
	private Cliente cliente;
	private VentanaLogin ventanaL;
	
	public VentanaCliente(Galeria galeria, Cliente cliente, VentanaLogin ventanaL) 
	{
		setTitle("Galeria");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setLayout( new BorderLayout( ) );
		
		panelS = new PanelSuperiorCliente();
		add(panelS, BorderLayout.NORTH);
		
		panelC = new PanelCentralCliente();
		add(panelC, BorderLayout.CENTER);
		
		panelI = new PanelInferiorCliente(this);
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
}
