package com.puntoventa.DAO;

import com.puntoventa.model.Usuario;

public interface UsuarioDAO {
	public Usuario login(String usuario, String password);
}
