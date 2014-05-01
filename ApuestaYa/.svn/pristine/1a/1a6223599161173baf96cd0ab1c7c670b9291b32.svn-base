package model;

import java.util.LinkedList;

import resultado.Resultado;
import resultado.ResultadoF1;
import resultado.ResultadoFutbol;
import resultado.ResultadoTenis;
/*
 * En esta clase usamos la clase Resultado para generar otro objeto 
 * Resultado con un resultado individual de cada evento
 */
public class ResultadosEvento {

	private LinkedList<Resultado> resultados;

	public ResultadosEvento (Resultado resultado){
		resultados = new LinkedList<Resultado>();
		
		for (ResultadoFutbol r: resultado.getResultadoFutbol()){
			Resultado resultadoFutbol = new Resultado();
			resultadoFutbol.getResultadoFutbol().add(r);
			resultados.add(resultadoFutbol);
		}
		
		for (ResultadoTenis r: resultado.getResultadoTenis()){
			Resultado resultadoTenis = new Resultado();
			resultadoTenis.getResultadoTenis().add(r);
			resultados.add(resultadoTenis);
		}
		
		for (ResultadoF1 r: resultado.getResultadoF1()){
			Resultado resultadoF1 = new Resultado();
			resultadoF1.getResultadoF1().add(r);
			resultados.add(resultadoF1);
		}
	}

	public LinkedList<Resultado> getResultados() {
		return resultados;
	}
	
}
