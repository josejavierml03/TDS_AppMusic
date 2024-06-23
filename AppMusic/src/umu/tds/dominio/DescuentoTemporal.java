package umu.tds.dominio;

import java.time.LocalDate;

import umu.tds.controlador.Controlador;

public class DescuentoTemporal implements Descuento {
	
	public static double DESCUENTOJOVENES = 0.8;
	public LocalDate fechaLimite;
	
	public DescuentoTemporal() {
		
		LocalDate fechaActual = LocalDate.now();
		int diasParaFechaLimite = 10;
		fechaLimite = fechaActual.plusDays(diasParaFechaLimite);
		
	}
	public double calcDescuento() {
		if (fechaLimite.isAfter(LocalDate.now())) {
			return Controlador.precio*DESCUENTOJOVENES;
		}
		return Controlador.precio;
	}
	
	public double getDescuento() 
	{
		if (fechaLimite.isAfter(LocalDate.now())) {
			return DESCUENTOJOVENES;
		}
		return 0;		
	}
	
}
