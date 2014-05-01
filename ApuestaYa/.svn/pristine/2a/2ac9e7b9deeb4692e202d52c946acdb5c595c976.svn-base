package model;

import java.util.List;
import java.util.StringTokenizer;

import dao.DAOException;
import dao.FactoriaDAO;

public class ApuestaMultipleSistema extends ApuestaMultiple {

	public ApuestaMultipleSistema(double cantidad, Pronostico pronostico,
			List<ApuestaRealizada> apuestasRealizadas, int usuarioApostador) {
		super(cantidad, pronostico, apuestasRealizadas, usuarioApostador);
		this.setTipoApuesta(2);
	}

	@Override
	public boolean comprobarApuestaMultiple() {
		// Obtenemos el pronostico que elegimos
		StringTokenizer strTok = new StringTokenizer(getPronostico()
				.getPronostico(), "/");
		// en el numerador tendriamos el minimo que tenemos que acertar para ser
		// ganada
		// por ejemplo en 3/5 tendremos que acertar 3 o mas para que haya premio
		int numerador = Integer.parseInt(strTok.nextToken());

		int contadorGanadoras = 0;
		int contadorFinalizadas = 0;
		for (ApuestaRealizada apuesta : super.getApuestasRealizadas()) {
			if (apuesta.isFinalizada())
				contadorFinalizadas++;

			if (apuesta.isGanadora())
				contadorGanadoras++;
		}

		// en estas apuestas solo podemos calcular cuando esten ya finalizadas
		// todas
		if (contadorFinalizadas == super.getApuestasRealizadas().size()) {
			setFinalizada(true);
			// comprobar si es ganadora
			if (contadorGanadoras >= numerador) {
				setGanadora(true);
				try {
					FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
							.getApuestaRealizadaDAO().cerrarApuesta(this);
				} catch (DAOException e) {
					e.printStackTrace();
				}
				return true;
			}
			try {
				FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
						.getApuestaRealizadaDAO().cerrarApuesta(this);
			} catch (DAOException e) {
				e.printStackTrace();
			}

		}

		return false;

	}

	private int factorial(int numero) {
		int resultado = 1;
		for (int i = numero; i > 0; i--) {
			resultado *= i;
		}
		return resultado;
	}

	private int binomial(int arriba, int debajo) {

		return ((factorial(arriba)) / (factorial(debajo) * (factorial(arriba
				- debajo))));

	}

	@Override
	public double obtenerCuotaTotal() {
		// en este caso la cuotaTotal se obtiene de manera distinta

		// primero obtenemos la ganancia total posible si acertamos todas las
		// cominaciones
		double gananciaTotal = 1;
		for (ApuestaRealizada aR : getApuestasRealizadas()) {
			gananciaTotal *= aR.getPronostico().getGanancia();
		}

		// ahora obtenemos el pronostico

		StringTokenizer strTok = new StringTokenizer(getPronostico()
				.getPronostico(), "/");

		int numerador = Integer.parseInt(strTok.nextToken());
		int denominador = Integer.parseInt(strTok.nextToken());

		// numero de posibilidades que tenemos totales acertando todo
		int posibilidades = binomial(denominador, numerador);

		// numero de aciertos
		int contadorGanadoras = 0;
		for (ApuestaRealizada apuesta : super.getApuestasRealizadas()) {

			if (apuesta.isGanadora())
				contadorGanadoras++;
		}

		// aqui solo llegan las ganadoras asi que no tenemos que comprobar que
		// sea mayor que el numerador
		// tan solo usamos este numero como la parte del denominador ahora
		int acertadas = binomial(contadorGanadoras, numerador);

		double ponderacion = (double) acertadas / (double) posibilidades;

		// hacemos la ponderacion de la ganancia y lo devolvemos

		return gananciaTotal * ponderacion;
	}
}
