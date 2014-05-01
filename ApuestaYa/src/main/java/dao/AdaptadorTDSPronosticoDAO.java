package dao;

import java.util.ArrayList;
import java.util.Arrays;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import beans.Entidad;
import beans.Propiedad;
import model.Pronostico;

public class AdaptadorTDSPronosticoDAO implements PronosticoDAO {
	private ServicioPersistencia servPersistencia;

	public AdaptadorTDSPronosticoDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance()
				.getServicioPersistencia();
	}

	public Pronostico registrarPronostico(Pronostico pronostico) {
		Entidad ePronostico;
		ePronostico = new Entidad();
		ePronostico.setNombre("pronostico");
		ePronostico.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("ganancia", String.valueOf(pronostico
						.getGanancia())), new Propiedad("pronosticoStr",
						pronostico.getPronostico()))));
		ePronostico = servPersistencia.registrarEntidad(ePronostico);
		pronostico.setCodigo(ePronostico.getId());
		return pronostico;
	}

	public Pronostico recuperarPronostico(int codigo) {
		Entidad ePronostico;
		double ganancia;
		String pronosticoStr;

		ePronostico = servPersistencia.recuperarEntidad(codigo);

		ganancia = Double.parseDouble(servPersistencia
				.recuperarPropiedadEntidad(ePronostico, "ganancia"));
		pronosticoStr = servPersistencia.recuperarPropiedadEntidad(ePronostico,
				"pronosticoStr");

		Pronostico pronostico = new Pronostico(ganancia, pronosticoStr);
		pronostico.setCodigo(codigo);

		return pronostico;

	}

}
