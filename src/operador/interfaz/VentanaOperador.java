package operador.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import inventario.modelo.Galeria;

public class VentanaOperador extends JFrame
{
	private JPanel panelS;
	private JPanel panelC;
	private JPanel panelI;
	
	private Galeria galeria;
	
	public VentanaOperador() 
	{
		setTitle("Galeria");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setLayout( new BorderLayout( ) );
		
		panelS = new PanelSuperiorOP();
		add(panelS, BorderLayout.NORTH);
		
		panelC = new PanelCentralOP();
		add(panelC, BorderLayout.CENTER);
		
		panelI = new PanelInferiorOP(this);
		add(panelI, BorderLayout.SOUTH);
	}
	
	public void cerrarSesion() 
	{
		
	}
}
