package com.puntoventa.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.puntoventa.DAO.ProductoDAO;
import com.puntoventa.model.Producto;
import com.puntoventa.utilities.Database;

public class ProductoDAOImpl implements ProductoDAO {

	@Override
	public Producto findProductoByCodigo(String codigo, int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Producto producto = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM PRODUCTO WHERE CODIGO_PRODUCTO = ? AND CVE_SUCURSAL = ?");
			preparedStatement.setString(1, codigo);
			preparedStatement.setInt(2, sucursal);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				producto = new Producto();
				producto.setId(resultSet.getLong(1));
				producto.setCodigo(resultSet.getString(2));
				producto.setNombre(resultSet.getString(3));
				producto.setCantidadDisponible(resultSet.getFloat(4));
				producto.setPrecioCompra(resultSet.getFloat(5));
				producto.setPrecioVenta(resultSet.getFloat(6));
				producto.setTipoVenta(resultSet.getInt(7));
				producto.setDepartamento(resultSet.getInt(8));
				producto.setSucursal(resultSet.getInt(9));
			}

		} catch (Exception e) {
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return producto;
	}

	@Override
	public boolean isProductoExists(String codigo, int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT ID_PRODUCTO FROM PRODUCTO WHERE CODIGO_PRODUCTO = ? AND CVE_SUCURSAL = ?");
			preparedStatement.setString(1, codigo);
			preparedStatement.setInt(2, sucursal);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return flag;
	}

	@Override
	public List<Producto> listAll(int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Producto producto = null;
		List<Producto> productos = new ArrayList<Producto>();
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCTO WHERE CVE_SUCURSAL = ?");
			preparedStatement.setInt(1, sucursal);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				producto = new Producto();
				producto.setId(resultSet.getLong(1));
				producto.setCodigo(resultSet.getString(2));
				producto.setNombre(resultSet.getString(3));
				producto.setCantidadDisponible(resultSet.getFloat(4));
				producto.setPrecioCompra(resultSet.getFloat(5));
				producto.setPrecioVenta(resultSet.getFloat(6));
				producto.setTipoVenta(resultSet.getInt(7));
				producto.setDepartamento(resultSet.getInt(8));
				producto.setSucursal(resultSet.getInt(9));
				productos.add(producto);
			}
		} catch (Exception e) {
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(resultSet);
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return productos;
	}

	@Override
	public boolean guardarProducto(Producto producto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean guardado = false;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO PRODUCTO(CODIGO_PRODUCTO, NOMBRE, CANTIDAD_DISPONIBLE, PRECIO_COMPRA, PRECIO_VENTA, TIPO_VENTA, CVE_DEPARTAMENTO, CVE_SUCURSAL) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, producto.getCodigo());
			preparedStatement.setString(2, producto.getNombre());
			preparedStatement.setFloat(3, producto.getCantidadDisponible());
			preparedStatement.setFloat(4, producto.getPrecioCompra());
			preparedStatement.setFloat(5, producto.getPrecioVenta());
			preparedStatement.setInt(6, producto.getTipoVenta());
			preparedStatement.setInt(7, producto.getDepartamento());
			preparedStatement.setInt(8, producto.getSucursal());
			guardado = preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			Database.rollback(connection);
			System.out.println("Producto Error: " + e.getMessage());
		} finally {
			Database.close(preparedStatement);
			Database.close(connection);
		}
		return guardado;
	}

	@Override
	public void editarProducto(Producto producto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(
					"UPDATE PRODUCTO SET NOMBRE = ?, CANTIDAD_DISPONIBLE = ?, PRECIO_COMPRA = ?, PRECIO_VENTA = ?, TIPO_VENTA = ?, CVE_DEPARTAMENTO = ? WHERE CODIGO_PRODUCTO = ?");
			preparedStatement.setString(1, producto.getNombre());
			preparedStatement.setFloat(2, producto.getCantidadDisponible());
			preparedStatement.setFloat(3, producto.getPrecioCompra());
			preparedStatement.setFloat(4, producto.getPrecioVenta());
			preparedStatement.setInt(5, producto.getTipoVenta());
			preparedStatement.setInt(6, producto.getDepartamento());
			preparedStatement.setString(7, producto.getCodigo());
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
	public void eliminarProducto(Long id, int sucursal) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM PRODUCTO WHERE ID_PRODUCTO = ? && CVE_SUCURSAL = ?");
			preparedStatement.setLong(1, id);
			preparedStatement.setInt(2, sucursal);
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

}
