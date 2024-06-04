package operador.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCentralOP extends JPanel
{
	private JButton verificar;
	private JPanel grid1;
	
	public PanelCentralOP() 
	{
		
		setLayout( new BorderLayout());
		
		add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
        
        add(new JLabel("Que accion desea realizar: "), BorderLayout.NORTH);
        
        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(3,1,5,5));
        grid1.add(new JLabel());
        verificar = new JButton("Registrar nueva oferta");
        grid1.add(verificar);
        grid1.add(new JLabel());
        
        add(grid1, BorderLayout.CENTER);
        
	}
}
