package login.interfaz;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaLogin extends JFrame
{
	private PanelSuperior panelSup;
	private PanelCentral panelC;
	private PanelInferior panelI;
	
	
	
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
        
        panelI = new PanelInferior( );
        add(panelI, BorderLayout.SOUTH);
        
        panelC = new PanelCentral();
        add(panelC, BorderLayout.CENTER);
        

	}
	
	public static void main(String[] args) 
	{
		new VentanaLogin();
	}
	
}
