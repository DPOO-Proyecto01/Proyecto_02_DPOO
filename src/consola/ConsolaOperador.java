package consola;
import inventario.modelo.*;
import usuarios.modelo.*;
import procesos.modelo.*;
import galeria.persistencia.CentralPersistencia;

import java.util.Scanner;
import java.util.Set;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsolaOperador {
	
	static Galeria galeria;
	
	ConsolaOperador(Galeria galeria_) {
		galeria = galeria_;
	}
	

	public static void printMenu(Operador operador, Scanner scanner) throws FileNotFoundException {

		System.out.println("Bienvenido " + operador.getNombre() + "!");
		String accion = "";
		while (!(accion.equals("2"))) {
			System.out.println("¿Que desea hacer?");
			System.out.println("1. Registrar nueva oferta");
			System.out.println("2. Logout");
			
			if (accion.equals("1")) {
				System.out.println("Ingrese el ID de la pieza de la subasta:");
				String ID = scanner.nextLine();
				Pieza pieza = galeria.getInventario().buscarPieza(ID);
				Set<Pieza>piezasEnSubasta = galeria.getAdminProcesos().getSubastasEnProceso().keySet();
				
				if (piezasEnSubasta.contains(pieza)) {
					Subasta subasta = galeria.getAdminProcesos().getSubastasEnProceso().get(pieza);
					System.out.println("Subasta encontrada:");
					System.out.println("La oferta actual es: " + subasta.getPrecioFinal());
					System.out.println("Cual es la nueva oferta: ");
					double oferta = Double.valueOf(scanner.nextLine());
					System.out.println("Ingrese el username del Cliente: ");
					String username = scanner.nextLine();
					if (oferta > subasta.getPrecioFinal()) {
						LocalDateTime dateTime = LocalDateTime.now();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						String formattedDateTime = dateTime.format(formatter);
						
						subasta.añadirOferta(formattedDateTime + "-" + String.valueOf(oferta) + "-" + username);
					}
				}
			}
		}
		CentralPersistencia.getPersistenciaProcesos().guardarProcesos("./data/procesos", galeria);
	}


	

}
