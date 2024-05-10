package inventario.modelo;



public class Escultura extends Pieza
{
	private String material;
	private String dimensiones;
	private double peso;
	private String instalacion;
	private static final String ESCULTURA = "Escultura";
	
	public Escultura(String autores, String fecha, String origen, String descripcion, double precio,
			String fechaP, String titulo, double minPrecio, String material, String dimensiones, double peso,
			String instalacion) 
	{
		super(autores, fecha, origen, descripcion, precio, fechaP, titulo, minPrecio);
		super.tipo = ESCULTURA;
		this.material = material;
		this.dimensiones = dimensiones;
		this.peso = peso;
		this.instalacion = instalacion;
	}

	public String getMaterial() 
	{
		return material;
	}

	public String getDimensiones() 
	{
		return dimensiones;
	}

	public double getPeso() 
	{
		return peso;
	}

	public String getInstalacion() 
	{
		return instalacion;
	}
	
	
	
	
}
