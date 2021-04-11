package com.puntoventa.utilities;

import java.util.List;

import com.puntoventa.model.Producto;

public class Util {

	public static boolean isCodigoInList(List<Producto> productos, String codigo) {
		for (Producto producto : productos) {
			if (producto.getCodigo().equals(codigo)) {
				return true;
			}
		}
		return false;
	}

}
