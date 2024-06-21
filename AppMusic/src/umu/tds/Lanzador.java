package umu.tds;

import java.awt.EventQueue;
import java.util.Optional;

import umu.tds.gui.*;

public class Lanzador {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 LoginView ventana = new LoginView();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
				 int i = 0;
			      Optional<Integer> tarjeta=Optional.ofNullable(null);
			      tarjeta.ifPresentOrElse(ia -> ia.getClass(), () ->System.out.println("\nEl usuario no tiene descuento\r\n"));
			}
		});
	}
}