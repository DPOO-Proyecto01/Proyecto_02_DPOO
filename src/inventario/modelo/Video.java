package inventario.modelo;



public class Video extends Pieza
{
	private double tamanio;
	private double duracion;
	private String instalacion;
	private static final String VIDEO = "Video";
	
	public Video(String autores, String fecha, String origen, String descripcion, double precio, String fechaP,
			String titulo, double minPrecio,double tamanio, double duracion, String instalacion) 
	{
		super(autores, fecha, origen, descripcion, precio, fechaP, titulo, minPrecio);
		super.tipo = VIDEO;
		this.tamanio = tamanio;
		this.duracion = duracion;
		this.instalacion = instalacion;
	}

	public double getTamanio() 
	{
		return tamanio;
	}

	public double getDuracion() 
	{
		return duracion;
	}

	public String getInstalacion() 
	{
		return instalacion;
	}
	
	
}
