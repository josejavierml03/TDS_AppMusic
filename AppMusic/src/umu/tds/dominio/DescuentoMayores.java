package umu.tds.dominio;

import umu.tds.controlador.Controlador;

public class DescuentoMayores implements Descuento{
	
	public double calcDescuento() {
		return Controlador.precio*0.5;
	}
	
}
