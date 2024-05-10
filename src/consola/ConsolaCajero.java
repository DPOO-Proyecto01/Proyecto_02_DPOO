package consola;
import inventario.modelo.Galeria;

public class ConsolaCajero {
	
	Galeria galeria;
	
	ConsolaCajero(Galeria galeria_) {
		galeria = galeria_;
	}
	
	
	
	public static void printMenu() {
		System.out.println("Consola Cajero");
	}
	
}
