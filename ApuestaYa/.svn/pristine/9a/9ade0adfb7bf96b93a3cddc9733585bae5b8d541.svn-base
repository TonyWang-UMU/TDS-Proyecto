package resultado;

import java.util.Iterator;

import resultado.JugadorTenis.Set;
import resultado.ResultadoF1.Piloto;

public class Programa {

	public static void main(String[] args) {

		Resultado resultado = CargadorResultados
				.cargarResultados("xml/resultados.xml"); //Obtener fichero a cargar mediante JFileChooser en Swing sobre un Evento

		for (ResultadoFutbol futbol: resultado.getResultadoFutbol()) {
			System.out.println("Evento Futbol: " + futbol.getLocal().getEquipo() + " - " + futbol.getLocal().getGoles() + " vs "+ futbol.getVisitante().getGoles() + " - " + futbol.getVisitante().getEquipo() );
			System.out.println("   Goles del " + futbol.getLocal().getEquipo());
			for (Goleador goleador: futbol.getLocal().getGoleador()) {
				System.out.println("      " + goleador.getMinuto() + " - " + goleador.getJugador());
			}
			System.out.println("   Goles del " + futbol.getVisitante().getEquipo());
			for (Goleador goleador: futbol.getVisitante().getGoleador()) {
				System.out.println("      " + goleador.getMinuto() + " - " + goleador.getJugador());
			}			
		}
		for (ResultadoTenis tenis: resultado.getResultadoTenis()) {		
				Iterator<Set> setJugador1 = tenis.getJugador1().getSet().iterator();
				Iterator<Set> setJugador2 = tenis.getJugador2().getSet().iterator();
				System.out.println("\t" + tenis.getJugador1().getNombre() + "\t" +tenis.getJugador2().getNombre());
				for (int i=1; i <= tenis.getJugador1().getSet().size(); i++) {
					System.out.println("Set " + i + "\t" +setJugador1.next().getJuegos() + "\t\t" + setJugador2.next().getJuegos());
				}
		}
		for (ResultadoF1 f1: resultado.getResultadoF1()) {
				for (Piloto piloto: f1.getPiloto()) {
					System.out.println(piloto.getPosicion() + "\t" + piloto.getNombre() + "\t\t" + piloto.getEscuderia() + "\t\t" + piloto.getVueltaRapida());
				}
				break;
		}

	}

}
