package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import controler.ApuestasYa;

import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class VentanaApuesta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Usuario usuLogin;
	private Evento eventoApuesta;
	private JTextField textGanancia = new JTextField();
	private JTextField textApostar;
	private JComboBox comboPregunta;
	private JComboBox comboRespuesta;
	private DecimalFormat dosDecimales = new DecimalFormat("#.##");

	/**
	 * Create the dialog.
	 */
	public VentanaApuesta(Usuario usuario, Evento evento) {
		setTitle("Apuestas para " + evento.getNombre());
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 568, 191);
		usuLogin = usuario;
		eventoApuesta = evento;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JLabel lblSelecciona = new JLabel("Selecciona Pron\u00F3stico");
			lblSelecciona.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelecciona.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblSelecciona);
		}
		{
			comboPregunta = new JComboBox();
			comboPregunta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Cada vez que pinchamos en el combobox
					int seleccionado = comboPregunta.getSelectedIndex();
					if (comboRespuesta != null)
						anadirRespuestas(seleccionado);

				}
			});
			contentPanel.add(comboPregunta);

			// Añadimos las preguntas recuperando las apuestas
			anadirPreguntas();

		}
		{
			comboRespuesta = new JComboBox();
			comboRespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Sirve para rellenar el campo de pronostico
					// Tenemos que obtener la lista de pronosticos de la apuesta
					// y coger el campo pronostico
					if (comboPregunta != null) {
						List<Pronostico> listaPronostico = eventoApuesta
								.getApuestas()
								.get(comboPregunta.getSelectedIndex())
								.getListaPronosticos();

						if (comboRespuesta.getSelectedIndex() != -1) {

							textGanancia.setText(String.valueOf(dosDecimales
									.format(listaPronostico.get(
											comboRespuesta.getSelectedIndex())
											.getGanancia())));

						}
					}

				}
			});
			anadirRespuestas(0);
			contentPanel.add(comboRespuesta);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Realizar Apuesta");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// Realizar una apuesta
						ApuestaSencilla apuestaSencilla;

						int codPronostico;
						int codApuestaOfrecida;
						// Comprobamos que se haya introducido una cantidad
						// correcta
						if (textApostar.getText().length() != 0) {
							// Comprobamos que tenemos saldo suficiente
							try {
								if (usuLogin.getSaldo() >= Double
										.parseDouble(textApostar.getText())) {

									// Obtener la apuesta ofrecida y el
									// pronostico elegidos
									StringTokenizer strTok = new StringTokenizer(
											(String) comboPregunta
													.getSelectedItem(), "-");
									codApuestaOfrecida = Integer
											.parseInt((String) strTok
													.nextElement());
									strTok = new StringTokenizer(
											(String) comboRespuesta
													.getSelectedItem(), "-");
									codPronostico = Integer
											.parseInt((String) strTok
													.nextElement());

									// Crear la apuesta sencilla

									apuestaSencilla = new ApuestaSencilla(
											Double.parseDouble(textApostar
													.getText()), eventoApuesta
													.recuperarApuesta(
															codApuestaOfrecida)
													.obtenerPronostico(
															codPronostico),
											String.valueOf(codApuestaOfrecida),
											usuLogin.getCodigo());
									// lo hacemos persistente
									apuestaSencilla = (ApuestaSencilla) ApuestasYa
											.getUnicaInstancia()
											.registrarApuestaRealizada(
													apuestaSencilla);
									// Lo añadimos al evento
									eventoApuesta.recuperarApuesta(
											codApuestaOfrecida)
											.addApuestaRealizada(
													apuestaSencilla);
									// Lo añadimos al usuario
									usuLogin.addApuesta(apuestaSencilla);

									// decrementamos el saldo
									usuLogin.setSaldo(usuLogin.getSaldo()
											- Double.parseDouble(textApostar
													.getText()));

									// actualizamos el usuario
									usuLogin = ApuestasYa.getUnicaInstancia()
											.modificarApuestasRealizadas(
													usuLogin);

									// actualizamos las apuestas ofrecidas
									eventoApuesta
											.modificarApuesta(
													codApuestaOfrecida,
													ApuestasYa
															.getUnicaInstancia()
															.modificarApuestaOfrecida(
																	eventoApuesta
																			.recuperarApuesta(codApuestaOfrecida)));

									// actualizamos el evento
									eventoApuesta = ApuestasYa
											.getUnicaInstancia()
											.modificarEvento(eventoApuesta);

									// Mostrar mensaje de finalizacion correcto

									VentanaError.mostrarMensaje(29);
									VentanaApuesta.this.dispose();
								} else
									// Incorrecto sin saldo
									VentanaError.mostrarMensaje(31);
							} catch (NumberFormatException e1) {
								// Si es un double mal introducido
								VentanaError.mostrarMensaje(30);
							}

						} else
							VentanaError.mostrarMensaje(30);

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel infoPanel = new JPanel();
			getContentPane().add(infoPanel, BorderLayout.EAST);
			infoPanel.setLayout(new BorderLayout(0, 0));
			{
				JPanel infoNorte = new JPanel();
				infoPanel.add(infoNorte, BorderLayout.NORTH);
				{
					JLabel lblInformacin = new JLabel("Informaci\u00F3n");
					infoNorte.add(lblInformacin);
				}
			}
			{
				JPanel infoCentral = new JPanel();
				infoPanel.add(infoCentral, BorderLayout.CENTER);
				infoCentral.setLayout(new FormLayout(
						new ColumnSpec[] { ColumnSpec.decode("185px:grow"), },
						new RowSpec[] { RowSpec.decode("20px"),
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC, }));
				{
					JLabel lblInformacionDePronstico = new JLabel(
							"Ganancia con Pron\u00F3stico ");
					infoCentral.add(lblInformacionDePronstico,
							"1, 1, center, center");
				}
				{

					textGanancia.setEditable(false);
					infoCentral.add(textGanancia, "1, 3, center, top");
					textGanancia.setColumns(10);
				}
				{
					JLabel lblCantidadAApostar = new JLabel(
							"Cantidad a Apostar");
					infoCentral.add(lblCantidadAApostar,
							"1, 5, center, default");
				}
				{
					textApostar = new JTextField();
					infoCentral.add(textApostar, "1, 7, center, default");
					textApostar.setColumns(10);
				}
			}
		}
	}

	private void anadirPreguntas() {
		List<ApuestaOfrecida> apuestasOfrecidas = eventoApuesta.getApuestas();
		for (ApuestaOfrecida apuestaOfrecida : apuestasOfrecidas) {
			if (apuestaOfrecida instanceof ApuestaSimple1X2) {
				// Añadir una pregunta por defecto de futbol simple
				comboPregunta.addItem(apuestaOfrecida.getCodigo()
						+ "-¿Como quedará el partido?");
			} else if (apuestaOfrecida instanceof ApuestaSimple12) {
				// Añadir una pregunta por defecto de tenis simple
				comboPregunta.addItem(apuestaOfrecida.getCodigo()
						+ "-¿Qué tenista se alzará con la victoria?");
			} else if (apuestaOfrecida instanceof ApuestaSimpleVictoria) {
				// Añadir una pregunta por defecto de f1 simple
				comboPregunta.addItem(apuestaOfrecida.getCodigo()
						+ "-¿Qué piloto ganará la carrera?");
			} else if (apuestaOfrecida instanceof ApuestaEspecial) {
				// Añadir la pregunta que tiene la apuesta
				comboPregunta.addItem(apuestaOfrecida.getCodigo()
						+ "-"
						+ ((ApuestaEspecial) apuestaOfrecida)
								.getTituloApuesta());
			}
		}

	}

	private void anadirRespuestas(int pregunta) {
		comboRespuesta.removeAllItems();
		// añadir posibles valores
		List<Pronostico> listaPronostico = eventoApuesta.getApuestas()
				.get(pregunta).getListaPronosticos();
		for (Pronostico pronostico : listaPronostico)
			comboRespuesta.addItem(pronostico.getCodigo() + "-Pronostico:  "
					+ pronostico.getPronostico());
	}
}
