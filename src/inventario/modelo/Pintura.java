package inventario.modelo;



public class Pintura extends Pieza
{
	private String tecnica;
	private String dimensiones;
	private static final String PINTURA = "Pintura";

	public Pintura(String autores, String fecha, String origen, String descripcion, double precio, String fechaP,
			String titulo, double minPrecio,String tecnica, String dimensiones) 
	{
		super(autores, fecha, origen, descripcion, precio, fechaP, titulo, minPrecio);
		super.tipo = PINTURA;
		this.tecnica = tecnica;
		this.dimensiones = dimensiones;
	}

	public String getTecnica() 
	{
		return tecnica;
	}

	public String getDimensiones() 
	{
		return dimensiones;
	}
	
	
}
