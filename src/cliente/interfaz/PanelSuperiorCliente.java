package cliente.interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSuperiorCliente extends JPanel 
{
private JLabel img;
	
	
	public PanelSuperiorCliente() 
	{
		img = new JLabel( );
        add(img);
        
        ImageIcon icon= new ImageIcon("./data/imagenes/clienteB.png" );
        img.setIcon( icon );
	}
}
