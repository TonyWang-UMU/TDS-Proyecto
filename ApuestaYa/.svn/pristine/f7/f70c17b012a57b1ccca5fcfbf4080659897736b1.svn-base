package model;

import java.util.List;

import resultado.Resultado;

public class ApuestaSimple12 extends ApuestaSimple {

	public ApuestaSimple12(List<Pronostico> listaPronosticos, Cuota cuota) {
		super(listaPronosticos, cuota);
		super.setTipoApuesta(0);
	}

	@Override
	public void obtenerGanadoras(Resultado resultado) {
		int contador1 = 0;
		int contador2 = 0;
		for (int i = 0; i < resultado.getResultadoTenis().get(0).getJugador1()
				.getSet().size(); i++) {
			int setJugador1 = resultado.getResultadoTenis().get(0)
					.getJugador1().getSet().get(i).getJuegos();
			int setJugador2 = resultado.getResultadoTenis().get(0)
					.getJugador2().getSet().get(i).getJuegos();

			if (setJugador1 > setJugador2)
				contador1++;
			else
				contador2++;
		}
		if (contador1 > contador2)
			super.seleccionarGanadores(resultado.getResultadoTenis().get(0)
					.getJugador1().getNombre());
		else
			super.seleccionarGanadores(resultado.getResultadoTenis().get(0)
					.getJugador2().getNombre());

		super.cerrarApuesta();

	}

}
