package consola;
import java.util.Scanner;

import inventario.modelo.Galeria;
import usuarios.modelo.Administrador;
import usuarios.modelo.Cliente;
import procesos.modelo.*;
import java.util.List;

public class ConsolaAdministrador {
	

	
	ConsolaAdministrador(Galeria galeria_) {

	}
	
	

	public static void printMenu(Administrador administrador, Scanner scanner, Galeria galeria) {
		// TODO Auto-generated method stub
		System.out.println("=".repeat(24));
		String accion = "";
		while (!(accion.equals("3"))) {
			System.out.println("Bienvenido!");
			System.out.println("Que desea hacer hoy?");
			System.out.println("1. Verificar usuarios");
			System.out.println("2. Confirmar procesos");
			System.out.println("3. Salir");
			accion = scanner.nextLine();
			
			if (accion.equals("1")) {
				System.out.println("Ingrese el nombre del Cliente:");
				String username = scanner.nextLine();
				Cliente cliente = galeria.getAdminUsuarios().buscarCliente(username);
				cliente.setVerificar();
				
			} else if (accion.equals("2")) {
				System.out.println("Que desea confirmar?");
				System.out.println("1. Ventas");
				System.out.println("2. Subastas");
				accion = scanner.nextLine();
				
				
				if (accion.equals("1")) {
					int i = 0;
					List<Venta> ventas = galeria.getAdminProcesos().getVentasSinConfirmar();
					while (i>=0 && i<ventas.size()-1) {
						Venta venta = ventas.get(i);
						System.out.println("=".repeat(24));
						System.out.println("Titulo");
						System.out.println(venta.getPieza().getTitulo());
						System.out.println("Autor");
						System.out.println(venta.getPieza().getAutores());
						System.out.println("Id de la pieza:");
						System.out.println(venta.getPieza().getId());
						System.out.println("Precio minimo : Precio de venta");
						System.out.println(String.valueOf(venta.getPieza().getPrecioMinimo()) + " : " + String.valueOf(venta.getPrecio()));
						System.out.println("Id de cliente");
						Integer idCliente = venta.getIdComprador();
						System.out.println(idCliente);
						System.out.println("Monto actual");
						Cliente cliente = galeria.getAdminUsuarios().buscarClientePorId(idCliente);
						System.out.println(cliente.getMonto());
						System.out.println("Nombre de Empleado : Id de empleado");
						System.out.println(venta.getEmpleado().getNombre() + " : " + venta.getEmpleado().getID());
						System.out.println("Medio de pago:");
						System.out.println(venta.getMedioDePago());
						System.out.println("=".repeat(24));
						
						System.out.println("Desea confirmar esta venta?");
						System.out.println("1. Si");
						System.out.println("2. No");
						System.out.println("3. Salir");
						String con = scanner.nextLine();
						
						if (con.equals("1")) {
							venta.setConfirmado();
							double montoRestante = cliente.getMonto() - venta.getPrecio();
							cliente.setMonto(montoRestante);
						} else if (con.equals("3")) {
							i = -1;
						}
						i++;
					}
					
				}
				else if (accion.equals("2")) {
					int x = 0;
					List<Subasta> subastas = galeria.getAdminProcesos().getSubastasSinConfirmar();
					while (x>=0 && x<subastas.size()) {
						Subasta subasta = subastas.get(x);
						
						System.out.println("Titulo:");
						System.out.println(subasta.getPieza().getTitulo());
						System.out.println("Autor");
						System.out.println(subasta.getPieza().getAutores());
						System.out.println("Id de la pieza:");
						System.out.println(subasta.getPieza().getId());
						System.out.println("Precio minimo : Precio final");
						System.out.println(subasta.getPieza().getPrecio() + " : " + subasta.getPrecioFinal());
						System.out.println("Id de cliente");
						System.out.println(subasta.getIdComprador());
						System.out.println("Monto actual");
						Cliente cliente = galeria.getAdminUsuarios().buscarClientePorId(subasta.getIdComprador());
						System.out.println(cliente.getMonto());
						System.out.println("Nombre empleado : Id empleado");
						System.out.println(subasta.getEmpleado().getNombre() + " : " + subasta.getEmpleado().getID());
						
						System.out.println("Desea confirmar esta subasta?");
						System.out.println("1. Si");
						System.out.println("2. No");
						System.out.println("3. Salir");
						String con1 = scanner.nextLine();
						
						if (con1.equals("1")) {
							subasta.setConfirmado();
							double montoRestante = cliente.getMonto() - subasta.getPrecioFinal();
							cliente.setMonto(montoRestante);
							} else if (con1.equals("3")) {
							x = -1;
						}
						
						x++;
					}
				}
			}
			
		}
		
	}

}
