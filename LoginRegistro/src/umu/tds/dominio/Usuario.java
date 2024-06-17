package umu.tds.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private LocalDate fechaNacimiento;
	private Boolean premium;
	private PlayList masRepro;
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
	

	public void addPL(PlayList pl) {
		playlists.add(pl);
	}
	
	public PlayList buscarPL(String nombrePl) {

		for(PlayList plBusqueda: playlists) {
			if (plBusqueda.getNombre().equals(nombrePl)) 
			{
				return plBusqueda;
			}
		}
		return null;
	}
	
	public void addRecientes(Cancion c) 
	{
		if (recientes.contains(c)) 
		{
			recientes.remove(c);
		}
		if (recientes.size()>10) 
		{
			recientes.remove(9);
		}
		recientes.add(0,c);
	}
	
	public PlayList addC(Cancion c,String nombre) 
	{
		PlayList plBusqueda = this.buscarPL(nombre);
		plBusqueda.añadirCancion(c);
		return plBusqueda;
		
	}
	
	public PlayList eliminarC(Cancion c,String nombre) 
	{
		PlayList plBusqueda = this.buscarPL(nombre);
		plBusqueda.eliminarCancion(c);
		return plBusqueda;
		
	}
	
	public PlayList eliminarPL (String nombre) 
	{
		PlayList plBusqueda = this.buscarPL(nombre);
		playlists.remove(plBusqueda);
		return plBusqueda;
	}
	
	public PlayList addPL (String nombre) 
	{
		if(buscarPL(nombre)!=null) 
		{
			PlayList pl = new PlayList(nombre);
			playlists.add(pl);
			return pl;
		}
		return null;
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
		else if (edad < 21) desc =new DescuentoJovenes();
		else desc=null;
		return null;
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

	public PlayList getMasRepro() {
		return masRepro;
	}

	public void setMasRepro(PlayList masRepro) {
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
