package com.puntoventa.utilities;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IOUtil {

	public static void close(XSSFWorkbook workbook) {
		try {
			if (workbook != null) {
				workbook.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
