package model;

import java.util.Date;
import java.util.StringTokenizer;

@SuppressWarnings({ "deprecation" })
public abstract class MetodosVentana {

	/**
	 * Metodo para convertir una fecha en string con / de separador en un
	 * formato Date
	 * 
	 * @param fecha
	 *            La fecha en String
	 * @return la fecha en Date
	 */
	public static Date parseFecha(String fecha) {
		int dia;
		int mes;
		int año;

		StringTokenizer strTok = new StringTokenizer(fecha, "/");
		if (strTok.countTokens() == 3) {

			try {
				dia = Integer.parseInt((String) strTok.nextElement());
				mes = Integer.parseInt((String) strTok.nextElement());
				año = Integer.parseInt((String) strTok.nextElement());
			} catch (NumberFormatException e) {
				return null;
			}

			if (dia > 0 && dia < 32 && mes > 0 && mes <= 12 && año <= 2015
					&& año > 1900)
				return new Date(año - 1900, mes - 1, dia);
		}

		return null;
	}

	/**
	 * Metodo para comprobar si la fecha introducida para una tarjeta es
	 * correcta
	 * 
	 * @param fecha
	 *            Fecha de caducidad de la tarjeta
	 * @return True si es correcta, false si es incorrecta
	 */
	public static boolean fechaTarjetaCorrecta(String fecha) {
		int mes = 0;
		int año = 0;
		StringTokenizer strTok = new StringTokenizer(fecha, "/");
		if (strTok.countTokens() == 2) {

			try {
				mes = Integer.parseInt((String) strTok.nextElement());
				año = Integer.parseInt((String) strTok.nextElement());
			} catch (NumberFormatException e) {
				return false;
			}

		}
		if (mes >= 1 && mes <= 12 && año >= 13 && año <= 99) {
			return true;

		}

		return false;

	}

	/**
	 * Metodo para transformar una hora string con formato HH:MM en Date
	 * 
	 * @param hora
	 *            La hora en String
	 * @return hora en Date
	 */

	public static Date parseHora(String hora) {
		int horas;
		int minutos;
		Date queHora = new Date();
		StringTokenizer strTok = new StringTokenizer(hora, ":");
		if (strTok.countTokens() == 2) {
			try {
				horas = Integer.parseInt((String) strTok.nextElement());
				minutos = Integer.parseInt((String) strTok.nextElement());
			} catch (NumberFormatException e) {
				return null;
			}
			if (horas >= 0 && horas < 24 && minutos >= 0 && minutos < 60) {
				queHora.setHours(horas);
				queHora.setMinutes(minutos);
				return queHora;
			}

		}

		return null;
	}

	/**
	 * Metodo que evalua un string correspondiente a una cuota y devuelve un
	 * objeto de ese tipo
	 * 
	 * @param cuota
	 *            La cuota en formato string
	 * @return Un objeto de ese tipo de cuota
	 */
	public static Cuota cuotaSeleccionada(String cuota) {
		if (cuota.equals("Cuota Britanica"))
			return new CuotaBritanica();
		else if (cuota.equals("Cuota Europea"))
			return new CuotaEuropea();
		else
			return null;
	}

	/**
	 * Metodo para poder mostrar correctamente de que deporte se trata pasandole
	 * el string que representa el objeto
	 * 
	 * @param nombreDeporte
	 *            El string que representa al objeto
	 * @return Una cadena para mostrar el deporte
	 */

	public static String fraseDeporte(String nombreDeporte) {
		if (nombreDeporte.equals("DeporteFutbol"))
			return "Partido de Fútbol";
		else if (nombreDeporte.equals("DeporteTenis"))
			return "Partido de Tenis";
		else if (nombreDeporte.equals("DeporteFormula1"))
			return "Carrera de Fórmula 1";

		return null;
	}

	/**
	 * Metodo analogo al de los deportes pero con las cuotas
	 * 
	 * @param nombreCuota
	 *            El objeto de la cuota en String
	 * @return un string que representa la cuota correspondiente
	 */

	public static String fraseCuota(String nombreCuota) {
		if (nombreCuota.equals("CuotaEuropea"))
			return "Europea";
		else if (nombreCuota.equals("CuotaBritanica"))
			return "Britanica";

		return null;
	}

	/**
	 * Metodo que comprueba una fecha y contrastando con la actual evalua si es
	 * mayor de 18 años
	 * 
	 * @param fechaNac
	 *            La fecha que queremos evaluar
	 * @return True si es mayor que 18 y false si no
	 */
	public static boolean mayorDieciocho(Date fechaNac) {
		// despues de crear un objeto Date con los datos del
		// usuario, vamos a compararlos con el actual
		Date fechaAct = new Date();
		if (fechaAct.getYear() - fechaNac.getYear() < 18) {
			return false;
		}
		// han pasado 18, hay que comprobar el mes y el dia
		else if (fechaAct.getYear() - fechaNac.getYear() == 18) {

			// si estamos en un mes que es mayor al del
			// nacimiento entonces es mayor de edad
			// en caso de que el mes actual sea menor entonces
			// es que es menor de edad

			if (fechaAct.getMonth() < fechaNac.getMonth()) {
				return false;
			}
			// si estamos en el mismo mes tenemos que comparar
			// el dia
			else if (fechaAct.getMonth() == fechaNac.getMonth()) {
				// si el dia en el que nos encontramos es menor
				// al del nacimiento entonces aun no es mayor de
				// edad
				if (fechaAct.getDate() < fechaNac.getDate()) {
					return false;
				}

			}

		}
		return true;
	}

	/**
	 * Metodo que pasandole un string con el mes que es, devuelve un entero con
	 * el que podemos construir un objeto Date
	 * 
	 * @param mes
	 *            Mes del nacimiento del usuario que se registra
	 * @return un entero que representa el mes de nacimiento
	 */
	public static int queMes(String mes) {
		if (mes.equals("Enero"))
			return 0;
		else if (mes.equals("Febero"))
			return 1;
		else if (mes.equals("Marzo"))
			return 2;
		else if (mes.equals("Abril"))
			return 3;
		else if (mes.equals("Mayo"))
			return 4;
		else if (mes.equals("Junio"))
			return 5;
		else if (mes.equals("Julio"))
			return 6;
		else if (mes.equals("Agosto"))
			return 7;
		else if (mes.equals("Septiembre"))
			return 8;
		else if (mes.equals("Octubre"))
			return 9;
		else if (mes.equals("Noviembre"))
			return 10;
		else
			return 11;
	}

	/**
	 * Metodo que comprueba si una cadena de caracteres tiene algun espacio
	 * 
	 * @param cadena
	 *            La cadena a comprobar
	 * @return Devuelve true si tiene algun espacio y false si no tiene
	 */
	public static boolean tieneEspacios(String cadena) {

		for (int i = 0; i < cadena.length(); i++)
			if (cadena.charAt(i) == ' ')
				return true;
		return false;
	}

	/**
	 * Metodo que comprueba si una cadena pasada es enteramente numérica
	 * 
	 * @param cadena
	 *            La cadena que queremos comprobar
	 * @return True si es numérica y false si no
	 */
	public static boolean sonNumeros(String cadena) {
		// Como pueden pasarse cadenas muy largas entonces hay que comprobar de
		// dos en dos
		String aux = "";

		try {
			for (int i = 0; i < cadena.length(); i++) {
				aux = cadena.charAt(i) + "";
				Integer.parseInt(aux);
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}
}
