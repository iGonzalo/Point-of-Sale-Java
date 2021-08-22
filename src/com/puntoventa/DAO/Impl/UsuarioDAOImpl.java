package com.puntoventa.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.puntoventa.DAO.UsuarioDAO;
import com.puntoventa.model.Usuario;
import com.puntoventa.utilities.Constants;
import com.puntoventa.utilities.Database;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public Usuario login(String user, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Usuario usuario = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(Constants.FIND_USER);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				usuario = new Usuario();
				usuario.setId(resultSet.getLong(1));
				usuario.setUsername(resultSet.getString(2));
				usuario.setPassword(resultSet.getString(3));
				usuario.setRol(resultSet.getInt(4));
				usuario.setSucursal(resultSet.getInt(5));
			}
		} catch (Exception e) {
			System.out.println("Login Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return usuario;
	}

}
