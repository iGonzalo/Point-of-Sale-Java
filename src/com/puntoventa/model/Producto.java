package com.puntoventa.model;

import java.io.Serializable;
import java.util.List;

public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigo;
	private String nombre;
	private Float cantidadDisponible;
	private Float precioCompra;
	private Float precioVenta;
	private int tipoVenta;
	private int departamento;
	private int sucursal;
	private List<Float> cantidad;
	private Float subtotal;
	private Float cantidadSeleccion;

	public Producto() {

	}

	public Producto(Long id, String codigo, String nombre, Float cantidadDisponible, Float precioCompra,
			Float precioVenta, int departamento, int tipoVenta, int sucursal) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidadDisponible = cantidadDisponible;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.departamento = departamento;
		this.tipoVenta = tipoVenta;
		this.sucursal = sucursal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Float getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Float cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(int tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	public List<Float> getCantidad() {
		return cantidad;
	}

	public void setCantidad(List<Float> cantidad) {
		this.cantidad = cantidad;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getCantidadSeleccion() {
		return cantidadSeleccion;
	}

	public void setCantidadSeleccion(Float cantidadSeleccion) {
		this.cantidadSeleccion = cantidadSeleccion;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", cantidadDisponible="
				+ cantidadDisponible + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta
				+ ", tipoVenta=" + tipoVenta + ", departamento=" + departamento + ", sucursal=" + sucursal
				+ ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", cantidadSeleccion=" + cantidadSeleccion
				+ "]";
	}

}
