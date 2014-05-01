package model;

import java.util.List;

import resultado.Resultado;

public class ApuestaSimple1X2 extends ApuestaSimple {

	public ApuestaSimple1X2(List<Pronostico> listaPronosticos, Cuota cuota) {
		super(listaPronosticos, cuota);
		super.setTipoApuesta(1);
	}

	@Override
	public void obtenerGanadoras(Resultado resultado) {
		if (resultado.getResultadoFutbol().get(0).getLocal().getGoles() > resultado
				.getResultadoFutbol().get(0).getVisitante().getGoles()) {
			super.seleccionarGanadores("1");
		} else if (resultado.getResultadoFutbol().get(0).getLocal().getGoles() < resultado
				.getResultadoFutbol().get(0).getVisitante().getGoles()) {
			super.seleccionarGanadores("2");
		} else {
			super.seleccionarGanadores("X");
		}
		super.cerrarApuesta();
	}

}
