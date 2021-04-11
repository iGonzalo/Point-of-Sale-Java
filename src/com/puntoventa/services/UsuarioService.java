package com.puntoventa.services;

import javax.faces.bean.RequestScoped;

import com.puntoventa.DAO.UsuarioDAO;
import com.puntoventa.DAO.Impl.UsuarioDAOImpl;
import com.puntoventa.model.Usuario;

@RequestScoped
public class UsuarioService {

	private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	
	public Usuario login(String usuario, String password) {
		return usuarioDAO.login(usuario, password);
	}
	
}
