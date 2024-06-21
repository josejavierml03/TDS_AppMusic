package umu.tds.services;

import java.util.EventListener;
import java.util.EventObject;

public interface IEncendidoListener extends EventListener {
	
	public void enteradoCambioEncendido (EventObject e);
	
}