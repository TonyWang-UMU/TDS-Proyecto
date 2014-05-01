package model;

import java.util.List;
import java.util.StringTokenizer;

import resultado.Resultado;
import resultado.ResultadoF1.Piloto;

public class ApuestaEspecial extends ApuestaOfrecida {

	private static final String PREGUNTA_FUTBOL_1 = "¿Quién será el primer goleador?";
	private static final String PREGUNTA_FUTBOL_2 = "¿En qué minuto se marcara el primer gol?";

	private static final String PREGUNTA_TENIS_1 = "¿Cuántos sets durará el partido?";
	private static final String PREGUNTA_TENIS_2 = "¿Quién hará más ace's?";

	private static final String PREGUNTA_F1_1 = "¿Quién quedará el última posición";
	private static final String PREGUNTA_F1_2 = "¿Quién hará la vuelta más rápida?";

	// Tendran un titulo las apuestas especiales
	// Para saber que estamos mostrando
	private String tituloApuesta;

	public ApuestaEspecial(List<Pronostico> listaPronosticos, Cuota cuota,
			String tituloApuesta) {
		super(listaPronosticos, cuota);
		this.tituloApuesta = tituloApuesta;
		super.setTipoApuesta(3);
	}

	public String getTituloApuesta() {
		return tituloApuesta;
	}

	public void setTituloApuesta(String tituloApuesta) {
		this.tituloApuesta = tituloApuesta;
	}

	@Override
	public void obtenerGanadoras(Resultado resultado) {
		if (!resultado.getResultadoFutbol().isEmpty()) {
			String goleadorLocal = "";
			int minLocal = 200;
			String goleadorVisitante = "";
			int minVisitante = 200;

			if (!resultado.getResultadoFutbol().get(0).getLocal().getGoleador()
					.isEmpty()) {
				goleadorLocal = resultado.getResultadoFutbol().get(0)
						.getLocal().getGoleador().get(0).getJugador();
				minLocal = resultado.getResultadoFutbol().get(0).getLocal()
						.getGoleador().get(0).getMinuto();
			}
			if (!resultado.getResultadoFutbol().get(0).getVisitante()
					.getGoleador().isEmpty()) {
				goleadorVisitante = resultado.getResultadoFutbol().get(0)
						.getVisitante().getGoleador().get(0).getJugador();
				minVisitante = resultado.getResultadoFutbol().get(0)
						.getVisitante().getGoleador().get(0).getMinuto();
			}

			if (tituloApuesta.equals(PREGUNTA_FUTBOL_1)) {

				if (minLocal < minVisitante)
					super.seleccionarGanadores(goleadorLocal);
				else if (minVisitante < minLocal)
					super.seleccionarGanadores(goleadorVisitante);
				else
					super.seleccionarGanadores("");

			} else if (tituloApuesta.equals(PREGUNTA_FUTBOL_2)) {
				if (minLocal < minVisitante)
					super.seleccionarGanadores(minLocal + "");
				else if (minVisitante < minLocal)
					super.seleccionarGanadores(minVisitante + "");
				else
					super.seleccionarGanadores("200");
			}

		} else if (!resultado.getResultadoTenis().isEmpty()) {
			if (tituloApuesta.equals(PREGUNTA_TENIS_1)) {
				super.seleccionarGanadores(resultado.getResultadoTenis().get(0)
						.getJugador1().getSet().size()
						+ "");

			} else if (tituloApuesta.equals(PREGUNTA_TENIS_2)) {
				int jugador1 = resultado.getResultadoTenis().get(0)
						.getJugador1().getAces();
				int jugador2 = resultado.getResultadoTenis().get(0)
						.getJugador2().getAces();

				if (jugador1 > jugador2)
					super.seleccionarGanadores(resultado.getResultadoTenis()
							.get(0).getJugador1().getNombre());
				else if (jugador2 > jugador1)
					super.seleccionarGanadores(resultado.getResultadoTenis()
							.get(0).getJugador2().getNombre());
				else
					super.seleccionarGanadores("");
			}

		} else if (!resultado.getResultadoF1().isEmpty()) {
			if (tituloApuesta.equals(PREGUNTA_F1_1)) {
				int ultimaPosicion = resultado.getResultadoF1().get(0)
						.getPiloto().size() - 1;
				super.seleccionarGanadores(resultado.getResultadoF1().get(0)
						.getPiloto().get(ultimaPosicion).getNombre());
			} else if (tituloApuesta.equals(PREGUNTA_F1_2)) {
				Piloto piloto = resultado.getResultadoF1().get(0).getPiloto()
						.get(0);
				for (Piloto p : resultado.getResultadoF1().get(0).getPiloto()) {
					piloto = pilotoRapido(p, piloto);
				}
				super.seleccionarGanadores(piloto.getNombre());
			}

		}

	}

	private Piloto pilotoRapido(Piloto p1, Piloto p2) {
		StringTokenizer strTok = new StringTokenizer(p1.getVueltaRapida(), ":");
		String vueltaRapida1 = "";
		while (strTok.hasMoreTokens()) {
			vueltaRapida1 += strTok.nextElement();
		}
		Double vueltaRapidaDouble1 = Double.parseDouble(vueltaRapida1);
		
		strTok = new StringTokenizer(p2.getVueltaRapida(), ":");
		String vueltaRapida2 = "";
		while (strTok.hasMoreTokens()) {
			vueltaRapida2 += strTok.nextElement();
		}
		Double vueltaRapidaDouble2 = Double.parseDouble(vueltaRapida2);
		
		if (vueltaRapidaDouble1 < vueltaRapidaDouble2) return p1;
		else if (vueltaRapidaDouble2 < vueltaRapidaDouble1) return p2;
		return p1;
	}

}
