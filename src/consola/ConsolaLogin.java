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
	
	
	public ConsolaLogin(Galeria galeria) throws IOException{
		clientes = new HashMap<Integer, Cliente>();
		cajeros = new HashMap<Integer, Cajero>();
		operadores = new HashMap<Integer, Operador>();
		administradores = new HashMap<Integer, Administrador>();
		
		inventario = new Inventario();
		adminProcesos = new AdministradorProcesos();
		adminUsuarios = new AdministradorUsuarios();
		galeria = new Galeria(inventario, adminProcesos, adminUsuarios);
		
		CentralPersistencia.getPersistenciaUsuarios().cargarUsuarios("./data/usuarios", galeria);
		CentralPersistencia.getPersistenciaProcesos().cargarProcesos("./data/procesos", galeria);
		CentralPersistencia.getPersistenciaInventario().cargarInventario("./data/inventario", galeria);
	}
	
	public static void printMenu() throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		String accion = "0";
		while (!(accion.equals("3"))) {
			System.out.println("Bienvenido a nuestra galeria!");
			System.out.println("Que desea hacer?");
			System.out.println("1. Login");
			System.out.println("2. Registrarse");
			System.out.println("3. Cerrar la aplicacion");
			accion = scanner.nextLine();
			
			if (accion.equals("1")) {
				Login();
			} else if (accion.equals("2")) {
				Registrar();
			} 
			CentralPersistencia.getPersistenciaInventario().guardarInventario("./data/inventario", galeria);
			CentralPersistencia.getPersistenciaProcesos().guardarProcesos("./data/procesos", galeria);
			CentralPersistencia.getPersistenciaUsuarios().guardarUsuarios("./data/usuarios", galeria);
		}
		scanner.close();
	}
	
	public static void Login() throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Por favor ingrese su tipo de usuario para hacer Login:");
		System.out.println("1. Soy un Cliente");
		System.out.println("2. Soy un Cajero");
		System.out.println("3. Soy un Operador");
		System.out.println("4. Soy un Administrador");
		String tipo = scanner.nextLine();
		
		System.out.println("Username:");
		String username = scanner.nextLine();
		
		System.out.println("Contraseña:");
		String contrasena = scanner.nextLine();
		
		if (galeria.getAdminUsuarios().logIn(username, contrasena, tipo)) {
			if (tipo.equals("1")) {
				Cliente usuario = galeria.getAdminUsuarios().buscarCliente(username);
				ConsolaCliente.printMenu(usuario);
			} else if (tipo.equals("2")) {
				Cajero cajero = galeria.getAdminUsuarios().buscarCajero(username);
				ConsolaCajero.printMenu(cajero);
			} else if (tipo.equals("3")) {
				Operador operador = galeria.getAdminUsuarios().buscarOperador(username);
				ConsolaOperador.printMenu(operador);
			} else if (tipo.equals("4")) {
				ConsolaAdministrador.printMenu();
			}
		}
		scanner.close();
	}
	
	public static void Registrar() {
		Scanner scanner = new Scanner(System.in);
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
			int telefono1 = scanner.nextInt();
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
		
		scanner.close();
	}
	
	public static void main(String[] args) throws IOException {
		ConsolaLogin consolaLogin = new ConsolaLogin(galeria);
		
		printMenu(); 
		
	}
	
	
	
}
