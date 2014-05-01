package model;

import java.util.Date;

public class TransaccionIngreso extends Transaccion {

	public TransaccionIngreso(double cantidad, Date fecha) {
		super(cantidad, fecha);
		super.setTipoTransaccion(1);
	}

}
