package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.DAOException;
import dao.FactoriaDAO;

public class CatalogoUsuarios {
	private Map<Integer, Usuario> usuarios;
	// Patron SINGLETON
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();

	/**
	 * Constructor del catalogo de usuarios
	 */
	private CatalogoUsuarios() {
		usuarios = new HashMap<Integer, Usuario>();
		try {
			List<Usuario> usuariosBD = this.recuperarUsuarios();
			for (Usuario usuario : usuariosBD) {
				usuarios.put(new Integer(usuario.getCodigo()), usuario);
			}
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	/**
	 * Getter que devuelve el unico catalogo de usuarios que hay
	 * 
	 * @return Catalogo de usuarios
	 */
	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}

	/**
	 * Metodo que devuelve la lista de usuarios que tenemos actualmente
	 * 
	 * @return Una lista que contiene los usuarios
	 */
	public List<Usuario> getAllUsuarios() {
		LinkedList<Usuario> lista = new LinkedList<Usuario>();
		Iterator<Usuario> iter = usuarios.values().iterator();
		while (iter.hasNext())
			lista.add(iter.next());
		return lista;
	}

	/**
	 * Metodo para obtener a partir de un usuario que metemos, su codigo de
	 * creacion
	 * 
	 * @param nombreUsuario
	 *            El usuario que metemos
	 * @return El codigo de creacion si existe, y 0 sino
	 */
	public int getKey(String nombreUsuario) {
		List<Usuario> lista = getAllUsuarios();
		for (Usuario usuario : lista) {
			if (usuario.getNombreUsuario().equals(nombreUsuario))
				return usuario.getCodigo();
		}
		return 0;
	}

	/**
	 * Metodo que devuelve el usuario asociado a un identificador
	 * 
	 * @param key
	 *            El identificador del usuario
	 * @return El usuario con ese identificador
	 */
	public Usuario getUsuario(Integer key) {
		return usuarios.get(new Integer(key));
	}

	/**
	 * Metodo que introduce un usuario nuevo en el catalogo
	 * 
	 * @param usuario
	 *            El usuario que vamos a introducir
	 */
	public void putUsuario(Usuario usuario) {
		usuarios.put(new Integer(usuario.getCodigo()), usuario);
	}

	/**
	 * Metodo que elimina un usuario mediante su identificador
	 * 
	 * @param usuario
	 *            El usuario que queremos eliminar
	 */
	public void removeUsuario(Usuario usuario) {
		usuarios.remove(usuario.getCodigo());
	}

	private List<Usuario> recuperarUsuarios() throws DAOException {
		return FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
				.getUsuarioDAO().recuperarTodosUsuarios();
	}

}
