package cajero.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCentralCajero extends JPanel 
{
	private JButton venta;
	private JPanel grid1;
	
	public PanelCentralCajero() 
	{
		setLayout( new BorderLayout());
		
		add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
        
        add(new JLabel("Que accion desea realizar: "), BorderLayout.NORTH);
        
        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(3,1,5,5));
        grid1.add(new JLabel());
        venta = new JButton("Registrar Venta");
        grid1.add(venta);
        grid1.add(new JLabel());
        
        add(grid1, BorderLayout.CENTER);
	}
}
