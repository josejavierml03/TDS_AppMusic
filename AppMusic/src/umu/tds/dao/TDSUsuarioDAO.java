package umu.tds.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import beans.Entidad;
import beans.Propiedad;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * Cambiar la fecha de string a date
 */
public final class TDSUsuarioDAO implements UsuarioDAO {

	private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String PREMIUM = "premium";
	private static final String PLAYLISTS = "playlists";
	private static final String RECIENTES = "recientes";

	private ServicioPersistencia servPersistencia;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {
		
		List<PlayList> lista= new LinkedList<PlayList>();
		List<Cancion> listaCanciones= new LinkedList<Cancion>();
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		listaCanciones = idtoCancion(servPersistencia.recuperarPropiedadEntidad(eUsuario,RECIENTES));
		lista = idtoPlayList(servPersistencia.recuperarPropiedadEntidad(eUsuario,PLAYLISTS));
		LocalDate fechaD = LocalDate.parse(fechaNacimiento, dateFormat);
		
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaD);
		usuario.setId(eUsuario.getId());
		if (premium != null) {
			 if (premium.equals("true")) 
			{
				usuario.setPremium(true);
			}
			else 
			{
				usuario.setPremium(false);
			}
		}
		lista.stream().forEach(usuario::addPL);
		listaCanciones.stream().forEach(usuario::addCancionRecientes);

		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		String s;
		if (usuario.getPremium()) {s="true";}
		else {s="false";}

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getLogin()), new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento().format(dateFormat)),new Propiedad(PREMIUM,s),
				new Propiedad(RECIENTES,cancionToId(usuario.getRecientes())),new Propiedad(PLAYLISTS,playlistToId(usuario.getPlaylists())))));
		return eUsuario;
	}

	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}
	

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
	}
	
	private String cancionToId(List<Cancion> c) {
		 String linea = "";
		 for (Cancion cancion : c) {
		 linea += cancion.getId() + " ";
		 }
		 return linea.trim();
	} 
	
	private String playlistToId(List<PlayList> pl) {
		 String linea = "";
		 for (PlayList playlist : pl) {
		 linea += playlist.getId() + " ";
		 }
			 
		
		 return linea.trim();
	}
	
	private List<PlayList> idtoPlayList(String lineas) {
		 List<PlayList> playlist = new LinkedList<PlayList>();
		 if (lineas!=null) {
			StringTokenizer strTok = new StringTokenizer(lineas, " ");
		 	TDSPlayListDAO adaptador = new TDSPlayListDAO();
		 	while (strTok.hasMoreTokens()) {
			 	playlist.add(adaptador.get(Integer.valueOf((String) strTok.nextElement())));
		 	}
		 }
		 return playlist;
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

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		String s;
		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals(LOGIN)) {
				prop.setValor(usuario.getLogin());
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(usuario.getFechaNacimiento().format(dateFormat));
			} else if (prop.getNombre().equals(PREMIUM)) {
				if (usuario.getPremium()) s = "true" ;
				else s = "false" ;
				prop.setValor(s);
			}else if (prop.getNombre().equals(PLAYLISTS)) {
				prop.setValor(String.valueOf(playlistToId(usuario.getPlaylists())));
			} else if (prop.getNombre().equals(RECIENTES)) {
				prop.setValor(String.valueOf(cancionToId(usuario.getRecientes())));
			} 
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}
		
		return usuarios;
	}

}
