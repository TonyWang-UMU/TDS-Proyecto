package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controler.*;
import model.*;
import javax.swing.JTabbedPane;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPasswordField;

import dao.DAOException;
import dao.FactoriaDAO;
import model.CatalogoEventos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JRadioButton;

@SuppressWarnings({ "serial", "deprecation", "rawtypes", "unchecked" })
public class VentanaPrincipal extends JDialog {
	private JTextField txtTituloDelEvento;

	private JList listaEventos;
	private JTextField textUsuario;
	private JTextField textNombre;
	private JTextField textMail;
	private JTextField textFechaNac;
	private JTextField textIngDefecto;
	private JTextField textCobDefecto;
	private JTextField textCantidad;
	private JTextField textSaldo;
	private JPasswordField passwordPass1;
	private JTabbedPane tabbedPane;
	private JTextField textTipoEve;
	private JTextField textFechaCel;
	private JTextField textHoraCel;
	private JTextField textCuota;
	private SimpleDateFormat fechaDia = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat fechaHora = new SimpleDateFormat("HH:mm");
	private DecimalFormat dosDecimales = new DecimalFormat("#.##");
	private JTextField textApellidos;
	private Usuario usuLogin;
	private boolean cambiarDatos = false;
	private boolean datosCorrectos = true;
	private JTable tablaTransacciones;
	private JTable tablaApuestas;
	private MiModelo modelo;
	private MiModelo modelo2;
	private boolean seleccionadoFutbol;
	private boolean seleccionadoTodos = true;
	private boolean seleccionadoTenis;
	private boolean seleccionadoF1;

