package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
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

}
