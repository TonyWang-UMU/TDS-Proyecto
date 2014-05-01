package model;

import java.util.List;

public class ParticipanteEquipo extends Participante {

	List<ParticipanteJugador> jugadores;

	public ParticipanteEquipo(String nombre, List<ParticipanteJugador> jugadores) {
		super(nombre);
		this.jugadores = jugadores;
	}

	public List<ParticipanteJugador> getJugadores() {
		return jugadores;
	}

}
