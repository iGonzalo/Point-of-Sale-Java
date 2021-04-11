package com.puntoventa.services;

import java.util.List;

import javax.faces.bean.RequestScoped;

import com.puntoventa.DAO.ProductoDAO;
import com.puntoventa.DAO.Impl.ProductoDAOImpl;
import com.puntoventa.model.Producto;

@RequestScoped
public class ProductoService {

	private ProductoDAO productoDAO = new ProductoDAOImpl();

	public Producto findProductoByCodigo(String codigo, int sucursal) {
		return productoDAO.findProductoByCodigo(codigo, sucursal);
	}

	public boolean isProductoExists(String codigo, int sucursal) {
		return productoDAO.isProductoExists(codigo, sucursal);
	}

	public List<Producto> listAll(int sucursal) {
		return productoDAO.listAll(sucursal);
	}

	public boolean guardarProducto(Producto producto) {
		return productoDAO.guardarProducto(producto);
	}

	public void editarProducto(Producto producto) {
		productoDAO.editarProducto(producto);
	}

	public void eliminarProducto(Long id, int sucursal) {
		productoDAO.eliminarProducto(id, sucursal);
	}

	public void saveOrUpdateProducto(Producto producto) {
		if (!productoDAO.isProductoExists(producto.getCodigo(), producto.getSucursal())) {
			productoDAO.guardarProducto(producto);
		} else {
			productoDAO.editarProducto(producto);
		}
	}

}
