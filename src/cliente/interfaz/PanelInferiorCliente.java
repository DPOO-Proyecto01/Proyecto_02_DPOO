package cliente.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelInferiorCliente extends JPanel implements ActionListener
{
private JButton btnCerrar;
	
	private VentanaCliente ventanaC;
	
	public PanelInferiorCliente(VentanaCliente ventanaC) 
	{
		this.ventanaC = ventanaC;
		
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
			ventanaC.cerrarSesion();
		}
		
	}
}
