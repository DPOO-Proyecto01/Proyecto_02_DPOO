package galeria.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import inventario.modelo.Escultura;
import inventario.modelo.Galeria;
import inventario.modelo.Pieza;
import inventario.modelo.Pintura;
import inventario.modelo.Video;

public class PersistenciaInventario 
{
	
	public void guardarInventario (String archivo)
	{
		
	}
	
	public void cargarInventario (String archivo, Galeria galeria) throws IOException
	{
		//Este es el archivo completo
		String jsonCompleto = new String (Files.readAllBytes(new File(archivo).toPath( ) ));
		//Acá se sigue con la lectura de un objeto (en los JSON se leen objetos)
		JSONObject root = new JSONObject(jsonCompleto);
		//Aquí se obtiene la parte del objeto con la info de las piezas (es como obtener lo que está en la llave piezas de un diccionario
		cargarPiezas(galeria,root.getJSONArray("Piezas"));
	}
	
	public void cargarPiezas (Galeria galeria, JSONArray jPiezas)
	{
		//Se obtiene el tamaño del JSONArray que es igual al número de piezas
		int numPiezas = jPiezas.length();
		
		//El ciclo hace que se obtenga la información de las piezas y se creen las piezas para luego ser agregadas
		for (int i = 0; i<numPiezas; i++)
		{
			JSONObject pieza = jPiezas.getJSONObject(i);
			String tipoPieza = pieza.getString("tipo");
			Pieza nuevaPieza = null;
			
			//Se usan tres métodos diferentes para crear la pieza dependiendo de su tipo
			if (tipoPieza.equals("Escultura"))
			{
				nuevaPieza = cargarEscultura(pieza, nuevaPieza);
			}
			
			else if (tipoPieza.equals("Pintura"))
			{
				nuevaPieza = cargarPintura(pieza,nuevaPieza);
			}
			else
			{
				nuevaPieza = cargarVideo(pieza,nuevaPieza);
			}
			
			
			//Se revisa si el Id de la pieza ya existe(si ya existe el id entonces la pieza ya fue agregada)
			//Básicamente si revisa si una pieza ya fue agregada o no para no tener copias inecesarias
			if (!galeria.existeId(nuevaPieza.getId()))
			{
				galeria.agregarPieza(nuevaPieza);
			}
		}
	}
	
	public Pieza cargarEscultura (JSONObject jPieza, Pieza nuevaPieza)
	{
		/**Se obtienen todos los atributos de la pieza como si se obtuvieran los valores
		asociados a unas llaves en diccionarios. La llaves serían los Strings dentro del
		getString. La idea es que la función de carga use las mismas llaves.**/
		
		String id = jPieza.getString("id");
		String autores = jPieza.getString("autor");
		String fecha = jPieza.getString("fecha");
		String origen = jPieza.getString("origen");
		String descripcion = jPieza.getString("descripcion");
		String titulo = jPieza.getString("titulo");
		double precio = jPieza.getDouble("precio");
		double precioMin = jPieza.getDouble("precioMinimo");
		String fechaP = jPieza.getString("fechaPrestamo");
		String material = jPieza.getString("material");
		String dimensiones = jPieza.getString("dimensiones");
		double peso = jPieza.getDouble("peso");
		String instalacion = jPieza.getString("instalacion");
		boolean disponible = jPieza.getBoolean("disponible");
		boolean subastable = jPieza.getBoolean("subastable");
		String status = jPieza.getString("status");
		
		//Se crea la pieza para luego ser agregada
		nuevaPieza = new Escultura(autores,fecha,origen,descripcion,precio,
				fechaP,titulo,precioMin,material,dimensiones,peso,instalacion);
		nuevaPieza.setId(id);
		nuevaPieza.setDisponible(disponible);
		nuevaPieza.setSubastable(subastable);
		nuevaPieza.setStatus(status);
		
		
		return nuevaPieza;
	}
	
	public Pieza cargarPintura (JSONObject jPieza, Pieza nuevaPieza)
	{
		//Mismo proceso que en el método anterior pero para Pintura
		String id = jPieza.getString("id");
		String autores = jPieza.getString("autor");
		String fecha = jPieza.getString("fecha");
		String origen = jPieza.getString("origen");
		String descripcion = jPieza.getString("descripcion");
		String titulo = jPieza.getString("titulo");
		double precio = jPieza.getDouble("precio");
		double precioMin = jPieza.getDouble("precioMinimo");
		String fechaP = jPieza.getString("fechaPrestamo");
		String tecnica = jPieza.getString("tecnica");
		String dimensiones = jPieza.getString("dimensiones");
		
		nuevaPieza = new Pintura(autores,fecha,origen,descripcion,precio,
				fechaP,titulo,precioMin,tecnica,dimensiones);
		nuevaPieza.setId(id);
		
		
		return nuevaPieza;
	}
	
	public Pieza cargarVideo(JSONObject jPieza, Pieza nuevaPieza)
	{
		//Mismo proceso que el usado para las otros dos tipos de pieza
		String id = jPieza.getString("id");
		String autores = jPieza.getString("autor");
		String fecha = jPieza.getString("fecha");
		String origen = jPieza.getString("origen");
		String descripcion = jPieza.getString("descripcion");
		String titulo = jPieza.getString("titulo");
		double precio = jPieza.getDouble("precio");
		String fechaP = jPieza.getString("fechaPrestamo");
		double precioMin = jPieza.getDouble("precioMinimo");
		double tamanio = jPieza.getDouble("tamanio");
		double duracion = jPieza.getDouble("duracion");
		String instalacion = jPieza.getString("instalacion");
		
		nuevaPieza = new Video(autores,fecha,origen,descripcion,precio,
				fechaP,titulo,precioMin,tamanio,duracion,instalacion);
		nuevaPieza.setId(id);
		
		
		return nuevaPieza;
		
		
	}
	
}
