package umu.tds.componente;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

@SuppressWarnings("serial")
public class CargadorCanciones implements Serializable{

	private static CargadorCanciones instancia;
	private String archivoCanciones;
	private PropertyChangeSupport cancionesNuevas;
	
	public CargadorCanciones() 
	{
		cancionesNuevas = new PropertyChangeSupport(this);
	}
	
	public static CargadorCanciones getUnicaInstancia() {
		if (instancia == null) {
			instancia = new CargadorCanciones();
		}
		return instancia;
	}
	
	public void setSueldo(String canciones) {
		String oldCanciones = this.archivoCanciones;
		this.archivoCanciones = canciones;
		cancionesNuevas.firePropertyChange("archivoCanciones", oldCanciones, archivoCanciones);
	}
	
	public void addSueldoChangeListener(PropertyChangeListener pcl) {
		cancionesNuevas.addPropertyChangeListener(pcl);
	}
	
	public void removeSueldoChangeListener(PropertyChangeListener pcl) {
		cancionesNuevas.removePropertyChangeListener(pcl);
	} 
}
