package view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public abstract class VentanaError {

	/**
	 * Metodo para mostrar ventanas de error dependiendo de con que argumento es
	 * llamado
	 * 
	 * @param numError
	 */

	public static void mostrarMensaje(int numError) {
		switch (numError) {
		case 1:
			JOptionPane.showMessageDialog(new JDialog(),
					"Alguno de los campos obligatorios está vacío",
					"Error en el registro", JOptionPane.ERROR_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(new JDialog(),
					"El usuario o el e-mail no puede contener espacios",
					"Error en el registro", JOptionPane.ERROR_MESSAGE);
			break;

		case 3:
			JOptionPane.showMessageDialog(new JDialog(),
					"Sólo puedes registrarte si eres mayor de edad",
					"Error en el registro", JOptionPane.ERROR_MESSAGE);
			break;
		case 4:
			JOptionPane.showMessageDialog(new JDialog(),
					"Las contraseñas no coinciden", "Error en el registro",
					JOptionPane.ERROR_MESSAGE);
			break;

		case 5:
			JOptionPane.showMessageDialog(new JDialog(),
					"Usuario registrado correctamente", "Aceptado",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 6:
			JOptionPane.showMessageDialog(new JDialog(),
					"El nombre de usuario ya existe", "Error en el registro",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 7:
			JOptionPane.showMessageDialog(new JDialog(),
					"El usuario introducido no existe", "Error en el registro",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 8:
			JOptionPane.showMessageDialog(new JDialog(),
					"La contraseña introducida no es correcta",
					"Error en el registro", JOptionPane.ERROR_MESSAGE);
			break;
		case 9:
			JOptionPane.showMessageDialog(new JDialog(),
					"Tienes que seleccionar el deporte del evento a registrar",
					"Error en registro", JOptionPane.ERROR_MESSAGE);
			break;
		case 10:
			JOptionPane
					.showMessageDialog(
							new JDialog(),
							"La fecha de celebración del evento no tiene el formato correcto",
							"Error en registro", JOptionPane.ERROR_MESSAGE);
			break;
		case 11:
			JOptionPane.showMessageDialog(new JDialog(),
					"Faltan jugadores por registrar", "Error en registro",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 12:
			JOptionPane.showMessageDialog(new JDialog(),
					"Evento registrado correctamente", "Evento registrado",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 13:
			JOptionPane.showMessageDialog(new JDialog(),
					"Contraseña Incorrecta", "Error actualizando datos",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 14:
			JOptionPane.showMessageDialog(new JDialog(),
					"Fecha de Nacimiento Incorrecta",
					"Error actualizando datos", JOptionPane.ERROR_MESSAGE);
			break;
		case 15:
			JOptionPane.showMessageDialog(new JDialog(),
					"Contraseña cambiada correctamente", "Datos actualizados",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 16:
			JOptionPane.showMessageDialog(new JDialog(),
					"Datos actualizados correctamente", "Datos actualizados",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 17:
			JOptionPane.showMessageDialog(new JDialog(), "Datos incorrectos",
					"Error cambiando modo de ingreso",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 18:
			JOptionPane.showMessageDialog(new JDialog(),
					"Datos del ingreso cambiados", "Ingreso cambiado",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 19:
			JOptionPane.showMessageDialog(new JDialog(), "Datos incorrectos",
					"Error cambiando modo de cobro", JOptionPane.ERROR_MESSAGE);
			break;
		case 20:
			JOptionPane.showMessageDialog(new JDialog(),
					"Datos del cobro cambiados", "Cobro cambiado",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 21:
			JOptionPane.showMessageDialog(new JDialog(),
					"No se puede eliminar al usuario conectado",
					"Eliminar usuario", JOptionPane.ERROR_MESSAGE);
			break;
		case 22:
			JOptionPane.showMessageDialog(new JDialog(),
					"Usuario borrado correctamente", "Eliminar usuario",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 23:
			JOptionPane.showMessageDialog(new JDialog(),
					"No dispone de tanto saldo para cobrar", "Cobrar saldo",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 24:
			JOptionPane.showMessageDialog(new JDialog(),
					"Cantidad de dinero mal introducida", "Cobrar saldo",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 25:
			JOptionPane.showMessageDialog(new JDialog(),
					"Ingreso realizado con éxito", "Ingresar Saldo",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 26:
			JOptionPane.showMessageDialog(new JDialog(),
					"Cobro realizado con éxito", "Cobrar saldo",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 27:
			JOptionPane.showMessageDialog(new JDialog(),
					"Error en las apuestas, seleccione un evento",
					"Gestion de Apuestas", JOptionPane.ERROR_MESSAGE);
			break;
		case 28:
			JOptionPane.showMessageDialog(new JDialog(),
					"Apuesta creada correctamente", "Crear apuestas",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 29:
			JOptionPane.showMessageDialog(new JDialog(),
					"Apuesta creada correctamente", "Realizar apuestas",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 30:
			JOptionPane.showMessageDialog(new JDialog(),
					"Error en las apuestas, cantidad a apostar incorrecta",
					"Realizar apuestas", JOptionPane.ERROR_MESSAGE);
			break;
		case 31:
			JOptionPane.showMessageDialog(new JDialog(),
					"No dispone de saldo suficiente para realizar esa apuesta",
					"Realizar apuestas", JOptionPane.ERROR_MESSAGE);
			break;
		case 32:
			JOptionPane.showMessageDialog(new JDialog(),
					"Debe de elegir un tipo de apuesta múltiple",
					"Realizar apuestas", JOptionPane.ERROR_MESSAGE);
			break;
		case 33:
			JOptionPane.showMessageDialog(new JDialog(),
					"Al menos deben de haber 2 apuestas para la combinada",
					"Realizar apuestas", JOptionPane.ERROR_MESSAGE);
			break;
		case 34:
			JOptionPane.showMessageDialog(new JDialog(),
					"Al menos deben de haber 3 apuestas para la sistema",
					"Realizar apuestas", JOptionPane.ERROR_MESSAGE);
			break;
		case 35:
			JOptionPane
					.showMessageDialog(
							new JDialog(),
							"La combinación elegida para apuestas de sistema es errónea",
							"Realizar apuestas", JOptionPane.ERROR_MESSAGE);
			break;
		case 36:
			JOptionPane.showMessageDialog(new JDialog(),
					"Apuesta creada correctamente", "Realizar apuestas",
					JOptionPane.DEFAULT_OPTION);
			break;
		case 37:
			JOptionPane.showMessageDialog(new JDialog(),
					"No se puede cobrar una cantidad inferior a 30 euros",
					"Realizar Cobro", JOptionPane.ERROR_MESSAGE);
			break;

		default:
			break;
		}
	}
}
