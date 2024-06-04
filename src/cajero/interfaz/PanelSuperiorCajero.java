package cajero.interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSuperiorCajero extends JPanel
{
private JLabel img;
	
	
	public PanelSuperiorCajero() 
	{
		img = new JLabel( );
        add(img);
        
        ImageIcon icon= new ImageIcon("./data/imagenes/cajeroB.png" );
        img.setIcon( icon );
	}
}
