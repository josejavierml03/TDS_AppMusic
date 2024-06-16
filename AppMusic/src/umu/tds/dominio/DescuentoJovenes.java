package umu.tds.dominio;

import umu.tds.controlador.Controlador;

public class DescuentoJovenes implements Descuento {

	@Override
	public double calcDescuento() {
		return Controlador.precio*0.2;
	}
	
}
