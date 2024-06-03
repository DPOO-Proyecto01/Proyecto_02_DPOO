package login.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelCentral extends JPanel
{
	private JComboBox<String> combo1;
	
	private JPanel panelGrid;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	
	private JLabel lblContraseña;
	private JPasswordField txtContraseña;
	
	private JButton btnIniciar;
	
	public PanelCentral() 
	{
		setLayout( new BorderLayout( ) );
		
        
        combo1 = new JComboBox<String>();
        combo1.setBounds(10,10,80,20);
        add(combo1);
        combo1.addItem("Cliente");
        combo1.addItem("Administrador");
        combo1.addItem("Operador");
        combo1.addItem("Cajero");
        add(combo1,BorderLayout.NORTH);
        
        
        
        panelGrid = new JPanel( );
        add(panelGrid, BorderLayout.CENTER);
        
        panelGrid.setLayout( new GridLayout(4, 1));
        
        panelGrid.add(new JLabel());
        panelGrid.add(new JLabel());
        
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
        
        panelGrid.add(new JLabel());
        panelGrid.add(new JLabel());
       
        btnIniciar = new JButton("Iniciar sesion");
        add(btnIniciar, BorderLayout.SOUTH);
        
        add(new JLabel(""), BorderLayout.WEST);
        
        add(new JLabel(""), BorderLayout.EAST);
        
        
       
        
	}
}
