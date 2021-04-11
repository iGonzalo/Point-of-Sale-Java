package com.puntoventa.views;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.puntoventa.model.DetalleVenta;
import com.puntoventa.model.ProductoVendido;
import com.puntoventa.services.VentaService;

@ManagedBean(name = "reporteVentasBean")
@ViewScoped
public class ReporteVentasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean session;

	private VentaService ventaService = new VentaService();
	private List<DetalleVenta> detalle = new ArrayList<DetalleVenta>();
	private ProductoVendido productoVendido = new ProductoVendido();
	private List<List<ProductoVendido>> productoList = new ArrayList<List<ProductoVendido>>();
	private Float total = 0.0f;
	private Date date;
	private Date maxDate;

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public List<DetalleVenta> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleVenta> detalle) {
		this.detalle = detalle;
	}

	public ProductoVendido getProductoVendido() {
		return productoVendido;
	}

	public void setProductoVendido(ProductoVendido productoVendido) {
		this.productoVendido = productoVendido;
	}

	public List<List<ProductoVendido>> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<List<ProductoVendido>> productoList) {
		this.productoList = productoList;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	@PostConstruct
	public void init() {
		maxDate = new Date();
	}

	public void setList() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		detalle = ventaService.getDetalleVenta(format.format(date), session.getSucursal());
		if (!detalle.isEmpty()) {
			total = 0.0f;
			for (int i = 0; i < detalle.size(); i++) {
				total += detalle.get(i).getTotal();
				productoList.add(i, ventaService.getProductoVendido(detalle.get(i).getId()));
			}
		} else {
			total = 0.0f;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ",
					"La consulta no produjo resultados, intente con otra fecha."));
		}
	}

}
