package administrador.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCentralAdmin extends JPanel implements ActionListener
{
	private JButton verificar;
	private JButton confirmar;
	private JPanel grid1;
	private JLabel lblNorte;
	private JPanel grid2;
	private JTextField txtUsuario;
	private JButton verificarUser;
	private JDialog completado;
	private JButton ventas;
	private JButton subastas;
	
	private VentanaAdministrador ventanaA;
	
	public PanelCentralAdmin(VentanaAdministrador ventanaA) 
	{
		this.ventanaA = ventanaA;
		
		setLayout( new BorderLayout());
		
		add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST); 
        
        lblNorte = new JLabel("Que accion desea realizar: ");
        add(lblNorte, BorderLayout.NORTH);
        
        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(3,2,5,5));
        grid1.add(new JLabel());
        grid1.add(new JLabel());
        verificar = new JButton("verificar usuarios");
        grid1.add(verificar);
        verificar.addActionListener(this);
        verificar.setActionCommand("VERIF");
        confirmar = new JButton("Confirmar usuarios");
        grid1.add(confirmar);
        confirmar.addActionListener(this);
        confirmar.setActionCommand("CONFIR");
        grid1.add(new JLabel());
        grid1.add(new JLabel());
        
        add(grid1, BorderLayout.CENTER);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == ("VERIF")) 
		{
			verificacionUser();
		}
		if(e.getActionCommand() == ("CONFIR")) 
		{
			confirmacionProcesos();
		}
		if(e.getActionCommand() == ("CVERIFICAR")) 
		{
			String username = txtUsuario.getText();
			ventanaA.verificarUsuarios(username);
			completado = new JDialog();
			completado.setVisible(true);
			completado.setLocationRelativeTo(null);
			completado.setSize(200, 200);
			completado.setLayout(new BorderLayout(100,30));
			completado.add(new JLabel("Verificacion completada"), BorderLayout.CENTER);
			grid2.setVisible(false);
			grid1.setVisible(true);
		}
		if(e.getActionCommand() == ("VENTAS")) 
		{
			
		}
		if(e.getActionCommand() == ("SUBASTAS")) 
		{
			
		}
		
	}
	
	public void verificacionUser() 
	{
		lblNorte.setVisible(false);
		grid1.setVisible(false);
		
		grid2 = new JPanel();
		grid2.setLayout(new GridLayout(5,1,5,5));
		grid2.add(new JLabel());
		grid2.add(new JLabel("Ingrese el nombre del usuario:"));
		txtUsuario = new JTextField("");
        txtUsuario.setEditable(true);
        grid2.add(txtUsuario);
        verificarUser = new JButton("Verificar usuario");
        grid2.add(verificarUser);
        verificarUser.addActionListener(this);
        verificarUser.setActionCommand("CVERIFICAR");
        grid2.add(new JLabel());
        
        add(grid2, BorderLayout.CENTER);
		
	}
	
	public void confirmacionProcesos() 
	{
		lblNorte.setVisible(false);
		
		grid2 = new JPanel();
		grid2.setLayout(new GridLayout(5,1,5,5));
		grid2.add(new JLabel());
		grid2.add(new JLabel("Que desea confirmar?"));
		ventas = new JButton("Ventas");
        grid2.add(ventas);
        ventas.addActionListener(this);
        ventas.setActionCommand("VENTAS");
        subastas = new JButton("Verificar usuario");
        grid2.add(subastas);
        subastas.addActionListener(this);
        subastas.setActionCommand("SUBASTAS");
        grid2.add(new JLabel());
        
        add(grid2, BorderLayout.CENTER);
	}
}
