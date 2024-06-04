package cliente.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import inventario.modelo.Galeria;

public class VentanaCliente extends JFrame
{
	private JPanel panelS;
	private JPanel panelC;
	private JPanel panelI;
	
	private Galeria galeria;
	
	public VentanaCliente() 
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
		
	}
}
