package login.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelCentral extends JPanel implements ActionListener
{
	private JPanel panelGrid1;
	private JComboBox<String> combo1;
	
	private JPanel panelGrid;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblContraseña;
	private JPasswordField txtContraseña;
	
	private JPanel panelGrid2;
	private JButton btnIniciar;
	
	private VentanaLogin ventanaL;
	
	public PanelCentral(VentanaLogin ventanaL) 
	{
		
		this.ventanaL = ventanaL;
		
		setLayout( new BorderLayout(100, 30) );
		
        panelGrid1 = new JPanel();
        panelGrid1.setLayout( new GridLayout(2, 1, 5, 5));
        combo1 = new JComboBox<String>();
        add(panelGrid1,BorderLayout.NORTH );
        panelGrid1.add(new JLabel("Seleccione el tipo de usuario"));
        panelGrid1.add(combo1);
        combo1.addItem("Cliente");
        combo1.addItem("Administrador");
        combo1.addItem("Operador");
        combo1.addItem("Cajero");
        
        
        
        panelGrid = new JPanel( );
        add(panelGrid, BorderLayout.CENTER);
        
        panelGrid.setLayout( new GridLayout(2, 2, 1, 10));

		lblUsuario= new JLabel("Usuario:" );
        txtUsuario = new JTextField( "");
        txtUsuario.setEditable( true );
        panelGrid.add(lblUsuario);
        panelGrid.add(txtUsuario);
        
        lblContraseña= new JLabel("Contraseña:" );
        txtContraseña = new JPasswordField( "");
        txtContraseña.setEditable( true );
        panelGrid.add(lblContraseña);
        panelGrid.add(txtContraseña);
       
       
        panelGrid2 = new JPanel();
        panelGrid2.setLayout( new GridLayout(2, 2, 5, 5));
        btnIniciar = new JButton("Iniciar sesion");
        add(panelGrid2, BorderLayout.SOUTH );
        panelGrid2.add(btnIniciar);
        btnIniciar.addActionListener( this );
        btnIniciar.setActionCommand( "INICIAR" );
        
        add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String username = getUsuario();
		String contrasena = getContraseña();
		String tipo = getTipoUsuario();
		
		if(e.getActionCommand() == ("INICIAR")) 
		{
			ventanaL.IniciarSesion(username, contrasena, tipo);
		}	
		
	}
	
	public String getTipoUsuario() 
	{
		return (String) this.combo1.getSelectedItem();
	}
	
	public String getUsuario() 
	{
		return this.txtUsuario.getText();
	}
	

	@SuppressWarnings("deprecation")
	public String getContraseña() 
	{
		return this.txtContraseña.getText();
	}
}
