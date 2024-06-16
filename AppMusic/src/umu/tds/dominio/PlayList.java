package umu.tds.dominio;

import java.util.LinkedList;
import java.util.List;

public class PlayList {
	
	private String nombre;
	private List<Cancion> canciones;
	private int id;
	
	public PlayList(String nombre) 
	{
		this.setNombre(nombre);
		this.canciones= new LinkedList<Cancion>();
	}
	
	public void añadirCancion(Cancion c) {
		if (!canciones.contains(c)) 
		{
			canciones.add(c);
		}
	}
	
	public void eliminarCancion(Cancion c) 
	{
		if (canciones.contains(c)) 
		{
			canciones.remove(c);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
