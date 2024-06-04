package cajero.interfaz;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import galeria.persistencia.CentralPersistencia;
import inventario.modelo.Galeria;
import inventario.modelo.Pieza;
import login.interfaz.VentanaLogin;
import procesos.modelo.Venta;
import usuarios.modelo.Administrador;
import usuarios.modelo.Cajero;
import usuarios.modelo.Cliente;

public class VentanaCajero extends JFrame
{
	private PanelSuperiorCajero panelS;
	private PanelCentralCajero panelC;
	private PanelInferiorCajero panelI;
	
	private Galeria galeria;
	private Cajero cajero;
	private VentanaLogin ventanaL;

	
	public VentanaCajero(Galeria galeria, Cajero cajero, VentanaLogin ventanaL) 
	{
		setTitle("Galeria");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setLayout( new BorderLayout( ) );
		
		panelS = new PanelSuperiorCajero();
		add(panelS, BorderLayout.NORTH);
		
		panelC = new PanelCentralCajero(this);
		add(panelC, BorderLayout.CENTER);
		
		panelI = new PanelInferiorCajero(this);
		add(panelI, BorderLayout.SOUTH);
		
	}

	public void cerrarSesion() 
	{
		try 
		{
			ventanaL.guardarAplicacion();
		} 
		catch (FileNotFoundException e) 
		{
			JOptionPane.showMessageDialog( this, "Error al guardar los datos" );
		}
		this.dispose();
		ventanaL.setVisible(true);
		
	}
	
	public void registrarVenta(String id, String username, String metodo, String nomAdmin) 
	{
		if (galeria.getInventario().getIds().contains(id)) 
		{
			
			Pieza pieza = galeria.getInventario().buscarPieza(id);
			
			Cliente cliente = galeria.getAdminUsuarios().buscarCliente(username);
			
			if (!(cliente.equals(null))) 
			{
				
				Administrador admin = galeria.getAdminUsuarios().buscarAdmin(nomAdmin);
				
				if (!(admin.equals(null))) 
				{
					Venta venta = new Venta(pieza, pieza.getPrecio(), cajero, admin, metodo, cliente.getID());
					galeria.getAdminProcesos().añadirVenta(venta);
				} 
				else 
				{
					JOptionPane.showMessageDialog( this, "No se encontro el administrador, vuelva a intentarlo" );
				}
				
				
			} 
			else 
			{
				JOptionPane.showMessageDialog( this, "No se encontro el usuario, vuelva a intentar" );
			}
			
		} 
		else 
		{
			JOptionPane.showMessageDialog( this, "No se encontro la pieza que esta buscando, vuelva a intentar" );
		}
		
		try {
			CentralPersistencia.getPersistenciaProcesos().guardarProcesos("./data/procesos", galeria);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog( this, "No se pudo registrar" );
		}
	}
	
	
}
