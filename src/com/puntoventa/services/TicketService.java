package com.puntoventa.services;

import java.sql.Timestamp;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.print.PrintService;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst.Justification;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.output.PrinterOutputStream;
import com.puntoventa.model.Producto;
import com.puntoventa.utilities.IOUtil;

@RequestScoped
public class TicketService {
	
	@SuppressWarnings("deprecation")
	public void printTicket(List<Producto> venta, Float total, Float pagoCliente, Float cambio) {
		PrintService printService = null;
		PrinterOutputStream printerOutputStream = null;
		EscPos escpos = null;
		try {
			//System.out.println(printService);
			printService = PrinterOutputStream.getPrintServiceByName("EPSON TM-T88V ");
			printerOutputStream = new PrinterOutputStream(printService);
			escpos = new EscPos(printerOutputStream);
			Style title = new Style().setFontSize(Style.FontSize._2, Style.FontSize._2)
					.setJustification(Justification.Center);
			Style bold = new Style(escpos.getStyle()).setBold(true);
			Style justification = new Style(escpos.getStyle().setJustification(Justification.Center));
			escpos.writeLF(title, "Abarrotes BERAKA").feed(2).write("Fecha: ")
					.writeLF(new Timestamp(System.currentTimeMillis()).toLocaleString()).feed(1);
			escpos.writeLF(bold, String.format("%-15s %-8s %-10s %-10s",  "ARTICULO", "PRECIO", "CANTIDAD", "TOTAL"));
//			escpos.writeLF(bold, "ARTICULO      PRECIO    CANTIDAD     TOTAL");
			for (int i = 0; i < venta.size(); i++) {
				if (venta.get(i).getTipoVenta() == 1) {
					escpos.writeLF(String.format("%-15s %-8s %-10s %-10s", venta.get(i).getNombre() , "$ " + venta.get(i).getPrecioVenta()
							, venta.get(i).getCantidadSeleccion().intValue() , "$" + venta.get(i).getSubtotal()));
//					escpos.writeLF(String.format("%-20s %-20s %-20s %-20s", venta.get(i).getNombre() + "   $ " + venta.get(i).getPrecioVenta() + "   "
//							+ venta.get(i).getCantidadSeleccion().intValue() + "   $ " + venta.get(i).getSubtotal()));
				} else {
					escpos.writeLF(venta.get(i).getNombre() + "   $ " + venta.get(i).getPrecioVenta() + " kg" + "   "
							+ venta.get(i).getCantidadSeleccion() + " gr" + "   $ " + venta.get(i).getSubtotal());
				}
			}
			escpos.feed(1);
			escpos.writeLF(justification, "----------------------------------------");
			escpos.feed(1);
			escpos.writeLF("TOTAL                           $ " + total);
			escpos.writeLF("EFECTIVO                        $ " + pagoCliente);
			escpos.writeLF("CAMBIO                          $ " + cambio);
			escpos.feed(2);
			escpos.writeLF(justification, "******** GRACIAS POR SU COMPRA ********");
			escpos.feed(4).cut(EscPos.CutMode.FULL);
			// escpos.pulsePin(PinConnector.Pin_2, 120, 240);

		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
			if(ex.getMessage().equals("printServiceName is not found")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Aviso: ", "No se encontró ningún dispositivo para impresión."));
			}
		} finally {
			IOUtil.close(escpos);
			IOUtil.close(printerOutputStream);
		}
	}

}
