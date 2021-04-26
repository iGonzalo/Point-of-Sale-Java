package com.puntoventa.utilities;

public final class Constants {

	// NAVIGATION CONSTANSTS
	public static final String INDEX_PATH = "index.xhtml";
	public static final String HOME_PATH = "home.xhtml";
	public static final String ADMIN_PATH = "admin.xhtml";

	// SESSION CONSTANTS
	public static final String USER_SESSION = "usuario";
	public static final String USER_ID = "id";
	public static final String USER_ROL = "rol";
	public static final String SUCURSAL = "sucursal";

	// MESSAGES CONSTANTS
	public static final String BAD_CREDENTIALS = "Credenciales inválidas.";
	public static final String EMPTY_CREDENTIALS = "Los campos de usuario y contraseña son requeridos.";

	// DATABASE CONSTANTS
	public static final String DB_DRIIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/puntoventa?serverTimezone=UTC";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "gonzalo19";
	public static final String USER_STATEMENT = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND PASSWORD = ?";
	
	
	// AWS CONSTANTS
	public static final String ACCESS_KEY = "AKIAZXJ7GP7DGLNM47OU";
	public static final String SECRET_KEY = "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";
}
