package model;

import java.util.Date;

public abstract class Transaccion {
	private double cantidad;
	private Date fecha;
	// Para saber si es un cobro o un ingreso 0=cobro, 1=ingreso
	private int tipoTransaccion;

	public Transaccion(double cantidad, Date fecha) {
		this.cantidad = cantidad;
		this.fecha = fecha;

	}

	public double getCantidad() {
		return cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(int tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

}
