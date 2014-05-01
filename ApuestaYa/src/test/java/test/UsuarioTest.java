package test;

import static org.junit.Assert.*;

import java.util.Date;

import model.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	
	Usuario usuario;

	@Before
	public void setUp() throws Exception {
		usuario = new Usuario ("Eva", "eva@eva", "eva", "x", "eva", new Date(), "que tal", "bien");
		}
	
	@Test
	public void testComprobarIngreso() {
		usuario.comprobarIngreso("Tarjeta");
		assertEquals("Resultado comprobarIngreso()", usuario.getIngresoPorDefecto().getClass(), Tarjeta.class);
		
		usuario.comprobarIngreso("SMS");
		assertEquals("Resultado comprobarIngreso()", usuario.getIngresoPorDefecto().getClass(), SMS.class);
		
		usuario.comprobarIngreso("Paypal");
		assertEquals("Resultado comprobarIngreso()", usuario.getIngresoPorDefecto().getClass(), Paypal.class);
	}

	@Test
	public void testComprobarCobro() {
		usuario.comprobarCobro("Transferencia");
		assertEquals("Resultado comprobarCobro()", usuario.getCobroPorDefecto().getClass(), Transferencia.class);
		
		usuario.comprobarCobro("Paypal");
		assertEquals("Resultado comprobarCobro()", usuario.getCobroPorDefecto().getClass(), Paypal.class);
	}

	@Test
	public void testAddTransaccion() {
		Transaccion transaccion = new TransaccionIngreso(10000, new Date());
		usuario.addTransaccion(transaccion);
		int index = usuario.getListaTransacciones().indexOf(transaccion);
		assertEquals ("Resultado addTransaccion()", usuario.getListaTransacciones().get(index), transaccion);
	}

	@Test
	public void testAddApuesta() {
		ApuestaRealizada apuesta = new ApuestaSencilla(12, new Pronostico(2, "2"), "apuestaOfrecida", usuario.getCodigo());
		usuario.addApuesta(apuesta);
		int index = usuario.getApuestasRealizadas().indexOf(apuesta);
		assertEquals("Resultado addApuesta()", usuario.getApuestasRealizadas().get(index), apuesta);
	}
}
