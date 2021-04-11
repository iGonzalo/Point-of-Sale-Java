package com.puntoventa.views;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.puntoventa.model.Usuario;
import com.puntoventa.services.UsuarioService;
import com.puntoventa.utilities.Constants;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioService usuarioService = new UsuarioService();

		if (!usuario.trim().equals("") && !password.trim().equals("")) {
			Usuario usuarioReturn = usuarioService.login(usuario, password);
			if (usuarioReturn != null) {
				context.getExternalContext().getSessionMap().put(Constants.USER_SESSION, usuarioReturn.getUsername());
				context.getExternalContext().getSessionMap().put(Constants.USER_ID, usuarioReturn.getId());
				context.getExternalContext().getSessionMap().put(Constants.USER_ROL, usuarioReturn.getRol());
				context.getExternalContext().getSessionMap().put(Constants.SUCURSAL, usuarioReturn.getSucursal());
				try {
					if (usuarioReturn.getRol() == 1) {
						context.getExternalContext().redirect(Constants.ADMIN_PATH);
					} else {
						context.getExternalContext().redirect(Constants.HOME_PATH);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Constants.BAD_CREDENTIALS));
			}
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Constants.BAD_CREDENTIALS));
		}
	}

	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().invalidateSession();
			context.getExternalContext().redirect(Constants.INDEX_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
