package com.puntoventa.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.puntoventa.model.Producto;
import com.puntoventa.services.ProductoService;
import com.puntoventa.utilities.Constants;

@ManagedBean(name = "productoBean")
@ViewScoped
public class ProductoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean session;

	private ProductoService productoService = new ProductoService();;
	private List<Producto> productos = new ArrayList<Producto>();
	private List<Producto> filter = new ArrayList<Producto>();
	private String codigo;
	private String nombre;
	private Float cantidad = 0.0f;
	private Float precioCompra = 0.0f;
	private Float precioVenta = 0.0f;
	private int departamento;
	private int tipoVenta = 1;

	private String codigoEdit;
	private String nombreEdit;
	private Float cantidadEdit;
	private Float precioCompraEdit;
	private Float precioVentaEdit;
	private int departamentoEdit;
	private int tipoVentaEdit;
	private Long idDelete;

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Producto> getFilter() {
		return filter;
	}

	public void setFilter(List<Producto> filter) {
		this.filter = filter;
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

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
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

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public int getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(int tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public String getCodigoEdit() {
		return codigoEdit;
	}

	public void setCodigoEdit(String codigoEdit) {
		this.codigoEdit = codigoEdit;
	}

	public String getNombreEdit() {
		return nombreEdit;
	}

	public void setNombreEdit(String nombreEdit) {
		this.nombreEdit = nombreEdit;
	}

	public Float getCantidadEdit() {
		return cantidadEdit;
	}

	public void setCantidadEdit(Float cantidadEdit) {
		this.cantidadEdit = cantidadEdit;
	}

	public Float getPrecioCompraEdit() {
		return precioCompraEdit;
	}

	public void setPrecioCompraEdit(Float precioCompraEdit) {
		this.precioCompraEdit = precioCompraEdit;
	}

	public Float getPrecioVentaEdit() {
		return precioVentaEdit;
	}

	public void setPrecioVentaEdit(Float precioVentaEdit) {
		this.precioVentaEdit = precioVentaEdit;
	}

	public int getDepartamentoEdit() {
		return departamentoEdit;
	}

	public void setDepartamentoEdit(int departamentoEdit) {
		this.departamentoEdit = departamentoEdit;
	}

	public int getTipoVentaEdit() {
		return tipoVentaEdit;
	}

	public void setTipoVentaEdit(int tipoVentaEdit) {
		this.tipoVentaEdit = tipoVentaEdit;
	}

	public Long getIdDelete() {
		return idDelete;
	}

	public void setIdDelete(Long idDelete) {
		this.idDelete = idDelete;
	}

	@PostConstruct
	public void init() {
		productos = productoService.listAll(session.getSucursal());
		PrimeFaces.current().executeScript(Constants.HELPER_HIDE);
	}

	public void guardarProducto() {
		if (!productoService.isProductoExists(codigo, session.getSucursal())) {
			Producto producto = new Producto();
			producto.setCodigo(codigo.trim());
			producto.setNombre(nombre.toUpperCase());
			producto.setCantidadDisponible(cantidad);
			producto.setPrecioCompra(precioCompra);
			producto.setPrecioVenta(precioVenta);
			producto.setDepartamento(departamento);
			producto.setTipoVenta(tipoVenta);
			producto.setSucursal(session.getSucursal());
			productoService.guardarProducto(producto);
			PrimeFaces.current().executeScript(Constants.RELOAD_PAGE);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "El producto ya existe."));
		}
	}

	public void setEditarProducto(int index) {
		codigoEdit = productos.get(index).getCodigo();
		nombreEdit = productos.get(index).getNombre();
		cantidadEdit = productos.get(index).getCantidadDisponible();
		precioCompraEdit = productos.get(index).getPrecioCompra();
		precioVentaEdit = productos.get(index).getPrecioVenta();
		departamentoEdit = productos.get(index).getDepartamento();
		tipoVentaEdit = productos.get(index).getTipoVenta();
	}

	public void editarProducto() {
		Producto productoEdit = new Producto();
		productoEdit.setCodigo(codigoEdit);
		productoEdit.setNombre(nombreEdit.toUpperCase());
		productoEdit.setCantidadDisponible(cantidadEdit);
		productoEdit.setPrecioCompra(precioCompraEdit);
		productoEdit.setPrecioVenta(precioVentaEdit);
		productoEdit.setTipoVenta(tipoVentaEdit);
		productoEdit.setDepartamento(departamentoEdit);
		productoService.editarProducto(productoEdit);
		PrimeFaces.current().executeScript(Constants.RELOAD_PAGE);
	}

	public void setEliminarProducto(int index) {
		idDelete = productos.get(index).getId();
	}

	public void eliminarProducto() {
		productoService.eliminarProducto(idDelete, session.getSucursal());
		PrimeFaces.current().executeScript(Constants.RELOAD_PAGE);
	}

}
