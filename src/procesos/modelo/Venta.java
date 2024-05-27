package procesos.modelo;

import inventario.modelo.Pieza;
import usuarios.modelo.Administrador;
import usuarios.modelo.Cajero;
import usuarios.modelo.Cliente;
import usuarios.modelo.Empleado;

public class Venta 
{

	private Pieza pieza;
	
	private double precio;
	
	private Empleado empleado;
	
	private Administrador admin;
	
	private String medioDePago;
	
	private Integer idComprador;
	
	private boolean confirmado;
	
	
	
	public Venta(Pieza pieza, double precio, Empleado empleado, Administrador admin, String medioDePago, Integer integer) 
	{
		this.pieza = pieza;
		this.precio = precio;
		this.empleado = empleado;
		this.admin = admin;
		this.medioDePago = medioDePago;	
		this.idComprador = integer;
		this.confirmado = false;
	}

	public Pieza getPieza() {
		return pieza;
	}


	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}


	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}


	/**
	 * @return the admin
	 */
	public Administrador getAdmin() {
		return admin;
	}


	/**
	 * @return the medioDePago
	 */
	public String getMedioDePago() {
		return medioDePago;
	}


	/**
	 * @return the comprador
	 */
	public Integer getIdComprador() {
		return idComprador;
	}


	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
	
	public boolean getConfirmado() {
		return this.confirmado;
	}
	
	public void setConfirmado() {
		this.confirmado = true;
	}
	
}
