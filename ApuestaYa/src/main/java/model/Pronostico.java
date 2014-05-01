package model;

public class Pronostico {

	/**
	 * Para simplicidad se ha suprimido la jerarquia de pronosticos y se trata
	 * como String ya sea simple o de participante
	 */

	private int codigo;
	private double ganancia;
	private String pronostico;

	public Pronostico(double ganancia, String pronostico) {
		this.pronostico = pronostico;
		this.ganancia = ganancia;
	}

	public int getCodigo() {
		return codigo;
	}

	public double getGanancia() {
		return ganancia;
	}

	public String getPronostico() {
		return pronostico;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}

}
