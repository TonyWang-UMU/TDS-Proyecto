package controler;

import java.util.Date;
import java.util.List;

import model.*;

import dao.ApuestaOfrecidaDAO;
import dao.ApuestaRealizadaDAO;
import dao.DAOException;
import dao.EventoDAO;
import dao.FactoriaDAO;
import dao.PronosticoDAO;
import dao.UsuarioDAO;

/*
 * Controlador de la clase.
 * Arquitectura modelo-vista-controlador
 * Esta clase será la encargada de las operaciones de los modelos
 */
public class ApuestasYa {
	// patron SINGLETON, solo puede haber una instancia del controlador
	private static ApuestasYa unicaInstancia = new ApuestasYa();
	private FactoriaDAO dao;
	private UsuarioDAO adaptadorUsuario;
	private EventoDAO adaptadorEvento;
	private PronosticoDAO adaptadorPronostico;
	private ApuestaRealizadaDAO adaptadorApuestaRealizada;
	private ApuestaOfrecidaDAO adaptadorApuestaOfrecida;

	private ICalculoImpuestos calculoImpuestos;

	/*
	 * Constructor privado de la clase
	 */
	private ApuestasYa() {
		// Inicializacion de atributos
		calculoImpuestos = new LegislacionActual(); // TODO esta bien crear un
													// objeto LegislacionActual
													// ?

		try {
			dao = FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS);

			adaptadorUsuario = dao.getUsuarioDAO();
			adaptadorEvento = dao.getEventoDAO();
			adaptadorPronostico = dao.getPronosticoDAO();
			adaptadorApuestaRealizada = dao.getApuestaRealizadaDAO();
			adaptadorApuestaOfrecida = dao.getApuestaOfrecidaDAO();

		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	public static ApuestasYa getUnicaInstancia() {
		return unicaInstancia;
	}

	// a continuacion serie de metodos del controlador

	public Usuario registrarUsuario(String nombreUsuario, String email,
			String nombre, String apellidos, String contraseña, Date fechaNac,
			String preguntaPrivada, String respuesta) {

		Usuario usuario = adaptadorUsuario.registrarUsuario(new Usuario(
				nombreUsuario, email, nombre, apellidos, contraseña, fechaNac,
				preguntaPrivada, respuesta));
		CatalogoUsuarios.getUnicaInstancia().putUsuario(usuario);
		return usuario;

	}

	public Evento registrarEvento(String nombre, Date fechaDia, Date fechaHora,
			Deporte deporte, List<Participante> participantes, Cuota cuota) {
		Evento evento = adaptadorEvento.registrarEvento(new Evento(nombre,
				fechaDia, fechaHora, deporte, participantes, cuota));
		CatalogoEventos.getUnicaInstancia().putEvento(evento);
		return evento;
	}

	public Pronostico registrarPronostico(double cantidad, String pronosticoStr) {
		Pronostico pronostico = adaptadorPronostico
				.registrarPronostico(new Pronostico(cantidad, pronosticoStr));
		return pronostico;
	}

	public ApuestaOfrecida registrarApuestaOfrecida(
			ApuestaOfrecida apuestaOfrecida) {
		apuestaOfrecida = adaptadorApuestaOfrecida
				.registrarApuestaOfrecida(apuestaOfrecida);
		return apuestaOfrecida;
	}

	public ApuestaRealizada registrarApuestaRealizada(
			ApuestaRealizada apuestaRealizada) {
		apuestaRealizada = adaptadorApuestaRealizada
				.registrarApuestaRealizada(apuestaRealizada);
		return apuestaRealizada;
	}

	/**
	 * Metodo para modificar las principales caracteristicas del usuario
	 * 
	 * @param usuario
	 *            El usuario con sus nuevos atributos
	 * @return El usuario con los nuevos atributos
	 */
	public Usuario modificarUsuario(Usuario usuario) {
		// modificamos en la base de datos
		usuario = adaptadorUsuario.modificarUsuario(usuario);
		// modificamos en el catalogo
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		CatalogoUsuarios.getUnicaInstancia().putUsuario(usuario);
		return usuario;
	}

	// A continuacion una serie de metodos identicos al anterior para ser
	// llamados de distintos sitios

	public Usuario modificarContrasena(Usuario usuario) {
		usuario = adaptadorUsuario.modificarContrasena(usuario);
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		CatalogoUsuarios.getUnicaInstancia().putUsuario(usuario);
		return usuario;
	}

	public Usuario modificarTipoIngreso(Usuario usuario) {
		usuario = adaptadorUsuario.modificarTipoIngreso(usuario);
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		CatalogoUsuarios.getUnicaInstancia().putUsuario(usuario);
		return usuario;
	}

	public Usuario modificarTipoPago(Usuario usuario) {
		usuario = adaptadorUsuario.modificarTipoPago(usuario);
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		CatalogoUsuarios.getUnicaInstancia().putUsuario(usuario);
		return usuario;
	}

	public Usuario modificarApuestasRealizadas(Usuario usuario) {
		usuario = adaptadorUsuario.modificarListaApuestas(usuario);
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		CatalogoUsuarios.getUnicaInstancia().putUsuario(usuario);
		return usuario;
	}

	public void borrarUsuario(Usuario usuario) {
		adaptadorUsuario.borrarUsuario(usuario);
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
	}

	public void borrarEvento(Evento evento) {
		adaptadorEvento.borrarEvento(evento);
		CatalogoEventos.getUnicaInstancia().removeEvento(evento);
	}

	public Evento modificarEvento(Evento evento) {
		evento = adaptadorEvento.modificarEvento(evento);
		CatalogoEventos.getUnicaInstancia().removeEvento(evento);
		CatalogoEventos.getUnicaInstancia().putEvento(evento);
		return evento;
	}

	public ApuestaOfrecida modificarApuestaOfrecida(
			ApuestaOfrecida apuestaOfrecida) {
		apuestaOfrecida = adaptadorApuestaOfrecida
				.modificarApuestaOfrecida(apuestaOfrecida);
		return apuestaOfrecida;
	}

	public ApuestaOfrecida meterCodEvento(ApuestaOfrecida apuestaOfrecida) {
		apuestaOfrecida = adaptadorApuestaOfrecida
				.meterCodEvento(apuestaOfrecida);
		return apuestaOfrecida;
	}

	public ApuestaOfrecida recuperarApuestaOfrecida(int codigo)
			throws DAOException {
		return FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
				.getApuestaOfrecidaDAO().recuperarApuestaOfrecida(codigo);
	}

	/**
	 * Cuando cobramos dinero tendremos que pagar las tasas. Este es el metodo
	 * del controlador que se usa
	 * 
	 * @param cantidad
	 *            cantidad que queremos cobrar
	 * @return la cantidad real que cobramos
	 */
	public double cobrar(double cantidad) {
		if (cantidad <= 1000)
			return cantidad - cantidad * 0.02;
		return cantidad;
	}

	public void cerrarApuestas() {
		// TODO cerrar apuestas para un evento o para todos los eventos?
		// Le mandamos un mensaje al evento y de cerrar apuestas
	}

	/**
	 * Metodo necesario para actualizar las transacciones en la base de datos
	 * Cuando el usuario realiza una transaccion nueva, hay que hacerla
	 * persistente como parte de un atributo del usuario
	 * 
	 * @param usuario
	 *            El usuario que realiza la transaccion
	 */

	public Usuario modificarTransacciones(Usuario usuario) {
		usuario = adaptadorUsuario.modificarTransacciones(usuario);
		return usuario;
	}

	/**
	 * 
	 * @param usuario
	 *            El usuario del que calculamos los impuestos a pagar
	 * @return Los impuestos que tenemos que pagar
	 */
	public double calcularImpuestos(Usuario usuario) {
		double cantidadGanada = 0;
		Date fechaActual = new Date();
		List<Transaccion> listaIngresos = usuario.getListaTransacciones();
		for (Transaccion transaccion : listaIngresos) {
			if (transaccion.getTipoTransaccion() == 1) {
				if ((fechaActual.getTime() - transaccion.getFecha().getTime())
						/ 1000 / 60 / 60 / 24 < 365) {

					cantidadGanada += transaccion.getCantidad();
				}
			}
		}

		return calculoImpuestos.calcular(cantidadGanada);
	}
}
