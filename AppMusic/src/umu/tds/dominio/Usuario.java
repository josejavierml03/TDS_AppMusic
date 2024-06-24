package umu.tds.dominio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private LocalDate fechaNacimiento;
	private Boolean premium;
	private List<Cancion> masRepro;
	private List<PlayList> playlists;
	private List<Cancion> recientes;
	private Descuento desc;

	public Usuario(String nombre, String apellidos, String email, String login, String password,
			LocalDate fechaNacimiento) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.setPremium(false);
		this.playlists= new LinkedList<PlayList>();
		this.recientes= new LinkedList<Cancion>();
		this.desc= this.asignarDescuento();
		
	}
		
	public void addPL(PlayList lista) {
		playlists.add(lista);
	}
	
	public PlayList buscarPL(String titulo) 
	{
		Optional<PlayList> optionalPlaylist = playlists.stream()
                .filter(pl -> pl.getNombre().equals(titulo))
                .findFirst();
		PlayList playlist = optionalPlaylist.orElse(null);
		return playlist;
	}
	public List<Cancion> cancionesLista (String nombre)
	{
		PlayList pl = this.buscarPL(nombre);
		LinkedList<Cancion> lista = (LinkedList<Cancion>) pl.getCanciones(); 
		return lista;
	}
	
	public void pago() 
	{
		premium=true;
	}
	
	public double descuento() 
	{
		return desc.calcDescuento();
	}
	
	public Descuento asignarDescuento() 
	{
		Period periodo = Period.between(fechaNacimiento, LocalDate.now());
		int edad = periodo.getYears();
		if (edad>65) desc=new DescuentoMayores();
		else desc =new Descuento10dias();
		return desc;
	}
	
	public PlayList crearPl(String titulo) 
	{	
		PlayList pla = buscarPL(titulo);
		if (pla==null) 
		{
			PlayList pl = new PlayList(titulo);
			playlists.add(pl);
			return pl;
		}
		return null;
		
	}
	
	public PlayList eliminarPl(String titulo) 
	{
		PlayList playlist = buscarPL(titulo);
		if (playlist!=null) 
		{
			playlists.remove(playlist);
		}
		return playlist;
	}
	
	public PlayList addCancionToPl(String titulo, Cancion ca) 
	{
		PlayList pl = buscarPL(titulo);
		pl.añadirCancion(ca);
		return pl;
	}
	
	public PlayList eliminarCancionToPl(String titulo, int id) 
	{
		PlayList pl = buscarPL(titulo);
		pl.eliminarCancion(id);
		return pl;
	}
	
	public List<Cancion> obtenerCanciones(String titulo)
	{
		PlayList pl = buscarPL(titulo);
		return pl.getCanciones();
	}
	
	public List<String> nombrePl()
	{
		List<String> pl = getPlaylists().stream().map(p -> p.getNombre()).collect(Collectors.toList());
		return pl;
	}
	
	public List<Cancion> cancionesPl()
	{
		List<Cancion> canciones = getPlaylists().stream().flatMap(pl -> pl.getCanciones().stream()).collect(Collectors.toList());
		return canciones;
	}
	
	public Document pdf(String ruta) throws FileNotFoundException, DocumentException {
	    if (premium && !playlists.isEmpty()) {
	        FileOutputStream archivo = new FileOutputStream(ruta + "/canciones.pdf");
	        Document documento = new Document();
	        PdfWriter.getInstance(documento, archivo);
	        documento.open();

	        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
	        Paragraph titulo = new Paragraph("Listado de Playlists y Canciones", titleFont);
	        titulo.setAlignment(Element.ALIGN_CENTER);
	        documento.add(titulo);

	        Font userFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
	        Paragraph usuario = new Paragraph("Usuario " + login, userFont);
	        documento.add(usuario);

	        for (PlayList p : playlists) {
	            Font playlistFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
	            Paragraph nombrePlaylist = new Paragraph("PlayList " + p.getNombre(), playlistFont);
	            documento.add(nombrePlaylist);

	            List<Cancion> canciones = p.getCanciones();
	            Font cancionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
	            for (Cancion c : canciones) {
	            	documento.add(new Paragraph("Cancion "+ c.getTitulo(),cancionFont));
	            }
	        }

	        documento.close();
	        return documento;
	    }
	    return null;
	}
	
	public void addCancionRecientes(Cancion c) 
	{
		Iterator<Cancion> iterator = recientes.iterator();
		while (iterator.hasNext()) {
			Cancion ca = iterator.next();
		    if (ca.getId() == c.getId()) {
		        iterator.remove(); 
		        break; 
		    }
		}
		
		if(recientes.size() == 10)	recientes.remove(9); 
		recientes.add(0, c);;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Boolean getPremium() {
		return premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	public List<Cancion> getMasRepro() {
		return masRepro;
	}

	public void setMasRepro(List<Cancion> masRepro) {
		this.masRepro = masRepro;
	}

	public List<PlayList> getPlaylists() {
		return playlists;
	}

	public List<Cancion> getRecientes() {
		return recientes;
	}

	public Descuento getDesc() {
		return desc;
	}

	public void setDesc(Descuento desc) {
		this.desc = desc;
	}

}
