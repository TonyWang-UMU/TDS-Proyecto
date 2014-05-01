package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.DAOException;
import dao.FactoriaDAO;

public class CatalogoEventos {
	// cada evento tiene un identificador entero
	private Map<Integer, Evento> eventos;
	private static CatalogoEventos unicaInstancia = new CatalogoEventos();

	/*
	 * Constructor de la clase
	 */
	private CatalogoEventos() {
		eventos = new HashMap<Integer, Evento>();
		try {
			List<Evento> eventosBD = this.recuperarEventos();
			for (Evento evento : eventosBD) {
				eventos.put(new Integer(evento.getCodigo()), evento);
			}
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	/**
	 * Getter del catalogo de eventos
	 * 
	 * @return La unica instancia que tiene el catalogo de eventos
	 */
	public static CatalogoEventos getUnicaInstancia() {
		return unicaInstancia;
	}

	// Metodos de un catalogo

	/**
	 * Metodo para obtener todos los eventos que hay registrados
	 * 
	 * @return Una lista con los eventos que hay registrados
	 */

	public List<Evento> getAllEventos() {
		LinkedList<Evento> lista = new LinkedList<Evento>();
		Iterator<Evento> iter = eventos.values().iterator();
		while (iter.hasNext())
			lista.add(iter.next());
		return lista;
	}

	/**
	 * Metodo que devuelve un evento concreto
	 * 
	 * @param key
	 *            El identificador del evento
	 * @return El evento asociado a ese identificador
	 */
	public Evento getEvento(int key) {
		return eventos.get(new Integer(key));
	}

	/**
	 * Metodo que añade un evento concreto
	 * 
	 * @param evento
	 *            El evento que queremos añadir
	 */
	public void putEvento(Evento evento) {
		eventos.put(new Integer(evento.getCodigo()), evento);
	}

	/**
	 * Metodo que elimina un evento concreto
	 * 
	 * @param evento
	 *            El evento que queremos eliminar
	 */
	public void removeEvento(Evento evento) {
		eventos.remove(new Integer(evento.getCodigo()));
	}

	private List<Evento> recuperarEventos() throws DAOException {
		return FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
				.getEventoDAO().recuperarTodosEventos();
	}

}
