package login.interfaz;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelInferior extends JPanel
{
	private JButton btnRegistrar;
	
	public PanelInferior() 
	{
		btnRegistrar = new JButton("Registrarse");
		add(btnRegistrar);
	}
	

}
