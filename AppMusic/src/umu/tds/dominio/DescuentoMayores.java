package umu.tds.dominio;

import umu.tds.controlador.Controlador;

public class DescuentoMayores implements Descuento{
	
	public static double DESCUENTOJOVENES = 0.5;
	
	public double calcDescuento() {
		return Controlador.precio*DESCUENTOJOVENES;
	}
	

	
}
