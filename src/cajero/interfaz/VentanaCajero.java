package cajero.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import inventario.modelo.Galeria;

public class VentanaCajero extends JFrame
{
	private JPanel panelS;
	private JPanel panelC;
	private JPanel panelI;
	
	private Galeria galeria;

	
	public VentanaCajero() 
	{
		setTitle("Galeria");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setLayout( new BorderLayout( ) );
		
		panelS = new PanelSuperiorCajero();
		add(panelS, BorderLayout.NORTH);
		
		panelC = new PanelCentralCajero();
		add(panelC, BorderLayout.CENTER);
		
		panelI = new PanelInferiorCajero(this);
		add(panelI, BorderLayout.SOUTH);
		
	}

	public void cerrarSesion() 
	{
		
	}
}
