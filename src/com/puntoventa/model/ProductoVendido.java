package com.puntoventa.model;

import java.io.Serializable;

public class ProductoVendido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String nombre;
	private Float cantidad;
	private Float precio;
	private Float subtotal;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "ProductoVendido [codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio="
				+ precio + ", subtotal=" + subtotal + "]";
	}
	
}
