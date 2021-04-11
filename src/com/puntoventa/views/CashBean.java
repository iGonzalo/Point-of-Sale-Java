package com.puntoventa.views;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.puntoventa.services.VentaService;

@ManagedBean(name = "cashBean")
@RequestScoped
public class CashBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean session;

	private VentaService ventaService = new VentaService();

	private Float cantidadIngreso = 0.0f;
	private Float cantidadEgreso = 0.0f;
	private String concepto;

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public Float getCantidadIngreso() {
		return cantidadIngreso;
	}

	public void setCantidadIngreso(Float cantidadIngreso) {
		this.cantidadIngreso = cantidadIngreso;
	}

	public Float getCantidadEgreso() {
		return cantidadEgreso;
	}

	public void setCantidadEgreso(Float cantidadEgreso) {
		this.cantidadEgreso = cantidadEgreso;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public void registrarMovimientoCaja(int tipo) {
		ventaService.registrarMovimientoCaja(tipo, tipo == 1 ? cantidadIngreso : cantidadEgreso, concepto,
				session.getSucursal());
	}
}
