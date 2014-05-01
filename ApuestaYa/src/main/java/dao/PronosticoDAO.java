package dao;

import model.Pronostico;

public interface PronosticoDAO {
	public Pronostico registrarPronostico(Pronostico pronostico);

	public Pronostico recuperarPronostico(int codigo);
}
