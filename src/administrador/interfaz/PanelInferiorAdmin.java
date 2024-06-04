package administrador.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import login.interfaz.VentanaLogin;

public class PanelInferiorAdmin extends JPanel implements ActionListener
{
	private JButton btnCerrar;
	
	private VentanaAdministrador ventanaA;
	
	public PanelInferiorAdmin(VentanaAdministrador ventanaA) 
	{
		this.ventanaA = ventanaA;
		
		btnCerrar = new JButton("Cerrar sesion");
		add(btnCerrar);
		btnCerrar.addActionListener( this );
		btnCerrar.setActionCommand( "CERRAR" );
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == ("CERRAR")) 
		{
			ventanaA.cerrarSesion();
		}
		
	}

}
