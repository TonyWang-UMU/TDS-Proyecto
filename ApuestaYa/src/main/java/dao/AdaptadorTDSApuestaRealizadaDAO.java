package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import model.*;

public class AdaptadorTDSApuestaRealizadaDAO implements ApuestaRealizadaDAO {
	private ServicioPersistencia servPersistencia;

	public AdaptadorTDSApuestaRealizadaDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance()
				.getServicioPersistencia();
	}

	public ApuestaRealizada recuperarApuestaRealizada(int codigo) {
		Entidad eApuestaRealizada;
		int tipoApuesta;
		double cantidad;
		// para el pronostico de una sencilla
		int codPronostico;
		// para el pronostico de una multiple
		String pronostico;
		String codApuestaOfrecida;
		String finalizada;
		String ganadora;

		int codUsuario;

		eApuestaRealizada = servPersistencia.recuperarEntidad(codigo);

		tipoApuesta = Integer.parseInt(servPersistencia
				.recuperarPropiedadEntidad(eApuestaRealizada, "tipoApuesta"));

		cantidad = Double.parseDouble(servPersistencia
				.recuperarPropiedadEntidad(eApuestaRealizada, "cantidad"));
		codUsuario = Integer.parseInt(servPersistencia
				.recuperarPropiedadEntidad(eApuestaRealizada, "codUsuario"));
		finalizada = servPersistencia.recuperarPropiedadEntidad(
				eApuestaRealizada, "finalizada");
		ganadora = servPersistencia.recuperarPropiedadEntidad(
				eApuestaRealizada, "ganadora");

		List<ApuestaRealizada> listaApuestas;
		// Crear apuestas diferentes dependiendo del tipo

		if (tipoApuesta == 0) {

			codPronostico = Integer
					.parseInt(servPersistencia.recuperarPropiedadEntidad(
							eApuestaRealizada, "pronostico"));
			codApuestaOfrecida = servPersistencia.recuperarPropiedadEntidad(
					eApuestaRealizada, "apuestaOfrecida");
			try {
				ApuestaSencilla apuestaSencilla = new ApuestaSencilla(cantidad,
						this.recuperarPronostico(codPronostico),
						codApuestaOfrecida, codUsuario);

				if (finalizada.equals("1")) {
					apuestaSencilla.setFinalizada(true);
				}

				if (ganadora.equals("1")) {
					apuestaSencilla.setGanadora(true);
				}

				return apuestaSencilla;
			} catch (DAOException e) {

			}
		} else if (tipoApuesta == 1) {
			pronostico = servPersistencia.recuperarPropiedadEntidad(
					eApuestaRealizada, "pronostico");
			// en este caso el pronostico no se guarda persistente, porque no es
			// un pronostico de apuesta sencilla.
			// lo guardamos como una cadena de ganancia y pronostico por lo que
			// tenemos que recuperarla
			// en la apuesta combinada funciona igual
			StringTokenizer strTok = new StringTokenizer(pronostico, "-");

			listaApuestas = obtenerListaApuestas(servPersistencia
					.recuperarPropiedadEntidad(eApuestaRealizada,
							"listaApuestas"));

			ApuestaMultipleCombinada apuestaCombinada = new ApuestaMultipleCombinada(
					cantidad, new Pronostico(Double.parseDouble(strTok
							.nextToken()), ""), listaApuestas, codUsuario);

			if (finalizada.equals("1")) {
				apuestaCombinada.setFinalizada(true);
			}

			if (ganadora.equals("1")) {
				apuestaCombinada.setGanadora(true);
			}

			return apuestaCombinada;

		} else if (tipoApuesta == 2) {

			pronostico = servPersistencia.recuperarPropiedadEntidad(
					eApuestaRealizada, "pronostico");
			StringTokenizer strTok = new StringTokenizer(pronostico, "-");
			String cadenaPronostico = strTok.nextToken();
			Double gananciaPronostico = Double.parseDouble(strTok.nextToken());
			listaApuestas = obtenerListaApuestas(servPersistencia
					.recuperarPropiedadEntidad(eApuestaRealizada,
							"listaApuestas"));

			ApuestaMultipleSistema apuestaSistema = new ApuestaMultipleSistema(
					cantidad, new Pronostico(gananciaPronostico,
							cadenaPronostico), listaApuestas, codUsuario);

			if (finalizada.equals("1")) {
				apuestaSistema.setFinalizada(true);
			}

			if (ganadora.equals("1")) {
				apuestaSistema.setGanadora(true);
			}

			return apuestaSistema;

		}
		return null;
	}

	public ApuestaRealizada registrarApuestaRealizada(
			ApuestaRealizada apuestaRealizada) {
		Entidad eApuestaRealizada;
		eApuestaRealizada = new Entidad();
		eApuestaRealizada.setNombre("apuestaRealizada");
		eApuestaRealizada.setPropiedades(new ArrayList<Propiedad>(Arrays
				.asList(new Propiedad("tipoApuesta", String
						.valueOf(apuestaRealizada.getTipoApuesta())),
						new Propiedad("cantidad", String
								.valueOf(apuestaRealizada.getCantidad())),
						new Propiedad("codUsuario",
								String.valueOf(apuestaRealizada
										.getUsuarioApostador())))));

		eApuestaRealizada = servPersistencia
				.registrarEntidad(eApuestaRealizada);

		if (apuestaRealizada.isFinalizada()) {

			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"finalizada", "1");
		} else {
			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"finalizada", "0");
		}

		if (apuestaRealizada.isGanadora()) {
			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"ganadora", "1");
		} else {
			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"ganadora", "0");

		}

		if (apuestaRealizada.getTipoApuesta() == 0) {

			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"apuestaOfrecida",
					((ApuestaSencilla) apuestaRealizada).getApuestaOfrecida());

			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"pronostico", String.valueOf(apuestaRealizada
							.getPronostico().getCodigo()));
		} else if (apuestaRealizada.getTipoApuesta() == 1) {
			String listaApuestas = obtenerListaApuestas(((ApuestaMultiple) apuestaRealizada)
					.getApuestasRealizadas());
			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"listaApuestas", listaApuestas);
			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"pronostico", apuestaRealizada.getPronostico()
							.getPronostico()
							+ "-"
							+ apuestaRealizada.getPronostico().getGanancia());

		} else if (apuestaRealizada.getTipoApuesta() == 2) {
			String listaApuestas = obtenerListaApuestas(((ApuestaMultiple) apuestaRealizada)
					.getApuestasRealizadas());
			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"listaApuestas", listaApuestas);
			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"pronostico", apuestaRealizada.getPronostico()
							.getPronostico()
							+ "-"
							+ apuestaRealizada.getPronostico().getGanancia());

		}
		apuestaRealizada.setCodigo(eApuestaRealizada.getId());
		return apuestaRealizada;
	}

	private String obtenerListaApuestas(List<ApuestaRealizada> apuestaRealizada) {
		String cadenaDevolver = "";
		for (ApuestaRealizada aR : apuestaRealizada) {
			cadenaDevolver += aR.getCodigo() + "-";
		}

		return cadenaDevolver;
	}

	private List<ApuestaRealizada> obtenerListaApuestas(String listaApuestas) {
		LinkedList<ApuestaRealizada> listaDevolver = new LinkedList<ApuestaRealizada>();
		StringTokenizer strTok = new StringTokenizer(listaApuestas, "-");
		ApuestaRealizada aR;
		int codigo;
		while (strTok.hasMoreTokens()) {
			try {
				codigo = Integer.parseInt((String) strTok.nextElement());
				aR = this.recuperarApuestaRealizada(codigo);
				// Cuando recuperamos una apuesta de la base de datos y la
				// instanciamos
				// tenemos que acordarnos de asignarle el correspondiente codigo
				aR.setCodigo(codigo);
				listaDevolver.add(aR);
			} catch (NumberFormatException e) {

			}
		}
		return listaDevolver;
	}

	private Pronostico recuperarPronostico(int codigo) throws DAOException {
		return FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
				.getPronosticoDAO().recuperarPronostico(codigo);
	}

	public ApuestaRealizada cerrarApuesta(ApuestaRealizada apuestaRealizada) {
		Entidad eApuestaRealizada;

		eApuestaRealizada = servPersistencia.recuperarEntidad(apuestaRealizada
				.getCodigo());
		// actualizamos parametro de cerrada

		if (apuestaRealizada.isGanadora()) {
			servPersistencia.eliminarPropiedadEntidad(eApuestaRealizada,
					"ganadora");
			servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
					"ganadora", "1");
		}

		servPersistencia.eliminarPropiedadEntidad(eApuestaRealizada,
				"finalizada");
		servPersistencia.anadirPropiedadEntidad(eApuestaRealizada,
				"finalizada", "1");

		// Modificamos la lista de apuestas realizadas del usuario actual para
		// indicarle en linea que esa apuesta ha sido cerrada o ganadora tambien

		List<ApuestaRealizada> listaApuestasUsuario = CatalogoUsuarios
				.getUnicaInstancia()
				.getUsuario(apuestaRealizada.getUsuarioApostador())
				.getApuestasRealizadas();
		for (ApuestaRealizada aR : listaApuestasUsuario) {
			if (aR.getCodigo() == apuestaRealizada.getCodigo()) {
				aR.finalizar();
				if (apuestaRealizada.isGanadora()) {
					aR.setGanadora(true);
				}
			}
		}

		// Lo mismo para la las apuestas de las ofrecidas del evento, pero solo
		// las simples. Las multiples solo pertenecen al usuario
		if (apuestaRealizada.getTipoApuesta() == 0) {

			List<ApuestaOfrecida> listaApuestasEvento = CatalogoEventos
					.getUnicaInstancia()
					.getEvento(
							FactoriaDAO
									.getFactoriaDAO()
									.getApuestaOfrecidaDAO()
									.recuperarApuestaOfrecida(
											Integer.parseInt(((ApuestaSencilla) apuestaRealizada)
													.getApuestaOfrecida()))
									.getCodEvento()).getApuestas();

			for (ApuestaOfrecida aO : listaApuestasEvento) {
				if (aO.getCodigo() == Integer
						.parseInt(((ApuestaSencilla) apuestaRealizada)
								.getApuestaOfrecida())) {

					for (ApuestaRealizada aR : aO.getApuestasRealizadas()) {
						if (aR.getCodigo() == apuestaRealizada.getCodigo()) {
							aR.finalizar();
							if (apuestaRealizada.isGanadora()) {
								aR.setGanadora(true);
							}
						}
					}

				}
			}
		}

		return apuestaRealizada;
	}

}
