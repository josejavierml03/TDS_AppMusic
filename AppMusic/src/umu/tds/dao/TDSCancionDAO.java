package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;

public class TDSCancionDAO implements CancionDAO {
	
	private static final String CANCION = "Cancion";

	private static final String TITULO = "titulo";
	private static final String RUTA = "ruta";
	private static final String REPRODUCCIONES = "numRepros";
	private static final String INTERPRETE = "interprete";
	private static final String ESTILO = "estilo";

	private ServicioPersistencia servPersistencia;
	
	public TDSCancionDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private Cancion entidadToCancion(Entidad cancion) 
	{
		String titulo = servPersistencia.recuperarPropiedadEntidad(cancion, TITULO);
		String ruta = servPersistencia.recuperarPropiedadEntidad(cancion, RUTA);
		int repros = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(cancion, REPRODUCCIONES));
		String interprete = servPersistencia.recuperarPropiedadEntidad(cancion, INTERPRETE);
		String estilo = servPersistencia.recuperarPropiedadEntidad(cancion, ESTILO);
		
		Cancion c = new Cancion(titulo,ruta,interprete,estilo);
		c.setNumRepros(repros);
		c.setId(cancion.getId());
		
		return c;
	}
	
	private Entidad usuarioToEntidad(Cancion cancion) {
		Entidad eCancion = new Entidad();
		eCancion.setNombre(CANCION);
	

		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(TITULO, cancion.getTitulo()),
				new Propiedad(RUTA, cancion.getRuta()), new Propiedad(REPRODUCCIONES, String.valueOf(cancion.getNumRepros())),
				new Propiedad(INTERPRETE, cancion.getInterprete()),
				new Propiedad(ESTILO, cancion.getEstilo()))));
		return eCancion;
	}

	@Override
	public void create(Cancion c) {
		Entidad eCancion;
		eCancion = this.usuarioToEntidad(c);
		eCancion = servPersistencia.registrarEntidad(eCancion);
		c.setId(eCancion.getId());
	}

	@Override
	public boolean delete(Cancion c) {
		Entidad eCancion= servPersistencia.recuperarEntidad(c.getId());
		return servPersistencia.borrarEntidad(eCancion);
	}

	@Override
	public void update(Cancion c) {
		Entidad eCancion = servPersistencia.recuperarEntidad(c.getId());

		for (Propiedad prop : eCancion.getPropiedades()) {
			if (prop.getNombre().equals(TITULO)) {
				prop.setValor(c.getTitulo());
			} else if (prop.getNombre().equals(RUTA)) {
				prop.setValor(c.getRuta());
			} else if (prop.getNombre().equals(REPRODUCCIONES)) {
				prop.setValor(String.valueOf(c.getNumRepros()));
			} else if (prop.getNombre().equals(INTERPRETE)) {
				prop.setValor(c.getInterprete());
			} else if (prop.getNombre().equals(ESTILO)) {
				prop.setValor(c.getEstilo());
			}
			servPersistencia.modificarPropiedad(prop);
		}			
	}

	@Override
	public Cancion get(int id) {
		Entidad eCancion = servPersistencia.recuperarEntidad(id);

		return entidadToCancion(eCancion);
	}

	@Override
	public List<Cancion> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(CANCION);

		List<Cancion> canciones = new LinkedList<Cancion>();
		for (Entidad eCancion : entidades) {
			canciones.add(get(eCancion.getId()));
		}
		
		return canciones;
	}

}
