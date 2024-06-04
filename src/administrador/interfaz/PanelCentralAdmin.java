package administrador.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCentralAdmin extends JPanel 
{
	private JButton verificar;
	private JButton confirmar;
	private JPanel grid1;
	
	public PanelCentralAdmin() 
	{
		
		setLayout( new BorderLayout());
		
		add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
        
        add(new JLabel("Que accion desea realizar: "), BorderLayout.NORTH);
        
        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(3,3,5,5));
        grid1.add(new JLabel());
        grid1.add(new JLabel());
        verificar = new JButton("verificar usuarios");
        grid1.add(verificar);
        confirmar = new JButton("Confirmar usuarios");
        grid1.add(confirmar);
        grid1.add(new JLabel());
        grid1.add(new JLabel());
        
        add(grid1, BorderLayout.CENTER);
        
	}
}
