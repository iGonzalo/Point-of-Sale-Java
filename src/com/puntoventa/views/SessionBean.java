package com.puntoventa.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.puntoventa.utilities.Constants;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String usuario;
	private Long idUsuario;
	private int rolUsuario;
	private int sucursal;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(int rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = context.getExternalContext().getSessionMap().get(Constants.USER_SESSION).toString();
		idUsuario = Long.parseLong(context.getExternalContext().getSessionMap().get(Constants.USER_ID).toString());
		rolUsuario = Integer.parseInt(context.getExternalContext().getSessionMap().get(Constants.USER_ROL).toString());
		sucursal = Integer.parseInt(context.getExternalContext().getSessionMap().get(Constants.SUCURSAL).toString());
	}

}
