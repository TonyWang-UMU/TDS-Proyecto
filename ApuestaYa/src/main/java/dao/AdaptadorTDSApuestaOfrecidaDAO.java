package dao;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import beans.Entidad;
import beans.Propiedad;
import model.*;

public class AdaptadorTDSApuestaOfrecidaDAO implements ApuestaOfrecidaDAO {
	private ServicioPersistencia servPersistencia;

	public AdaptadorTDSApuestaOfrecidaDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance()
				.getServicioPersistencia();
	}

	public ApuestaOfrecida registrarApuestaOfrecida(
			ApuestaOfrecida apuestaOfrecida) {
		Entidad eApuestaOfrecida;
		eApuestaOfrecida = new Entidad();
		eApuestaOfrecida.setNombre("apuestaOfrecida");
		eApuestaOfrecida.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("tipoApuesta", String.valueOf(apuestaOfrecida
						.getTipoApuesta())),
				new Propiedad("apuestasRealizadas",
						obtenerApuestasString(apuestaOfrecida
								.getApuestasRealizadas())),
				new Propiedad("pronosticos",
						pronosticosToString(apuestaOfrecida
								.getListaPronosticos())), new Propiedad(
						"cuota", apuestaOfrecida.getCuota().getClass()
								.getSimpleName()))));
		eApuestaOfrecida = servPersistencia.registrarEntidad(eApuestaOfrecida);

		if (apuestaOfrecida instanceof ApuestaEspecial)
			servPersistencia.anadirPropiedadEntidad(eApuestaOfrecida,
					"tituloApuesta",
					((ApuestaEspecial) apuestaOfrecida).getTituloApuesta());

		apuestaOfrecida.setCodigo(eApuestaOfrecida.getId());
		return apuestaOfrecida;
	}

	private String pronosticosToString(List<Pronostico> listaPronosticos) {
		String strPronostico = "";
		for (Pronostico pronostico : listaPronosticos) {
			strPronostico += pronostico.getCodigo() + "-";
		}
		return strPronostico;
	}

	private List<Pronostico> pronosticosToList(String strPronosticos) {
		List<Pronostico> listaPronosticos = new LinkedList<Pronostico>();
		StringTokenizer strTok = new StringTokenizer(strPronosticos, "-");
		int codigo;
		Pronostico pronostico;
		while (strTok.hasMoreTokens()) {
			try {
				// Cuando recuperamos un pronostico de la base de datos y la
				// instanciamos
				// tenemos que acordarnos de asignarle el correspondiente codigo
				codigo = Integer.parseInt((String) strTok.nextElement());
				pronostico = this.recuperarPronostico(codigo);
				pronostico.setCodigo(codigo);
				listaPronosticos.add(pronostico);
			} catch (NumberFormatException e) {
			} catch (DAOException e) {
			}
		}

		return listaPronosticos;
	}

	public ApuestaOfrecida recuperarApuestaOfrecida(int codigo) {
		Entidad eApuestaOfrecida;
		int tipoApuesta;
		String cuota;
		String pronosticos;
		String tituloApuesta;
		String apuestasRealizadasStr;
		int codEvento;
		eApuestaOfrecida = servPersistencia.recuperarEntidad(codigo);

		tipoApuesta = Integer.parseInt(servPersistencia
				.recuperarPropiedadEntidad(eApuestaOfrecida, "tipoApuesta"));
		cuota = servPersistencia.recuperarPropiedadEntidad(eApuestaOfrecida,
				"cuota");
		pronosticos = servPersistencia.recuperarPropiedadEntidad(
				eApuestaOfrecida, "pronosticos");
		apuestasRealizadasStr = servPersistencia.recuperarPropiedadEntidad(
				eApuestaOfrecida, "apuestasRealizadas");
		codEvento = Integer.parseInt(servPersistencia
				.recuperarPropiedadEntidad(eApuestaOfrecida, "codEvento"));
		List<Pronostico> listaProsnosticos = pronosticosToList(pronosticos);
		List<ApuestaRealizada> listaApuestas = obtenerApuestasList(apuestasRealizadasStr);

		if (tipoApuesta == 0) {
			ApuestaSimple12 apuestaSimple12 = new ApuestaSimple12(
					listaProsnosticos, null);
			apuestaSimple12.obtenerCuota(cuota);
			apuestaSimple12.setApuestasRealizadas(listaApuestas);
			apuestaSimple12.setCodigo(codigo);
			apuestaSimple12.setCodEvento(codEvento);
			return apuestaSimple12;

		} else if (tipoApuesta == 1) {
			ApuestaSimple1X2 apuestaSimple1X2 = new ApuestaSimple1X2(
					listaProsnosticos, null);
			apuestaSimple1X2.obtenerCuota(cuota);
			apuestaSimple1X2.setApuestasRealizadas(listaApuestas);
			apuestaSimple1X2.setCodigo(codigo);
			apuestaSimple1X2.setCodEvento(codEvento);
			return apuestaSimple1X2;

		} else if (tipoApuesta == 2) {
			ApuestaSimpleVictoria apuestaSimpleVictoria = new ApuestaSimpleVictoria(
					listaProsnosticos, null);
			apuestaSimpleVictoria.obtenerCuota(cuota);
			apuestaSimpleVictoria.setApuestasRealizadas(listaApuestas);
			apuestaSimpleVictoria.setCodigo(codigo);
			apuestaSimpleVictoria.setCodEvento(codEvento);
			return apuestaSimpleVictoria;

		} else if (tipoApuesta == 3) {
			tituloApuesta = servPersistencia.recuperarPropiedadEntidad(
					eApuestaOfrecida, "tituloApuesta");
			ApuestaEspecial apuestaEspecial = new ApuestaEspecial(
					listaProsnosticos, null, tituloApuesta);
			apuestaEspecial.obtenerCuota(cuota);
			apuestaEspecial.setApuestasRealizadas(listaApuestas);
			apuestaEspecial.setCodigo(codigo);
			apuestaEspecial.setCodEvento(codEvento);
			return apuestaEspecial;

		}

		return null;

	}

	private Pronostico recuperarPronostico(int codigo) throws DAOException {
		return FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
				.getPronosticoDAO().recuperarPronostico(codigo);
	}

	private String obtenerApuestasString(List<ApuestaRealizada> listaApuestas) {
		String strApuestas = "";
		for (ApuestaRealizada aR : listaApuestas) {
			strApuestas += aR.getCodigo() + "-";
		}
		return strApuestas;
	}

	private List<ApuestaRealizada> obtenerApuestasList(String apuestasStr) {
		List<ApuestaRealizada> listApuestas = new LinkedList<ApuestaRealizada>();
		StringTokenizer strTok = new StringTokenizer(apuestasStr, "-");
		int codigo;
		ApuestaRealizada aR;
		while (strTok.hasMoreTokens()) {
			try {
				// Cuando recuperamos una apuesta de la base de datos y la
				// instanciamos
				// tenemos que acordarnos de asignarle el correspondiente codigo
				codigo = Integer.parseInt((String) strTok.nextElement());
				aR = this.recuperarApuestaRealizada(codigo);
				aR.setCodigo(codigo);
				listApuestas.add(aR);
			} catch (NumberFormatException e) {
			} catch (DAOException e) {
			}
		}

		return listApuestas;
	}

	public ApuestaOfrecida modificarApuestaOfrecida(
			ApuestaOfrecida apuestaOfrecida) {
		Entidad eApuestaOfrecida;

		eApuestaOfrecida = servPersistencia.recuperarEntidad(apuestaOfrecida
				.getCodigo());
		// actualizamos parametro de apuestas
		servPersistencia.eliminarPropiedadEntidad(eApuestaOfrecida,
				"apuestasRealizadas");
		servPersistencia.anadirPropiedadEntidad(eApuestaOfrecida,
				"apuestasRealizadas",
				obtenerApuestasString(apuestaOfrecida.getApuestasRealizadas()));

		return apuestaOfrecida;
	}

	public ApuestaOfrecida meterCodEvento(ApuestaOfrecida apuestaOfrecida) {
		Entidad eApuestaOfrecida;

		eApuestaOfrecida = servPersistencia.recuperarEntidad(apuestaOfrecida
				.getCodigo());
		// actualizamos parametro de apuestas
		servPersistencia.anadirPropiedadEntidad(eApuestaOfrecida, "codEvento",
				String.valueOf(apuestaOfrecida.getCodEvento()));

		return apuestaOfrecida;
	}

	private ApuestaRealizada recuperarApuestaRealizada(int codigo)
			throws DAOException {
		return FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
				.getApuestaRealizadaDAO().recuperarApuestaRealizada(codigo);
	}

	public ApuestaOfrecida cerrarApuesta(ApuestaOfrecida apuestaOfrecida) {
		Entidad eApuestaOfrecida;

		eApuestaOfrecida = servPersistencia.recuperarEntidad(apuestaOfrecida
				.getCodigo());
		// actualizamos parametro de cerrada
		servPersistencia.eliminarPropiedadEntidad(eApuestaOfrecida, "cerrada");
		servPersistencia.anadirPropiedadEntidad(eApuestaOfrecida, "cerrada",
				"1");
		// cerramos tambien en el objeto que esta creado en la aplicacion en su
		// lista de apuestas. En la BDD no hace falta puesto que se recuperara
		// bien la proxima vez
		List<ApuestaOfrecida> listaApuestasEvento = CatalogoEventos
				.getUnicaInstancia().getEvento(apuestaOfrecida.getCodEvento())
				.getApuestas();
		for (ApuestaOfrecida aR : listaApuestasEvento) {
			if (aR.getCodigo() == apuestaOfrecida.getCodigo()) {
				aR.cerrar();
			}
		}

		return apuestaOfrecida;
	}

}
