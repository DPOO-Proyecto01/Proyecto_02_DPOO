package galeria.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import inventario.modelo.Escultura;
import inventario.modelo.Galeria;
import inventario.modelo.Inventario;
import inventario.modelo.Pieza;
import inventario.modelo.Pintura;
import inventario.modelo.Video;
import procesos.modelo.Venta;
import usuarios.modelo.AdministradorUsuarios;
import usuarios.modelo.Cajero;
import usuarios.modelo.Cliente;

public class PersistenciaInventario 
{
	
	public void guardarInventario (String archivo, Galeria galeria) throws FileNotFoundException
	{
		Inventario inventario = galeria.getInventario();
		JSONObject jobject = new JSONObject( );

     
        guardarExhibicion( inventario, jobject );
        

        guardarBodega( inventario, jobject );
        
     
        guardarDevolucion( inventario, jobject );
        
     
        guardarEnVenta( inventario, jobject );
        
     
        guardarParaSubasta( inventario, jobject );
        
    
        guardarPiezasPasadas( inventario, jobject );
        
        
        guardarIDs( inventario, jobject );

        // Escribir la estructura JSON en un archivo
        PrintWriter pw;
			pw = new PrintWriter(archivo);
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	private void guardarIDs(Inventario inventario, JSONObject jobject) 
	{
		JSONArray jIds = new JSONArray( );
        for( String Id : inventario.getIds() )
        {
            jIds.put( Id );
        }
        jobject.put( "ListaIds", jIds );
		
	}

	private void guardarExhibicion(Inventario inventario, JSONObject jobject) 
	{
		JSONArray jExhib = new JSONArray( );
		Map<String,Pieza> Exhib = inventario.piezasEnExhibicion();
		for (Pieza pieza : Exhib.values()) 
		{
			JSONObject jPieza = new JSONObject( );
			String tipo = pieza.getTipo();
			jPieza.put( "id", pieza.getId() );
			jPieza.put( "tipo", tipo);
			jPieza.put( "autores", pieza.getAutores() );
			jPieza.put( "fecha", pieza.getFecha() );
			jPieza.put( "origen", pieza.getOrigen() );
			jPieza.put( "descripcion", pieza.getDescripcion() );
			jPieza.put( "titulo", pieza.getTitulo() );
			jPieza.put( "status", pieza.getStatus() );
			jPieza.put( "subastable", pieza.isSubastable() );
			jPieza.put( "disponible", pieza.isDisponible() );
			jPieza.put( "precio", pieza.getPrecio() );
			jPieza.put( "precioMinimo", pieza.getPrecioMinimo() );
			jPieza.put( "fechaDePrestamo", pieza.getFechaDePrestamo());
			
			
			if (tipo.equalsIgnoreCase("Escultura")) 
			{
				Escultura escultura = (Escultura) pieza;
				jPieza.put( "material", escultura.getMaterial() );
				jPieza.put( "dimensiones", escultura.getDimensiones() );
				jPieza.put( "instalacion", escultura.getInstalacion() );
				jPieza.put( "peso", escultura.getPeso());
				
				
			}
			else if (tipo.equalsIgnoreCase("Pintura")) 
			{
				Pintura pintura = (Pintura) pieza;
				jPieza.put( "tecnica", pintura.getTecnica() );
				jPieza.put( "dimensiones", pintura.getDimensiones() );
			}
			else if (tipo.equalsIgnoreCase("Video")) 
			{
				Video video = (Video) pieza;
				jPieza.put( "duracion", video.getDuracion() );
				jPieza.put( "instalacion", video.getInstalacion() );
				jPieza.put( "tamanio", video.getTamanio() );
			}

            jExhib.put(jPieza);
        }
        jobject.put( "Exhibicion", jExhib );
		
	}

	private void guardarBodega(Inventario inventario, JSONObject jobject) 
	{
		JSONArray jBodega = new JSONArray( );
		Map<String,Pieza> Bodega = inventario.piezasEnBodega();
		for (Pieza pieza : Bodega.values()) 
		{
			JSONObject jPieza = new JSONObject( );
			String tipo = pieza.getTipo();
			jPieza.put( "id", pieza.getId() );
			jPieza.put( "tipo", tipo);
			jPieza.put( "autores", pieza.getAutores() );
			jPieza.put( "fecha", pieza.getFecha() );
			jPieza.put( "origen", pieza.getOrigen() );
			jPieza.put( "descripcion", pieza.getDescripcion() );
			jPieza.put( "titulo", pieza.getTitulo() );
			jPieza.put( "status", pieza.getStatus() );
			jPieza.put( "subastable", pieza.isSubastable() );
			jPieza.put( "disponible", pieza.isDisponible() );
			jPieza.put( "precio", pieza.getPrecio() );
			jPieza.put( "precioMinimo", pieza.getPrecioMinimo() );
			jPieza.put( "fechaDePrestamo", pieza.getFechaDePrestamo());

			
			if (tipo.equalsIgnoreCase("Escultura")) 
			{
				Escultura escultura = (Escultura) pieza;
				jPieza.put( "material", escultura.getMaterial() );
				jPieza.put( "dimensiones", escultura.getDimensiones() );
				jPieza.put( "instalacion", escultura.getInstalacion() );
				jPieza.put( "peso", escultura.getPeso());
				
				
			}
			else if (tipo.equalsIgnoreCase("Pintura")) 
			{
				Pintura pintura = (Pintura) pieza;
				jPieza.put( "tecnica", pintura.getTecnica() );
				jPieza.put( "dimensiones", pintura.getDimensiones() );
			}
			else if (tipo.equalsIgnoreCase("Video")) 
			{
				Video video = (Video) pieza;
				jPieza.put( "duracion", video.getDuracion() );
				jPieza.put( "instalacion", video.getInstalacion() );
				jPieza.put( "tamanio", video.getTamanio() );
			}

            jBodega.put(jPieza);
        }
        jobject.put( "Bodega", jBodega );
		
	}

	private void guardarDevolucion(Inventario inventario, JSONObject jobject) 
	{
		JSONArray jDevolucion = new JSONArray( );
		Map<String,Pieza> Devolucion = inventario.piezasDevolucion();
		for (Pieza pieza : Devolucion.values()) 
		{
			JSONObject jPieza = new JSONObject( );
			String tipo = pieza.getTipo();
			jPieza.put( "id", pieza.getId() );
			jPieza.put( "tipo", tipo);
			jPieza.put( "autores", pieza.getAutores() );
			jPieza.put( "fecha", pieza.getFecha() );
			jPieza.put( "origen", pieza.getOrigen() );
			jPieza.put( "descripcion", pieza.getDescripcion() );
			jPieza.put( "titulo", pieza.getTitulo() );
			jPieza.put( "status", pieza.getStatus() );
			jPieza.put( "subastable", pieza.isSubastable() );
			jPieza.put( "disponible", pieza.isDisponible() );
			jPieza.put( "precio", pieza.getPrecio() );
			jPieza.put( "precioMinimo", pieza.getPrecioMinimo() );
			jPieza.put( "fechaDePrestamo", pieza.getFechaDePrestamo());
			

			
			if (tipo.equalsIgnoreCase("Escultura")) 
			{
				Escultura escultura = (Escultura) pieza;
				jPieza.put( "material", escultura.getMaterial() );
				jPieza.put( "dimensiones", escultura.getDimensiones() );
				jPieza.put( "instalacion", escultura.getInstalacion() );
				jPieza.put( "peso", escultura.getPeso());
				
				
			}
			else if (tipo.equalsIgnoreCase("Pintura")) 
			{
				Pintura pintura = (Pintura) pieza;
				jPieza.put( "tecnica", pintura.getTecnica() );
				jPieza.put( "dimensiones", pintura.getDimensiones() );
			}
			else if (tipo.equalsIgnoreCase("Video")) 
			{
				Video video = (Video) pieza;
				jPieza.put( "duracion", video.getDuracion() );
				jPieza.put( "instalacion", video.getInstalacion() );
				jPieza.put( "tamanio", video.getTamanio() );
			}

            jDevolucion.put(jPieza);
        }
        jobject.put( "Devolucion", jDevolucion );
		
	}

	private void guardarEnVenta(Inventario inventario, JSONObject jobject) 
	{
		JSONArray jEnVenta = new JSONArray( );
		Map<String,Pieza> EnVenta = inventario.piezasEnVenta();
		for (Pieza pieza : EnVenta.values()) 
		{
			JSONObject jPieza = new JSONObject( );
			String tipo = pieza.getTipo();
			jPieza.put( "id", pieza.getId() );
			jPieza.put( "tipo", tipo);
			jPieza.put( "autores", pieza.getAutores() );
			jPieza.put( "fecha", pieza.getFecha() );
			jPieza.put( "origen", pieza.getOrigen() );
			jPieza.put( "descripcion", pieza.getDescripcion() );
			jPieza.put( "titulo", pieza.getTitulo() );
			jPieza.put( "status", pieza.getStatus() );
			jPieza.put( "subastable", pieza.isSubastable() );
			jPieza.put( "disponible", pieza.isDisponible() );
			jPieza.put( "precio", pieza.getPrecio() );
			jPieza.put( "precioMinimo", pieza.getPrecioMinimo() );
			jPieza.put( "fechaDePrestamo", pieza.getFechaDePrestamo());
			

			
			if (tipo.equalsIgnoreCase("Escultura")) 
			{
				Escultura escultura = (Escultura) pieza;
				jPieza.put( "material", escultura.getMaterial() );
				jPieza.put( "dimensiones", escultura.getDimensiones() );
				jPieza.put( "instalacion", escultura.getInstalacion() );
				jPieza.put( "peso", escultura.getPeso());
				
				
			}
			else if (tipo.equalsIgnoreCase("Pintura")) 
			{
				Pintura pintura = (Pintura) pieza;
				jPieza.put( "tecnica", pintura.getTecnica() );
				jPieza.put( "dimensiones", pintura.getDimensiones() );
			}
			else if (tipo.equalsIgnoreCase("Video")) 
			{
				Video video = (Video) pieza;
				jPieza.put( "duracion", video.getDuracion() );
				jPieza.put( "instalacion", video.getInstalacion() );
				jPieza.put( "tamanio", video.getTamanio() );
			}

            jEnVenta.put(jPieza);
        }
        jobject.put( "EnVenta", jEnVenta );
		
	}

	private void guardarParaSubasta(Inventario inventario, JSONObject jobject) 
	{
		JSONArray jParaSubasta = new JSONArray( );
		Map<String,Pieza> ParaSubasta = inventario.piezasParaSubasta();
		for (Pieza pieza : ParaSubasta.values()) 
		{
			JSONObject jPieza = new JSONObject( );
			String tipo = pieza.getTipo();
			jPieza.put( "id", pieza.getId() );
			jPieza.put( "tipo", tipo);
			jPieza.put( "autores", pieza.getAutores() );
			jPieza.put( "fecha", pieza.getFecha() );
			jPieza.put( "origen", pieza.getOrigen() );
			jPieza.put( "descripcion", pieza.getDescripcion() );
			jPieza.put( "titulo", pieza.getTitulo() );
			jPieza.put( "status", pieza.getStatus() );
			jPieza.put( "subastable", pieza.isSubastable() );
			jPieza.put( "disponible", pieza.isDisponible() );
			jPieza.put( "precio", pieza.getPrecio() );
			jPieza.put( "precioMinimo", pieza.getPrecioMinimo() );
			jPieza.put( "fechaDePrestamo", pieza.getFechaDePrestamo());

			
			if (tipo.equalsIgnoreCase("Escultura")) 
			{
				Escultura escultura = (Escultura) pieza;
				jPieza.put( "material", escultura.getMaterial() );
				jPieza.put( "dimensiones", escultura.getDimensiones() );
				jPieza.put( "instalacion", escultura.getInstalacion() );
				jPieza.put( "peso", escultura.getPeso());
				
				
			}
			else if (tipo.equalsIgnoreCase("Pintura")) 
			{
				Pintura pintura = (Pintura) pieza;
				jPieza.put( "tecnica", pintura.getTecnica() );
				jPieza.put( "dimensiones", pintura.getDimensiones() );
			}
			else if (tipo.equalsIgnoreCase("Video")) 
			{
				Video video = (Video) pieza;
				jPieza.put( "duracion", video.getDuracion() );
				jPieza.put( "instalacion", video.getInstalacion() );
				jPieza.put( "tamanio", video.getTamanio() );
			}

            jParaSubasta.put(jPieza);
        }
        jobject.put( "ParaSubasta", jParaSubasta );
		
	}

	private void guardarPiezasPasadas(Inventario inventario, JSONObject jobject) 
	{
		JSONArray jPiezasPasadas = new JSONArray( );
		Map<String,Pieza> PiezasPasadas = inventario.getPiezasPasadas();
		for (Pieza pieza : PiezasPasadas.values()) 
		{
			JSONObject jPieza = new JSONObject( );
			String tipo = pieza.getTipo();
			jPieza.put( "id", pieza.getId() );
			jPieza.put( "tipo", tipo);
			jPieza.put( "autores", pieza.getAutores() );
			jPieza.put( "fecha", pieza.getFecha() );
			jPieza.put( "origen", pieza.getOrigen() );
			jPieza.put( "descripcion", pieza.getDescripcion() );
			jPieza.put( "titulo", pieza.getTitulo() );
			jPieza.put( "status", pieza.getStatus() );
			jPieza.put( "subastable", pieza.isSubastable() );
			jPieza.put( "disponible", pieza.isDisponible() );
			jPieza.put( "precio", pieza.getPrecio() );
			jPieza.put( "precioMinimo", pieza.getPrecioMinimo() );
			jPieza.put( "fechaDePrestamo", pieza.getFechaDePrestamo());
			

			
			if (tipo.equalsIgnoreCase("Escultura")) 
			{
				Escultura escultura = (Escultura) pieza;
				jPieza.put( "material", escultura.getMaterial() );
				jPieza.put( "dimensiones", escultura.getDimensiones() );
				jPieza.put( "instalacion", escultura.getInstalacion() );
				jPieza.put( "peso", escultura.getPeso());
				
				
			}
			else if (tipo.equalsIgnoreCase("Pintura")) 
			{
				Pintura pintura = (Pintura) pieza;
				jPieza.put( "tecnica", pintura.getTecnica() );
				jPieza.put( "dimensiones", pintura.getDimensiones() );
			}
			else if (tipo.equalsIgnoreCase("Video")) 
			{
				Video video = (Video) pieza;
				jPieza.put( "duracion", video.getDuracion() );
				jPieza.put( "instalacion", video.getInstalacion() );
				jPieza.put( "tamanio", video.getTamanio() );
			}

            jPiezasPasadas.put(jPieza);
        }
        jobject.put( "PiezasPasadas", jPiezasPasadas );
		
	}

	public void cargarInventario (String archivo, Galeria galeria) throws IOException
	{
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
		Inventario inventario = galeria.getInventario();


        cargarExhibicion( inventario, raiz.getJSONArray( "Exhibicion" ) );
        cargarBodega( inventario, raiz.getJSONArray( "Bodega" ) );
        cargarDevolucion( inventario, raiz.getJSONArray( "Devolucion" ) );
        cargarEnVenta( inventario, raiz.getJSONArray( "EnVenta" ) );
        cargarParaSubasta( inventario, raiz.getJSONArray( "ParaSubasta" ) );
        cargarPiezasPasadas( inventario, raiz.getJSONArray( "PiezasPasadas" ) );
        cargarIDs( inventario, raiz.getJSONArray( "ListaIds" ) );
	}
	
	
	private void cargarIDs(Inventario inventario, JSONArray jIds) 
	{
		int numIds = jIds.length( );
        for( int i = 0; i < numIds; i++ ) 
        {
        	String Id = jIds.getString( i );
        	inventario.añadirId(Id);;
        }
		
	}

	private void cargarExhibicion(Inventario inventario, JSONArray jExhibicion) 
	{
		
		int numExhibicion = jExhibicion.length( );
        for( int i = 0; i < numExhibicion; i++ ) 
        {
        	JSONObject JPieza = jExhibicion.getJSONObject( i );
        	
        	String tipo = JPieza.getString("tipo");
        	
        	if (tipo.equalsIgnoreCase("Escultura")) 
        	{
        		Escultura pieza = null;
        		pieza = (Escultura) cargarEscultura(JPieza, pieza);
            	inventario.añadirAExhibicion(pieza);
        		
        	}
        	else if (tipo.equalsIgnoreCase("Pintura")) 
			{
        		Pintura pieza = null;
        		pieza = (Pintura) cargarPintura(JPieza, pieza);

            	inventario.añadirAExhibicion(pieza);
			}
        	else if (tipo.equalsIgnoreCase("Video")) 
        	{
        		Video pieza = null;
        		pieza = (Video) cargarVideo(JPieza, pieza);
            	
            	inventario.añadirAExhibicion(pieza);
        	}
            
        }
		
	}

	private void cargarBodega(Inventario inventario, JSONArray jBodega) 
	{
		int numBodega = jBodega.length( );
        for( int i = 0; i < numBodega; i++ ) 
        {
        	JSONObject JPieza = jBodega.getJSONObject( i );
        	
        	String tipo = JPieza.getString("tipo");
        	
        	if (tipo.equalsIgnoreCase("Escultura")) 
        	{
        		Escultura pieza = null;
        		pieza = (Escultura) cargarEscultura(JPieza, pieza);
            	
            	inventario.añadirABodega(pieza);
        		
        	}
        	else if (tipo.equalsIgnoreCase("Pintura")) 
			{
        		Pintura pieza = null;
        		pieza = (Pintura) cargarPintura(JPieza, pieza);
            	
            	inventario.añadirABodega(pieza);
			}
        	else if (tipo.equalsIgnoreCase("Video")) 
        	{
        		Video pieza = null;
        		pieza = (Video) cargarVideo(JPieza, pieza);
            	
            	inventario.añadirABodega(pieza);
        	}
            
        }
		
	}

	private void cargarDevolucion(Inventario inventario, JSONArray jDevolucion) 
	{
		int numDevolucion = jDevolucion.length( );
        for( int i = 0; i < numDevolucion; i++ ) 
        {
        	JSONObject JPieza = jDevolucion.getJSONObject( i );
        	
        	String tipo = JPieza.getString("tipo");
        	
        	if (tipo.equalsIgnoreCase("Escultura")) 
        	{
        		Escultura pieza = null;
        		pieza = (Escultura) cargarEscultura(JPieza, pieza);
            	
            	inventario.añadirADevolucion(pieza);
        		
        	}
        	else if (tipo.equalsIgnoreCase("Pintura")) 
			{
        		Pintura pieza = null;
        		pieza = (Pintura) cargarPintura(JPieza, pieza);
            	
            	inventario.añadirADevolucion(pieza);
			}
        	else if (tipo.equalsIgnoreCase("Video")) 
        	{
        		Video pieza = null;
        		pieza = (Video) cargarVideo(JPieza, pieza);
            	
            	inventario.añadirADevolucion(pieza);
        	}
            
        }
		
	}

	private void cargarEnVenta(Inventario inventario, JSONArray jEnVenta) 
	{
		int numEnVenta = jEnVenta.length( );
        for( int i = 0; i < numEnVenta; i++ ) 
        {
        	JSONObject JPieza = jEnVenta.getJSONObject( i );
        	
        	String tipo = JPieza.getString("tipo");
        	
        	if (tipo.equalsIgnoreCase("Escultura")) 
        	{
        		Escultura pieza = null;
        		pieza = (Escultura) cargarEscultura(JPieza, pieza);
            	
            	inventario.añadirAEnVenta(pieza);
        		
        	}
        	else if (tipo.equalsIgnoreCase("Pintura")) 
			{
        		Pintura pieza = null;
        		pieza = (Pintura) cargarPintura(JPieza, pieza);
            	
            	inventario.añadirAEnVenta(pieza);
			}
        	else if (tipo.equalsIgnoreCase("Video")) 
        	{
        		Video pieza = null;
        		pieza = (Video) cargarVideo(JPieza, pieza);
            	
            	inventario.añadirAEnVenta(pieza);
        	}
            
        }
		
	}

	private void cargarParaSubasta(Inventario inventario, JSONArray jParaSubasta) 
	{
		int numParaSubasta = jParaSubasta.length( );
        for( int i = 0; i < numParaSubasta; i++ ) 
        {
        	JSONObject JPieza = jParaSubasta.getJSONObject( i );
        	
        	String tipo = JPieza.getString("tipo");
        	
        	if (tipo.equalsIgnoreCase("Escultura")) 
        	{
        		Escultura pieza = null;
        		pieza = (Escultura) cargarEscultura(JPieza, pieza);
            	
            	inventario.añadirASubastas(pieza);
        		
        	}
        	else if (tipo.equalsIgnoreCase("Pintura")) 
			{
        		Pintura pieza = null;
        		pieza = (Pintura) cargarPintura(JPieza, pieza);
            	
            	inventario.añadirASubastas(pieza);
			}
        	else if (tipo.equalsIgnoreCase("Video")) 
        	{
        		Video pieza = null;
        		pieza = (Video) cargarVideo(JPieza, pieza);
            	
            	inventario.añadirASubastas(pieza);
        	}
            
        }
		
	}

	private void cargarPiezasPasadas(Inventario inventario, JSONArray jPiezasPasadas) 
	{
		int numPiezasPasadas = jPiezasPasadas.length( );
        for( int i = 0; i < numPiezasPasadas; i++ ) 
        {
        	
        	JSONObject JPieza = jPiezasPasadas.getJSONObject( i );
        	
        	String tipo = JPieza.getString("tipo");
        	
        	if (tipo.equalsIgnoreCase("Escultura")) 
        	{	
        		
        		Escultura pieza = null;
        		pieza = (Escultura) cargarEscultura(JPieza, pieza);

            	inventario.añadirAPasadas(pieza);
        		
        	}
        	else if (tipo.equalsIgnoreCase("Pintura")) 
			{
        		Pintura pieza = null;
        		pieza = (Pintura) cargarPintura(JPieza, pieza);

            	inventario.añadirAPasadas(pieza);
			}
        	else if (tipo.equalsIgnoreCase("Video")) 
        	{
        		Video pieza = null;
        		pieza = (Video) cargarVideo(JPieza, pieza);
            	
            	inventario.añadirAPasadas(pieza);
            	
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
		boolean disponible = jPieza.getBoolean("disponible");
		boolean subastable = jPieza.getBoolean("subastable");
		String status = jPieza.getString("status");
		
		nuevaPieza = new Pintura(autores,fecha,origen,descripcion,precio,
				fechaP,titulo,precioMin,tecnica,dimensiones);
		nuevaPieza.setId(id);
		nuevaPieza.setDisponible(disponible);
		nuevaPieza.setSubastable(subastable);
		nuevaPieza.setStatus(status);
		
		
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
		boolean disponible = jPieza.getBoolean("disponible");
		boolean subastable = jPieza.getBoolean("subastable");
		String status = jPieza.getString("status");
		
		nuevaPieza = new Video(autores,fecha,origen,descripcion,precio,
				fechaP,titulo,precioMin,tamanio,duracion,instalacion);
		nuevaPieza.setId(id);
		nuevaPieza.setDisponible(disponible);
		nuevaPieza.setSubastable(subastable);
		nuevaPieza.setStatus(status);
		
		
		return nuevaPieza;
		
		
	}
	
}
