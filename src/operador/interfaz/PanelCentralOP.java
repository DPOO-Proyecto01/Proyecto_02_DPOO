package operador.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import procesos.modelo.Subasta;

public class PanelCentralOP extends JPanel implements ActionListener
{
	private JPanel grid1;
	private JTextField txtIdPieza;
	private JTextField txtOferta;
	private JTextField txtCliente;
	private JButton oferta;
	private JLabel lblUsername;
	private JLabel lblSubasta;
	private JLabel lblActual;
	private JLabel lblOfertaNumero;
	private JLabel lblNueva;
	private JDialog completado;
	
	private VentanaOperador ventanaO;
	private Subasta subasta;
	
	public PanelCentralOP(VentanaOperador ventanaO) 
	{
		this.ventanaO = ventanaO;
		
		setLayout( new BorderLayout());
		
		add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
        
        add(new JLabel("Reistrar nueva oferta"), BorderLayout.NORTH);
        
        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(7,2,5,5));
        grid1.add(new JLabel());
        grid1.add(new JLabel());
        
        grid1.add(new JLabel("Ingrese el ID de la pieza de la subasta:"));
        txtIdPieza = new JTextField("");
        txtIdPieza.setEditable(true);
        grid1.add(txtIdPieza);
        txtIdPieza.addActionListener(this);
        txtIdPieza.setActionCommand("ID");
        
        
        lblSubasta = new JLabel("Subasta encontrada: ");
        grid1.add(lblSubasta);
        grid1.add(new JLabel());
        lblSubasta.setVisible(false);
        
        lblActual = new JLabel("La oferta actual es: ");
        grid1.add(lblActual);
        lblOfertaNumero = new JLabel("0");
        grid1.add(lblOfertaNumero);
        lblActual.setVisible(false);
        lblOfertaNumero.setVisible(false);
        
        lblNueva = new JLabel("Cual es la nueva oferta:");
        grid1.add(lblNueva);
        txtOferta = new JTextField("");
        txtOferta.setEditable(true);
        grid1.add(txtOferta);
        lblNueva.setVisible(false);
        txtOferta.setVisible(false);
        
        lblUsername = new JLabel("Ingrese el username del Cliente:");
        grid1.add(lblUsername);
        txtCliente = new JTextField("");
        txtCliente.setEditable(true);
        grid1.add(txtCliente);
        lblUsername.setVisible(false);
        txtCliente.setVisible(false);
        
        
        oferta = new JButton("Registrar oferta");
        oferta.addActionListener(this);
        oferta.setActionCommand("REGISTRAR");
        grid1.add(oferta);
        grid1.add(new JLabel());
        
        add(grid1, BorderLayout.CENTER);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == ("ID") ) 
		{	
			String id = getId();
			ventanaO.encontrarSubasta(id);
		}
		
		if(e.getActionCommand() == ("REGISTRAR") ) 
		{
			String nuevaO = getNuevaOferta();
			String username = getCliente();
			ventanaO.registrarOferta(nuevaO, username, subasta);
			
			completado = new JDialog();
			completado.setVisible(true);
			completado.setLocationRelativeTo(null);
			completado.setSize(200, 200);
			completado.setLayout(new BorderLayout(100,30));
			completado.add(new JLabel("Oferta registrada"), BorderLayout.CENTER);
			
		}
		
	}
	
	public void subastaEncontrada(double preciofinal, Subasta subastaE) 
	{
		subasta = subastaE;
		
		lblSubasta.setVisible(true);
		lblActual.setVisible(true);
        lblOfertaNumero.setVisible(true);
        lblNueva.setVisible(true);
        txtOferta.setVisible(true);
        lblUsername.setVisible(true);
        txtCliente.setVisible(true);
        
        String precioF = String.valueOf(preciofinal);
        
        lblOfertaNumero.setText(precioF);
	}
	
	public String getNuevaOferta() 
	{
		return txtOferta.getText();
	}
	
	public String getCliente() 
	{
		return txtCliente.getText();
	}
	
	public String getId() 
	{
		return txtIdPieza.getText();
	}

	
}
