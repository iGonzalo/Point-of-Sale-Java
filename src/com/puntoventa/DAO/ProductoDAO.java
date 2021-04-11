package com.puntoventa.DAO;

import java.util.List;

import com.puntoventa.model.Producto;

public interface ProductoDAO {
	public Producto findProductoByCodigo(String codigo, int sucursal);

	public boolean isProductoExists(String codigo, int sucursal);

	public List<Producto> listAll(int sucursal);

	public boolean guardarProducto(Producto producto);

	public void editarProducto(Producto producto);

	public void eliminarProducto(Long id, int sucursal);
}
