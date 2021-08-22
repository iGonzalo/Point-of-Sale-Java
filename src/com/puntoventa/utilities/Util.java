package com.puntoventa.utilities;

import java.util.Base64;
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
	
	public static boolean isEquals(String string1, String string2) {
		return string1.equals(string2);
	}
	
	public static boolean isNotNull(Object object) {
		return object != null;
	}
	
	public static boolean isNotEmpty(String string) {
		return !string.isEmpty();
	}
	
	public static boolean isNotNullOrEmpty(String string) {
		return isNotNull(string) && isNotEmpty(string);
	}
	
	public static boolean isNotNullOrEmpty(Collection<?> collection) {
		return isNotNull(collection) && !collection.isEmpty();
	}
	
	public static String getDecodedString(String stringToDecode) {
		byte[] decoded = Base64.getDecoder().decode(stringToDecode);
		return new String(decoded);
	}

}
