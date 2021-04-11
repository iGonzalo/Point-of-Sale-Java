package com.puntoventa.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(Constants.DB_DRIIVER);
			connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USERNAME,
					Constants.DB_PASSWORD);
			connection.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println("Database connection error: " + e.getMessage());
		}
		return connection;
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Database close connection error: " + e.getMessage());
		}
	}

	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("Database close resultSet error: " + e.getMessage());
		}
	}

	public static void close(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			System.out.println("Database close preparedStatement error: " + e.getMessage());
		}
	}

	public static void rollback(Connection connection) {
		try {
			if (connection != null) {
				connection.rollback();
			}
		} catch (Exception e) {
			System.out.println("Database rollback error: " + e.getMessage());
		}
	}

}
