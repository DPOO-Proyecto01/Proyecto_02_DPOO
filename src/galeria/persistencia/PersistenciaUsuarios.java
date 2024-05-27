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
import usuarios.modelo.Administrador;
import usuarios.modelo.AdministradorUsuarios;
import usuarios.modelo.Cajero;
import usuarios.modelo.Cliente;
import usuarios.modelo.Empleado;
import usuarios.modelo.Operador;

public class PersistenciaUsuarios 
{
	public void cargarUsuarios (String archivo, Galeria galeria) throws IOException
	{
		
		String jsonCompleto = new String( Files.readAllBytes( Path.of(archivo)));
        JSONObject raiz = new JSONObject( jsonCompleto );
		AdministradorUsuarios adminU = galeria.getAdminUsuarios();


        cargarIds( adminU, raiz.getJSONArray( "ListaIds" ) );
        cargarOperadores( adminU, raiz.getJSONArray( "Operadores" ) );
        cargarClientes( adminU, raiz.getJSONArray( "Clientes" ) );
        cargarAdministradores( adminU, raiz.getJSONArray( "Administradores" ) );
        cargarCajeros( adminU, raiz.getJSONArray( "Cajeros" ) );
		
	}
	
	
	private void cargarCajeros(AdministradorUsuarios adminU, JSONArray jCajeros) 
	{
		int numCajeros = jCajeros.length( );
        for( int i = 0; i < numCajeros; i++ ) 
        {
        	JSONObject JCajero = jCajeros.getJSONObject( i );
        	
        	String nombre = JCajero.getString("Nombre");
        	String contraseña = JCajero.getString("Contraseña");
			String tipo = JCajero.getString("Tipo");
        	String rol = JCajero.getString("Rol");
        	Integer ID =JCajero.getInt("Id");
        	
        	Cajero cajero = new Cajero(nombre, contraseña, tipo, rol, ID);
            adminU.agregarCajero(cajero);
        }
	}


	private void cargarAdministradores(AdministradorUsuarios adminU, JSONArray jAdmins) 
	{
		int numAdmins = jAdmins.length( );
        for( int i = 0; i < numAdmins; i++ ) 
        {
        	JSONObject Admin = jAdmins.getJSONObject( i );
        	
        	String nombre = Admin.getString("Nombre");
        	String contraseña = Admin.getString("Contraseña");
			String tipo = Admin.getString("Tipo");
        	Integer ID = Admin.getInt("Id");
        	
        	Administrador admin = new Administrador(nombre, contraseña, tipo, ID);
            adminU.agregarAdministrador(admin);
        }
		
	}


	private void cargarClientes(AdministradorUsuarios adminU, JSONArray jClientes) 
	{
		int numClientes = jClientes.length( );
        for( int i = 0; i < numClientes; i++ ) 
        {
        	JSONObject JCliente = jClientes.getJSONObject( i );
        	
        	String nombre = JCliente.getString("Nombre");
        	String contraseña = JCliente.getString("Contraseña");
			String tipo = JCliente.getString("Tipo");
			int telefono = JCliente.getInt("Telefono");
			String email = JCliente.getString("Email");
        	Integer ID =JCliente.getInt("Id");
        	double monto = JCliente.getDouble("Monto");
        	
        	Cliente cliente = new Cliente(nombre, contraseña, tipo, telefono, email, ID);
        	
        	cliente.setMonto(monto);
        	
        	JSONArray piezasCompradas = (JSONArray) JCliente.get("PiezasCompradas");
        	for (Object Opieza : piezasCompradas) 
        	{
        		Pieza pieza = (Pieza) Opieza;
        		cliente.añadirPiezaComprada(pieza);
        	}
        	
            adminU.agregarCliente(cliente);
        }
		
	}


	private void cargarOperadores(AdministradorUsuarios adminU, JSONArray jOperadores) 
	{
		int numOps = jOperadores.length( );
        for( int i = 0; i < numOps; i++ ) 
        {
        	JSONObject JOp = jOperadores.getJSONObject( i );
        	
        	String nombre = JOp.getString("Nombre");
        	String contraseña = JOp.getString("Contraseña");
			String tipo = JOp.getString("Tipo");
        	String rol = JOp.getString("Rol");
        	Integer ID =JOp.getInt("Id");
        	
        	Operador operador = new Operador(nombre, contraseña, tipo, rol, ID);
            adminU.agregarOperador(operador);
        }
		
	}


	private void cargarIds(AdministradorUsuarios adminU, JSONArray jIds)
	{
		int numIds = jIds.length( );
        for( int i = 0; i < numIds; i++ ) 
        {
        	Integer Id = jIds.getInt( i );
        	adminU.agregarID(Id);
        }
		
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
        for( Integer Id : AdministradorUsuarios.getListaIDs() )
        {
            jIds.put( Id );
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
			
			JSONArray jPiezas = new JSONArray( );
			ArrayList<Pieza> piezasCompradas = cliente.getPiezasCompradas();
			for (Pieza pieza : piezasCompradas) 
			{
				jPiezas.put (pieza);
			}
			jCliente.put( "PiezasCompradas", jPiezas );
			
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
