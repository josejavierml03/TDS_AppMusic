package umu.tds.dominio;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.h2.command.dml.Set;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;

public enum RepositorioCanciones {
INSTANCE;
	private FactoriaDAO factoria;
	
	private List<Cancion> canciones;
	
	private RepositorioCanciones (){
		canciones = new LinkedList<Cancion>();	
		try {
			factoria = FactoriaDAO.getInstancia();
			List<Cancion> ca = new LinkedList<Cancion>(factoria.getCancionDAO().getAll());
			ca.stream().forEach(c->addCancion(c));
			
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public void addCancion(Cancion ca) 
	{
		boolean existe = canciones.stream().anyMatch(c-> c.getInterprete().equals(ca.getInterprete())
				&& c.getTitulo().equals(ca.getTitulo())); 
		if(!existe) {
			canciones.add(ca);
		}
	}
	
	public void removeCancion(Cancion ca) {
		canciones.remove(ca);
	}
	
	public List<Cancion> findAll() {
		return canciones;
	}
	
	public List<Cancion> findTitulo(String titulo)
	{
		List<Cancion> ca = canciones.stream().filter(c-> c.getTitulo().equals(titulo)).collect(Collectors.toList());
		return ca;
	}
	
	public HashSet<String> findEstilos()
	{
		List<Cancion> ca = findAll();
	    HashSet<String> es = (HashSet<String>) ca.stream()
	                       .map(Cancion::getEstilo)
	                       .collect(Collectors.toSet());
	    return es;
	}
	
	public Cancion findCancionTiInEs(String titulo,String interprete,String estilo) 
	{
		return canciones.stream()
	            .filter(c -> c.getTitulo().equals(titulo)
	                    && c.getEstilo().equals(estilo)
	                    && c.getInterprete().equals(interprete))
	            .findFirst()
	            .orElse(null);
	}
	
	public List<Cancion> findTituloyPlaylist(List<Cancion> cancionesPl , String titulo)
	{
		List<Cancion> ca = canciones.stream().filter(c-> c.getTitulo().equals(titulo)).collect(Collectors.toList());
		if (ca != null) 
		{
			return ca.stream().filter( cancion -> cancionesPl.contains(cancion)).collect(Collectors.toList());
		
		}
		return null;
		 
	}
	//Añadir todos los find's
}

