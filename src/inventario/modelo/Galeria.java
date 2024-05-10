package inventario.modelo;

import java.io.IOException;

import galeria.persistencia.CentralPersistencia;
import galeria.persistencia.PersistenciaInventario;
import galeria.persistencia.PersistenciaProcesos;
import galeria.persistencia.PersistenciaUsuarios;
import procesos.modelo.AdministradorProcesos;
import usuarios.modelo.AdministradorUsuarios;

public class Galeria 
{
	//Galeria conoce a un adminUsuarios, un adminProcesos y un inventario
	private Inventario inventario;
	private AdministradorProcesos adminProcesos;
	private AdministradorUsuarios adminUsuarios;
	
	public Galeria(Inventario inventario, AdministradorProcesos adminProcesos, AdministradorUsuarios adminUsuarios) 
	{
		this.inventario = inventario;
		this.adminProcesos = adminProcesos;
		this.adminUsuarios = adminUsuarios;
	}
	
	
	public Inventario getInventario() 
	{
		return inventario;
	}



	public AdministradorProcesos getAdminProcesos() 
	{
		return adminProcesos;
	}



	public AdministradorUsuarios getAdminUsuarios() 
	{
		return adminUsuarios;
	}
	
	public boolean existeId (String id)
	{
		/**Este método se relaciona con la persistencia de inventario
		 su función es obtener si un id ya existe para ver si se agrega
		 o no una pieza en la carga de datos**/
		
		return inventario.getIds().contains(id);
	}
	
	public void agregarPieza(Pieza pieza)
	{
		/**Al igual que el método anterior, este proceso también está relacionado con la
		 persistencia, simplemente le pide al inventario agregar una pieza**/
		
		inventario.agregarPieza(pieza);
	}


	//Los siguientes métodos llaman a las distintas persitencias para hacer la carga y guardar los datos

	public void cargarInventario (String archivo) throws IOException
	{
		PersistenciaInventario cargador = CentralPersistencia.getPersistenciaInventario();
		cargador.cargarInventario(archivo,this);
	}
	
	public void guardarInventario (String archivo)
	{
		PersistenciaInventario cargador = CentralPersistencia.getPersistenciaInventario();
		cargador.guardarInventario(archivo);
	}
	
	public void cargarProcesos (String archivo)
	{
		PersistenciaProcesos cargador = CentralPersistencia.getPersistenciaProcesos();
		cargador.cargarProcesos(archivo);
	}
	
	public void guardarProcesos (String archivo)
	{
		PersistenciaProcesos cargador = CentralPersistencia.getPersistenciaProcesos();
		cargador.guardarProcesos(archivo);
	}
	
	public void cargarUsuarios (String archivo)
	{
		PersistenciaUsuarios cargador = CentralPersistencia.getPersistenciaUsuarios();
		cargador.cargarUsuarios(archivo);
	}
	
	public void guardarUsuarios (String archivo)
	{
		PersistenciaUsuarios cargador = CentralPersistencia.getPersistenciaUsuarios();
		cargador.guardarUsuarios(archivo);
	}
}
