package model;

public class CuotaEuropea extends Cuota {

	public CuotaEuropea() {

	}

	@Override
	public double calcular(double importe, double ganancia) {
		return importe * ganancia - importe;
	}

}
