package usuarios.modelo;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;

public class AdministradorUsuarios 
{	
	private HashMap<Integer, Administrador> administradores;
	private HashMap<Integer, Cajero> cajeros;
	private HashMap<Integer, Operador> operadores;
	private HashMap<Integer, Cliente> clientes;
	private static ArrayList<Integer> listaIDs = new ArrayList<Integer>();
	private static Integer ultimoID = listaIDs.get(listaIDs.size()-1);
	//En el ArrayList listaIDs a algunos integrantes les sale la opcion .getLast()
	//pero a otros no, se dejara como .get(listaIds.size()-1) para que sirva en todas 
	//las maquinas
	
	public AdministradorUsuarios() 
	{
		administradores = new HashMap<Integer,Administrador>();
		cajeros = new HashMap<Integer,Cajero>();
		operadores = new HashMap<Integer,Operador>();
		clientes = new HashMap<Integer,Cliente>();
	}
	
	public Administrador buscarAdmin(String nombre) 
	{
		//En esta funcion vamos a buscar un Administrador de acuerdo a su nombre
		
		//retorna null si no lo encuentra
		for (Entry<Integer, Administrador> entry : administradores.entrySet()) {
			
			String nombreAdmin = entry.getValue().getNombre();
			
			if (nombreAdmin.equals(nombre)) 
			{
				return entry.getValue();
			} 
			
		}
		return null;
	}
	
	public Cajero buscarCajero(String nombre) 
	{
		//En esta funcion buscamos un Cajero de acuerdo a su nombre 
		// retorna null si no lo encuentra
		for (Entry<Integer, Cajero> entry : cajeros.entrySet())
		{
			
			String nombreCajero = entry.getValue().getNombre();
			
			if (nombreCajero.equals(nombre))
			{
				return entry.getValue();
			}
		}
		return null;
	}
	
	public Operador buscarOperador(String nombre)
	{
		//En esta funcion busvcamos un Operador por su nombre
		//retorna null si no se encuentra
		for (Entry<Integer, Operador> entry : operadores.entrySet()) 
		{
			
			String nombreOperador = entry.getValue().getNombre();
			
			if (nombreOperador.equals(nombre))
			{
				return entry.getValue();
			}
		}
		return null;
	}
	
	public Cliente buscarCliente(String nombre)
	{
		//En esta funcion buscamos un cliente de acuerdo a su nombre
		// retorna null si no lo encuentra
		for (Entry<Integer, Cliente> entry : clientes.entrySet())
		{
			
			String nombreCliente = entry.getValue().getNombre();
			
			if (nombreCliente .equals(nombre))
			{
				return entry.getValue();
			}
		}
		return null;
	}
	
	public void agregarAdministrador(Administrador user)
	{
		administradores.put(user.getID(), user);
	}
	
	public void agregarCajero(Cajero user) 
	{
		cajeros.put(user.getID(), user);
	}
	
	public void agregarOperador(Operador user)
	{
		operadores.put(user.getID(), user);
	}
	
	public void agregarCliente(Cliente user)
	{
		clientes.put(user.getID(), user);
	}
	
	public void eliminarUsuario(Usuario user) 
	{
		String tipo = user.getTipo();
		if (tipo.equals("Cliente")) {
			clientes.remove(user.getID());
		} else if (tipo.equals("Cajero")) {
			cajeros.remove(user.getID());
		} else if (tipo.equals("Operadores")) {
			operadores.remove(user.getID());
		} else {
			administradores.remove(user.getID());
		}
	}

	
	public boolean logIn(String username, String password, String tipo) {
		HashMap<Integer, ? extends Usuario> mapa;
		
		if (tipo.equals("Clientes")) 
		{
			mapa = clientes;
		} else if (tipo.equals("Cajero"))
		{
			mapa = cajeros;
		} else if (tipo.equals("Operador"))
		{
			mapa = operadores;
		} else 
		{
			mapa = administradores;
		}
		
		for (Map.Entry<Integer, ? extends Usuario> entry : mapa.entrySet()) {
			if (username.equals(entry.getValue().getNombre()))
			{
				if (password.equals(entry.getValue().getContrasenia())) 
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static Integer crearID() {
	 listaIDs.add(ultimoID+1);
	 return ultimoID;
 }
	
}

