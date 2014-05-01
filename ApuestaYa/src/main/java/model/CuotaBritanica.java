package model;

public class CuotaBritanica extends Cuota {

	public CuotaBritanica() {
	}

	@Override
	public double calcular(double importe, double ganancia) {
		return ganancia * importe;
	}

}