	public VentanaPrincipal(Usuario usuario) {
		setTitle("Bienvenido a la aplicaci�n de ApuestaYA! "
				+ usuario.getNombre() + " " + usuario.getApellidos());
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 703, 405);
		getContentPane().setLayout(new BorderLayout());
		usuLogin = usuario;

		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					if (tabbedPane.getSelectedIndex() == 2)
						repintarApuestas();
					if (tabbedPane.getSelectedIndex() == 3)
						repintarTransacciones();
				}
			});

			JPanel panelPerfil = new JPanel(null);
			tabbedPane.addTab("Perfil", null, panelPerfil, "Panel Perfil");
			panelPerfil.setLayout(new GridLayout(0, 2, 0, 0));

			JPanel panelDatosPersonales = new JPanel();
			panelDatosPersonales.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(0, 0, 0)));
			panelPerfil.add(panelDatosPersonales);
			panelDatosPersonales.setLayout(new BorderLayout(0, 0));

			JPanel panelDatosPersNorte = new JPanel();
			panelDatosPersonales.add(panelDatosPersNorte, BorderLayout.NORTH);

			JLabel lblDatosPersonales = new JLabel("DATOS PERSONALES");
			panelDatosPersNorte.add(lblDatosPersonales);

			JPanel panelDatosPersCentro = new JPanel();
			panelDatosPersonales.add(panelDatosPersCentro, BorderLayout.CENTER);
			panelDatosPersCentro.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"), }, new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC, }));

			JLabel lblUsuario = new JLabel("Usuario");
			panelDatosPersCentro.add(lblUsuario, "6, 4, right, default");

			textUsuario = new JTextField();
			textUsuario.setEditable(false);
			panelDatosPersCentro.add(textUsuario, "8, 4, left, default");
			textUsuario.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre");
			panelDatosPersCentro.add(lblNombre, "6, 6, right, default");

			textNombre = new JTextField();
			textNombre.setEditable(false);
			panelDatosPersCentro.add(textNombre, "8, 6, left, default");
			textNombre.setColumns(10);

			JLabel lblApellidos = new JLabel("Apellidos");
			lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
			panelDatosPersCentro.add(lblApellidos, "6, 8, right, default");

			textApellidos = new JTextField();
			textApellidos.setEditable(false);
			panelDatosPersCentro.add(textApellidos, "8, 8, left, default");
			textApellidos.setColumns(10);

			JLabel lblEmail = new JLabel("Email");
			panelDatosPersCentro.add(lblEmail, "6, 10, right, default");

			textMail = new JTextField();
			textMail.setEditable(false);
			panelDatosPersCentro.add(textMail, "8, 10, left, default");
			textMail.setColumns(10);

			JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
			panelDatosPersCentro.add(lblFechaDeNacimiento,
					"6, 12, right, default");

			textFechaNac = new JTextField();
			textFechaNac.setEditable(false);
			textFechaNac.setHorizontalAlignment(SwingConstants.LEFT);
			panelDatosPersCentro.add(textFechaNac, "8, 12, left, default");
			textFechaNac.setColumns(10);

			JLabel lblContrasea = new JLabel("Contrase\u00F1a");
			panelDatosPersCentro.add(lblContrasea, "6, 16, right, default");

			passwordPass1 = new JPasswordField();
			passwordPass1.setEditable(false);
			passwordPass1.setColumns(10);
			panelDatosPersCentro.add(passwordPass1, "8, 16, left, default");

			JPanel panelDatosPersSur = new JPanel();
			panelDatosPersonales.add(panelDatosPersSur, BorderLayout.SOUTH);

			JButton btnActualizarDatos = new JButton("Actualizar datos");
			btnActualizarDatos.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (cambiarDatos) {
						Date fechaNac = MetodosVentana.parseFecha(textFechaNac
								.getText());
						if (fechaNac != null) {
							if (!MetodosVentana.mayorDieciocho(fechaNac)) {
								datosCorrectos = false;
								VentanaError.mostrarMensaje(3);
							}
						} else {
							datosCorrectos = false;
							VentanaError.mostrarMensaje(14);
						}
						if (MetodosVentana.tieneEspacios(textMail.getText())) {
							datosCorrectos = false;
							VentanaError.mostrarMensaje(2);
						}

						if (!passwordPass1.getText().equals(
								usuLogin.getContrase�a())) {
							datosCorrectos = false;
							VentanaError.mostrarMensaje(13);
						}

						if (datosCorrectos) {

							usuLogin.setNombre(textNombre.getText());
							usuLogin.setApellidos(textApellidos.getText());
							usuLogin.setEmail(textMail.getText());
							usuLogin.setFechaNac(fechaNac);

							// llamar al metodo modificar de la persistencia
							// para este
							// usuario
							// modificar el usuario del catalogo de usuarios

							// modificamos el usuario que esta conectado en ese
							// momento

							usuLogin = ApuestasYa.getUnicaInstancia()
									.modificarUsuario(usuLogin);
							VentanaError.mostrarMensaje(16);

							// comprobar que se han introducido correctamente
							// los campos
							// comprobar que las contrase�as coinciden para
							// cambiarlo

							// una vez cambiado mostrar nuevos datos y poner
							// editable a
							// false y cambiar datos a false
							textNombre.setText(usuLogin.getNombre());
							textApellidos.setText(usuLogin.getApellidos());
							textFechaNac.setText(fechaDia.format(usuLogin
									.getFechaNac()));
							textMail.setText(usuLogin.getEmail());

							textNombre.setEditable(false);
							textMail.setEditable(false);
							textFechaNac.setEditable(false);
							passwordPass1.setEditable(false);
							textApellidos.setEditable(false);
							passwordPass1.setText("");
							cambiarDatos = false;

						} else
							datosCorrectos = true;

					}

				}
			});
			panelDatosPersSur.add(btnActualizarDatos);

			JPanel panelDatosCuenta = new JPanel();
			panelDatosCuenta.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(0, 0, 0)));
			panelPerfil.add(panelDatosCuenta);
			panelDatosCuenta.setLayout(new BorderLayout(0, 0));

			JPanel panelDatosCuentaNorte = new JPanel();
			panelDatosCuenta.add(panelDatosCuentaNorte, BorderLayout.NORTH);

			JLabel lblDatosDeIngreso = new JLabel("DATOS DE INGRESO Y COBRO");
			panelDatosCuentaNorte.add(lblDatosDeIngreso);

			JPanel panelDatosCuentaCentro = new JPanel();
			panelDatosCuenta.add(panelDatosCuentaCentro, BorderLayout.CENTER);
			panelDatosCuentaCentro.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC, }));

			JLabel lblPagoPorDefecto = new JLabel("Ingreso por defecto");
			panelDatosCuentaCentro.add(lblPagoPorDefecto, "8, 2");

			textIngDefecto = new JTextField();
			textIngDefecto.setEditable(false);
			panelDatosCuentaCentro.add(textIngDefecto, "8, 4, left, default");
			textIngDefecto.setColumns(10);

			JButton btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaIngreso ventanaIngreso = new VentanaIngreso(usuLogin);
					ventanaIngreso.setLocationRelativeTo(VentanaPrincipal.this);
					ventanaIngreso.setVisible(true);
					textIngDefecto.setText(usuLogin.getIngresoPorDefecto()
							.getClass().getSimpleName());
				}
			});
			panelDatosCuentaCentro.add(btnModificar, "10, 4");

			JLabel lblCobroPorDefecto = new JLabel("Cobro por defecto");
			panelDatosCuentaCentro.add(lblCobroPorDefecto, "8, 8");

			textCobDefecto = new JTextField();
			textCobDefecto.setEditable(false);
			panelDatosCuentaCentro.add(textCobDefecto, "8, 10, left, default");
			textCobDefecto.setColumns(10);

			JButton btnModificar_1 = new JButton("Modificar");
			btnModificar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaCobro ventanaCobro = new VentanaCobro(usuLogin);
					ventanaCobro.setLocationRelativeTo(VentanaPrincipal.this);
					ventanaCobro.setVisible(true);
					textCobDefecto.setText(usuLogin.getCobroPorDefecto()
							.getClass().getSimpleName());
				}
			});
			panelDatosCuentaCentro.add(btnModificar_1, "10, 10");

			JLabel lblIngresarSaldo = new JLabel("Cantidad de ingreso/cobro");
			panelDatosCuentaCentro.add(lblIngresarSaldo,
					"8, 14, 3, 1, center, default");

			textCantidad = new JTextField();
			panelDatosCuentaCentro.add(textCantidad,
					"8, 16, 3, 1, center, default");
			textCantidad.setColumns(10);

			JButton btnIngresar = new JButton("Ingresar");
			btnIngresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Crear una nueva transferencia y hacerla persistente
					try {
						TransaccionIngreso transaccionIngreso = new TransaccionIngreso(
								Double.parseDouble(textCantidad.getText()),
								new Date());
						usuLogin.addTransaccion(transaccionIngreso);

						// Llamamos al metodo pasandole el usuario para que lo
						// modifique en la base de datos
						usuLogin = ApuestasYa.getUnicaInstancia()
								.modificarTransacciones(usuLogin);
						VentanaError.mostrarMensaje(25);
						textSaldo.setText(String.valueOf(dosDecimales
								.format(usuLogin.getSaldo())) + " �");
						textCantidad.setText("");
					} catch (NumberFormatException e) {
						VentanaError.mostrarMensaje(24);
					}

				}
			});
			panelDatosCuentaCentro.add(btnIngresar, "8, 18, right, default");

			JButton btnCobrar = new JButton("Cobrar");
			btnCobrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// cantidad que vamos a cobrar descontado las tasas
					Double cantidadCobrar = Double.parseDouble(textCantidad
							.getText());

					if (usuLogin.getSaldo() >= cantidadCobrar) {
						if (cantidadCobrar >= 30) {

							// crear una nueva transferencia y hacerla
							// persistente
							try {
								TransaccionCobro transaccionCobro = new TransaccionCobro(
										ApuestasYa.getUnicaInstancia().cobrar(
												Double.parseDouble(textCantidad
														.getText())),
										new Date());

								usuLogin.addTransaccion(transaccionCobro);

								// Llamamos al metodo pasandole el usuario para
								// que
								// lo
								// modifique en la base de datos
								usuLogin = ApuestasYa.getUnicaInstancia()
										.modificarTransacciones(usuLogin);
								VentanaError.mostrarMensaje(26);
								textSaldo.setText(String.valueOf(dosDecimales
										.format(usuLogin.getSaldo())) + " �");
								textCantidad.setText("");
							} catch (NumberFormatException e) {
								VentanaError.mostrarMensaje(24);
							}

						} else {

							// no se puede cobrar una cantidad menor que 30
							VentanaError.mostrarMensaje(37);
						}

					} else {
						VentanaError.mostrarMensaje(23);
					}

				}
			});
			panelDatosCuentaCentro
					.add(btnCobrar, "10, 18, 4, 1, left, default");

			JPanel paelDatosSur = new JPanel();
			panelDatosCuenta.add(paelDatosSur, BorderLayout.SOUTH);

			JLabel lblSaldoActual = new JLabel("Saldo actual:");
			paelDatosSur.add(lblSaldoActual);

			textSaldo = new JTextField();
			paelDatosSur.add(textSaldo);
			textSaldo.setForeground(Color.RED);
			textSaldo.setEditable(false);
			textSaldo.setColumns(10);

			textUsuario.setText(usuLogin.getNombreUsuario());
			textMail.setText(usuLogin.getEmail());
			textNombre.setText(usuLogin.getNombre());
			textApellidos.setText(usuLogin.getApellidos());
			textFechaNac.setText(fechaDia.format(usuLogin.getFechaNac()));

			JButton btnCambiarDeContrasea = new JButton(
					"Cambiar Contrase\u00F1a");
			btnCambiarDeContrasea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaPass ventanaPass = new VentanaPass(usuLogin);
					ventanaPass.setLocationRelativeTo(VentanaPrincipal.this);
					ventanaPass.setVisible(true);
				}
			});

			JButton botonCambiarDatos = new JButton("Cambiar Datos");
			botonCambiarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarDatos = true;
					textNombre.setEditable(true);
					textMail.setEditable(true);
					textFechaNac.setEditable(true);
					passwordPass1.setEditable(true);
					textApellidos.setEditable(true);

				}
			});
			panelDatosPersCentro.add(botonCambiarDatos,
					"6, 20, center, default");
			panelDatosPersCentro.add(btnCambiarDeContrasea,
					"8, 20, left, default");
			textSaldo.setText(String.valueOf(dosDecimales.format(usuLogin
					.getSaldo())) + " �");
			textIngDefecto.setText(usuLogin.getIngresoPorDefecto().getClass()
					.getSimpleName());
			textCobDefecto.setText(usuLogin.getCobroPorDefecto().getClass()
					.getSimpleName());

			JButton botonImpuestos = new JButton("Ver Impuestos");
			botonImpuestos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(
							new JDialog(),
							"Este a�o tiene que pagar "
									+ dosDecimales.format((ApuestasYa
											.getUnicaInstancia()
											.calcularImpuestos(usuLogin)))
									+ "�", "Ver Impuestos",
							JOptionPane.DEFAULT_OPTION);

				}
			});
			panelDatosCuentaCentro.add(botonImpuestos, "8, 20");

			JButton btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textSaldo.setText(String.valueOf(dosDecimales
							.format(usuLogin.getSaldo())) + " �");
				}
			});
			panelDatosCuentaCentro.add(btnActualizar, "10, 20");

			JPanel panelEventos = new JPanel();
			tabbedPane.addTab("Eventos", null, panelEventos, "Panel Eventos");
			panelEventos.setLayout(new BorderLayout(0, 0));

			JPanel panelBotones = new JPanel();
			panelEventos.add(panelBotones, BorderLayout.SOUTH);

			JButton botonActualizar = new JButton("Actualizar Eventos");
			botonActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if (seleccionadoTodos)
						repintarEventos();
					else if (seleccionadoF1)
						repintarEventosF1();
					else if (seleccionadoFutbol)
						repintarEventosFutbol();
					else if (seleccionadoTenis)
						repintarEventosTenis();
				}
			});
			panelBotones.add(botonActualizar);

			JPanel panelCentral = new JPanel();
			panelEventos.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
			listaEventos = new JList();
			// eventos iniciales
			repintarEventos();
			listaEventos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (listaEventos.getSelectedValue() != null)
						ponerInfoEventos((String) listaEventos
								.getSelectedValue());
				}
			});

			JScrollPane scrollEventos = new JScrollPane(listaEventos);
			panelCentral.add(scrollEventos);

			JPanel panelInfoEvento = new JPanel();
			panelCentral.add(panelInfoEvento);
			panelInfoEvento.setLayout(new BorderLayout(0, 0));

			JPanel panelTituloInfo = new JPanel();
			panelTituloInfo.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(0, 0, 0)));
			panelInfoEvento.add(panelTituloInfo, BorderLayout.NORTH);

			txtTituloDelEvento = new JTextField();
			panelTituloInfo.add(txtTituloDelEvento);
			txtTituloDelEvento.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtTituloDelEvento.setHorizontalAlignment(SwingConstants.CENTER);
			txtTituloDelEvento.setEditable(false);
			txtTituloDelEvento.setBackground(null);
			txtTituloDelEvento.setBorder(null);
			txtTituloDelEvento.setText("Titulo del Evento");
			txtTituloDelEvento.setColumns(25);

			JPanel panelCentralInfo = new JPanel();
			panelInfoEvento.add(panelCentralInfo, BorderLayout.CENTER);
			panelCentralInfo.setLayout(new BorderLayout(0, 0));

			JPanel panelInfo = new JPanel();
			panelCentralInfo.add(panelInfo, BorderLayout.CENTER);
			panelInfo.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.UNRELATED_GAP_COLSPEC,
					ColumnSpec.decode("125px"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("22px"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("137px"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("42px"), }, new RowSpec[] {
					FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("13px"),
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("max(23dlu;default)"), }));

			JLabel lblTipoDeEvento = new JLabel("Tipo de Evento:");
			panelInfo.add(lblTipoDeEvento, "2, 4, right, top");
			lblTipoDeEvento.setHorizontalAlignment(SwingConstants.RIGHT);

			textTipoEve = new JTextField();
			panelInfo.add(textTipoEve, "6, 4, default, top");
			textTipoEve.setEditable(false);
			textTipoEve.setColumns(10);

			JLabel lblFechaDeCelebracion = new JLabel(
					"Fecha de Celebraci\u00F3n:");
			panelInfo.add(lblFechaDeCelebracion, "2, 6, right, center");
			lblFechaDeCelebracion.setHorizontalAlignment(SwingConstants.RIGHT);

			textFechaCel = new JTextField();
			panelInfo.add(textFechaCel, "6, 6");
			textFechaCel.setEditable(false);
			textFechaCel.setColumns(10);

			JLabel lblHoraDeCelebracin = new JLabel("Hora de Celebraci\u00F3n:");
			panelInfo.add(lblHoraDeCelebracin, "2, 8, right, center");
			lblHoraDeCelebracin.setHorizontalAlignment(SwingConstants.RIGHT);

			textHoraCel = new JTextField();
			panelInfo.add(textHoraCel, "6, 8, default, top");
			textHoraCel.setEditable(false);
			textHoraCel.setColumns(10);

			JLabel lblCuota = new JLabel("Cuota:");
			panelInfo.add(lblCuota, "2, 10, right, center");
			lblCuota.setHorizontalAlignment(SwingConstants.RIGHT);

			textCuota = new JTextField();
			panelInfo.add(textCuota, "6, 10, default, top");
			textCuota.setEditable(false);
			textCuota.setColumns(10);

			JButton botonApostar = new JButton("Ver Apuestas Diponibles");

			panelInfo.add(botonApostar, "1, 12, 8, 1, center, default");
			botonApostar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Coge el seleccionado para ver sus apuestas
					Object[] seleccionados = listaEventos.getSelectedValues();
					int identificadorEvento;
					if (seleccionados.length == 1) {

						StringTokenizer strTk = new StringTokenizer(
								(String) seleccionados[0], "-");
						identificadorEvento = Integer.parseInt((String) strTk
								.nextElement());
						// podemos recuperar el objeto evento
						// de la persistencia y por tanto podemos recuperar
						// sus
						// apuestas
						Evento eventoApuesta = CatalogoEventos
								.getUnicaInstancia().getEvento(
										identificadorEvento);

						// abrir ventana de apuestas
						VentanaApuesta ventanaApuesta = new VentanaApuesta(
								usuLogin, eventoApuesta);
						ventanaApuesta
								.setLocationRelativeTo(VentanaPrincipal.this);
						ventanaApuesta.setVisible(true);
						// actualizar el saldo por si se hizo alguna
						// modificacion
						textSaldo.setText(String.valueOf(dosDecimales
								.format(usuLogin.getSaldo())) + " �");

					} else
						VentanaError.mostrarMensaje(27);
				}
			});

			JPanel panelDeportes = new JPanel();
			panelCentralInfo.add(panelDeportes, BorderLayout.SOUTH);
			panelDeportes.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("58px"), ColumnSpec.decode("109px"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("172px"), }, new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC,
					RowSpec.decode("23px"), FormFactory.LINE_GAP_ROWSPEC,
					RowSpec.decode("26px"), }));

			JLabel lblFiltrar = new JLabel("Filtrar Eventos");
			lblFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
			panelDeportes.add(lblFiltrar, "1, 2, 4, 1, center, default");
			ButtonGroup grupoDeportes = new ButtonGroup();
			JRadioButton rdbtnTodos = new JRadioButton("Todos");
			rdbtnTodos.setSelected(true);
			rdbtnTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					seleccionadoTodos = true;
					seleccionadoF1 = false;
					seleccionadoTenis = false;
					seleccionadoFutbol = false;
					repintarEventos();
				}
			});
			panelDeportes.add(rdbtnTodos, "2, 4, right, top");
			grupoDeportes.add(rdbtnTodos);
			JRadioButton rdbtnFutbol = new JRadioButton("F\u00FAtbol");
			rdbtnFutbol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionadoTodos = false;
					seleccionadoF1 = false;
					seleccionadoTenis = false;
					seleccionadoFutbol = true;
					repintarEventosFutbol();
				}
			});
			panelDeportes.add(rdbtnFutbol, "4, 4, left, top");
			grupoDeportes.add(rdbtnFutbol);
			JRadioButton rdbtnTenis = new JRadioButton("Tenis");
			rdbtnTenis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionadoTodos = false;
					seleccionadoF1 = false;
					seleccionadoTenis = true;
					seleccionadoFutbol = false;
					repintarEventosTenis();
				}
			});
			panelDeportes.add(rdbtnTenis, "2, 6, right, top");
			grupoDeportes.add(rdbtnTenis);
			JRadioButton rdbtnF1 = new JRadioButton("F\u00F3rmula 1");
			rdbtnF1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionadoTodos = false;
					seleccionadoF1 = true;
					seleccionadoTenis = false;
					seleccionadoFutbol = false;
					repintarEventosF1();
				}
			});
			panelDeportes.add(rdbtnF1, "4, 6, left, top");
			grupoDeportes.add(rdbtnF1);
			JPanel panelInfoBoton = new JPanel();
			panelInfoEvento.add(panelInfoBoton, BorderLayout.SOUTH);

			JPanel panelApuestas = new JPanel();
			tabbedPane.addTab("Mis Apuestas", null, panelApuestas,
					"Panel Apuestas");
			panelApuestas.setLayout(new BorderLayout(0, 0));

			JPanel panelApuestasNorte = new JPanel();
			panelApuestas.add(panelApuestasNorte, BorderLayout.NORTH);

			JLabel lblApuestasRealizadas = new JLabel("Apuestas Realizadas");
			lblApuestasRealizadas.setFont(new Font("Tahoma", Font.BOLD, 14));
			panelApuestasNorte.add(lblApuestasRealizadas);

			JPanel panelApuestasCentro = new JPanel();
			panelApuestas.add(panelApuestasCentro, BorderLayout.CENTER);
			panelApuestasCentro.setLayout(new BorderLayout(0, 0));

			tablaApuestas = new JTable();

			JScrollPane panelApuestasScroll = new JScrollPane(tablaApuestas);
			panelApuestasCentro.add(panelApuestasScroll);
			repintarApuestas();

			JPanel panelApuestasSur = new JPanel();
			panelApuestas.add(panelApuestasSur, BorderLayout.SOUTH);

			JButton btnRealizarApuestaMult = new JButton(
					"Realizar Apuesta M\u00FAltiple");
			btnRealizarApuestaMult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// abrir ventana de apuestas multiples
					VentanaApuestaMultiple ventanaApuestaM = new VentanaApuestaMultiple(
							usuLogin);
					ventanaApuestaM
							.setLocationRelativeTo(VentanaPrincipal.this);
					ventanaApuestaM.setVisible(true);
				}
			});

			JButton botonVerApuestas = new JButton(
					"Ver Apuestas M\u00FAltiples");
			botonVerApuestas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaVerMultiples ventanaVerMultiple = new VentanaVerMultiples(
							usuLogin);
					ventanaVerMultiple
							.setLocationRelativeTo(VentanaPrincipal.this);
					ventanaVerMultiple.setVisible(true);
				}
			});
			panelApuestasSur.add(botonVerApuestas);
			panelApuestasSur.add(btnRealizarApuestaMult);

			getContentPane().add(tabbedPane, BorderLayout.CENTER);

			JPanel panelTransacciones = new JPanel();
			tabbedPane.addTab("Transacciones", null, panelTransacciones, null);
			panelTransacciones.setLayout(new BorderLayout(0, 0));

			JPanel panelNorte = new JPanel();
			panelTransacciones.add(panelNorte, BorderLayout.NORTH);

			JLabel lblTransaccionesRealizadas = new JLabel(
					"Transacciones Realizadas");
			lblTransaccionesRealizadas
					.setFont(new Font("Tahoma", Font.BOLD, 14));
			panelNorte.add(lblTransaccionesRealizadas);

			JPanel panelCentro = new JPanel();
			panelTransacciones.add(panelCentro, BorderLayout.CENTER);
			panelCentro.setLayout(new BorderLayout(0, 0));

			tablaTransacciones = new JTable();

			JScrollPane panelTransaccionesScroll = new JScrollPane(
					tablaTransacciones);
			panelCentro.add(panelTransaccionesScroll);
			repintarTransacciones();
		}

		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu menuAdmin = new JMenu("Administrar");
		menuBar.add(menuAdmin);

		JMenuItem menuItemAdministracion = new JMenuItem(
				"Entrar como administrador");
		menuItemAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAdmin ventanaAdmin = new VentanaAdmin(usuLogin);
				ventanaAdmin.setLocationRelativeTo(VentanaPrincipal.this);
				ventanaAdmin.setVisible(true);
			}
		});
		menuAdmin.add(menuItemAdministracion);

		JMenu menuOtros = new JMenu("Otros");
		menuBar.add(menuOtros);

		JMenuItem menuItemAyuda = new JMenuItem("Ayuda");
		menuOtros.add(menuItemAyuda);

		JMenuItem menuItemAcerca = new JMenuItem("Acerca de");
		menuOtros.add(menuItemAcerca);

		JSeparator separador = new JSeparator();
		menuOtros.add(separador);

		JMenuItem menuItemSalir = new JMenuItem("Salir");
		menuItemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal.this.dispose();
			}
		});
		menuOtros.add(menuItemSalir);
	}

	private void ponerInfoEventos(String tituloEvento) {
		StringTokenizer strTok = new StringTokenizer(tituloEvento, "-");
		Evento evento = CatalogoEventos.getUnicaInstancia().getEvento(
				Integer.parseInt((String) strTok.nextElement()));
		txtTituloDelEvento.setText(evento.getNombre());
		textCuota.setText(MetodosVentana.fraseCuota(evento.getCuota()
				.getClass().getSimpleName()));
		textFechaCel.setText(fechaDia.format(evento.getFecha()));
		textHoraCel.setText(fechaHora.format(evento.getHora()));
		textTipoEve.setText(MetodosVentana.fraseDeporte(evento.getDeporte()
				.getClass().getSimpleName()));
	}

	private void repintarEventos() {
		DefaultListModel datos = new DefaultListModel();
		String[] eventosPrueba = null;
		try {
			eventosPrueba = FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
					.getEventoDAO().recuperarTitulosEventos();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < eventosPrueba.length; i++) {
			StringTokenizer strTok = new StringTokenizer(eventosPrueba[i], "-");
			Evento evento = CatalogoEventos.getUnicaInstancia().getEvento(
					Integer.parseInt((String) strTok.nextElement()));
			if (!evento.isCerrado())
				datos.addElement(eventosPrueba[i]);

		}

		listaEventos.setModel(datos);
	}

	private void repintarEventosFutbol() {
		DefaultListModel datos = new DefaultListModel();
		String[] eventosPrueba = null;
		try {
			eventosPrueba = FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
					.getEventoDAO().recuperarTitulosEventos();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < eventosPrueba.length; i++) {
			StringTokenizer strTok = new StringTokenizer(eventosPrueba[i], "-");
			Evento evento = CatalogoEventos.getUnicaInstancia().getEvento(
					Integer.parseInt((String) strTok.nextElement()));
			if (evento.getDeporte() instanceof DeporteFutbol
					&& !evento.isCerrado())
				datos.addElement(eventosPrueba[i]);
		}
		listaEventos.setModel(datos);
	}

	private void repintarEventosTenis() {
		DefaultListModel datos = new DefaultListModel();
		String[] eventosPrueba = null;
		try {
			eventosPrueba = FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
					.getEventoDAO().recuperarTitulosEventos();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < eventosPrueba.length; i++) {
			StringTokenizer strTok = new StringTokenizer(eventosPrueba[i], "-");
			Evento evento = CatalogoEventos.getUnicaInstancia().getEvento(
					Integer.parseInt((String) strTok.nextElement()));
			if (evento.getDeporte() instanceof DeporteTenis
					&& !evento.isCerrado())
				datos.addElement(eventosPrueba[i]);
		}

		listaEventos.setModel(datos);
	}

	private void repintarEventosF1() {
		DefaultListModel datos = new DefaultListModel();
		String[] eventosPrueba = null;
		try {
			eventosPrueba = FactoriaDAO.getFactoriaDAO(FactoriaDAO.PERSIST_TDS)
					.getEventoDAO().recuperarTitulosEventos();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < eventosPrueba.length; i++) {
			StringTokenizer strTok = new StringTokenizer(eventosPrueba[i], "-");
			Evento evento = CatalogoEventos.getUnicaInstancia().getEvento(
					Integer.parseInt((String) strTok.nextElement()));
			if (evento.getDeporte() instanceof DeporteFormula1
					&& !evento.isCerrado())
				datos.addElement(eventosPrueba[i]);
		}

		listaEventos.setModel(datos);
	}

	public class MiModelo extends DefaultTableModel {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	private void repintarTransacciones() {
		List<Transaccion> transacciones = usuLogin.getListaTransacciones();
		modelo = new MiModelo();

		modelo.addColumn("Tipo");
		modelo.addColumn("Fecha");
		modelo.addColumn("Cantidad");

		for (Transaccion transaccion : transacciones) {
			Object[] objetoTransaccion = new Object[3];
			if (transaccion instanceof TransaccionIngreso) {
				objetoTransaccion[0] = "Ingreso";
			} else if (transaccion instanceof TransaccionCobro)
				objetoTransaccion[0] = "Cobro";
			objetoTransaccion[1] = fechaDia.format(transaccion.getFecha());
			objetoTransaccion[2] = dosDecimales.format(transaccion
					.getCantidad());

			modelo.addRow(objetoTransaccion);
		}

		tablaTransacciones.setModel(modelo);

	}

	private void repintarApuestas() {
		List<ApuestaRealizada> apuestasRealizadas = usuLogin
				.getApuestasRealizadas();
		modelo2 = new MiModelo();
		modelo2.addColumn("Cod. Apuesta");
		modelo2.addColumn("Evento");
		modelo2.addColumn("Fecha");
		modelo2.addColumn("Pronostico elegido");
		modelo2.addColumn("Cant. Apuesta");
		modelo2.addColumn("Ganancia");
		modelo2.addColumn("Finalizada");
		modelo2.addColumn("Ganadora");

		// poner mas informacion

		for (ApuestaRealizada aR : apuestasRealizadas) {
			Object[] objetoApuesta = new Object[8];
			// La apuesta ofrecida debe de tener informacion del evento
			// para poder coger el titulo, se hace igual, con un codigo nos
			// sobraria
			ApuestaOfrecida aO;
			Evento eventoApostado;

			if (aR.getTipoApuesta() == 0) {

				// Obtenemos el codigo de la apuesta sobre la que se realizo
				int codApuesta = Integer.parseInt(((ApuestaSencilla) aR)
						.getApuestaOfrecida());

				try {

					aO = ApuestasYa.getUnicaInstancia()
							.recuperarApuestaOfrecida(codApuesta);
					// Obtenemos el evento sobre el que apostamos
					int codEvento = aO.getCodEvento();
					eventoApostado = CatalogoEventos.getUnicaInstancia()
							.getEvento(codEvento);
					// Obtenemos el objeto evento
					objetoApuesta[0] = aR.getCodigo();
					objetoApuesta[1] = eventoApostado.getNombre();
					objetoApuesta[2] = fechaDia.format(eventoApostado
							.getFecha())
							+ " "
							+ fechaHora.format(eventoApostado.getHora());

					// Comprobar el tipo de apuesta
					if (aO.getTipoApuesta() == 0) {
						objetoApuesta[3] = "Ganara el Tenista: "
								+ aR.getPronostico().getPronostico();

					} else if (aO.getTipoApuesta() == 1) {
						objetoApuesta[3] = "Resultado del encuentro: "
								+ aR.getPronostico().getPronostico();

					} else if (aO.getTipoApuesta() == 2) {
						objetoApuesta[3] = "Ganara la carrera: "
								+ aR.getPronostico().getPronostico();

					} else if (aO.getTipoApuesta() == 3) {
						objetoApuesta[3] = ((ApuestaEspecial) aO)
								.getTituloApuesta()
								+ " "
								+ aR.getPronostico().getPronostico();

					}

					objetoApuesta[4] = aR.getCantidad() + " �";
					objetoApuesta[5] = dosDecimales.format(aR.getPronostico()
							.getGanancia());
					if (aR.isFinalizada()) {
						objetoApuesta[6] = "Si";
					} else {
						objetoApuesta[6] = "No";
					}
					if (aR.isGanadora()) {
						objetoApuesta[7] = "Si";
					} else {
						objetoApuesta[7] = "No";
					}
					modelo2.addRow(objetoApuesta);
				} catch (NumberFormatException e) {
				} catch (DAOException e) {
				}
			}

		}
		tablaApuestas.setModel(modelo2);
		// modificar el tama�o de las columnas
		tablaApuestas.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaApuestas.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablaApuestas.getColumnModel().getColumn(2).setPreferredWidth(90);
		tablaApuestas.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablaApuestas.getColumnModel().getColumn(4).setPreferredWidth(40);
		tablaApuestas.getColumnModel().getColumn(5).setPreferredWidth(40);
	}
}
