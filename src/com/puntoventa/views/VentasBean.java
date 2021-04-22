package com.puntoventa.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

//import org.primefaces.PrimeFaces;

import com.puntoventa.model.Producto;
import com.puntoventa.services.ProductoService;
import com.puntoventa.services.TicketService;
import com.puntoventa.services.VentaService;
import com.puntoventa.utilities.Util;

@ManagedBean(name = "ventasBean")
@ViewScoped
public class VentasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean session;

	private List<Producto> venta = new ArrayList<Producto>();
	private Float total = 0.0f;
	private String codigo;
	private ProductoService productoService = new ProductoService();
	private VentaService ventaService = new VentaService();
	private TicketService ticketService = new TicketService();
	private Producto producto = new Producto();
	private List<Float> cantidad;
	private Float cantidadSel;
	private Float subtotal = 0.0f;
	private Float pagoCliente = 0.0f;
	private Float cambio = 0.0f;

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public List<Producto> getVenta() {
		return venta;
	}

	public void setVenta(List<Producto> venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public List<Float> getCantidad() {
		return cantidad;
	}

	public void setCantidad(List<Float> cantidad) {
		this.cantidad = cantidad;
	}

	public Float getCantidadSel() {
		return cantidadSel;
	}

	public void setCantidadSel(Float cantidadSel) {
		this.cantidadSel = cantidadSel;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getPagoCliente() {
		return pagoCliente;
	}

	public void setPagoCliente(Float pagoCliente) {
		this.pagoCliente = pagoCliente;
	}

	public void setCambio(Float cambio) {
		this.cambio = cambio;
	}

	public Float getCambio() {
		return cambio;
	}

//	@PostConstruct
//	public void init() {
//		printService = PrinterOutputStream.getPrintServiceByName("EPSON TM-T88V");
//	}

	public void agregarProducto() {
		if (codigo != null && !codigo.equals("")) {
			producto = productoService.findProductoByCodigo(codigo.toUpperCase(), session.getSucursal());
			if (producto != null) {
				if (!Util.isCodigoInList(venta, producto.getCodigo())) {
					if (producto.getCantidadDisponible() >= 1) {
						if (producto.getTipoVenta() == 1) {
							codigo = "";
							cantidad = new ArrayList<Float>();
							for (int i = 0; i < producto.getCantidadDisponible(); i++) {
								cantidad.add(i, Float.valueOf(i + 1));
							}
							producto.setCantidad(cantidad);
							subtotal = producto.getPrecioVenta();
							producto.setSubtotal(subtotal);
							producto.setCantidadSeleccion(1.0f);
							venta.add(producto);
							setTotal();
						} else {
							codigo = "";
							producto.setCantidadSeleccion(0.0f);
							venta.add(producto);
						}
					} else {
						codigo = "";
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error: ", "El producto no cuenta con inventario."));
					}
				} else {
					codigo = "";
//					venta.
//					producto.setCantidadSeleccion(producto.getCantidadSeleccion() + 1.0f);
					setTotal();
//					FacesContext.getCurrentInstance().addMessage(null,
//							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "El producto ya se ha agregado."));
				}
			} else {
				codigo = "";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "El producto no existe."));
			}
		}
	}

	public void removeElement(int index) {
		total = total - venta.get(index).getSubtotal();
		venta.remove(index);
	}

	public void setSubtotal(int index) {
		venta.get(index).setSubtotal(venta.get(index).getPrecioVenta() * venta.get(index).getCantidadSeleccion());
		setTotal();
	}

	public void setSubtotalAGranel(int index) {
		if (venta.get(index).getCantidadSeleccion() <= ventaService.getStockActual(venta.get(index).getCodigo(), 1)) {
			venta.get(index).setSubtotal((float) Math
					.ceil((venta.get(index).getCantidadSeleccion() * venta.get(index).getPrecioVenta()) / 1000));
			setTotal();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ",
					"La cantidad ingresada supera la cantidad disponible en inventario."));
		}
	}

	public void setTotal() {
		float suma = 0.0f;
		for (int i = 0; i < venta.size(); i++) {
			suma += venta.get(i).getSubtotal();
		}
		total = suma;
	}

	public void limpiarLista() {
		if (venta != null) {
			venta = new ArrayList<Producto>();
			total = 0.0f;
		}
	}

	public void getCambioCliente() {
		cambio = pagoCliente - total;
	}

	public void limpiarPago() {
		cambio = 0.0f;
		pagoCliente = 0.0f;
	}

	public void terminarVenta() {
		if (!venta.isEmpty() || venta.size() > 0) {
			int detalleVenta = ventaService.generarDetalleVenta(total, session.getSucursal(), session.getIdUsuario());
			ventaService.registrarVenta(venta, detalleVenta);
			ticketService.printTicket(venta, total, pagoCliente, cambio);
			limpiarLista();
			limpiarPago();
		}
	}

}
