package com.puntoventa.utilities;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.output.PrinterOutputStream;

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
	
	public static void close(PrinterOutputStream printerOutputStream) {
		try {
			if (printerOutputStream != null) {
				printerOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(EscPos escPos) {
		try {
			if (escPos != null) {
				escPos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
