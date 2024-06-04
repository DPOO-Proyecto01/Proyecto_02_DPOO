package cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCentralCliente extends JPanel
{
	private JButton consultar;
	private JButton piezasV;
	private JButton subastas;
	private JButton consignar;
	private JPanel grid1;
	
	public PanelCentralCliente() 
	{
		
		setLayout( new BorderLayout());
		
		add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
        
        add(new JLabel("Que accion desea realizar: "), BorderLayout.NORTH);
        
        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(4,2,5,5));
        grid1.add(new JLabel());
        grid1.add(new JLabel());
        consultar = new JButton("Consultar perfil");
        grid1.add(consultar);
        piezasV = new JButton("Ver piezas en venta");
        grid1.add(piezasV);
        subastas = new JButton("Ver subastas");
        grid1.add(subastas);
        consignar = new JButton("Consignar una pieza");
        grid1.add(consignar);
        grid1.add(new JLabel());
        grid1.add(new JLabel());
        
        add(grid1, BorderLayout.CENTER);
        
	}
}
