package galeria.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import inventario.modelo.Galeria;
import inventario.modelo.Pieza;
import procesos.modelo.AdministradorProcesos;
import procesos.modelo.Subasta;
import procesos.modelo.Venta;
import usuarios.modelo.Administrador;
import usuarios.modelo.AdministradorUsuarios;
import usuarios.modelo.Cliente;
import usuarios.modelo.Empleado;
import inventario.modelo.Inventario;

public class PersistenciaProcesos 
{
	public void guardarProcesos (String archivo, Galeria galeria) throws FileNotFoundException
	{
		AdministradorProcesos adminP = galeria.getAdminProcesos();
		JSONObject jobject = new JSONObject( );

        //Ventas
        guardarVentas( adminP, jobject );

        //Guardar subastas en proceso
        guardarSubastasEnProceso( adminP, jobject );
        
      //Guardar subastas finalizadas
        guardarSubastasFinalizadas( adminP, jobject );

        // Escribir la estructura JSON en un archivo
        PrintWriter pw;
			pw = new PrintWriter(archivo);
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	public void cargarProcesos (String archivo, Galeria galeria) throws IOException
	{
		Inventario inventario = galeria.getInventario();
		String jsonCompleto = new String( Files.readAllBytes( Path.of(archivo) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
		AdministradorProcesos adminP = galeria.getAdminProcesos();
		AdministradorUsuarios adminU = galeria.getAdminUsuarios();


        cargarVentas( adminP, raiz.getJSONArray( "Ventas" ), inventario, adminU );
        cargarSubastasEnProceso( adminP, raiz.getJSONArray( "SubastasEnProceso" ) );
        cargarSubastasFinalizadas( adminP, raiz.getJSONArray( "SubastasFinalizadas" ) );
	}
	
	
	private void guardarVentas(AdministradorProcesos adminp, JSONObject jobject)
	{
		JSONArray jVentas = new JSONArray( );
        for( Venta venta : adminp.getVentas() )
        {
            JSONObject jventa = new JSONObject( );
            jventa.put( "Pieza", venta.getPieza() );
            jventa.put( "Precio", venta.getPrecio());
            jventa.put( "Comprador", venta.getIdComprador() );
            jventa.put( "MedioDePago", venta.getMedioDePago() );
            jventa.put( "Empleado", venta.getEmpleado() );
            jventa.put( "Administrador", venta.getAdmin() );
            jventa.put( "Confirmado", venta.getConfirmado() );

            jVentas.put( jventa );
        }
        jobject.put( "Ventas", jVentas );
				
	}
			
	private void guardarSubastasEnProceso(AdministradorProcesos adminp, JSONObject jobject)
	{
		JSONArray jSubastasP = new JSONArray( );
		Map<Pieza,Subasta> SubastasP = adminp.getSubastasEnProceso();
		for (Subasta subasta : SubastasP.values()) 
		{
			JSONObject jSubasta = new JSONObject( );
            jSubasta.put( "Pieza", subasta.getPieza() );
            jSubasta.put( "PrecioFinal", subasta.getPrecioFinal());
            
            JSONArray jOfertas = new JSONArray( );
			List<String> ofertas = subasta.getOfertas();
			for (String oferta : ofertas) 
			{
				jOfertas.put (oferta);
			}
            jSubasta.put( "Ofertas", jOfertas );
            
            
            jSubasta.put( "Terminada", subasta.isTerminada() );
            jSubasta.put( "Fecha", subasta.getFecha() );
            jSubasta.put( "Empleado", subasta.getEmpleado());
            jSubasta.put( "Administrador", subasta.getAdmin());
            jSubasta.put( "Confirmado", subasta.getConfirmado());

            jSubastasP.put( jSubasta );
        }
        jobject.put( "SubastasEnProceso", jSubastasP );
	}
	
	private void guardarSubastasFinalizadas(AdministradorProcesos adminp, JSONObject jobject)
	{
		JSONArray jSubastasF = new JSONArray( );
        for( Subasta subasta : adminp.getSubastasFinalizadas() )
        {
            JSONObject jSubasta = new JSONObject( );
            jSubasta.put( "Pieza", subasta.getPieza() );
            jSubasta.put( "PrecioFinal", subasta.getPrecioFinal());
            
            JSONArray jOfertas = new JSONArray( );
			List<String> ofertas = subasta.getOfertas();
			for (String oferta : ofertas) 
			{
				jOfertas.put (oferta);
			}
            jSubasta.put( "Ofertas", jOfertas );
            
            jSubasta.put( "Terminada", subasta.isTerminada() );
            jSubasta.put( "Fecha", subasta.getFecha() );
            jSubasta.put( "Empleado", subasta.getEmpleado());
            jSubasta.put( "Administrador", subasta.getAdmin());
            jSubasta.put( "Confirmado", subasta.getConfirmado());

            jSubastasF.put( jSubasta );
        }
        jobject.put( "SubastasFinalizadas", jSubastasF );

	}
	
	private void cargarVentas(AdministradorProcesos adminp, JSONArray jVentas, Inventario inventario, AdministradorUsuarios adminU) 
	{
		int numVentas = jVentas.length( );
        for( int i = 0; i < numVentas; i++ ) 
        {
        	JSONObject venta = jVentas.getJSONObject( i );
        	
        	String idPieza = (String) venta.get("IdPieza");
        	double precio = venta.getDouble("Precio");
        	String empleado = (String) venta.get("NombreEmpleado");
        	String admin = (String) venta.get("NombreAdministrador");
        	String medioDePago = venta.getString("MedioDePago");
        	Integer comprador =  (Integer) venta.get("idComprador");
        	boolean confirmado = (boolean) venta.getBoolean("Confirmado");
        	
        	Venta Venta = new Venta(inventario.buscarPieza(idPieza), precio, adminU.buscarCajero(empleado), adminU.buscarAdmin(admin), medioDePago, comprador);
        	if (confirmado) { Venta.setConfirmado(); }
            adminp.añadirVenta(Venta);
        }
	}
	
	private void cargarSubastasEnProceso(AdministradorProcesos adminp, JSONArray jSubastasP) 
	{
		int numSubastas = jSubastasP.length( );
        for( int i = 0; i < numSubastas; i++ ) 
        {
        	JSONObject subasta = jSubastasP.getJSONObject( i );
        	
        	Pieza pieza = (Pieza) subasta.get("Pieza");
        	double precioFinal = subasta.getDouble("PrecioFinal");
        	String fecha = subasta.getString("Fecha");
        	Empleado empleado = (Empleado) subasta.get("Empleado");
        	Administrador admin = (Administrador) subasta.get("Administrador");
        	boolean confirmado = (boolean) subasta.getBoolean("Confirmado");
        	
        	Subasta Subasta = new Subasta(pieza, precioFinal, fecha, empleado, admin);
        	
        	JSONArray ofertas = (JSONArray) subasta.get("Ofertas");
        	for (Object jOferta : ofertas) 
        	{
        		String oferta = (String) jOferta;
        		Subasta.añadirOferta(oferta);
        	}
        	
        	if (confirmado) { Subasta.setConfirmado(); }
        	
            adminp.añadirSubasta(Subasta);
        }
	}
	
	private void cargarSubastasFinalizadas(AdministradorProcesos adminp, JSONArray jSubastasF) 
	{
		int numSubastas = jSubastasF.length( );
        for( int i = 0; i < numSubastas; i++ ) 
        {
        	JSONObject subasta = jSubastasF.getJSONObject( i );
        	
        	Pieza pieza = (Pieza) subasta.get("Pieza");
        	double precioFinal = subasta.getDouble("PrecioFinal");
        	String fecha = subasta.getString("Fecha");
        	Empleado empleado = (Empleado) subasta.get("Empleado");
        	Administrador admin = (Administrador) subasta.get("Administrador");
        	boolean confirmado = (boolean) subasta.getBoolean("Confirmado");
        	
        	Subasta Subasta = new Subasta(pieza, precioFinal, fecha, empleado, admin);
        	if (confirmado) { Subasta.setConfirmado(); }
        	JSONArray ofertas = (JSONArray) subasta.get("Ofertas");
        	for (Object jOferta : ofertas) 
        	{
        		String oferta = (String) jOferta;
        		Subasta.añadirOferta(oferta);
        	}
        	
        	Subasta.setTerminada();
        	
            adminp.añadirSubasta(Subasta);
        }
	}


	
}
