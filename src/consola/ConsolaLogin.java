package consola;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import usuarios.modelo.*;
import inventario.modelo.*;
import procesos.modelo.AdministradorProcesos;
import galeria.persistencia.*;

public class ConsolaLogin {
	HashMap<Integer, Cliente> clientes;
	HashMap<Integer, Cajero> cajeros;
	HashMap<Integer, Operador> operadores;
	HashMap<Integer, Administrador> administradores;
	static Galeria galeria;
	static Inventario inventario;
	static AdministradorProcesos adminProcesos;
	static AdministradorUsuarios adminUsuarios;
	static String ruta;
	
	
	public ConsolaLogin() throws IOException{
		clientes = new HashMap<Integer, Cliente>();
		cajeros = new HashMap<Integer, Cajero>();
		operadores = new HashMap<Integer, Operador>();
		administradores = new HashMap<Integer, Administrador>();
		
		
	}
	
	private static Galeria cargaDeDatos() throws IOException {
		
		inventario = new Inventario();
		adminProcesos = new AdministradorProcesos();
		adminUsuarios = new AdministradorUsuarios();
		galeria = new Galeria(inventario, adminProcesos, adminUsuarios);
		
		CentralPersistencia.getPersistenciaUsuarios().cargarUsuarios("/Users/andrestello/Desktop/DPOOProyecto2/Proyecto_02_DPOO/src/data/usuarios.json", galeria);
		CentralPersistencia.getPersistenciaInventario().cargarInventario("/Users/andrestello/Desktop/DPOOProyecto2/Proyecto_02_DPOO/src/data/inventario.json", galeria);
		CentralPersistencia.getPersistenciaProcesos().cargarProcesos("/Users/andrestello/Desktop/DPOOProyecto2/Proyecto_02_DPOO/src/data/procesos.json", galeria);

		
		return galeria;
	}
	
	public static void printMenu() throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		String accion = "0";
		
		CentralPersistencia.getPersistenciaInventario().guardarInventario("/Users/andrestello/Desktop/DPOOProyecto2/Proyecto_02_DPOO/src/data/inventario.json", galeria);
		CentralPersistencia.getPersistenciaProcesos().guardarProcesos("/Users/andrestello/Desktop/DPOOProyecto2/Proyecto_02_DPOO/src/data/procesos.json", galeria);
		CentralPersistencia.getPersistenciaUsuarios().guardarUsuarios("/Users/andrestello/Desktop/DPOOProyecto2/Proyecto_02_DPOO/src/data/usuarios.json", galeria);
		
		while (!(accion.equals("3"))) {
			System.out.println("Bienvenido a nuestra galeria!");
			System.out.println("Que desea hacer?");
			System.out.println("1. Login");
			System.out.println("2. Registrarse");
			System.out.println("3. Cerrar la aplicacion");
			accion = scanner.nextLine();
			
			if (accion.equals("1")) {
				Login(scanner);
			} else if (accion.equals("2")) {
				Registrar(scanner);
			} 
			
		}
		scanner.close();
	}
	
	public static void Login(Scanner scanner) throws FileNotFoundException {
		
		System.out.println("Por favor ingrese su tipo de usuario para hacer Login:");
		System.out.println("1. Soy un Cliente");
		System.out.println("2. Soy un Cajero");
		System.out.println("3. Soy un Operador");
		System.out.println("4. Soy un Administrador");
		String tipo1 = scanner.nextLine();
		
		System.out.println("Username:");
		String username = scanner.nextLine();
		
		System.out.println("Contraseña:");
		String contrasena = scanner.nextLine();
		String tipo = null;
		if (tipo1.equals("1")) {
			tipo = "Cliente";
		} else if (tipo1.equals("2")) {
			tipo = "Cajero";
		} else if (tipo1.equals("3")) {
			tipo = "Operado";
		} else if (tipo1.equals("3")) {
			tipo = "Administrador";
		}
		
		if (galeria.getAdminUsuarios().logIn(username, contrasena, tipo)) {
			if (tipo1.equals("1")) {
				Cliente usuario = galeria.getAdminUsuarios().buscarCliente(username);
				ConsolaCliente.printMenu(usuario, scanner);
			} else if (tipo1.equals("2")) {
				Cajero cajero = galeria.getAdminUsuarios().buscarCajero(username);
				ConsolaCajero.printMenu(cajero, scanner);
			} else if (tipo1.equals("3")) {
				Operador operador = galeria.getAdminUsuarios().buscarOperador(username);
				ConsolaOperador.printMenu(operador, scanner);
			} else if (tipo1.equals("4")) {
				ConsolaAdministrador.printMenu();
			}
		}
	}
	
	public static void Registrar(Scanner scanner) {

		System.out.println("Username:");
		String username = scanner.nextLine();
		
		System.out.println("Contraseña:");
		String contrasenia = scanner.nextLine();
		
		System.out.println("Ingrese el tipo de perfil que desea crear:");
		System.out.println("1. Cliente");
		System.out.println("2. Cajero");
		System.out.println("3. Operador");
		System.out.println("4. Administrador");
		String tipo = scanner.nextLine();
		
		if (tipo.equals("1")) {
			System.out.println("Numero de telefono:");
			String telefono1 = scanner.nextLine();
			Integer telefono = Integer.valueOf(telefono1);
			
			System.out.println("Ingrese su correo electronico:");
			String email = scanner.nextLine();
			
			
			Cliente cliente = new Cliente(username, contrasenia, "", telefono, email, null);
			galeria.getAdminUsuarios().agregarCliente(cliente);
			
		} else if (tipo.equals("2")) {
			Cajero cajero = new Cajero(username, contrasenia, "", "Cajero", null);
			galeria.getAdminUsuarios().agregarCajero(cajero);
			
		} else if (tipo.equals("3")) {
			Operador operador = new Operador(username, contrasenia, "", "Operador", null);
			galeria.getAdminUsuarios().agregarOperador(operador);
			
		} else if (tipo.equals("4")) {
			Administrador admin = new Administrador(username, contrasenia, "", null);
			galeria.getAdminUsuarios().agregarAdministrador(admin);
		}
	}
	
	public static void main(String[] args) throws IOException {
		galeria = cargaDeDatos();
		printMenu();
		
		
	}
	
	
	
}
