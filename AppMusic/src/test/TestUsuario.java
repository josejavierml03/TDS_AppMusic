package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;

public class TestUsuario {
	Usuario usuario;
	@Before
	public void initilize() 
	{
		usuario = new Usuario("Juan","Cortado","juanlax@gmail.com","juanlax","ads",LocalDate.now());
	}
	@Test
	public void pruebaPremiumInicial() {
		assertEquals("Prueba 1",false,usuario.getPremium());
	}
	
	@Test
	public void pruebaDescuento() {
		usuario.asignarDescuento();
		assertEquals("Prueba 2", usuario.getDesc().getClass().getSimpleName(),"Descuento10dias");
	}
	@Test
	public void pruebaRecientes() {
	
        Cancion cancion = new Cancion(" ", " " + " ", " "," ");
        usuario.addCancionRecientes(cancion);
        if (usuario.getRecientes().contains(cancion)) {
        	assertTrue(true);
        }
        else {
        	assertTrue(false);
        }
	}
	
	@Test
	public void pruebaPlayList() {
	
        PlayList pl = usuario.crearPl("a");
        if (pl!=null) 
        {
        	assertTrue(true);
        }
        else {
        	assertTrue(false);
        }
	}
	
	@Test
	public void pruebaPlayList2() {
		
        usuario.crearPl("a");
        PlayList pl2 = usuario.crearPl("a");
        if (pl2!=null) 
        {
        	assertTrue(false);
        }
        else {
        	assertTrue(true);
        }
	}
	
	@Test
	public void pruebaPlayList3() {
		
        PlayList pl = usuario.crearPl("a");
        if (usuario.getPlaylists().contains(pl)) 
        {
        	assertTrue(true);
        }
        else {
        	assertTrue(false);
        }
	}
	
	@Test
	public void pruebaPlayList4() {
		
        PlayList pl = usuario.crearPl("a");
        usuario.eliminarPl(pl.getNombre());
        if (!usuario.getPlaylists().contains(pl)) 
        {
        	assertTrue(true);
        }
        else {
        	assertTrue(false);
        }
	}
	@Test
	public void crearPDF() throws FileNotFoundException, DocumentException 
	{
		Document d = usuario.pdf("C:\\Users\\juan3\\OneDrive\\Escritorio");
		if (d == null) 
		{
			assertTrue(true);
        }
        else {
        	assertTrue(false);
        }
	}
	@Test
	public void crearPDF2() throws FileNotFoundException, DocumentException 
	{
		usuario.setPremium(true);
		usuario.crearPl("a");
		Document d = usuario.pdf("C:\\Users\\juan3\\OneDrive\\Escritorio"); //Añadir tu escritorio para que tu test funcione
		if (d != null) 
		{
			assertTrue(true);
        }
        else {
        	assertTrue(false);
        }
	}
	
	@Test
	public void crearPago() 
	{
	usuario.pago();
	assertEquals("Prueba 1",true,usuario.getPremium());
	}
	
	

}
