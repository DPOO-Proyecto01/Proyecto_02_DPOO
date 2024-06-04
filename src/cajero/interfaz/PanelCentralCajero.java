package cajero.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCentralCajero extends JPanel implements ActionListener
{
	private VentanaCajero ventanaC;
	
	private JButton venta;
	private JPanel grid1;
	private JTextField txtIdPieza;
	private JTextField txtcomprador;
	private JTextField txtmetodo;
	private JTextField txtAdmin;
	private JDialog completado;
	
	public PanelCentralCajero(VentanaCajero ventanaC) 
	{
		
		this.ventanaC = ventanaC;
		
		setLayout( new BorderLayout());
		
		add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
        
        add(new JLabel("Registrar venta: "), BorderLayout.NORTH);
        
        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(6,2,5,5));
        grid1.add(new JLabel());
        grid1.add(new JLabel());
        
        grid1.add(new JLabel("Ingrese el id de la pieza"));
        txtIdPieza = new JTextField("");
        txtIdPieza.setEditable(true);
        grid1.add(txtIdPieza);
        
        grid1.add(new JLabel("Ingrese el usuario del comprador:"));
        txtcomprador = new JTextField("");
        txtcomprador.setEditable(true);
        grid1.add(txtcomprador);
        
        grid1.add(new JLabel("Ingrese el metodo de pago:"));
        txtmetodo = new JTextField("");
        txtmetodo.setEditable(true);
        grid1.add(txtmetodo);
        
        grid1.add(new JLabel("Ingrese el administrador que confirmo la venta:"));
        txtAdmin = new JTextField("");
        txtAdmin.setEditable(true);
        grid1.add(txtAdmin);
        
        venta = new JButton("Registrar");
        venta.addActionListener(this);
        venta.setActionCommand("REGISTRAR");
        grid1.add(venta);
        grid1.add(new JLabel());
        
        add(grid1, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == ("REGISTRAR")) 
		{	
			String id = txtIdPieza.getText();
			String username = txtcomprador.getText();
			String metodo = txtmetodo.getText();
			String nomAdmin = txtAdmin.getText();
			
			
			ventanaC.registrarVenta(id, username, metodo, nomAdmin);
			completado = new JDialog();
			completado.setVisible(true);
			completado.setLocationRelativeTo(null);
			completado.setSize(200, 200);
			completado.setLayout(new BorderLayout(100,30));
			completado.add(new JLabel("Registro completado"), BorderLayout.CENTER);
		}
	}
}
