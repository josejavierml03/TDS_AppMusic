package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;

public class TDSPlayListDAO implements PlayListDAO {

	private static final String PLAYLIST = "PlayList";
	
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "canciones";
	
	private ServicioPersistencia servPersistencia;
	
	public TDSPlayListDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private PlayList entidadToPlayList(Entidad ePlayList) {
		List<Cancion> canciones = new LinkedList<Cancion>();
		String nombre = servPersistencia.recuperarPropiedadEntidad(ePlayList, NOMBRE);
		canciones = idtoCancion(servPersistencia.recuperarPropiedadEntidad(ePlayList,CANCIONES));
		
		PlayList pl = new PlayList (nombre);
		pl.setId(ePlayList.getId());
		canciones.stream().forEach(pl::añadirCancion);
		return pl;
	}
	
	private Entidad playListToEntindad(PlayList pl) 
	{
		Entidad ePlayList = new Entidad();
		ePlayList.setNombre(PLAYLIST);
		ePlayList.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE,pl.getNombre()),
				new Propiedad(CANCIONES,cancionToId(pl.getCanciones()))))
				);
		return ePlayList;
	}
	
	private String cancionToId(List<Cancion> c) {
		 String linea = "";
		 for (Cancion cancion : c) {
		 linea += cancion.getId() + " ";
		 }
		 return linea.trim();
	} 
	
	private List<Cancion> idtoCancion(String lineas) {
		 List<Cancion> canciones = new LinkedList<Cancion>();
		 if (lineas!=null) {
			StringTokenizer strTok = new StringTokenizer(lineas, " ");
		 	TDSCancionDAO adaptador = new TDSCancionDAO();
		 	while (strTok.hasMoreTokens()) {
			 	canciones.add(adaptador.get(Integer.valueOf((String) strTok.nextElement())));
		 	}
		 }
		 return canciones;
	}
	
	@Override
	public void create(PlayList pl) {
		Entidad eUsuario = this.playListToEntindad(pl);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		pl.setId(eUsuario.getId());
	}

	@Override
	public boolean delete(PlayList pl) {
		Entidad ePlayList;
		ePlayList = servPersistencia.recuperarEntidad(pl.getId());

		return servPersistencia.borrarEntidad(ePlayList);
	}

	@Override
	public void update(PlayList pl) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(pl.getId());
		for (Propiedad prop : ePlayList.getPropiedades()) {
			if(prop.getNombre().equals(CANCIONES)) 
			{
				prop.setValor(String.valueOf(cancionToId(pl.getCanciones())));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public PlayList get(int id) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(id);

		return entidadToPlayList(ePlayList);
	}

	@Override
	public List<PlayList> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(PLAYLIST);

		List<PlayList> pl = new LinkedList<PlayList>();
		for (Entidad ePlayList : entidades) {
			pl.add(get(ePlayList.getId()));
		}
		
		return pl;
	}

}
