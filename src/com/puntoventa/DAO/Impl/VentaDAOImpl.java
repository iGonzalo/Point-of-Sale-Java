package com.puntoventa.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.puntoventa.DAO.VentaDAO;
import com.puntoventa.model.DetalleVenta;
import com.puntoventa.model.Producto;
import com.puntoventa.model.ProductoVendido;
import com.puntoventa.utilities.Database;

public class VentaDAOImpl implements VentaDAO {

	@Override
	public int generarDetalleVenta(Float total, int sucursal, Long idUsuario) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int detalleVenta = 0;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO DETALLE_VENTA(FECHA_HORA, TOTAL_VENTA, CVE_SUCURSAL, CVE_USUARIO) VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
		    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		    Timestamp timestamp = new Timestamp(date.getTime());
			preparedStatement.setTimestamp(1, timestamp);
			preparedStatement.setFloat(2, total);
			preparedStatement.setInt(3, sucursal);
			preparedStatement.setLong(4, idUsuario);
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				detalleVenta = resultSet.getInt(1);
			}
			connection.commit();
		} catch (Exception e) {
			Database.rollback(connection);
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return detalleVenta;
	}

	@Override
	public void registrarVenta(Producto producto, int detalleVenta) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO VENTA_PRODUCTO(CODIGO_PRODUCTO, NOMBRE_PRODUCTO, CANTIDAD_PRODUCTO, PRECIO_PRODUCTO, SUBTOTAL, CVE_DETALLE_VENTA) VALUES(?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, producto.getCodigo());
			preparedStatement.setString(2, producto.getNombre());
			preparedStatement.setFloat(3, producto.getCantidadSeleccion());
			preparedStatement.setFloat(4, producto.getPrecioVenta());
			preparedStatement.setFloat(5, producto.getSubtotal());
			preparedStatement.setInt(6, detalleVenta);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			Database.rollback(connection);
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(preparedStatement);
			Database.close(connection);
		}
	}

	@Override
	public void actualizarStock(Float cantidad, String codigo, int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"UPDATE PRODUCTO SET CANTIDAD_DISPONIBLE = CANTIDAD_DISPONIBLE - ? WHERE CODIGO_PRODUCTO = ? AND CVE_SUCURSAL = ?");
			preparedStatement.setFloat(1, cantidad);
			preparedStatement.setString(2, codigo);
			preparedStatement.setInt(3, sucursal);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			Database.rollback(connection);
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(preparedStatement);
			Database.close(connection);
		}
	}

	@Override
	public Float getStockActual(String codigo, int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Float stockActual = 0.0f;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT CANTIDAD_DISPONIBLE FROM PRODUCTO WHERE CODIGO_PRODUCTO = ? AND CVE_SUCURSAL = ?");
			preparedStatement.setString(1, codigo);
			preparedStatement.setInt(2, sucursal);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				stockActual = resultSet.getFloat(1);
			}
		} catch (Exception e) {
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return stockActual;
	}

	@Override
	public List<DetalleVenta> getDetalleVenta(String date, int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<DetalleVenta> detalleList = new ArrayList<DetalleVenta>();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT ID_DETALLE_VENTA, FECHA_HORA, TOTAL_VENTA FROM DETALLE_VENTA WHERE DATE(FECHA_HORA) = ? AND CVE_SUCURSAL = ?");
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, sucursal);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DetalleVenta detalle = new DetalleVenta();
				detalle.setId(resultSet.getLong(1));
				detalle.setFechaHora(format.format(resultSet.getTimestamp(2)));
				detalle.setTotal(resultSet.getFloat(3));
				detalleList.add(detalle);
			}
		} catch (Exception e) {
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return detalleList;
	}

	@Override
	public List<ProductoVendido> getProductoVendido(Long idDetalle) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ProductoVendido> productoList = new ArrayList<ProductoVendido>();
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT CODIGO_PRODUCTO, NOMBRE_PRODUCTO, CANTIDAD_PRODUCTO, PRECIO_PRODUCTO, SUBTOTAL FROM VENTA_PRODUCTO WHERE CVE_DETALLE_VENTA = ?");
			preparedStatement.setLong(1, idDetalle);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProductoVendido producto = new ProductoVendido();
				producto.setCodigo(resultSet.getString(1));
				producto.setNombre(resultSet.getString(2));
				producto.setCantidad(resultSet.getFloat(3));
				producto.setPrecio(resultSet.getFloat(4));
				producto.setSubtotal(resultSet.getFloat(5));
				productoList.add(producto);
			}
		} catch (Exception e) {
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return productoList;
	}

	@Override
	public void registrarMovimientoCaja(int tipo, Float cantidad, String concepto, int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO MOVIMIENTO_CAJA(FECHA_HORA, MONTO, TIPO_MOVIMIENTO, CONCEPTO, CVE_SUCURSAL) VALUES(?, ?, ?, ?, ?)");
			preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setFloat(2, cantidad);
			preparedStatement.setInt(3, tipo);
			preparedStatement.setString(4, concepto);
			preparedStatement.setInt(5, sucursal);
			if (preparedStatement.execute()) {
				preparedStatement2 = connection.prepareStatement("UPDATE CAJA SET MONTO = MONTO "
						+ (tipo == 1 ? "+" : "-") + " ? WHERE CVE_SUCURSAL = ?");
				preparedStatement2.setFloat(1, cantidad);
				preparedStatement2.setInt(2, sucursal);
				preparedStatement2.execute();
			}
			connection.commit();
		} catch (Exception e) {
			Database.rollback(connection);
			System.out.println("Movimiento Error: " + e.getMessage());
		} finally {
			Database.close(preparedStatement2);
			Database.close(preparedStatement);
			Database.close(connection);
		}
	}

	public void registrarOperacionCaja(int tipo, Float cantidad, int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE CAJA SET MONTO = MONTO "
					+ (tipo == 1 ? "+" : "-") + " ? WHERE CVE_SUCURSAL = ?");
			preparedStatement.setFloat(1, cantidad);
			preparedStatement.setInt(2, sucursal);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			Database.rollback(connection);
			System.out.println("Movimiento Error: " + e.getMessage());
		} finally {
			Database.close(preparedStatement);
			Database.close(connection);
		}
	}

}
