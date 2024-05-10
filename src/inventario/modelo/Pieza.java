package inventario.modelo;


import java.util.HashMap;
import java.util.Map;

import procesos.modelo.Venta;

public abstract class Pieza 
{
	private String id;
	protected String tipo;
	private String autores;
	private String fecha;
	private String origen;
	private String descripcion;
	private String titulo;
	String status;
	private boolean subastable;
	private boolean disponible;
	private double precio;
	private double precioMinimo;
	private String fechaDePrestamo;
	private Map<String,Venta> historial;
	
	
	public Pieza(String autores,String fecha,String origen,String descripcion,double precio,String fechaP,String titulo,
			double minPrecio) 
	{
		id = "";
		this.autores = autores;
		this.fecha = fecha;
		this.origen = origen;
		this.descripcion = descripcion;
		this.precio = precio;
		fechaDePrestamo = fechaP;
		this.titulo = titulo;
		subastable = false;
		disponible = false;
		precioMinimo = minPrecio;
		historial = new HashMap<String, Venta>();
	}

	public String getId() 
	{
		return id;
	}
	

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getAutores() 
	{
		return autores;
	}

	public String getFecha() 
	{
		return fecha;
	}

	public String getOrigen() 
	{
		return origen;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public String getTitulo() 
	{
		return titulo;
	}

	public boolean isSubastable() 
	{
		return subastable;
	}
	

	public void setSubastable(boolean subastable) 
	{
		this.subastable = subastable;
	}


	public boolean isDisponible() 
	{
		return disponible;
	}
	
	public void setDisponible(boolean disponible) 
	{
		this.disponible = disponible;
	}
	
	

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public double getPrecio() 
	{
		return precio;
	}

	public String getFechaDePrestamo() 
	{
		return fechaDePrestamo;
	}
	
	public double getPrecioMinimo()
	{
		return precioMinimo;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	

	public Map<String, Venta> getHistorial() 
	{
		return historial;
	}

	public void cambiarDisponibilidad()
	{
		if (disponible)
		{
			disponible = false;
		}
		else
		{
			disponible = true;
		}
	}
	
	public void cambiarSubastable()
	{
		if (subastable)
		{
			subastable = false;
		}
		else
		{
			subastable = true;
		}
	}
	
}
