package consola;
import inventario.modelo.*;
import procesos.modelo.*;
import usuarios.modelo.*;
import galeria.persistencia.CentralPersistencia;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsolaCajero {
	
	static Galeria galeria;
	
	ConsolaCajero(Galeria galeria_) {
		galeria = galeria_;
	}
	
	
	
	public static void printMenu(Cajero cajero) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		String accion = "";
		while (!(accion.equals("2"))) {
			System.out.println("-".repeat(24));
			System.out.println("Bienvenido " + cajero.getNombre() + "!");
			System.out.println("¿Que desea hacer?");
			System.out.println("1. Registrar una venta:");
			System.out.println("2. Logout");
			accion = scanner.nextLine();
			
			if (accion.equals("1")) {
				System.out.println("Ingrese el id de la pieza");
				String id = scanner.nextLine();
				Pieza pieza = galeria.getInventario().buscarPieza(id);
				System.out.println("Ingrese el usuario del comprador:");
				String username = scanner.nextLine();
				Cliente cliente = galeria.getAdminUsuarios().buscarCliente(username);
				System.out.println("Ingrese el metodo de pago:");
				String metodo = scanner.nextLine();
				System.out.println("Ingrese el administrador que confirmo la venta:");
				String nomAdmin = scanner.nextLine();
				Administrador admin = galeria.getAdminUsuarios().buscarAdmin(nomAdmin);
				Venta venta = new Venta(pieza, pieza.getPrecio(), cajero, admin, metodo, cliente);
				galeria.getAdminProcesos().añadirVenta(venta);
				
			}
		
		}
		CentralPersistencia.getPersistenciaProcesos().guardarProcesos("./data/procesos", galeria);
		scanner.close();
	}




	
	
}
