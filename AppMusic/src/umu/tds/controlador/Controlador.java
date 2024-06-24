package umu.tds.controlador;

import umu.tds.dao.UsuarioDAO;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import com.itextpdf.text.DocumentException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import umu.tds.componente.Canciones;
import umu.tds.componente.CargadorCanciones;
import umu.tds.componente.MapperCancionesXMLtoJava;
import umu.tds.dao.CancionDAO;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.PlayListDAO;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.RepositorioCanciones;
import umu.tds.dominio.RepositorioUsuarios;

public enum Controlador implements PropertyChangeListener {
	INSTANCE;
	private Usuario usuarioActual;
	private FactoriaDAO factoria;
	
	private UsuarioDAO usuarioDAO;
	private CancionDAO cancionDAO;
	private PlayListDAO plDAO;
	
	private CargadorCanciones cargador;
	
	private MediaPlayer mediaPlayer;
	
	public static double precio = 6;
	
	public void propertyChange(PropertyChangeEvent evento) 
	{
		Canciones canciones = MapperCancionesXMLtoJava.cargarCanciones(evento.getNewValue().toString());
		for(umu.tds.componente.Cancion cancion: canciones.getCancion()) {
			Cancion ca = new Cancion(cancion.getTitulo(), cancion.getURL(),cancion.getInterprete(), cancion.getEstilo() );
			cancionDAO.create(ca);
			RepositorioCanciones.INSTANCE.addCancion(ca);
		}
	}
	
	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
			cargador = CargadorCanciones.getUnicaInstancia();
			cargador.addCancionChangeListener(this);
			cancionDAO = factoria.getCancionDAO();
			usuarioDAO = factoria.getUsuarioDAO();
			plDAO = factoria.getPlayListDAO();
			activarReproductor(); 
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return RepositorioUsuarios.INSTANCE.findUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			if (!usuarioActual.getPremium()) 
			{
				usuarioActual.asignarDescuento();
			}
			else {
				List<Cancion> cancionesMasRepro = RepositorioCanciones.INSTANCE.findMasRepro();
				usuarioActual.setMasRepro(cancionesMasRepro);
			}
			return true;
		}
		return false;
	}
	
	public boolean loginUsuarioGit(String nombre) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(nombre);
		if (usuario != null) {
			this.usuarioActual = usuario;
			if (!usuarioActual.getPremium()) 
			{
				this.addMasRepro();
			}
			else {
				this.addMasRepro();
			}
			return true;
		}
		return false;
	}
	
	public boolean comprobarPremium() {
		return usuarioActual.getPremium();
	}
	
	public void cargarCanciones(String fichero) {
		cargador.setCancion(fichero);
	}
	
	private void activarReproductor() {
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	
	public void reproducirCancion(Cancion cancion) {	
		URL url = null;
		try {
			url = new URL(cancion.getRuta().replaceAll("\\s", ""));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		
		Media media = new Media(url.toString());
		mediaPlayer = new MediaPlayer(media); 
		mediaPlayer.play();
				
		usuarioActual.addCancionRecientes(cancion);	
		usuarioDAO.update(usuarioActual);	
		RepositorioCanciones.INSTANCE.reproducida(cancion);	

	}
	
	public void pausarCancion() {
		mediaPlayer.pause();
	}
	
	public void reanudarCancion() {
		mediaPlayer.play();
	}
	
	public void pararCancion() {
		mediaPlayer.stop();
	}
	
	public List<Cancion> recientes(){
		return usuarioActual.getRecientes();
	}
	
	public List<String> nombrePlayLists(){
		return usuarioActual.nombrePl();
	}
	
	public Boolean crearPl(String nombre){
		PlayList pl = usuarioActual.crearPl(nombre);
		if (pl!=null) 
		{
			plDAO.create(pl);
			usuarioDAO.update(usuarioActual);
			return true;
		}
		return false;
	}
	
	public Boolean eliminarPl(String nombre) 
	{
		Boolean existe=false;
		PlayList pl = usuarioActual.eliminarPl(nombre);
		if (pl!=null) 
		{
			existe=true;
			plDAO.delete(pl);
			usuarioDAO.update(usuarioActual);
			
		}
		return existe;
	}
	
	public void addCancionPl(String pl, Cancion ca)
	{
		PlayList lista = usuarioActual.addCancionToPl(pl, ca);
		plDAO.update(lista);
	}
	
	public void eliminarCancionPl(String pl, int id)
	{
		PlayList lista = usuarioActual.eliminarCancionToPl(pl,id);
		plDAO.update(lista);
	}
	
	public Boolean PDF(String ruta) throws FileNotFoundException, DocumentException
	{
		if (usuarioActual.pdf(ruta)!=null) {
			return true;
		}
		return false;
	}
	
	public HashSet<String> estilos()
	{
		return RepositorioCanciones.INSTANCE.findEstilos();
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			LocalDate fechaNacimiento) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);
		usuarioDAO.create(usuario);

		RepositorioUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}
	
	public List<Cancion> cancionesPl(String titulo)
	{
		return usuarioActual.obtenerCanciones(titulo);
	}
	
	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		usuarioDAO.delete(usuario);

		RepositorioUsuarios.INSTANCE.removeUsuario(usuario);
		return true;
	}
	
	public void usuarioPremium() 
	{
		usuarioActual.pago();
		this.addMasRepro();
		usuarioDAO.update(usuarioActual);
		
	}
	
	public void addMasRepro() 
	{
		List<Cancion> cancionesMasRepro = RepositorioCanciones.INSTANCE.findMasRepro();
		usuarioActual.setMasRepro(cancionesMasRepro);
	}
	
	public List<Cancion> getMasRepro(){
		return usuarioActual.getMasRepro();
	}
	
	public double aplicarDescuento() 
	{
		return usuarioActual.descuento();
	}
	
	public List<Cancion> getAllCanciones(){
		return RepositorioCanciones.INSTANCE.findAll();
	}
	
	public List<Cancion> getCancionesTitulo(String titulo){
		return RepositorioCanciones.INSTANCE.findTitulo(titulo);
	}
	
	public List<Cancion> getCancionesTituloPl(String titulo)
	{
		List<Cancion> canciones = usuarioActual.cancionesPl();
		return RepositorioCanciones.INSTANCE.findTituloyPlaylist(canciones,titulo);
	}
	
	public Cancion getCancionTituloInterpreteEstilo(String titulo,String interprete,String estilo) 
	{
		return RepositorioCanciones.INSTANCE.findCancionTiInEs(titulo, interprete, estilo);
	}
	
}
