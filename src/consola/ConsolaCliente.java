package consola;
import inventario.modelo.Escultura;
import inventario.modelo.Galeria;
import inventario.modelo.Pieza;
import inventario.modelo.Pintura;
import inventario.modelo.Video;
import usuarios.modelo.Cliente;
import procesos.modelo.Subasta;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsolaCliente {
	


	ConsolaCliente(Galeria galeria_) {

	}
	
	public static void printMenu(Cliente usuario, Scanner scanner, Galeria galeria) {

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
					Pieza pieza = piezasEnVenta.get(observar);
					System.out.println("Fecha:");
					System.out.println(pieza.getFecha());
					System.out.println("Origen:");
					System.out.println(pieza.getOrigen());
					System.out.println("Descripcion:");
					System.out.println(pieza.getDescripcion());
					System.out.println("Fecha de Vencimiento de Consignacion:");
					System.out.println(pieza.getFechaDePrestamo());
				
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
					
				
				System.out.println("Si desea ver los detalles de una pieza ingrese el ID");
				System.out.println("Si no desea observar una pieza ingrese 'no'");
				String obs = scanner.nextLine();
				if (!(obs.equals("no"))) {
					Pieza pieza = piezasEnSubasta.get(obs);
					Subasta subasta = galeria.getAdminProcesos().getSubastasEnProceso().get(pieza);
					System.out.println("Ultima oferta:");
					System.out.println(subasta.getPrecioFinal());
					System.out.println("Origen:");
					System.out.println(pieza.getOrigen());
					System.out.println("Descripcion:");
					System.out.println(pieza.getDescripcion());
					System.out.println("Fecha de Vencimiento de Consignacion:");
					System.out.println(pieza.getFechaDePrestamo());
					}
				}
				
			} else if (accion.equals("4")) {
				System.out.println("=".repeat(24));
				System.out.println("Antes de iniciar el proceso. debria conocer la siguiente informacion de antemano:");
				System.out.println("Los autores y autoras de la pieza");
				System.out.println("Fecha de creacion");
				System.out.println("Origen geografico de la pieza");
				System.out.println("Una Descripcion de la pieza");
				System.out.println("El precio minimo por el que desea venderla");
				System.out.println("La fehca de vencimiento de la consignacion que usted desea");
				System.out.println("El titulo de la pieza");
				
				System.out.println();
				System.out.println("-".repeat(24));
				System.out.println("Si su pieza es una pintura o cumple con el formato de un lienzo:");
				System.out.println("La tecnica usada:");
				System.out.println("Las dimensiones de alto y ancho (x,y) en centimetros");
				System.out.println("");
				
				System.out.println();
				System.out.println("-".repeat(24));
				System.out.println("Si su pieza es un video o es de naturaleza digital:");
				System.out.println("Tamaño en Gigabytes");
				System.out.println("La duracion en segundos");
				System.out.println("Detalles de la instalacion (Conectividad al internet, pantalla o proyector, etc.)");
				
				System.out.println();
				System.out.println("-".repeat(24));
				System.out.println("Si su pieza es un arte plastica tridimensional:");
				System.out.println("Los materiales que se usaron para su construccion");
				System.out.println("Dimensiones en centimetros");
				System.out.println("Peso en kilogramos");
				System.out.println("Detalles de la instalacion (un espacio particular de la galeria, materiales, mantenimiento, medidas de seguridad, etc))");
				
				System.out.println("Si tiene la informacion relevante escriba 'si'");
				System.out.println("Si no escriba 'no'");
				String decision = scanner.nextLine();
				
				if (decision.equals("si")) {
					
					System.out.println("De acuerdo con la descripcion anterior que tipo de pieza va a consignar?");
					System.out.println("1. Pintura");
					System.out.println("2. Video");
					System.out.println("3. Escultura");
					String tipo = scanner.nextLine();
					
					System.out.println("Autores de la pieza:");
					String autores = scanner.nextLine();
					
					System.out.println("Ingrese la fecha de creacion de la pieza");
					System.out.println("en el formato AAAA/MM/DD");
					String fecha = scanner.nextLine();
					
					System.out.println("Ingrese la zona geografica de donde origina la pieza:");
					String origen = scanner.nextLine();
					
					System.out.println("Ingrese una descripcion corta de la pieza:");
					String descripcion = scanner.nextLine();
					
					System.out.println("Ingrese el precio MINIMO al que quiere que se venda la pieza:");
					System.out.println("Por favor entienda que este es el precio al que comenzara su pieza en la aplicacion");
					double precioMinimo = Double.valueOf(scanner.nextLine());
					
					System.out.println("Ingrese la fecha de vencimiento de la consignacion:");
					System.out.println("Si llega esta fecha y la pieza no se ha vendido esta se devolvera al cliente");
					System.out.println("Use el formato AAAA/MM/DD");
					String fechaVencimiento = scanner.nextLine();
					
					System.out.println("Ingrese el titulo de la pieza");
					String titulo = scanner.nextLine();
					
					if (tipo.equals("1") ) {
						System.out.println("Ingrese las tecnicas que se usaron en la creacioin de la pieza:");
						String tecnica = scanner.nextLine();
						
						System.out.println("Ingrese del ancho del lienso en centimetros:");
						String x = scanner.nextLine();
						System.out.println("Ingrese la altura del lienzo en centimetros:");
						String y = scanner.nextLine();
						String dimensiones = x+"x"+y;
						
						Pintura pintura = new Pintura(autores, fecha, origen, descripcion, precioMinimo, fechaVencimiento, titulo, precioMinimo, tecnica, dimensiones);
						pintura.cambiarDisponibilidad();
						 System.out.println("Si desea que su pieza se subaste en vez de venderse al precio que indico, escriba 'si'");
						 String subastable = scanner.nextLine();
						 
						 if (subastable.equals("si")) {
							 pintura.cambiarSubastable();
						 }
						 galeria.getInventario().agregarPieza(pintura);
						 
					} else if (tipo.equals("2")) {
						System.out.println("Ingrese el tamaño en Gigabytes del archivo:");
						double tamano = Double.valueOf(scanner.nextLine());
						
						System.out.println("Ingrese la duracion del archivo (si no tiene duracion como una fota ingrese 0)");
						double duracion = Double.valueOf(scanner.nextLine());
						
						System.out.println("Ingrese los detalles de la instalacion de la pieza");
						String instalacion = scanner.nextLine();
						
						Video video = new Video(autores, fecha, origen, descripcion, precioMinimo, fechaVencimiento, titulo, precioMinimo, tamano, duracion, instalacion);
						video.cambiarDisponibilidad();
						
						System.out.println("Si desea que su pieza se subaste en vez de venderse al precio que indico, escriba 'si'");
						 String subastable = scanner.nextLine();
						 
						 if (subastable.equals("si")) {
							 video.cambiarSubastable();
						 }
						 galeria.getInventario().agregarPieza(video);
					} else if (tipo.equals("3")) {
						System.out.println("Ingrese los materiales usados en la pieza");
						String material = scanner.nextLine();
						
						System.out.println("Ingrese el largo de la pieza en cm:");
						String largo = scanner.nextLine();
						System.out.println("Ingrese la altura de la pieza en cm:");
						String alto = scanner.nextLine();
						System.out.println("Ingrese el grosor de la pieza en cm");
						String grosor = scanner.nextLine();
						String dimensiones = largo + "x" + alto + "x" + grosor;
						
						System.out.println("Ingrese el peso de la pieza en kilogramos:");
						double peso = Double.valueOf(scanner.nextLine());
						
						System.out.println("Ingrese los detalles de la instalacion:");
						String instalacion = scanner.nextLine();
						
						Escultura escultura = new Escultura(autores, fecha, origen, descripcion, precioMinimo, fechaVencimiento, titulo, precioMinimo, material, dimensiones, peso, instalacion);
						escultura.cambiarDisponibilidad();
						
						System.out.println("Si desea que su pieza se subaste en vez de venderse al precio que indico, escriba 'si'");
						 String subastable = scanner.nextLine();
						 
						 if (subastable.equals("si")) {
							 escultura.cambiarSubastable();
						 }
						 galeria.getInventario().agregarPieza(escultura);
					}
							
							
				}
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
