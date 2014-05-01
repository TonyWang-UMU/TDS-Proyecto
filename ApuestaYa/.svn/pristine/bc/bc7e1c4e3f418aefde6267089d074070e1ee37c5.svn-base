package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.*;

import org.junit.Before;
import org.junit.Test;

import resultado.*;

public class EventoTest {

	private Evento evento;

	@Before
	public void setUp() throws Exception {
		String nombre = "EventoFutbol";
		Date fecha = new Date();
		Date hora = new Date();
		Deporte deporte = new DeporteFutbol();
		List<Participante> participantes = new LinkedList<Participante>();
		Cuota cuota = new CuotaEuropea();

		evento = new Evento(nombre, fecha, hora, deporte, participantes, cuota);

	}

	@Test
	public void testObtenerDeporte() {
		evento.obtenerDeporte("DeporteFutbol");
		assertEquals("Resultado obtenerDeporte()", evento.getDeporte()
				.getClass(), DeporteFutbol.class);
		evento.obtenerDeporte("DeporteTenis");
		assertEquals("Resultado obtenerDeporte()", evento.getDeporte()
				.getClass(), DeporteTenis.class);
		evento.obtenerDeporte("DeporteFormula1");
		assertEquals("Resultado obtenerDeporte()", evento.getDeporte()
				.getClass(), DeporteFormula1.class);
	}

	@Test
	public void testObtenerCuota() {
		evento.obtenerCuota("CuotaBritanica");
		assertEquals("Resultado obtenerCuota()", evento.getCuota().getClass(),
				CuotaBritanica.class);
		evento.obtenerCuota("CuotaEuropea");
		assertEquals("Resultado obtenerCuota()", evento.getCuota().getClass(),
				CuotaEuropea.class);
	}

	@Test
	public void testRecuperarApuesta() {
		List<Pronostico> pronosticos = new LinkedList<Pronostico>();
		Cuota cuota = new CuotaEuropea();
		ApuestaOfrecida ao = new ApuestaSimple12(pronosticos, cuota);
		ao.setCodigo(11);
		evento.addApuesta(ao);

		assertEquals("Resultado recuperarApuesta()",
				evento.recuperarApuesta(11), ao);
	}

	@Test
	public void testModificarApuesta() {
		List<Pronostico> pronosticos = new LinkedList<Pronostico>();
		Cuota cuota = new CuotaEuropea();
		ApuestaOfrecida ao = new ApuestaSimple12(pronosticos, cuota);
		ao.setCodigo(11);
		evento.addApuesta(ao);

		int index = evento.getApuestas().indexOf(ao);

		Cuota nuevaCuota = new CuotaBritanica();
		ApuestaOfrecida nuevaApuesta = new ApuestaSimple1X2(pronosticos,
				nuevaCuota);

		evento.modificarApuesta(11, nuevaApuesta);
		assertEquals("Resultado modificarApuesta()",
				evento.getApuestas().get(index), nuevaApuesta);
	}

	@Test
	public void testAddApuesta() {
		List<Pronostico> pronosticos = new LinkedList<Pronostico>();
		Cuota cuota = new CuotaEuropea();
		ApuestaOfrecida ao = new ApuestaSimple12(pronosticos, cuota);
		ao.setCodigo(11);
		evento.addApuesta(ao);

		assertEquals("Resultado addApuesta()",
				evento.getApuestas().get(evento.getApuestas().size() - 1), ao);
	}

	@Test
	public void testNuevosResultados() {
		Participante participante1 = new ParticipanteEquipo("Real Murcia", new LinkedList<ParticipanteJugador>());
		Participante participante2 = new ParticipanteEquipo("Xerez", new LinkedList<ParticipanteJugador>());

		evento.getParticipantes().add(participante1);
		evento.getParticipantes().add(participante2);
		
		Resultado resultado = new Resultado();
		ResultadoFutbol futbol = new ResultadoFutbol();
		
		Equipo equipo = new Equipo();
		equipo.setEquipo("Real Murcia");
		equipo.setGoles(3);
		futbol.setLocal(equipo);
		
		Equipo equipo1 = new Equipo();
		equipo1.setEquipo("Xerez");
		equipo1.setGoles(1);
		futbol.setVisitante(equipo1);
		
		resultado.getResultadoFutbol().add(futbol);
		
		ResultadosEvento resultados = new ResultadosEvento(resultado);
		
		evento.nuevosResultados(resultados);
		
		assertEquals("Resultado nuevosResultados()", evento.getResultado().getResultadoFutbol().get(0).getLocal().getGoles(), 3);
		assertEquals("Resultado nuevosResultados()", evento.getResultado().getResultadoFutbol().get(0).getVisitante().getGoles(), 1);
	}
}
