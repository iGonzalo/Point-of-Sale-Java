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
	public Usuario login(String usuario, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Usuario usuarioReturn = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(Constants.USER_STATEMENT);
			preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				usuarioReturn = new Usuario();
				usuarioReturn.setId(resultSet.getLong(1));
				usuarioReturn.setUsername(resultSet.getString(2));
				usuarioReturn.setPassword(resultSet.getString(3));
				usuarioReturn.setRol(resultSet.getInt(4));
				usuarioReturn.setSucursal(resultSet.getInt(5));
			}
		} catch (Exception e) {
			System.out.println("Login Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return usuarioReturn;
	}

}
