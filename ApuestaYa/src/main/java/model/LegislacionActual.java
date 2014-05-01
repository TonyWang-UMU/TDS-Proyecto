package model;

public class LegislacionActual implements ICalculoImpuestos{

	public double calcular(double importe) {
		if (importe > 1000) return 30 + importe * ICalculoImpuestos.IMPUESTOS;
		else return importe * ICalculoImpuestos.IMPUESTOS;
	}

}
