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
	public static final String DB_URL = "amRiYzpteXNxbDovL2xvY2FsaG9zdDozMzA2L3B1bnRvdmVudGE/c2VydmVyVGltZXpvbmU9VVRD";
	public static final String DB_USERNAME = "cm9vdA==";
	public static final String DB_PASSWORD = "Z29uemFsbzE5";

	// STATEMENTS
	public static final String FIND_USER = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND PASSWORD = ?";

	public static final String FIND_PRODUCT = "SELECT * FROM PRODUCTO WHERE CODIGO_PRODUCTO = ? AND CVE_SUCURSAL = ?";
	public static final String FIND_ID_PRODUCT = "SELECT ID_PRODUCTO FROM PRODUCTO WHERE CODIGO_PRODUCTO = ? AND CVE_SUCURSAL = ?";
	public static final String FIND_ALL_PRODUCTS = "SELECT * FROM PRODUCTO WHERE CVE_SUCURSAL = ?";
	public static final String INSERT_PRODUCT = "INSERT INTO PRODUCTO(CODIGO_PRODUCTO, NOMBRE, CANTIDAD_DISPONIBLE, PRECIO_COMPRA, PRECIO_VENTA, TIPO_VENTA, CVE_DEPARTAMENTO, CVE_SUCURSAL) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_PRODUCT = "UPDATE PRODUCTO SET NOMBRE = ?, CANTIDAD_DISPONIBLE = ?, PRECIO_COMPRA = ?, PRECIO_VENTA = ?, TIPO_VENTA = ?, CVE_DEPARTAMENTO = ? WHERE CODIGO_PRODUCTO = ?";
	public static final String DELETE_PRODUCT = "DELETE FROM PRODUCTO WHERE ID_PRODUCTO = ? && CVE_SUCURSAL = ?";

	public static final String INSERT_SALE_DETAIL = "INSERT INTO DETALLE_VENTA(FECHA_HORA, TOTAL_VENTA, CVE_SUCURSAL, CVE_USUARIO) VALUES(?, ?, ?, ?)";
	public static final String INSERT_PRODUCT_SALE= "INSERT INTO VENTA_PRODUCTO(CODIGO_PRODUCTO, NOMBRE_PRODUCTO, CANTIDAD_PRODUCTO, PRECIO_PRODUCTO, SUBTOTAL, CVE_DETALLE_VENTA) VALUES(?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_STOCK = "UPDATE PRODUCTO SET CANTIDAD_DISPONIBLE = CANTIDAD_DISPONIBLE - ? WHERE CODIGO_PRODUCTO = ? AND CVE_SUCURSAL = ?";
	public static final String GET_ACTUAL_STOCK = "SELECT CANTIDAD_DISPONIBLE FROM PRODUCTO WHERE CODIGO_PRODUCTO = ? AND CVE_SUCURSAL = ?";
	public static final String GET_SALE_DETAIL = "SELECT ID_DETALLE_VENTA, FECHA_HORA, TOTAL_VENTA FROM DETALLE_VENTA WHERE DATE(FECHA_HORA) = ? AND CVE_SUCURSAL = ?";
	public static final String GET_PRODUCT_SALE = "SELECT CODIGO_PRODUCTO, NOMBRE_PRODUCTO, CANTIDAD_PRODUCTO, PRECIO_PRODUCTO, SUBTOTAL FROM VENTA_PRODUCTO WHERE CVE_DETALLE_VENTA = ?";
	
	// AWS
	public static final String ACCESS_KEY = "QUtJQVpYSjdHUDdER0xOTTQ3T1U=";
	public static final String SECRET_KEY = "NHlJRGxoRlBLWEszejVRcnlYanNyVzN1VXo2MW5RZkNaWmFzSnRlLw==";

	// SCRIPTS
	public static final String HELPER_HIDE = "$('.ui-helper-hidden-accessible :input[type=text]').hide()";
	public static final String RELOAD_PAGE = "window.location.href = window.location.href;";
}
