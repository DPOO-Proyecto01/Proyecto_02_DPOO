package consola;
import inventario.modelo.Galeria;
import inventario.modelo.Pieza;
import usuarios.modelo.Cliente;
import procesos.modelo.Subasta;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsolaCliente {
	
	static Galeria galeria;

	ConsolaCliente(Galeria galeria_) {
		galeria = galeria_;
	}
	
	public static void printMenu(Cliente usuario, Scanner scanner) {

		String line = "-".repeat(24);
		String accion = "";
		while (!(accion.equals("5"))) {
			System.out.println("Bienvenido!");
			System.out.println("Que desea hacer hoy?");
			System.out.println("1. Consultar perfil");
			System.out.println("2. Ver piezas en venta");
			System.out.println("3. Ver subastas");
			System.out.println("4. Consignar una pieza");
			System.out.println("5. Logout");
			accion = scanner.nextLine();
			
			if (accion.equals("1")) {
				printPerfil(usuario, scanner);
			} else if (accion.equals("2")) {
				Map<String, Pieza> piezasEnVenta = galeria.getInventario().piezasEnVenta();
				for (Map.Entry<String, Pieza> entry : piezasEnVenta.entrySet()) {
					System.out.println(line);
					System.out.println("Id de la pieza:");
					System.out.println(entry.getKey());
					System.out.println("Titulo:");
					System.out.println(entry.getValue().getTitulo());
					System.out.println("Autores:");
					System.out.println(entry.getValue().getAutores());
					System.out.println("Precio:");
					System.out.println(entry.getValue().getPrecioMinimo());
				}
				System.out.println("Si desea ver los detalles de una pieza ingrese el ID");
				System.out.println("Si no desea observar una pieza ingrese 'no'");
				String observar = scanner.nextLine();
				if (!(observar.equals("no"))) {
					Integer id = Integer.valueOf(observar);
					Pieza pieza = piezasEnVenta.get(id);
					System.out.println("Titulo:");
					System.out.println(pieza.getTitulo());
					System.out.println("Autores:");
					System.out.println(pieza.getAutores());
					System.out.println("Precio:");
					System.out.println(pieza.getPrecio());
				}
				
			} else if (accion.equals("3")) {
				Map<String, Pieza> piezasEnSubasta = galeria.getInventario().piezasParaSubasta();
				for (Map.Entry<String, Pieza> entry : piezasEnSubasta.entrySet()) {
					System.out.println(line);
					System.out.println("Id de la pieza:");
					System.out.println(entry.getKey());
					System.out.println("Titulo:");
					System.out.println(entry.getValue().getTitulo());
					System.out.println("Autores:");
					System.out.println(entry.getValue().getAutores());
					System.out.println("Precio Inicial:");
					System.out.println(entry.getValue().getPrecioMinimo());
				}
			} else if (accion.equals("4")) {
				
			}
			
		}
	}
	
	public static void printPerfil(Cliente usuario, Scanner scanner) {

		System.out.println("Username:");
		System.out.println(usuario.getNombre());
		System.out.println("Constraseña:");
		System.out.println(usuario.getContrasenia());
		System.out.println("Numero celular:");
		System.out.println(usuario.getTELEFONO());
		System.out.println("Correo:");
		System.out.println(usuario.getEMAIL());
		System.out.println("Monto:");
		System.out.println(usuario.getMonto());
		String accion = "";
		List<Pieza> listaCompras = usuario.getPiezasCompradas();

		
		while (!(accion.equals("3"))) {
			System.out.println("Que desea hacer?");
			System.out.println("1. Consultar historial de compras");
			System.out.println("2. Aumentar monto");
			System.out.println("3. Salir");
			
			accion = scanner.nextLine();
			
			if (accion.equals("1")) {
				for (Pieza pieza : listaCompras) {
					System.out.println("------------");
					System.out.println("Titulo");
					System.out.println(pieza.getTitulo());
					System.out.println("Autores:");
					System.out.println(pieza.getAutores());
					System.out.println("Precio:");
					System.out.println(pieza.getPrecio());
				}
			} else if (accion.equals("2")) {
				System.out.println("Cuanto desea agregarle al monto?");
				String numero = scanner.nextLine();
				Double cantidad = Double.valueOf(numero);
				
				double monto = usuario.getMonto();
				
				usuario.setMonto(monto+cantidad);
			}
		}
	}
}
