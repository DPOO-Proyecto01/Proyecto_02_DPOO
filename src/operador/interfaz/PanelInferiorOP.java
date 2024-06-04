package operador.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelInferiorOP extends JPanel implements ActionListener
{
private JButton btnCerrar;
	
	private VentanaOperador ventanaO;
	
	public PanelInferiorOP(VentanaOperador ventanaO) 
	{
		this.ventanaO = ventanaO;
		
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
			ventanaO.cerrarSesion();
		}
		
	}
}
