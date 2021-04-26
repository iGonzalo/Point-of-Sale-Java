package com.puntoventa.utilities;

import java.util.Collection;
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
	
	public static boolean isNotNull(Object object) {
		return !object.equals(null);
	}
	
	public static boolean isNotEmpty(String string) {
		return !string.isEmpty();
	}
	
	public static boolean isNotNullOrEmpty(String string) {
		return !string.equals(null) && !string.isEmpty();
	}
	
	public static boolean isNotNullOrEmpty(Collection<?> collection) {
		return isNotNull(collection) && !collection.isEmpty();
	}

}
