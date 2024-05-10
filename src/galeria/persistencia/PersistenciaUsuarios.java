package galeria.persistencia;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import inventario.modelo.Galeria;
import usuarios.modelo.Administrador;
import usuarios.modelo.AdministradorUsuarios;
import usuarios.modelo.Cajero;
import usuarios.modelo.Cliente;
import usuarios.modelo.Operador;

public class PersistenciaUsuarios 
{
	public void cargarUsuarios (String archivo, Galeria galeria) throws FileNotFoundException
	{
		
		
	}
	
	
	public void guardarUsuarios (String archivo, Galeria galeria) throws FileNotFoundException
	{
		AdministradorUsuarios adminU = galeria.getAdminUsuarios();
		JSONObject jobject = new JSONObject( );

        //guardar administradores
        guardarAdministradores( adminU, jobject );

        //Guardar cajeros
        guardarCajeros( adminU, jobject );
        
      //Guardar clientes
        guardarClientes( adminU, jobject );
        
      //Guardar operadores
        guardarOperadores( adminU, jobject );
        
      //Guardar IDs
        guardarIDs( adminU, jobject );

        // Escribir la estructura JSON en un archivo
        PrintWriter pw;
			pw = new PrintWriter(archivo);
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	
	private void guardarIDs(AdministradorUsuarios adminU, JSONObject jobject) 
	{
		JSONArray jIds = new JSONArray( );
        for( Integer Ids : AdministradorUsuarios.getListaIDs() )
        {
            jIds.put( Ids );
        }
        jobject.put( "ListaIds", jIds );
		
	}


	private void guardarOperadores(AdministradorUsuarios adminU, JSONObject jobject) 
	{
		JSONArray jOperadores = new JSONArray( );
		Map<Integer,Operador> OperadoresId = adminU.getOperadores();
		for (Operador operador : OperadoresId.values()) 
		{
			JSONObject jOperador = new JSONObject( );
            jOperador.put( "Nombre", operador.getNombre() );
            jOperador.put( "Contraseña", operador.getContrasenia());
            jOperador.put( "Tipo", "Operador" );
            jOperador.put( "Rol", operador.getRol() );
            jOperador.put( "Id", operador.getID() );

            jOperadores.put( jOperador );
        }
        jobject.put( "Operadores", jOperadores );
		
	}


	private void guardarClientes(AdministradorUsuarios adminU, JSONObject jobject) 
	{
		JSONArray jClientes = new JSONArray( );
		Map<Integer,Cliente> Clientes = adminU.getClientes();
		for (Cliente cliente : Clientes.values()) 
		{
			JSONObject jCliente = new JSONObject( );
			jCliente.put( "Nombre", cliente.getNombre() );
			jCliente.put( "Contraseña", cliente.getContrasenia());
			jCliente.put( "Tipo", "Cliente" );
			jCliente.put( "Telefono", cliente.getTELEFONO() );
			jCliente.put( "Email", cliente.getEMAIL() );
			jCliente.put( "Id", cliente.getID() );
			jCliente.put( "Monto", cliente.getMonto() );
			jCliente.put( "PiezasCompradas", cliente.getPiezasCompradas() );

            jClientes.put( jCliente );
        }
        jobject.put( "Clientes", jClientes );
		
	}


	private void guardarCajeros(AdministradorUsuarios adminU, JSONObject jobject) 
	{
		JSONArray jCajeros = new JSONArray( );
		Map<Integer,Cajero> Cajeros = adminU.getCajeros();
		for (Cajero cajero : Cajeros.values()) 
		{
			JSONObject jCajero = new JSONObject( );
			jCajero.put( "Nombre", cajero.getNombre() );
			jCajero.put( "Contraseña", cajero.getContrasenia());
			jCajero.put( "Tipo", "Cajero" );
			jCajero.put( "Rol", cajero.getRol() );
			jCajero.put( "Id", cajero.getID() );

            jCajeros.put( jCajero );
        }
        jobject.put( "Cajeros", jCajeros );
		
	}


	private void guardarAdministradores(AdministradorUsuarios adminU, JSONObject jobject) 
	{
		JSONArray jAdmins = new JSONArray( );
		Map<Integer,Administrador> Admins = adminU.getAdministradores();
		for (Administrador admin : Admins.values()) 
		{
			JSONObject jAdmin = new JSONObject( );
			jAdmin.put( "Nombre", admin.getNombre() );
			jAdmin.put( "Contraseña", admin.getContrasenia());
			jAdmin.put( "Tipo", "Administrador" );
			jAdmin.put( "Id", admin.getID() );

            jAdmins.put( jAdmin );
        }
        jobject.put( "Administradores", jAdmins );
		
	}
}
