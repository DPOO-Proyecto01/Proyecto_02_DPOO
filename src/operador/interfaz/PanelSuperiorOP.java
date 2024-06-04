package operador.interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSuperiorOP extends JPanel
{
private JLabel img;
	
	
	public PanelSuperiorOP() 
	{
		img = new JLabel( );
        add(img);
        
        ImageIcon icon= new ImageIcon("./data/imagenes/operadorB.png" );
        img.setIcon( icon );
	}
}
