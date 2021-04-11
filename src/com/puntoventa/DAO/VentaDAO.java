package com.puntoventa.DAO;

import java.util.List;

import com.puntoventa.model.DetalleVenta;
import com.puntoventa.model.Producto;
import com.puntoventa.model.ProductoVendido;

public interface VentaDAO {
	public int generarDetalleVenta(Float total, int sucursal, Long idUsuario);

	public void registrarVenta(Producto producto, int detalleVenta);

	public void actualizarStock(Float cantidad, String codigo, int sucursal);

	public Float getStockActual(String codigo, int sucursal);

	public List<DetalleVenta> getDetalleVenta(String date, int sucursal);

	public List<ProductoVendido> getProductoVendido(Long idDetalle);

	public void registrarMovimientoCaja(int tipo, Float cantidad, String concepto, int sucursal);
}
