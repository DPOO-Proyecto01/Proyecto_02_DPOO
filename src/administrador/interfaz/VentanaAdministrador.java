package administrador.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaAdministrador extends JFrame
{
	private JPanel panelS;
	private JPanel panelC;
	private JPanel panelI;
	
	public VentanaAdministrador() 
	{
		setTitle("Galeria");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setLayout( new BorderLayout( ) );
		
		panelS = new PanelSuperiorAdmin();
		add(panelS, BorderLayout.NORTH);
		
		panelC = new PanelCentralAdmin();
		add(panelC, BorderLayout.CENTER);
		
		panelI = new PanelInferiorAdmin();
		add(panelI, BorderLayout.SOUTH);
	}
}
