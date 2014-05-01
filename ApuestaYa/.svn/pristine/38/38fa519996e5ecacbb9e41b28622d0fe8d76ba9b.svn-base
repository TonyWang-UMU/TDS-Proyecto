package model;

import java.util.List;

import resultado.Resultado;
import resultado.ResultadoF1.Piloto;

public class ApuestaSimpleVictoria extends ApuestaSimple {

	public ApuestaSimpleVictoria(List<Pronostico> listaPronosticos, Cuota cuota) {
		super(listaPronosticos, cuota);
		super.setTipoApuesta(2);
	}

	@Override
	public void obtenerGanadoras(Resultado resultado) {
		// Suponemos que estan ordenados. En caso de no estar ordenados, buscar
		// por posicion.
		for (Piloto p : resultado.getResultadoF1().get(0).getPiloto()) {
			if (p.getPosicion() == 1) {
				super.seleccionarGanadores(resultado.getResultadoF1().get(0)
						.getPiloto().get(0).getNombre());
				break;
			}
		}
		super.cerrarApuesta();

	}
}
