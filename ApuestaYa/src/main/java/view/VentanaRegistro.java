package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import controler.*;
import model.CatalogoUsuarios;
import model.MetodosVentana;

import java.awt.GridLayout;
import java.util.Date;

@SuppressWarnings({ "serial", "deprecation","rawtypes","unchecked"})
public class VentanaRegistro extends JDialog {

	private final JPanel panelCentral = new JPanel();
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textUsu;
	private JPasswordField textPass;
	private JPasswordField textPass2;
	private JTextField textResp;
	private JTextField textMail;
	private JComboBox boxMes;
	private JComboBox boxAno;
	private JComboBox boxDia;
	private JComboBox boxPreg;
	private boolean registroCompleto = true;

	/**
	 * Constructor de la ventana de registro
	 */
	public VentanaRegistro() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 275, 402);

		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panelTitulo = new JPanel();
			getContentPane().add(panelTitulo, BorderLayout.NORTH);
			{
				JLabel etiqTitulo = new JLabel("Registrar Nuevo Usuario");
				etiqTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
				panelTitulo.add(etiqTitulo);
			}
		}
		panelCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		{
			JPanel panelNombre = new JPanel();
			panelCentral.add(panelNombre);
			panelNombre.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel etiqNombre = new JLabel("Nombre: ");
				panelNombre.add(etiqNombre);
				etiqNombre.setHorizontalAlignment(SwingConstants.CENTER);

			}

			{
				textNombre = new JTextField();
				panelNombre.add(textNombre);
				textNombre.setColumns(10);
			}
		}
		{
			JPanel panelApellidos = new JPanel();
			panelCentral.add(panelApellidos);
			panelApellidos.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel etiqApellidos = new JLabel("Apellidos: ");
				etiqApellidos.setHorizontalAlignment(SwingConstants.CENTER);
				panelApellidos.add(etiqApellidos);
			}
			{
				textApellidos = new JTextField();
				panelApellidos.add(textApellidos);
				textApellidos.setColumns(10);
			}
		}
		{
			JPanel panelFechaNac = new JPanel();

			panelCentral.add(panelFechaNac);
			panelFechaNac.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel etiqFNac = new JLabel("Fecha Nacimiento: ");
				etiqFNac.setHorizontalAlignment(SwingConstants.CENTER);
				panelFechaNac.add(etiqFNac);
			}
			{

				JPanel panelBoxFecha = new JPanel();
				panelFechaNac.add(panelBoxFecha);
				panelBoxFecha.setLayout(new BoxLayout(panelBoxFecha,
						BoxLayout.Y_AXIS));

				boxDia = new JComboBox();
				panelBoxFecha.add(boxDia);
				for (int i = 1; i <= 31; i++)
					boxDia.addItem(i);
				{
					{

						boxMes = new JComboBox();
						panelBoxFecha.add(boxMes);
						boxMes.addItem("Enero");
						boxMes.addItem("Febrero");
						boxMes.addItem("Marzo");
						boxMes.addItem("Abril");
						boxMes.addItem("Mayo");
						boxMes.addItem("Junio");
						boxMes.addItem("Julio");
						boxMes.addItem("Agosto");
						boxMes.addItem("Septiembre");
						boxMes.addItem("Octubre");
						boxMes.addItem("Noviembre");
						boxMes.addItem("Diciembre");
					}
					boxAno = new JComboBox();
					panelBoxFecha.add(boxAno);
					for (int i = 0; i < 10; i++)
						boxAno.addItem("190" + i);
					for (int i = 10; i <= 99; i++)
						boxAno.addItem("19" + i);
					for (int i = 0; i < 10; i++)
						boxAno.addItem("200" + i);
				}
			}

		}
		{
			JPanel panelUsuario = new JPanel();
			panelCentral.add(panelUsuario);
			panelUsuario.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel etiqUsu = new JLabel("Usuario: ");
				etiqUsu.setHorizontalAlignment(SwingConstants.CENTER);

				panelUsuario.add(etiqUsu);
			}
			{
				textUsu = new JTextField();
				panelUsuario.add(textUsu);
				textUsu.setColumns(10);
			}
		}
		{
			JPanel panelMail = new JPanel();
			panelCentral.add(panelMail);
			panelMail.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel etiqMail = new JLabel("Email: ");
				etiqMail.setHorizontalAlignment(SwingConstants.CENTER);
				panelMail.add(etiqMail);
			}
			{
				textMail = new JTextField();
				panelMail.add(textMail);
				textMail.setColumns(10);
			}
		}
		{
			JPanel panelPass = new JPanel();
			panelCentral.add(panelPass);
			panelPass.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel etiqPass = new JLabel("Contrase\u00F1a: ");
				etiqPass.setHorizontalAlignment(SwingConstants.CENTER);
				panelPass.add(etiqPass);
			}
			{
				textPass = new JPasswordField();
				panelPass.add(textPass);
				textPass.setColumns(10);
			}
		}
		{
			JPanel panelPass2 = new JPanel();
			panelCentral.add(panelPass2);
			panelPass2.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel etiqPass2 = new JLabel("Repite la Contrase\u00F1a: ");
				etiqPass2.setHorizontalAlignment(SwingConstants.CENTER);
				panelPass2.add(etiqPass2);
			}
			{
				textPass2 = new JPasswordField();
				panelPass2.add(textPass2);
				textPass2.setColumns(10);
			}
		}
		{
			JPanel panelPregunta = new JPanel();
			panelCentral.add(panelPregunta);
			panelPregunta.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JLabel etiqPreg = new JLabel("Elige Pregunta Secreta: ");
				etiqPreg.setHorizontalAlignment(SwingConstants.CENTER);
				panelPregunta.add(etiqPreg);
			}
		}
		{
			JPanel panelBoxPreg = new JPanel();
			FlowLayout fl_panelBoxPreg = (FlowLayout) panelBoxPreg.getLayout();
			fl_panelBoxPreg.setAlignment(FlowLayout.LEFT);
			panelCentral.add(panelBoxPreg);
			{
				boxPreg = new JComboBox();
				panelBoxPreg.add(boxPreg);
				boxPreg.addItem("Nombre del profesor de la infancia");
				boxPreg.addItem("Feha de nacimiento de la madre");
				boxPreg.addItem("Nombre de su primer amigo imaginario");
			}
		}
		{
			JPanel panelRespuesta = new JPanel();
			panelCentral.add(panelRespuesta);
			panelRespuesta.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel etiqResp = new JLabel("Respuesta: ");
				etiqResp.setHorizontalAlignment(SwingConstants.CENTER);
				panelRespuesta.add(etiqResp);
			}
			{
				textResp = new JTextField();
				panelRespuesta.add(textResp);
				textResp.setColumns(10);
			}
		}
		{
			JPanel panelBotones = new JPanel();
			getContentPane().add(panelBotones, BorderLayout.SOUTH);
			{
				JButton bottonAceptar = new JButton("Aceptar");
				bottonAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						// comprobar que todos los campos esten correctos

						// comprobar que haya algo escrito al menos
						if (textNombre.getText().length() == 0
								|| textApellidos.getText().length() == 0
								|| textUsu.getText().length() == 0
								|| textPass.getPassword().length == 0
								|| textPass2.getPassword().length == 0
								|| textResp.getText().length() == 0
								|| textMail.getText().length() == 0) {
							registroCompleto = false;
							VentanaError.mostrarMensaje(1);

						}

						// comprobar que lo escrito no sean blancos
						// en cuanto haya un espacio falla
						if (MetodosVentana.tieneEspacios(textUsu.getText())
								|| MetodosVentana.tieneEspacios(textMail
										.getText())) {
							registroCompleto = false;
							VentanaError.mostrarMensaje(2);
						}

						// comprobar que la edad sea mayor que 18

						Date fechaNac = new Date(
								Integer.parseInt((String) boxAno
										.getSelectedItem()) - 1900,
								MetodosVentana.queMes((String) boxMes
										.getSelectedItem()), (Integer) boxDia
										.getSelectedItem());

						if (!MetodosVentana.mayorDieciocho(fechaNac)) {
							registroCompleto = false;
							VentanaError.mostrarMensaje(3);
						}
						// comprobar que ambas contraseñas sean validas
						// con el getPassword no funcionaba asi que se hizo con
						// el getText
						if (!textPass.getText().equals(textPass2.getText())) {
							registroCompleto = false;
							VentanaError.mostrarMensaje(4);
						}

						if (registroCompleto) {
							// crear un nuevo usuario en la base de datos en
							// caso de que no exista y el registrado haya sido
							// hecho correctamente
							int codigo = CatalogoUsuarios.getUnicaInstancia()
									.getKey(textUsu.getText());

							if (codigo == 0) {
								// si llega hasta aqui es que el usuario no
								// existe
								ApuestasYa.getUnicaInstancia()
										.registrarUsuario(
												textUsu.getText(),
												textMail.getText(),
												textNombre.getText(),
												textApellidos.getText(),
												textPass.getText(),
												fechaNac,
												(String) boxPreg
														.getSelectedItem(),
												textResp.getText());
								VentanaError.mostrarMensaje(5);
								VentanaRegistro.this.dispose();
							} else {
								registroCompleto = false;
								VentanaError.mostrarMensaje(6);
							}

						} else
							registroCompleto = true;

					}
				});
				panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				bottonAceptar.setActionCommand("OK");
				panelBotones.add(bottonAceptar);
				getRootPane().setDefaultButton(bottonAceptar);
			}
			{
				JButton botonCancelar = new JButton("Cancelar");
				botonCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						VentanaRegistro.this.dispose();
					}
				});
				botonCancelar.setActionCommand("Cancel");
				panelBotones.add(botonCancelar);
			}
		}
	}

}
