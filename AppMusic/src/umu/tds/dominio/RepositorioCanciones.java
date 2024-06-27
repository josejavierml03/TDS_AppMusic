package umu.tds.dominio;

import java.io.File;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import umu.tds.dao.CancionDAO;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;

public enum RepositorioCanciones {
INSTANCE;
	private FactoriaDAO factoria;
	
	private List<Cancion> canciones;
	private CancionDAO cancionDAO;
	
	private RepositorioCanciones (){
		canciones = new LinkedList<Cancion>();	
		try {
			factoria = FactoriaDAO.getInstancia();
			cancionDAO = factoria.getCancionDAO();
			this.cancionesLocal();
			List<Cancion> ca = new LinkedList<Cancion>(factoria.getCancionDAO().getAll());
			ca.stream().forEach(c->addCancion(c));
			
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public void cancionesLocal() 
	{
        File directorio = new File("src/canciones");

        if (directorio.exists() && directorio.isDirectory()) {

            File[] fichero = directorio.listFiles((dir, name) -> name.endsWith(".mp3"));

            if (fichero != null) {
                for (File file : fichero) {
                    String nombreFichero = file.getName().replace(".mp3", "");

                    String[] parts = nombreFichero.split(" - ");

                    if (parts.length == 3) {
                        String titulo = parts[0].trim();
                        String interprete = parts[1].trim();
                        String estilo = parts[2].trim();
                        Cancion ca = new Cancion(titulo, file.getName(), interprete, estilo);
                        List<Cancion> cancionesBBDD = new LinkedList<Cancion>(factoria.getCancionDAO().getAll());
                        boolean existe = cancionesBBDD.stream().anyMatch(c-> c.getTitulo().equals(ca.getTitulo()) && c.getInterprete().equals(ca.getInterprete()));
                        if (!existe) {
                        	cancionDAO.create(ca);
                        }
                    }
                }
            }
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
	
	public void reproducida(Cancion c) {
		
		Cancion cancion = c;
		for(Cancion ca: canciones) {
			if(ca.getId() == c.getId()) {
				cancion = ca;
				break;
			}
		}
		
		cancion.reproducida();
		cancionDAO.update(cancion);
	}
	
	public List<Cancion> findAll() {
		return canciones;
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
	
	public Cancion findCancionTiIn(String titulo,String interprete) 
	{
		return canciones.stream()
	            .filter(c -> c.getTitulo().equals(titulo)
	                    && c.getInterprete().equals(interprete))
	            .findFirst()
	            .orElse(null);
	}
	
	public List<Cancion> findCancionTiEs(String titulo,String estilo) 
	{
		return canciones.stream()
	            .filter(c -> c.getTitulo().equals(titulo) && c.getEstilo().equals(estilo))
	            .collect(Collectors.toList());      
	}
	
	public List<Cancion> findCancionInEs(String interprete,String estilo) 
	{
		return canciones.stream()
	            .filter(c -> c.getInterprete().equals(interprete) && c.getEstilo().equals(estilo))
	            .collect(Collectors.toList());   
	}
	

	public List<Cancion> findTitulo(String titulo)
	{
		return canciones.stream().filter(c-> c.getTitulo().equals(titulo))
				.collect(Collectors.toList());	
	}
	
	public List<Cancion> findInterprete(String interprete) 
	{
		return canciones.stream().filter(c->c.getInterprete().equals(interprete))
				.collect(Collectors.toList());			
	}
	
	public List<Cancion> findMasRepro()
	{
		return canciones.stream()
		        .sorted(Comparator.comparingInt(Cancion::getNumRepros).reversed())
		        .limit(10)
		        .collect(Collectors.toList());
	}
	
	
	public List<Cancion> findEstilo(String estilo) 
	{
		return canciones.stream().filter(c->c.getEstilo().equals(estilo))
				.collect(Collectors.toList()); 
	}
	
	
	public List<Cancion> findTituloyPlaylist(List<Cancion> cancionesPl , String titulo)
	{
		List<Cancion> ca = canciones.stream().filter(c-> c.getTitulo().equals(titulo)).collect(Collectors.toList());
		if (ca != null) 
		{
			LinkedList<Cancion> salida = new LinkedList<>();
			for (Cancion c: ca) 
			{
				for (Cancion can : cancionesPl) 
				{
					if (c.getTitulo().equals(can.getTitulo()) && c.getInterprete().equals(can.getInterprete())) {
						salida.add(c);
					}
				}
			}
			return salida;
		
		}
		return null; 
	}
	
	public List<Cancion> findEstiloyPlaylist(List<Cancion> cancionesPl , String estilo)
	{
		List<Cancion> ca = canciones.stream().filter(c->c.getEstilo().equals(estilo)).collect(Collectors.toList());
		if (ca != null) 
		{
			LinkedList<Cancion> salida = new LinkedList<>();
			for (Cancion c: ca) 
			{
				for (Cancion can : cancionesPl) 
				{
					if (c.getTitulo().equals(can.getTitulo()) && c.getInterprete().equals(can.getInterprete())) {
						salida.add(c);
					}
				}
			}
			return salida;
		
		}
		return null; 
	}
	
	public List<Cancion> findInterpreteyPlaylist(List<Cancion> cancionesPl , String interprete)
	{
		List<Cancion> ca = canciones.stream().filter(c->c.getInterprete().equals(interprete))
				.collect(Collectors.toList());
		if (ca != null) 
		{
			LinkedList<Cancion> salida = new LinkedList<>();
			for (Cancion c: ca) 
			{
				for (Cancion can : cancionesPl) 
				{
					if (c.getTitulo().equals(can.getTitulo()) && c.getInterprete().equals(can.getInterprete())) {
						salida.add(c);
					}
				}
			}
			return salida;
		
		}
		return null; 
	}
	
	public List<Cancion> findInEsyPlaylist(List<Cancion> cancionesPl,String interprete,String estilo)
	{
		List<Cancion> ca = canciones.stream()
	            .filter(c -> c.getInterprete().equals(interprete) && c.getEstilo().equals(estilo))
	            .collect(Collectors.toList());   
		if (ca != null) 
		{
			LinkedList<Cancion> salida = new LinkedList<>();
			for (Cancion c: ca) 
			{
				for (Cancion can : cancionesPl) 
				{
					if (c.getTitulo().equals(can.getTitulo()) && c.getInterprete().equals(can.getInterprete())) {
						salida.add(c);
					}
				}
			}
			return salida;
		
		}
		return null; 
	}
	
	
	
	//Añadir todos los find's playList
}

