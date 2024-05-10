package inventario.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario 
{
	private Map<String,Pieza> exhibicion;
	private Map<String,Pieza> bodega;
	private Map<String,Pieza> devolucion;
	private Map<String,Pieza> enVenta;
	private Map<String,Pieza> paraSubasta;
	private Map<String,Pieza> piezasPasadas;
	/**Se tiene una lista de ids para crear ids completamente nuevos
	 * También se usa para tener un trazado de qué piezas se han cargado con el fin
	 * de que no se cargue una pieza dos veces**/
	private List<String> ids = new ArrayList<String>();
	
	public Inventario() 
	{
		exhibicion = new HashMap<String,Pieza>();
		bodega = new HashMap<String,Pieza>();
		devolucion = new HashMap<String,Pieza>();
		enVenta = new HashMap<String,Pieza>();
		paraSubasta = new HashMap<String,Pieza>();
		piezasPasadas = new HashMap<String,Pieza>();
	}
	
	public Map<String,Pieza> piezasEnExhibicion()
	{
		return exhibicion;
	}
	
	public Map<String,Pieza> piezasEnBodega()
	{
		return bodega;
	}
	
	public Map<String,Pieza> piezasDevolucion()
	{
		return devolucion;
	}
	
	public Map<String,Pieza> piezasEnVenta()
	{
		return enVenta;
	}
	
	public Map<String,Pieza> piezasParaSubasta()
	{
		return paraSubasta;
	}
	
	
	public Map<String, Pieza> getPiezasPasadas() 
	{
		return piezasPasadas;
	}

	public List<String> getIds() 
	{
		return ids;
	}

	public Pieza buscarPieza (String id)
	{
		//Busca una pieza en todas las listas de piezas que aun le pertencen a la galeria
		boolean encontro = false;
		Pieza pieza = null;
		
		if (exhibicion.containsKey(id))
		{
			pieza = exhibicion.get(id);
			encontro = true;
		}
		
		if ((!encontro)&&(bodega.containsKey(id)))
		{
			pieza = bodega.get(id);
			encontro = true;
		}
		
		if ((!encontro)&&(enVenta.containsKey(id)))
		{
			pieza = enVenta.get(id);
			encontro = true;
		}
		
		if ((!encontro)&&(devolucion.containsKey(id)))
		{
			pieza = devolucion.get(id);
			encontro = true;
		}
		
		if ((!encontro)&&(paraSubasta.containsKey(id)))
		{
			pieza = paraSubasta.get(id);
			encontro = true;
		}
		
		return pieza;
	}
	
	public void retirarPieza(Pieza obra)
	{
		String idPieza = obra.getId();
		
		//Se retira una pieza de todas las listas que la contengan
		
		if (exhibicion.containsKey(idPieza))
		{
			exhibicion.remove(idPieza);
		}
		
		if (bodega.containsKey(idPieza))
		{
			bodega.remove(idPieza);
		}
		
		if (devolucion.containsKey(idPieza))
		{
			devolucion.remove(idPieza);
		}
		
		if (enVenta.containsKey(idPieza))
		{
			enVenta.remove(idPieza);
		}
		
		if (paraSubasta.containsKey(idPieza))
		{
			paraSubasta.remove(idPieza);
		}
	}
	
	public void moverPieza(String id, Map<String,Pieza> mapa_1, Map<String,Pieza> mapa_2)
	{
		//Se mueve una pieza de una lista a otra
		Pieza piezaMover = null;
		if(mapa_1.containsKey(id))
		{
			piezaMover = mapa_1.get(id);
			mapa_1.remove(id);
		}
		
		if (!(piezaMover.equals(null)))
		{
			mapa_2.put(id, piezaMover);
		}
	}
	
	public String generarId ()
	{
		//Se genera un id aleatriamente
		Integer numAleatorio = (int) Math.random()*100000;
		String cod = numAleatorio.toString();
		//Se vuelve a generar el id si el que se generó ya existía
		while (ids.contains(cod))
		{
			numAleatorio = (int) Math.random()*100000;
			cod = numAleatorio.toString();
		}
		//Una vez obtenido un codigo nuevo se añade a la lista de ids
		ids.add(cod);
		return cod;
	}
	
	public void agregarPieza (Pieza pieza)
	{
		//Se agrega una pieza a una lista dependiendo de sus atributos(subastable,disponible,status)
		if (pieza.isDisponible())
		{
			enVenta.put(pieza.getId(), pieza);
		}
		
		if (pieza.isSubastable())
		{
			paraSubasta.put(pieza.getId(), pieza);
		}
		if (pieza.getStatus().equals("Bodega"))
		{
			bodega.put(pieza.getId(), pieza);
		}
		else if (pieza.getStatus().equals("Exhibicion"))
		{
			exhibicion.put(pieza.getId(), pieza);
		}
		else if (pieza.getStatus().equals("Devolucion"))
		{
			devolucion.put(pieza.getId(), pieza);
		}
		
		else if (pieza.getStatus().equals("Pasado"))
		{
			piezasPasadas.put(pieza.getId(), pieza);
		}
		
		ids.add(pieza.getId());
		
	}
}
