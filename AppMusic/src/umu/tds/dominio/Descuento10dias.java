package umu.tds.dominio;

import java.time.LocalDate;

import umu.tds.controlador.Controlador;

public class Descuento10dias implements Descuento {
	
	public static double DESCUENTOJOVENES = 0.8;
	public LocalDate fechaFinal;
	
	public Descuento10dias() {
		
		LocalDate fechaActual = LocalDate.now();
		int diasParaFechaLimite = 10;
		fechaFinal = fechaActual.plusDays(diasParaFechaLimite);
		
	}
	//Solo aplica el descuento si no han pasado los 10 dias de descuento
	public double calcDescuento() {
		if (fechaFinal.isAfter(LocalDate.now())) {
			return Controlador.precio*DESCUENTOJOVENES;
		}
		return Controlador.precio;
	}
	
	
}
