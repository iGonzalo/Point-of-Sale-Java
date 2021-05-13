package com.puntoventa.utilities;

public final class Constants {

	// NAVIGATION
	public static final String INDEX_PATH = "index.xhtml";
	public static final String HOME_PATH = "home.xhtml";
	public static final String ADMIN_PATH = "admin.xhtml";

	// SESSION
	public static final String USER_SESSION = "usuario";
	public static final String USER_ID = "id";
	public static final String USER_ROL = "rol";
	public static final String SUCURSAL = "sucursal";

	// MESSAGES (INFO, WARN, ERROR)
	public static final String BAD_CREDENTIALS = "Credenciales inválidas.";
	public static final String EMPTY_CREDENTIALS = "Los campos de usuario y contraseña son requeridos.";
	public static final String NO_RESULTS = "La consulta no produjo resultados, intente con otra fecha.";
	public static final String READ_FILE_ERROR = "Ocurrió un error durante la lectura del archivo.";

	// DATABASE
	public static final String DB_DRIIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/puntoventa?serverTimezone=UTC";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "gonzalo19";
	
	// STATEMENTS
	public static final String USER_STATEMENT = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND PASSWORD = ?";
	
	// AWS
	public static final String ACCESS_KEY = "AKIAZXJ7GP7DGLNM47OU";
	public static final String SECRET_KEY = "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";
	
	// SCRIPTS
	public static final String HELPER_HIDE = "$('.ui-helper-hidden-accessible :input[type=text]').hide()";
	public static final String RELOAD_PAGE = "window.location.href = window.location.href;";
}
