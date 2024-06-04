package login.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelInferior extends JPanel implements ActionListener
{
	private JButton btnRegistrar;
	
	private VentanaLogin ventanaL;
	
	public PanelInferior(VentanaLogin ventanaL) 
	{
		this.ventanaL = ventanaL;
		
		btnRegistrar = new JButton("Registrarse");
		add(btnRegistrar);
		btnRegistrar.addActionListener( this );
		btnRegistrar.setActionCommand( "REGISTRAR" );
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == ("REGISTRAR")) 
		{
			ventanaL.btnregistrarse();
		}
		
	}
	

}
