package dao;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO() {
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new AdaptadorTDSUsuarioDAO();
	}

	@Override
	public EventoDAO getEventoDAO() {
		return new AdaptadorTDSEventoDAO();
	}

	@Override
	public ApuestaRealizadaDAO getApuestaRealizadaDAO() {
		return new AdaptadorTDSApuestaRealizadaDAO();
	}

	@Override
	public ApuestaOfrecidaDAO getApuestaOfrecidaDAO() {
		return new AdaptadorTDSApuestaOfrecidaDAO();
	}

	@Override
	public PronosticoDAO getPronosticoDAO() {
		return new AdaptadorTDSPronosticoDAO();
	}

}
