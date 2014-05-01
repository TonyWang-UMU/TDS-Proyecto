package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.JLabel;

import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import controler.ApuestasYa;
import dao.DAOException;

import javax.swing.JScrollPane;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.ApuestaEspecial;
import model.ApuestaMultipleCombinada;
import model.ApuestaMultipleSistema;
import model.ApuestaOfrecida;
import model.ApuestaRealizada;
import model.ApuestaSencilla;
import model.CatalogoEventos;
import model.Evento;
import model.Pronostico;
import model.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({ "serial" })
public class VentanaApuestaMultiple extends JDialog {
	private JTextField textFieldPrimerNumero;
	private JTextField textFieldSegundoNumero;
	private Usuario usuLogin;
	private MiModelo modelo;
	private MiModelo modelo2;
	private JTable listaDisponibles;
	private List<ApuestaRealizada> listaApuestas;
	// creamos una nueva lista para meter las apuestas que son posibles para
	// las multiples
	List<ApuestaRealizada> apuestasRealizadas;
	private JTable listaSeleccionadas;
	JRadioButton radioBotonApuestaCombinada;
	JRadioButton radioBotonApuestaSistema;
	private JTextField textCantidad;

	/**
	 * Create the dialog.
	 */
	public VentanaApuestaMultiple(Usuario usuario) {
		setBounds(100, 100, 900, 324);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		usuLogin = usuario;
		listaApuestas = new LinkedList<ApuestaRealizada>();
		apuestasRealizadas = new LinkedList<ApuestaRealizada>();
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Realizar Apuesta");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						boolean apuestaCorrecta = false;
						// Comprobar que haya mas de una apuesta

						// Comprobar si es combinada o sistema
						if (radioBotonApuestaCombinada.isSelected()) {
							// tiene que haber mas de una apuesta para la
							// combinada
							if (listaApuestas.size() > 1)
								apuestaCorrecta = true;
							else
								VentanaError.mostrarMensaje(33);

						} else if (radioBotonApuestaSistema.isSelected()) {

							// tiene que haber mas de dos apuestas para la
							// sistema
							if (listaApuestas.size() > 2) {

								// Si es sistema comprobar que se han rellenado
								// los
								// campos combinatorios
								// tiene que no ser vacio, y ser mayor que 1 y
								// menor que el total
								if (!textFieldPrimerNumero.getText().isEmpty()
										&& Integer
												.parseInt(textFieldPrimerNumero
														.getText()) > 1
										&& Integer
												.parseInt(textFieldPrimerNumero
														.getText()) < listaApuestas
												.size()) {
									apuestaCorrecta = true;
								} else {
									VentanaError.mostrarMensaje(35);
								}
							} else {
								VentanaError.mostrarMensaje(34);

							}
						} else {

							VentanaError.mostrarMensaje(32);
						}

						// crear una nueva apuesta y hacerle persistente en la
						// lista de apuestas
						if (apuestaCorrecta) {

							if (textCantidad.getText().length() != 0) {
								// Comprobamos que tenemos saldo suficiente

								if (usuLogin.getSaldo() >= Double
										.parseDouble(textCantidad.getText())) {
									try {
										// comprobamos de nuevo el tipo de
										// apuesta
										// puesto
										// que se instancian objetos distintos
										if (radioBotonApuestaSistema
												.isSelected()) {
											// creamos la apuesta
											ApuestaMultipleSistema apuestaSistema;
											// la combinacion que va a tener la
											// apuesta
											// lo ponemos en este caso como el
											// pronostico
											// porque realmente el pronostico de
											// una
											// apuesta
											// multiple es ese
											Pronostico pronostico = new Pronostico(
													Math.random() + 1,
													textFieldPrimerNumero
															.getText()
															+ "/"
															+ textFieldSegundoNumero
																	.getText());
											// creamos la apuesta
											// correspondiente
											apuestaSistema = new ApuestaMultipleSistema(
													Double.parseDouble(textCantidad
															.getText()),
													pronostico, listaApuestas,
													usuLogin.getCodigo());
											// lo hacemos persistente
											apuestaSistema = (ApuestaMultipleSistema) ApuestasYa
													.getUnicaInstancia()
													.registrarApuestaRealizada(
															apuestaSistema);
											// Lo a�adimos al usuario
											usuLogin.addApuesta(apuestaSistema);
											// decrementamos el saldo
											usuLogin.setSaldo(usuLogin
													.getSaldo()
													- Double.parseDouble(textCantidad
															.getText()));
											// actualizamos el usuario
											usuLogin = ApuestasYa
													.getUnicaInstancia()
													.modificarApuestasRealizadas(
															usuLogin);

										} else if (radioBotonApuestaCombinada
												.isSelected()) {
											ApuestaMultipleCombinada apuestaCombinada;
											apuestaCombinada = new ApuestaMultipleCombinada(
													Double.parseDouble(textCantidad
															.getText()),
													new Pronostico(Math
															.random() + 1, ""),
													listaApuestas, usuLogin
															.getCodigo());
											// lo hacemos persistente
											apuestaCombinada = (ApuestaMultipleCombinada) ApuestasYa
													.getUnicaInstancia()
													.registrarApuestaRealizada(
															apuestaCombinada);

											// Lo a�adimos al usuario
											usuLogin.addApuesta(apuestaCombinada);
											// decrementamos el saldo
											usuLogin.setSaldo(usuLogin
													.getSaldo()
													- Double.parseDouble(textCantidad
															.getText()));
											// actualizamos el usuario
											usuLogin = ApuestasYa
													.getUnicaInstancia()
													.modificarApuestasRealizadas(
															usuLogin);

										}
										// mostrar que es correcto y cerrar
										VentanaError.mostrarMensaje(36);
										VentanaApuestaMultiple.this.dispose();
									} catch (NumberFormatException e1) {
										// Si es un double mal introducido
										VentanaError.mostrarMensaje(30);
									}

								} else {
									// Incorrecto sin saldo
									VentanaError.mostrarMensaje(31);
								}
							} else {
								VentanaError.mostrarMensaje(30);
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VentanaApuestaMultiple.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panelTitulo = new JPanel();
				panel.add(panelTitulo, BorderLayout.NORTH);
				panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel lblApuestasMultiple = new JLabel(
							"Apuestas M\u00FAltiples");
					lblApuestasMultiple
							.setHorizontalAlignment(SwingConstants.CENTER);
					lblApuestasMultiple.setFont(new Font("Tahoma", Font.BOLD,
							16));
					panelTitulo.add(lblApuestasMultiple);
				}
			}
			{
				JPanel panelCentral = new JPanel();
				panel.add(panelCentral, BorderLayout.CENTER);
				panelCentral.setLayout(new FormLayout(new ColumnSpec[] {
						ColumnSpec.decode("400px"),
						ColumnSpec.decode("center:50dlu:grow"),
						ColumnSpec.decode("400px"), }, new RowSpec[] { RowSpec
						.decode("155px:grow"), }));
				{
					JPanel panelApuestas = new JPanel();
					panelCentral.add(panelApuestas, "1, 1, fill, fill");
					panelApuestas.setLayout(new BorderLayout(0, 0));
					{
						JScrollPane scrollPane = new JScrollPane();
						panelApuestas.add(scrollPane);
						{
							listaDisponibles = new JTable();
							scrollPane.setViewportView(listaDisponibles);
							repintarApuestasOfrecidas();
						}
					}
				}
				{
					JPanel panelFlechas = new JPanel();
					panelCentral.add(panelFlechas, "2, 1");
					panelFlechas.setLayout(new FormLayout(
							new ColumnSpec[] { ColumnSpec.decode("75px"), },
							new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.RELATED_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.RELATED_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC, }));
					{
						{
							JButton button_1 = new JButton(">>");
							button_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {

									// Cogemos la apuesta seleccionada del lado
									// izquierdo
									// y lo a�adimos a la lista de apuestas
									// en caso de no haber ninguna seleccionada
									// no
									// hacer nada

									if (listaDisponibles.getSelectedRows().length != 0) {

										listaApuestas.add(apuestasRealizadas
												.get(listaDisponibles
														.getSelectedRow()));

										// repintamos las apuestas
										repintarApuestasEscogidas();

										// ponemos el total sobre la que podemos
										// hacer la sistema en caso de que este
										// apuesta de sistema activo
										if (radioBotonApuestaSistema
												.isSelected()) {
											textFieldSegundoNumero.setText(Integer
													.toString(listaApuestas
															.size()));
										}
									}

								}
							});
							panelFlechas.add(button_1, "1, 2");
						}
					}
					JButton button = new JButton("<<");
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							// Cogemos la apuesta seleccionada del lado
							// derecho
							// y lo quitamos de la lista de apuestas
							// en caso de no haber ninguna seleccionada o
							// hacer nada
							if (listaSeleccionadas.getSelectedRows().length != 0) {

								listaApuestas.remove(listaSeleccionadas
										.getSelectedRow());

								// repintamos las apuestas
								repintarApuestasEscogidas();

								// ponemos el total sobre la que podemos
								// hacer la sistema en caso de que este
								// apuesta de sistema activo
								if (radioBotonApuestaSistema.isSelected()) {
									textFieldSegundoNumero.setText(Integer
											.toString(listaApuestas.size()));
								}
							}
						}
					});
					panelFlechas.add(button, "1, 4");
				}
				{
					JPanel panelApuestasEscogidas = new JPanel();
					panelCentral
							.add(panelApuestasEscogidas, "3, 1, fill, fill");
					panelApuestasEscogidas.setLayout(new BorderLayout(0, 0));
					{
						JScrollPane scrollPane = new JScrollPane();
						panelApuestasEscogidas.add(scrollPane);
						{
							listaSeleccionadas = new JTable();
							scrollPane.setViewportView(listaSeleccionadas);
							repintarApuestasEscogidas();
						}
					}

				}
			}
			{
				JPanel panelSur = new JPanel();
				panel.add(panelSur, BorderLayout.SOUTH);
				{
					ButtonGroup grupo = new ButtonGroup();
					panelSur.setLayout(new FormLayout(new ColumnSpec[] {
							ColumnSpec.decode("400px"),
							ColumnSpec.decode("94px:grow"),
							ColumnSpec.decode("400px"), },
							new RowSpec[] { RowSpec.decode("66px:grow"), }));
					JPanel panelSeleccionApuesta = new JPanel();
					panelSur.add(panelSeleccionApuesta, "1, 1, fill, fill");
					panelSeleccionApuesta.setLayout(new GridLayout(2, 1, 0, 0));
					{
						JLabel tituloTipoApuesta = new JLabel("Tipo de Apuesta");
						tituloTipoApuesta.setFont(new Font("Tahoma",
								Font.PLAIN, 13));
						tituloTipoApuesta
								.setHorizontalAlignment(SwingConstants.CENTER);
						panelSeleccionApuesta.add(tituloTipoApuesta);
					}
					{
						JPanel panelTipoApuesta = new JPanel();
						panelSeleccionApuesta.add(panelTipoApuesta);
						{
							radioBotonApuestaCombinada = new JRadioButton(
									"Apuesta Combinada");
							radioBotonApuestaCombinada
									.addActionListener(new ActionListener() {
										// Desactivamos las opciones de la
										// apuesta sistema
										public void actionPerformed(
												ActionEvent e) {
											textFieldPrimerNumero
													.setEditable(false);
											textFieldPrimerNumero.setText("");
											textFieldSegundoNumero.setText("");
										}
									});
							panelTipoApuesta.add(radioBotonApuestaCombinada);
							grupo.add(radioBotonApuestaCombinada);
						}
						{
							radioBotonApuestaSistema = new JRadioButton(
									"Apuesta Sistema");
							radioBotonApuestaSistema
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent e) {
											// ponemos el numero total de
											// apuestas sobre las que podemos
											// realizar la sistema
											textFieldPrimerNumero
													.setEditable(true);
											textFieldSegundoNumero.setText(Integer
													.toString(listaApuestas
															.size()));
										}
									});
							panelTipoApuesta.add(radioBotonApuestaSistema);
							grupo.add(radioBotonApuestaSistema);
						}
					}

				}
				{
					JPanel panelCantidad = new JPanel();
					panelSur.add(panelCantidad, "2, 1, fill, fill");
					panelCantidad.setLayout(new GridLayout(0, 1, 0, 0));
					{
						JLabel lblCantidad = new JLabel("Cantidad");
						lblCantidad
								.setHorizontalAlignment(SwingConstants.CENTER);
						lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
						panelCantidad.add(lblCantidad);
					}
					{
						JPanel panelCajaCantidad = new JPanel();
						panelCantidad.add(panelCajaCantidad);
						{
							textCantidad = new JTextField();
							panelCajaCantidad.add(textCantidad);
							textCantidad.setColumns(8);
						}
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panelSur.add(panel_1, "3, 1, fill, fill");
					panel_1.setLayout(new GridLayout(2, 1, 0, 0));
					{
						JPanel panelVacio = new JPanel();
						panel_1.add(panelVacio);
						{
							JLabel labelCombiSistema = new JLabel(
									"Combinacion Sistema");
							labelCombiSistema.setFont(new Font("Tahoma",
									Font.PLAIN, 13));
							panelVacio.add(labelCombiSistema);
						}
					}
					{
						JPanel panelSistema = new JPanel();
						panel_1.add(panelSistema);
						{
							textFieldPrimerNumero = new JTextField();
							textFieldPrimerNumero.setEditable(false);
							panelSistema.add(textFieldPrimerNumero);
							textFieldPrimerNumero.setColumns(5);
						}
						{
							JLabel lblBarra = new JLabel("/");
							panelSistema.add(lblBarra);
						}
						{
							textFieldSegundoNumero = new JTextField();
							textFieldSegundoNumero.setEditable(false);
							panelSistema.add(textFieldSegundoNumero);
							textFieldSegundoNumero.setColumns(5);
						}
					}
				}
			}
		}
	}

	public class MiModelo extends DefaultTableModel {

		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

	private void repintarApuestasOfrecidas() {

		// iteramos sobre las apuestas realizadas
		for (ApuestaRealizada apuestaRealizada : usuLogin
				.getApuestasRealizadas()) {
			if (!apuestaRealizada.isFinalizada()
					&& apuestaRealizada.getTipoApuesta() == 0) {
				apuestasRealizadas.add(apuestaRealizada);
			}
		}

		modelo = new MiModelo();
		modelo.addColumn("Evento");
		modelo.addColumn("Pronostico elegido");
		// poner mas informacion

		for (ApuestaRealizada aR : apuestasRealizadas) {
			Object[] objetoApuesta = new Object[2];
			// La apuesta ofrecida debe de tener informacion del evento
			// para poder coger el titulo, se hace igual, con un codigo nos
			// sobraria
			ApuestaOfrecida aO;
			Evento eventoApostado;

			// Obtenemos el codigo de la apuesta sobre la que se realizo
			int codApuesta = Integer.parseInt(((ApuestaSencilla) aR)
					.getApuestaOfrecida());

			try {
				aO = ApuestasYa.getUnicaInstancia().recuperarApuestaOfrecida(
						codApuesta);
				// Obtenemos el evento sobre el que apostamos
				int codEvento = aO.getCodEvento();
				eventoApostado = CatalogoEventos.getUnicaInstancia().getEvento(
						codEvento);
				// Obtenemos el objeto evento

				objetoApuesta[0] = eventoApostado.getNombre();

				// Comprobar el tipo de apuesta
				if (aO.getTipoApuesta() == 0) {
					objetoApuesta[1] = "Ganara el Tenista: "
							+ aR.getPronostico().getPronostico();

				} else if (aO.getTipoApuesta() == 1) {
					objetoApuesta[1] = "Resultado del encuentro: "
							+ aR.getPronostico().getPronostico();

				} else if (aO.getTipoApuesta() == 2) {
					objetoApuesta[1] = "Ganara la carrera: "
							+ aR.getPronostico().getPronostico();

				} else if (aO.getTipoApuesta() == 3) {
					objetoApuesta[1] = ((ApuestaEspecial) aO)
							.getTituloApuesta()
							+ " "
							+ aR.getPronostico().getPronostico();

				}
			} catch (DAOException e) {
			}
			modelo.addRow(objetoApuesta);

		}
		listaDisponibles.setModel(modelo);
		// modificar el tama�o de las columnas
		listaDisponibles.getColumnModel().getColumn(0).setPreferredWidth(200);

		listaDisponibles.getColumnModel().getColumn(1).setPreferredWidth(200);

	}

	private void repintarApuestasEscogidas() {
		modelo2 = new MiModelo();
		modelo2.addColumn("Evento");
		modelo2.addColumn("Pronostico elegido");
		for (ApuestaRealizada aR : listaApuestas) {
			Object[] objetoApuesta = new Object[2];
			// La apuesta ofrecida debe de tener informacion del evento
			// para poder coger el titulo, se hace igual, con un codigo nos
			// sobraria
			ApuestaOfrecida aO;
			Evento eventoApostado;

			// Obtenemos el codigo de la apuesta sobre la que se realizo
			int codApuesta = Integer.parseInt(((ApuestaSencilla) aR)
					.getApuestaOfrecida());

			try {
				aO = ApuestasYa.getUnicaInstancia().recuperarApuestaOfrecida(
						codApuesta);
				// Obtenemos el evento sobre el que apostamos
				int codEvento = aO.getCodEvento();
				eventoApostado = CatalogoEventos.getUnicaInstancia().getEvento(
						codEvento);
				// Obtenemos el objeto evento

				objetoApuesta[0] = eventoApostado.getNombre();

				// Comprobar el tipo de apuesta
				if (aO.getTipoApuesta() == 0) {
					objetoApuesta[1] = "Ganara el Tenista: "
							+ aR.getPronostico().getPronostico();

				} else if (aO.getTipoApuesta() == 1) {
					objetoApuesta[1] = "Resultado del encuentro: "
							+ aR.getPronostico().getPronostico();

				} else if (aO.getTipoApuesta() == 2) {
					objetoApuesta[1] = "Ganara la carrera: "
							+ aR.getPronostico().getPronostico();

				} else if (aO.getTipoApuesta() == 3) {
					objetoApuesta[1] = ((ApuestaEspecial) aO)
							.getTituloApuesta()
							+ " "
							+ aR.getPronostico().getPronostico();

				}
			} catch (DAOException e) {
			}

			modelo2.addRow(objetoApuesta);
		}
		listaSeleccionadas.setModel(modelo2);
		// modificar el tama�o de las columnas
		listaSeleccionadas.getColumnModel().getColumn(0).setPreferredWidth(200);

		listaSeleccionadas.getColumnModel().getColumn(1).setPreferredWidth(200);
	}
}