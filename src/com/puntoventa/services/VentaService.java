package com.puntoventa.services;

import java.util.List;

import javax.faces.bean.RequestScoped;

import com.puntoventa.DAO.VentaDAO;
import com.puntoventa.DAO.Impl.VentaDAOImpl;
import com.puntoventa.model.DetalleVenta;
import com.puntoventa.model.Producto;
import com.puntoventa.model.ProductoVendido;

@RequestScoped
public class VentaService {

	private AmazonService amazonService = new AmazonService();

	private VentaDAO ventaDAO = new VentaDAOImpl();

	public int generarDetalleVenta(Float total, int sucursal, Long idUsuario) {
		return ventaDAO.generarDetalleVenta(total, sucursal, idUsuario);
	}

	public void registrarVenta(List<Producto> venta, int detalleVenta) {
		for (Producto producto : venta) {
			ventaDAO.registrarVenta(producto, detalleVenta);
			ventaDAO.actualizarStock(producto.getCantidadSeleccion(), producto.getCodigo(), producto.getSucursal());
			Float stockActual = ventaDAO.getStockActual(producto.getCodigo(), producto.getSucursal());
			if (stockActual == 0.0 || (stockActual <= 100 && producto.getTipoVenta() == 2)) {
				amazonService.sendSMSMessage(producto.getCodigo(), producto.getNombre(), producto.getSucursal());
			}
		}
	}

	public Float getStockActual(String codigo, int sucursal) {
		return ventaDAO.getStockActual(codigo, sucursal);
	}

	public List<DetalleVenta> getDetalleVenta(String date, int sucursal) {
		return ventaDAO.getDetalleVenta(date, sucursal);
	}

	public List<ProductoVendido> getProductoVendido(Long idDetalle) {
		return ventaDAO.getProductoVendido(idDetalle);
	}

	public void registrarMovimientoCaja(int tipo, Float cantidad, String concepto, int sucursal) {
		ventaDAO.registrarMovimientoCaja(tipo, cantidad, concepto, sucursal);
	}

}
