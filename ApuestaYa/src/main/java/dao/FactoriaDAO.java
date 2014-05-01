package dao;

//Define una factoria abstracta que devuelve todos los DAO de la aplicacion

public abstract class FactoriaDAO {
	private static FactoriaDAO unicaInstancia;

	public static FactoriaDAO getFactoriaDAO() {
		return unicaInstancia;
	}

	public static FactoriaDAO getFactoriaDAO(int tipo) throws DAOException {
		switch (tipo) {
		case PERSIST_TDS: {
			try {
				unicaInstancia = new TDSFactoriaDAO();
				return unicaInstancia;
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		}
		default:
			return null;
		}
	}

	protected FactoriaDAO() {

	}

	// Metodos factoria
	public abstract UsuarioDAO getUsuarioDAO();

	public abstract EventoDAO getEventoDAO();

	public abstract ApuestaRealizadaDAO getApuestaRealizadaDAO();

	public abstract ApuestaOfrecidaDAO getApuestaOfrecidaDAO();

	public abstract PronosticoDAO getPronosticoDAO();

	// Declaracion como constantes de los tipos de factoria
	public final static int PERSIST_TDS = 1;

}
