package operador.interfaz;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import galeria.persistencia.CentralPersistencia;
import inventario.modelo.Galeria;
import inventario.modelo.Pieza;
import login.interfaz.VentanaLogin;
import procesos.modelo.Subasta;
import usuarios.modelo.Operador;

public class VentanaOperador extends JFrame
{
	private PanelSuperiorOP panelS;
	private PanelCentralOP panelC;
	private PanelInferiorOP panelI;
	
	private Galeria galeria;
	private Operador operador;
	private VentanaLogin ventanaL;
	
	public VentanaOperador(Galeria galeria, Operador operador, VentanaLogin ventanaL) 
	{
		this.galeria = galeria;
		this.operador = operador;
		this.ventanaL = ventanaL;
		
		setTitle("Galeria");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setLayout( new BorderLayout( ) );
		
		panelS = new PanelSuperiorOP();
		add(panelS, BorderLayout.NORTH);
		
		panelC = new PanelCentralOP(this);
		add(panelC, BorderLayout.CENTER);
		
		panelI = new PanelInferiorOP(this);
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
	
	public void encontrarSubasta(String ID) 
	{
		Pieza pieza = galeria.getInventario().buscarPieza(ID);
		Set<Pieza>piezasEnSubasta = galeria.getAdminProcesos().getSubastasEnProceso().keySet();
		
		if (piezasEnSubasta.contains(pieza)) 
		{
			Subasta subasta = galeria.getAdminProcesos().getSubastasEnProceso().get(pieza);
			double preciofinal = subasta.getPrecioFinal();
			panelC.subastaEncontrada(preciofinal, subasta);
		}
	}
	
	public void registrarOferta(String ofertaS, String username, Subasta subasta ) 
	{
		double oferta = Double.valueOf(ofertaS);
		if (oferta > subasta.getPrecioFinal()) 
		{
			LocalDateTime dateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			String formattedDateTime = dateTime.format(formatter);
			
			subasta.añadirOferta(formattedDateTime + "-" + String.valueOf(oferta) + "-" + username);
		}
		try {
			CentralPersistencia.getPersistenciaProcesos().guardarProcesos("./data/procesos", galeria);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog( this, "Error al registrar la oferta" );
		}
	}
	
	
}
