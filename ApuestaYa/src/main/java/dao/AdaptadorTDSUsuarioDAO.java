package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import model.*;

public class AdaptadorTDSUsuarioDAO implements UsuarioDAO {
	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;

	public AdaptadorTDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance()
				.getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Usuario registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");

		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(
						new Propiedad("nombre", usuario.getNombre()),
						new Propiedad("apellidos", usuario.getApellidos()),
						new Propiedad("email", usuario.getEmail()),
						new Propiedad("nombreUsuario", usuario
								.getNombreUsuario()),
						new Propiedad("contrasena", usuario.getContraseña()),
						new Propiedad("saldo", String.valueOf(usuario
								.getSaldo())),
						new Propiedad("fechaNacimiento", dateFormat
								.format(usuario.getFechaNac())),
						new Propiedad("apuestasRealizadas",
								obtenerApuestasString(usuario
										.getApuestasRealizadas())),
						new Propiedad("pregunta", usuario.getPreguntaPrivada()),
						new Propiedad("respuesta", usuario.getRespuesta()),
						new Propiedad("transacciones",
								obtenerTransaccionesString(usuario
										.getListaTransacciones())),
						new Propiedad("ingreso", usuario.getIngresoPorDefecto()
								.getClass().getSimpleName()), new Propiedad(
								"cobro", usuario.getCobroPorDefecto()
										.getClass().getSimpleName()))));
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setCodigo(eUsuario.getId());
		return usuario;
	}

	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.borrarEntidad(eUsuario);
		// TODO borrar tambien su lista de apuestas realizadas

	}

	public Usuario modificarUsuario(Usuario usuario) {
		Entidad eUsuario;

		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre",
				String.valueOf(usuario.getNombre()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "apellidos",
				String.valueOf(usuario.getApellidos()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechaNacimiento");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechaNacimiento",
				dateFormat.format(usuario.getFechaNac()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email",
				usuario.getEmail());

		return usuario;
	}

	public Usuario modificarContrasena(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "contrasena");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contrasena",
				String.valueOf(usuario.getContraseña()));
		return usuario;
	}

	public Usuario modificarTipoIngreso(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "ingreso");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "ingreso", usuario
				.getIngresoPorDefecto().getClass().getSimpleName());
		return usuario;
	}

	public Usuario modificarTipoPago(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "cobro");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "cobro", usuario
				.getCobroPorDefecto().getClass().getSimpleName());
		return usuario;
	}

	public Usuario recuperarUsuario(int codigo) {
		Entidad eUsuario;
		String nombreUsuario;
		String email;
		String nombre;
		String apellidos;
		String contraseña;
		Date fechaNac = null;
		String preguntaPrivada;
		String respuesta;
		double saldo;
		String formaIngreso;
		String formaCobro;
		String cadenaTransacciones;
		String apuestasStr;

		eUsuario = servPersistencia.recuperarEntidad(codigo);
		nombreUsuario = servPersistencia.recuperarPropiedadEntidad(eUsuario,
				"nombreUsuario");
		saldo = Double.parseDouble(servPersistencia.recuperarPropiedadEntidad(
				eUsuario, "saldo"));
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario,
				"apellidos");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		contraseña = servPersistencia.recuperarPropiedadEntidad(eUsuario,
				"contrasena");
		formaIngreso = servPersistencia.recuperarPropiedadEntidad(eUsuario,
				"ingreso");
		formaCobro = servPersistencia.recuperarPropiedadEntidad(eUsuario,
				"cobro");
		cadenaTransacciones = servPersistencia.recuperarPropiedadEntidad(
				eUsuario, "transacciones");
		apuestasStr = servPersistencia.recuperarPropiedadEntidad(eUsuario,
				"apuestasRealizadas");

		try {
			fechaNac = dateFormat.parse(servPersistencia
					.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preguntaPrivada = servPersistencia.recuperarPropiedadEntidad(eUsuario,
				"pregunta");
		respuesta = servPersistencia.recuperarPropiedadEntidad(eUsuario,
				"respuesta");
		List<ApuestaRealizada> apuestasRealizadas = obtenerApuestasList(apuestasStr);

		Usuario usuario = new Usuario(nombreUsuario, email, nombre, apellidos,
				contraseña, fechaNac, preguntaPrivada, respuesta);
		usuario.setCodigo(codigo);
		usuario.setSaldo(saldo);
		usuario.comprobarIngreso(formaIngreso);
		usuario.comprobarCobro(formaCobro);
		usuario.setListaTransacciones(obtenerTransaccionesList(cadenaTransacciones));
		usuario.setApuestasRealizadas(apuestasRealizadas);
		return usuario;
	}

	public List<Usuario> recuperarTodosUsuarios() {
		List<Entidad> eUsuarios = servPersistencia
				.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}

		return usuarios;
	}

	/**
	 * Metodo para convertir una lista en un string y poder guardarlo en la base
	 * de datos
	 * 
	 * @param listaTransacciones
	 *            La lista que queremos convertir
	 * @return El string que guardamos
	 */
	private String obtenerTransaccionesString(
			List<Transaccion> listaTransacciones) {
		String listaString = "";
		for (Transaccion transaccion : listaTransacciones) {
			if (transaccion.getTipoTransaccion() == 0) {
				listaString += "Cobro-" + transaccion.getCantidad() + "-"
						+ dateFormat.format(transaccion.getFecha()) + "-";
			} else if (transaccion.getTipoTransaccion() == 1) {
				listaString += "Ingreso-" + transaccion.getCantidad() + "-"
						+ dateFormat.format(transaccion.getFecha()) + "-";
			}
		}
		return listaString;
	}

	private List<Transaccion> obtenerTransaccionesList(
			String cadenaTransacciones) {
		List<Transaccion> listaTransacciones = new LinkedList<Transaccion>();
		String tipoTransaccion;
		double cantidad;
		Date fecha = null;
		StringTokenizer strTok = new StringTokenizer(cadenaTransacciones, "-");
		while (strTok.hasMoreTokens()) {
			tipoTransaccion = (String) strTok.nextElement();
			if (tipoTransaccion.equals("Ingreso")) {
				cantidad = Double.parseDouble((String) strTok.nextElement());
				try {
					fecha = dateFormat.parse((String) strTok.nextElement());
				} catch (ParseException e) {
				}
				listaTransacciones.add(new TransaccionIngreso(cantidad, fecha));

			} else if (tipoTransaccion.equals("Cobro")) {
				cantidad = Double.parseDouble((String) strTok.nextElement());
				try {
					fecha = dateFormat.parse((String) strTok.nextElement());
				} catch (ParseException e) {
				}
				listaTransacciones.add(new TransaccionCobro(cantidad, fecha));
			}

		}

		return listaTransacciones;
	}

	public Usuario modificarTransacciones(Usuario usuario) {
		// modificamos el saldo
		LinkedList<Transaccion> transacciones = (LinkedList<Transaccion>) usuario
				.getListaTransacciones();
		if (transacciones.getLast().getTipoTransaccion() == 1)
			usuario.setSaldo(usuario.getSaldo()
					+ transacciones.getLast().getCantidad());
		else if (transacciones.getLast().getTipoTransaccion() == 0)
			usuario.setSaldo(usuario.getSaldo()
					- transacciones.getLast().getCantidad());

		// modificar al usuario en la base de datos
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		// modificar el saldo
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "saldo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "saldo",
				String.valueOf(usuario.getSaldo()));

		// modificar la lista de transacciones
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "transacciones");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "transacciones",
				obtenerTransaccionesString(usuario.getListaTransacciones()));

		return usuario;
	}

	/**
	 * Metodo para guardar una lista de apuestas en una cadena de caracteres y
	 * poder hacerlo persistente. Realmente solo guarda el codigo de la
	 * persistencia
	 * 
	 * @param listaApuestas
	 *            La lista que queremos convertir a String
	 * @return la cadena de caracteres correspondiente a la lista de apuestas
	 */
	private String obtenerApuestasString(List<ApuestaRealizada> listaApuestas) {
		String strApuestas = "";
		for (ApuestaRealizada aR : listaApuestas) {
			strApuestas += aR.getCodigo() + "-";
		}
		return strApuestas;
	}

	/**
	 * Metodo para guardar un string de apuestas en una lista
	 * 
	 * @param apuestasStr
	 *            La apuesta en string
	 * @return La lista creada
	 */
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

	public Usuario modificarListaApuestas(Usuario usuario) {
		Entidad eUsuario;

		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		// actualizamos parametro de apuestas
		servPersistencia.eliminarPropiedadEntidad(eUsuario,
				"apuestasRealizadas");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "apuestasRealizadas",
				obtenerApuestasString(usuario.getApuestasRealizadas()));
		// como cuando realiza una apuesta tambien se le modifica el saldo, lo
		// modificamos
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "saldo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "saldo",
				String.valueOf(usuario.getSaldo()));
		return usuario;
	}

	private ApuestaRealizada recuperarApuestaRealizada(int codigo)
			throws DAOException {
		return FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
				.getApuestaRealizadaDAO().recuperarApuestaRealizada(codigo);
	}
}
