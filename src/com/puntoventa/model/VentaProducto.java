package com.puntoventa.model;

import java.io.Serializable;

public class VentaProducto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigoProducto;
	private Float cantidadProducto;
	private Float precioProducto;
	private Long idDetalle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Float getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Float cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Float getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(Float precioProducto) {
		this.precioProducto = precioProducto;
	}

	public Long getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Long idDetalle) {
		this.idDetalle = idDetalle;
	}
}
