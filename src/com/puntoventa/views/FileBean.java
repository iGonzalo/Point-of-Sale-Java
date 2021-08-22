package com.puntoventa.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.model.UploadedFile;

import com.puntoventa.model.Producto;
import com.puntoventa.services.ProductoService;
import com.puntoventa.utilities.Constants;
import com.puntoventa.utilities.IOUtil;

@ManagedBean(name = "fileBean")
@RequestScoped
public class FileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UploadedFile uploadedFile;

	private ProductoService productoService = new ProductoService();

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void readExcelFile() {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(uploadedFile.getInputstream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			List<Producto> productos = new ArrayList<Producto>();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Producto producto = new Producto();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					cell.setCellType(CellType.STRING);
					switch (cell.getColumnIndex()) {
					case 0:
						producto.setCodigo(cell.getStringCellValue());
						break;
					case 1:
						producto.setNombre(cell.getStringCellValue().toUpperCase());
						break;
					case 2:
						producto.setTipoVenta(Integer.valueOf(cell.getStringCellValue()));
						break;
					case 3:
						producto.setCantidadDisponible(Float.valueOf(cell.getStringCellValue()));
						break;
					case 4:
						producto.setPrecioCompra(Float.valueOf(cell.getStringCellValue()));
						break;
					case 5:
						producto.setPrecioVenta(Float.valueOf(cell.getStringCellValue()));
						break;
					case 6:
						producto.setDepartamento(Integer.valueOf(cell.getStringCellValue()));
						break;
					case 7:
						producto.setSucursal(Integer.valueOf(cell.getStringCellValue()));
						break;
					}
				}
				productos.add(producto);
			}

			for (Producto producto : productos) {
				productoService.saveOrUpdateProducto(producto);
			}

			PrimeFaces.current().executeScript(Constants.RELOAD_PAGE);

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", Constants.READ_FILE_ERROR));
		} finally {
			IOUtil.close(workbook);
		}
	}

	public void clearForm() {
		uploadedFile = null;
	}

}
