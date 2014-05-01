package model;

import java.util.List;

import dao.DAOException;
import dao.FactoriaDAO;

public class ApuestaMultipleCombinada extends ApuestaMultiple {

	public ApuestaMultipleCombinada(double cantidad, Pronostico pronostico,
			List<ApuestaRealizada> apuestasRealizadas, int usuarioApostador) {
		super(cantidad, pronostico, apuestasRealizadas, usuarioApostador);
		this.setTipoApuesta(1);
	}

	@Override
	public boolean comprobarApuestaMultiple() {

		int contadorGanadoras = 0;
		int contadorFinalizadas = 0;
		for (ApuestaRealizada apuesta : super.getApuestasRealizadas()) {

			if (apuesta.isFinalizada()) {
				contadorFinalizadas++;
			}
			if (apuesta.isGanadora())
				contadorGanadoras++;
		}

		if (contadorGanadoras == super.getApuestasRealizadas().size()) {
			setFinalizada(true);
			setGanadora(true);
			try {
				FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
						.getApuestaRealizadaDAO().cerrarApuesta(this);
			} catch (DAOException e) {
				e.printStackTrace();
			}

			return true;

		}
		if (contadorFinalizadas == super.getApuestasRealizadas().size()) {
			setFinalizada(true);
			try {
				FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
						.getApuestaRealizadaDAO().cerrarApuesta(this);
			} catch (DAOException e) {
				e.printStackTrace();
			}

		}
		return false;

	}

	@Override
	public double obtenerCuotaTotal() {
		double gananciaTotal = 1;
		for (ApuestaRealizada aR : getApuestasRealizadas()) {
			gananciaTotal *= aR.getPronostico().getGanancia();
		}
		return gananciaTotal;
	}
}
