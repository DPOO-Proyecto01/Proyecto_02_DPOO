package login.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterDialog extends JDialog implements ItemListener, ActionListener
{
	private JPanel panelGrid1;
	private JComboBox<String> comboB;
	private String tipo;
	
	private JPanel panelGrid2;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblContraseña;
	private JPasswordField txtContraseña;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	
	private JPanel panelGrid3;
	private JButton btnRegistrarse;
	
	private VentanaLogin ventanaL;
	
	private JDialog completado;
	
	public RegisterDialog(VentanaLogin ventanaL) 
	{
		
		this.ventanaL = ventanaL;
		
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(500, 500);
		setLayout(new BorderLayout(100,30));
		
		add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
		
		panelGrid1 = new JPanel();
        panelGrid1.setLayout( new GridLayout(2, 1, 5, 5));
        comboB = new JComboBox<String>();
        add(panelGrid1,BorderLayout.NORTH );
        panelGrid1.add(new JLabel("Seleccione el tipo de usuario"));
        panelGrid1.add(comboB);
        comboB.addItem("Aministrador");
        comboB.addItem("Cliente");
        comboB.addItem("Operador");
        comboB.addItem("Cajero");
        comboB.addItemListener(this);
        
        panelGrid2 = new JPanel( );
        add(panelGrid2, BorderLayout.CENTER);
        panelGrid2.setLayout( new GridLayout(6, 2, 1, 10));
		lblUsuario= new JLabel("Usuario:" );
        txtUsuario = new JTextField( "");
        txtUsuario.setEditable( true );
        panelGrid2.add(lblUsuario);
        panelGrid2.add(txtUsuario);
        lblContraseña= new JLabel("Contraseña:" );
        txtContraseña = new JPasswordField( "");
        txtContraseña.setEditable( true );
        panelGrid2.add(lblContraseña);
        panelGrid2.add(txtContraseña);
        lblTelefono= new JLabel("Telefono/Celular:" );
        txtTelefono = new JTextField( "");
        txtTelefono.setEditable( true );
        panelGrid2.add(lblTelefono);
        panelGrid2.add(txtTelefono);
        lblTelefono.setVisible(false);
        txtTelefono.setVisible(false);
        lblCorreo= new JLabel("Correo electronico:" );
        txtCorreo = new JTextField( "");
        txtCorreo.setEditable( true );
        panelGrid2.add(lblCorreo);
        panelGrid2.add(txtCorreo);
        lblCorreo.setVisible(false);
        txtCorreo.setVisible(false);
        
        panelGrid3 = new JPanel( );
        add(panelGrid3, BorderLayout.SOUTH);
        panelGrid3.setLayout( new GridLayout(3, 2, 1, 5));
        btnRegistrarse = new JButton("Registrarse");
        panelGrid3.add(btnRegistrarse);
        btnRegistrarse.addActionListener( this );
        btnRegistrarse.setActionCommand( "REGISTRAR" );
        panelGrid3.add(new JLabel());
        panelGrid3.add(new JLabel());
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		if (e.getSource()==comboB) 
		{
            String tipo =(String)comboB.getSelectedItem();
            
            if( tipo.equalsIgnoreCase("Cliente")) 
            {
            	lblTelefono.setVisible(true);
                txtTelefono.setVisible(true);
                lblCorreo.setVisible(true);
                txtCorreo.setVisible(true);
            }
            
            else 
            {
            	 lblTelefono.setVisible(false);
                 txtTelefono.setVisible(false);
                 lblCorreo.setVisible(false);
                 txtCorreo.setVisible(false);
            }
        }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String username = getUsuario();
		String contrasena = getContraseña();
		String tipo = getTipoUsuario();
		String correo = getCorreo();
		String telefono = getTelefono();
		
		if(e.getActionCommand() == ("REGISTRAR") ) 
		{
			if( tipo.equalsIgnoreCase("Cliente") && username.isEmpty()==false && contrasena.isEmpty()==false && telefono.isEmpty()==false && correo.isEmpty()==false ) 
            {
				ventanaL.RegistroCliente(username, contrasena, correo, telefono);
				completado = new JDialog();
				completado.setVisible(true);
				completado.setLocationRelativeTo(null);
				completado.setSize(200, 200);
				completado.setLayout(new BorderLayout(100,30));
				
				completado.add(new JLabel("Registro completado"), BorderLayout.NORTH);
				completado.add(new JButton("Volver al inicio"), BorderLayout.CENTER);
            }
			
			else if (username.isEmpty()==false && contrasena.isEmpty()==false)
			{
				ventanaL.RegistroEmpleados(tipo, username, contrasena);
				completado = new JDialog();
				completado.setVisible(true);
				completado.setLocationRelativeTo(null);
				completado.setSize(200, 200);
				completado.setLayout(new BorderLayout(100,30));
				
				completado.add(new JLabel("Registro completado"), BorderLayout.NORTH);
				completado.add(new JLabel("Volver al login"), BorderLayout.CENTER);
		
			}
		}	
		
	}
	
	public String getTipoUsuario() 
	{
		return (String) this.comboB.getSelectedItem();
	}
	
	public String getUsuario() 
	{
		return  (String) this.txtUsuario.getText();
	}
	

	@SuppressWarnings("deprecation")
	public String getContraseña() 
	{
		return (String) this.txtContraseña.getText();
	}
	
	public String getCorreo() 
	{
		return (String) this.txtCorreo.getText();
	}
	

	public String getTelefono() 
	{
		return (String) this.txtTelefono.getText();
	}
	
	
}
