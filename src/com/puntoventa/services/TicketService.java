package com.puntoventa.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.print.PrintService;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst.Justification;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.output.PrinterOutputStream;
import com.puntoventa.model.Producto;

@RequestScoped
public class TicketService {
	
	private PrintService printService = PrinterOutputStream.getPrintServiceByName("EPSON TM-T88V");

	@SuppressWarnings("deprecation")
	public void printTicket(List<Producto> venta, Float total, Float pagoCliente, Float cambio) {
		try {
			//System.out.println(printService);
			PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
			EscPos escpos = new EscPos(printerOutputStream);
			Style title = new Style().setFontSize(Style.FontSize._2, Style.FontSize._2)
					.setJustification(Justification.Center);
			Style bold = new Style(escpos.getStyle()).setBold(true);
			escpos.writeLF(title, "Abarrotes BERAKA").feed(2).write("Fecha: ")
					.writeLF(new Timestamp(System.currentTimeMillis()).toLocaleString()).feed(1);
			escpos.writeLF(bold, "ARTICULO      PRECIO    CANTIDAD     TOTAL");
			for (int i = 0; i < venta.size(); i++) {
				if (venta.get(i).getTipoVenta() == 1) {
					escpos.writeLF(venta.get(i).getNombre() + "   $ " + venta.get(i).getPrecioVenta() + "   "
							+ venta.get(i).getCantidadSeleccion().intValue() + "   $ " + venta.get(i).getSubtotal());
				} else {
					escpos.writeLF(venta.get(i).getNombre() + "   $ " + venta.get(i).getPrecioVenta() + " kg" + "   "
							+ venta.get(i).getCantidadSeleccion() + " gr" + "   $ " + venta.get(i).getSubtotal());
				}
			}
			escpos.feed(1);
			escpos.writeLF("----------------------------------------");
			escpos.feed(1);
			escpos.writeLF("TOTAL                           $ " + total);
			escpos.writeLF("EFECTIVO                        $ " + pagoCliente);
			escpos.writeLF("CAMBIO                          $ " + cambio);
			escpos.feed(2);
			escpos.writeLF("********* GRACIAS POR SU COMPRA *********");
			escpos.feed(4).cut(EscPos.CutMode.FULL);
			// escpos.pulsePin(PinConnector.Pin_2, 120, 240);
			escpos.close();

		} catch (IOException ex) {
			System.out.println("ERROR: " + ex.getStackTrace());
		} 
	}

}
