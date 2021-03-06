package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import dao.DAOException;
import dao.FactoriaDAO;

import controler.*;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import model.*;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.io.File;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import pulsador.Luz;
import pulsador.IEncendidoListener;

import java.util.EventObject;

@SuppressWarnings({ "serial", "deprecation", "rawtypes", "unchecked" })
public class VentanaAdmin extends JDialog {
	private JTable tablaUsuarios;
	private boolean esFutbol = false;
	private boolean esTenis = false;
	private boolean esF1 = false;
	private JTextField txtTituloDelEvento;
	private JList listaEventos;
	private SimpleDateFormat fechaDia = new SimpleDateFormat("dd/MM/yyyy");

	private JTextField textJug1;
	private JTextField textJug2;
	private JTextField textField_0;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JTextField textField_35;
	private JTextField textField_36;
	private JTextField textField_37;
	private JTextField textField_38;
	private JTextField textField_39;
	private JTextField textField_40;
	private JTextField textField_41;
	private JTextField textField_42;
	private JTextField textField_43;
	private JTextField textField_44;
	private JTextField textField_45;
	private JTextField textField_46;
	private JTextField textField_47;
	private JTextField textFechaEvento;
	private JTextField textHoraEvento;
	private JTextField textNombreEvento;
	private JTabbedPane panelCentral;
	private JComboBox comboCuota;
	private MiModelo modelo;
	private boolean registradoCorrecto = true;
	private Usuario usuLogin;
	private JComboBox respuestaTenis;
	private JComboBox respuestaFutbol;
	private JComboBox respuestaF1;
	private JComboBox preguntaTenis;
	private JComboBox preguntaF1;
	private JComboBox preguntaFutbol;
	private Evento eventoApuestas;

	public VentanaAdmin(Usuario usuario) {
		setTitle("Ventana de Administraci\u00F3n de ApuestaYA!");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 733, 650);
		usuLogin = usuario;
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(panelBotones, BorderLayout.SOUTH);
			{
				JButton botonCancelar = new JButton("Volver");
				botonCancelar.setHorizontalAlignment(SwingConstants.RIGHT);
				botonCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VentanaAdmin.this.dispose();
					}
				});
				panelBotones.add(botonCancelar);
				getRootPane().setDefaultButton(botonCancelar);
			}
		}
		{
			panelCentral = new JTabbedPane(JTabbedPane.TOP);
			panelCentral.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (panelCentral.getSelectedIndex() == 1) {
						repintarEventos();
					}
				}
			});
			getContentPane().add(panelCentral, BorderLayout.CENTER);
			{
				JPanel panelEventos = new JPanel();
				panelCentral.addTab("Crear Eventos", null, panelEventos, null);
				panelEventos.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblTituloInsert = new JLabel(
							"Menu de Inserci\u00F3n de Nuevo Evento");
					lblTituloInsert
							.setHorizontalAlignment(SwingConstants.CENTER);
					lblTituloInsert.setFont(new Font("Tahoma", Font.BOLD, 16));
					panelEventos.add(lblTituloInsert, BorderLayout.NORTH);
				}
				{
					JPanel panelSelDeporte = new JPanel();
					panelSelDeporte.setBorder(new BevelBorder(
							BevelBorder.LOWERED, null, null, null, null));
					panelEventos.add(panelSelDeporte, BorderLayout.WEST);
					ButtonGroup grupo = new ButtonGroup();
					panelSelDeporte.setLayout(new GridLayout(1, 1, 0, 0));
					{
						JPanel panelSuperior = new JPanel();
						panelSelDeporte.add(panelSuperior);
						panelSuperior
								.setLayout(new FormLayout(
										new ColumnSpec[] { ColumnSpec
												.decode("131px"), },
										new RowSpec[] {
												RowSpec.decode("max(29dlu;default)"),
												RowSpec.decode("24px"),
												RowSpec.decode("27px"),
												FormFactory.DEFAULT_ROWSPEC,
												RowSpec.decode("22px"),
												RowSpec.decode("23px"),
												RowSpec.decode("23px"),
												RowSpec.decode("23px"),
												FormFactory.DEFAULT_ROWSPEC,
												RowSpec.decode("14px"),
												RowSpec.decode("26px"),
												FormFactory.DEFAULT_ROWSPEC,
												RowSpec.decode("14px"),
												RowSpec.decode("25px"),
												FormFactory.DEFAULT_ROWSPEC,
												FormFactory.RELATED_GAP_ROWSPEC,
												RowSpec.decode("22px"),
												RowSpec.decode("20px"), }));
						{
							JLabel lblNombre = new JLabel("Nombre del Evento:");
							lblNombre
									.setHorizontalAlignment(SwingConstants.CENTER);
							panelSuperior
									.add(lblNombre, "1, 2, center, center");
						}
						{
							textNombreEvento = new JTextField();
							panelSuperior.add(textNombreEvento,
									"1, 3, center, center");
							textNombreEvento.setColumns(10);
						}
						{
							JLabel lblSelDeporte = new JLabel("Deporte:");
							lblSelDeporte
									.setHorizontalAlignment(SwingConstants.LEFT);
							panelSuperior.add(lblSelDeporte,
									"1, 5, center, center");
						}
						{
							JRadioButton botonFutbol = new JRadioButton(
									"Futbol");
							botonFutbol
									.setHorizontalAlignment(SwingConstants.LEFT);
							botonFutbol.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									// ponemos que es futbol
									esFutbol = true;
									esTenis = false;
									esF1 = false;
									// limpiamos las casillas que no sean de
									// futbol
									limpiarTenis();
									limpiarF1();
									ponerFutbol();

									// ponemos a true las casillas de futbol

								}
							});
							panelSuperior
									.add(botonFutbol, "1, 6, left, center");
							grupo.add(botonFutbol);
						}
						{
							JRadioButton botonTenis = new JRadioButton("Tenis");
							botonTenis
									.setHorizontalAlignment(SwingConstants.LEFT);
							botonTenis.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									esFutbol = false;
									esTenis = true;
									esF1 = false;
									limpiarFutbol();
									limpiarF1();
									ponerTenis();
								}
							});
							panelSuperior.add(botonTenis, "1, 7, left, center");
							grupo.add(botonTenis);
						}
						{
							JRadioButton botonF1 = new JRadioButton("Formula 1");
							botonF1.setHorizontalAlignment(SwingConstants.LEFT);
							botonF1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									esFutbol = false;
									esTenis = false;
									esF1 = true;
									limpiarFutbol();
									limpiarTenis();
									ponerF1();
								}
							});
							panelSuperior.add(botonF1, "1, 8, left, center");
							grupo.add(botonF1);
						}
						{
							JLabel lblFecha = new JLabel("Fecha (dd/MM/aaaa): ");
							lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
							panelSuperior
									.add(lblFecha, "1, 10, center, center");
						}
						{
							textFechaEvento = new JTextField();
							panelSuperior.add(textFechaEvento,
									"1, 11, center, center");
							textFechaEvento.setColumns(10);
						}
						{
							JLabel lblHora = new JLabel("Hora (HH:mm): ");
							lblHora.setHorizontalAlignment(SwingConstants.LEFT);
							panelSuperior.add(lblHora, "1, 13, center, center");
						}
						{
							textHoraEvento = new JTextField();
							panelSuperior.add(textHoraEvento,
									"1, 14, center, center");
							textHoraEvento.setColumns(10);
						}
						{
							JLabel lblCuota = new JLabel("Cuota:");
							lblCuota.setHorizontalAlignment(SwingConstants.LEFT);
							panelSuperior
									.add(lblCuota, "1, 17, center, center");
						}
						{
							comboCuota = new JComboBox();
							panelSuperior.add(comboCuota,
									"1, 18, center, center");
							comboCuota.addItem("Cuota Europea");
							comboCuota.addItem("Cuota Britanica");
						}
					}
				}
				{
					JPanel panelParticipantes = new JPanel();
					panelParticipantes.setBorder(new BevelBorder(
							BevelBorder.LOWERED, null, null, null, null));
					panelEventos.add(panelParticipantes, BorderLayout.CENTER);
					panelParticipantes.setLayout(new GridLayout(0, 3, 0, 0));
					{
						JPanel panelTenis = new JPanel();
						panelTenis.setBorder(new MatteBorder(1, 1, 1, 1,
								(Color) new Color(0, 0, 0)));
						panelParticipantes.add(panelTenis);
						panelTenis.setLayout(new BorderLayout(0, 0));
						{
							JPanel panelTenisCentro = new JPanel();
							panelTenis.add(panelTenisCentro,
									BorderLayout.CENTER);
							panelTenisCentro
									.setLayout(new FormLayout(
											new ColumnSpec[] {
													FormFactory.RELATED_GAP_COLSPEC,
													FormFactory.DEFAULT_COLSPEC,
													FormFactory.RELATED_GAP_COLSPEC,
													ColumnSpec.decode("105px"),
													FormFactory.RELATED_GAP_COLSPEC,
													FormFactory.DEFAULT_COLSPEC, },
											new RowSpec[] {
													RowSpec.decode("max(9dlu;default)"),
													FormFactory.RELATED_GAP_ROWSPEC,
													FormFactory.DEFAULT_ROWSPEC,
													FormFactory.DEFAULT_ROWSPEC, }));
							{
								JLabel lblJug1 = new JLabel("Jugador 1:");
								panelTenisCentro.add(lblJug1, "2, 3");
							}
							{
								textJug1 = new JTextField();
								panelTenisCentro.add(textJug1,
										"4, 3, fill, default");
								textJug1.setColumns(10);
							}
							{
								JLabel lblJug2 = new JLabel("Jugador 2:");
								panelTenisCentro.add(lblJug2, "2, 4");
							}
							{
								textJug2 = new JTextField();
								panelTenisCentro.add(textJug2,
										"4, 4, fill, default");
								textJug2.setColumns(10);
							}
						}
						{
							JPanel panelTenisNorte = new JPanel();
							panelTenis.add(panelTenisNorte, BorderLayout.NORTH);
							{
								JLabel lblNewLabel = new JLabel(
										"Jugadores de Tenis:");
								panelTenisNorte.add(lblNewLabel);
							}
						}
					}
					{
						JPanel panelFutbol = new JPanel();
						panelFutbol.setBorder(new MatteBorder(1, 1, 1, 1,
								(Color) new Color(0, 0, 0)));
						panelParticipantes.add(panelFutbol);
						panelFutbol.setLayout(new BorderLayout(0, 0));
						{
							JPanel panelFutbolNorte = new JPanel();
							panelFutbol.add(panelFutbolNorte,
									BorderLayout.NORTH);
							{
								JLabel lblTituloFutbol = new JLabel(
										"Jugadores de Futbol:");
								panelFutbolNorte.add(lblTituloFutbol);
							}
						}
						{
							JPanel panelFutbolCentro = new JPanel();
							panelFutbol.add(panelFutbolCentro,
									BorderLayout.CENTER);
							panelFutbolCentro
									.setLayout(new FormLayout(
											new ColumnSpec[] {
													ColumnSpec.decode("76px"),
													ColumnSpec.decode("105px"), },
											new RowSpec[] {
													FormFactory.NARROW_LINE_GAP_ROWSPEC,
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"), }));
							{
								JLabel lblEquipo = new JLabel("Equipo 1:");
								lblEquipo
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblEquipo,
										"1, 2, fill, fill");
							}
							{
								textField_0 = new JTextField();
								panelFutbolCentro.add(textField_0,
										"2, 2, fill, fill");
								textField_0.setColumns(10);
							}
							{
								JLabel lblJ = new JLabel("E1-J1:");
								lblJ.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ, "1, 3, fill, fill");
							}
							{
								textField_1 = new JTextField();
								panelFutbolCentro.add(textField_1,
										"2, 3, fill, fill");
								textField_1.setColumns(10);
							}
							{
								JLabel lblJ_1 = new JLabel("E1-J2");
								lblJ_1.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_1,
										"1, 4, fill, fill");
							}
							{
								textField_2 = new JTextField();
								panelFutbolCentro.add(textField_2,
										"2, 4, fill, fill");
								textField_2.setColumns(10);
							}
							{
								JLabel lblJ_2 = new JLabel("E1-J3");
								lblJ_2.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_2,
										"1, 5, fill, fill");
							}
							{
								textField_3 = new JTextField();
								panelFutbolCentro.add(textField_3,
										"2, 5, fill, fill");
								textField_3.setColumns(10);
							}
							{
								JLabel lblJ_4 = new JLabel("E1-J4");
								lblJ_4.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_4,
										"1, 6, fill, fill");
							}
							{
								textField_4 = new JTextField();
								panelFutbolCentro.add(textField_4,
										"2, 6, fill, fill");
								textField_4.setColumns(10);
							}
							{
								JLabel lblJ_5 = new JLabel("E1-J5");
								lblJ_5.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_5,
										"1, 7, fill, fill");
							}
							{
								textField_5 = new JTextField();
								panelFutbolCentro.add(textField_5,
										"2, 7, fill, fill");
								textField_5.setColumns(10);
							}
							{
								JLabel lblJ_6 = new JLabel("E1-J6");
								lblJ_6.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_6,
										"1, 8, fill, fill");
							}
							{
								textField_6 = new JTextField();
								panelFutbolCentro.add(textField_6,
										"2, 8, fill, fill");
								textField_6.setColumns(10);
							}
							{
								JLabel lblJ_7 = new JLabel("E1-J7");
								lblJ_7.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_7,
										"1, 9, fill, fill");
							}
							{
								textField_7 = new JTextField();
								panelFutbolCentro.add(textField_7,
										"2, 9, fill, fill");
								textField_7.setColumns(10);
							}
							{
								JLabel lblJ_8 = new JLabel("E1-J8");
								lblJ_8.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_8,
										"1, 10, fill, fill");
							}
							{
								textField_8 = new JTextField();
								panelFutbolCentro.add(textField_8,
										"2, 10, fill, fill");
								textField_8.setColumns(10);
							}
							{
								JLabel lblJ_9 = new JLabel("E1-J9");
								lblJ_9.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_9,
										"1, 11, fill, fill");
							}
							{
								textField_9 = new JTextField();
								panelFutbolCentro.add(textField_9,
										"2, 11, fill, fill");
								textField_9.setColumns(10);
							}
							{
								JLabel lblJ_10 = new JLabel("E1-J10");
								lblJ_10.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_10,
										"1, 12, fill, fill");
							}
							{
								textField_10 = new JTextField();
								panelFutbolCentro.add(textField_10,
										"2, 12, fill, fill");
								textField_10.setColumns(10);
							}
							{
								JLabel lblJ_11 = new JLabel("E1-J11");
								lblJ_11.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_11,
										"1, 13, fill, fill");
							}
							{
								textField_11 = new JTextField();
								panelFutbolCentro.add(textField_11,
										"2, 13, fill, fill");
								textField_11.setColumns(10);
							}
							{
								JLabel lblEquipo_1 = new JLabel("Equipo 2:");
								lblEquipo_1
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblEquipo_1,
										"1, 14, fill, fill");
							}
							{
								textField_12 = new JTextField();
								panelFutbolCentro.add(textField_12,
										"2, 14, fill, fill");
								textField_12.setColumns(10);
							}
							{
								JLabel lblJ = new JLabel("E1-J1");
								lblJ.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro
										.add(lblJ, "1, 15, fill, fill");
							}
							{
								textField_13 = new JTextField();
								panelFutbolCentro.add(textField_13,
										"2, 15, fill, fill");
								textField_13.setColumns(10);
							}
							{
								JLabel lblJ_1 = new JLabel("E2-J2");
								lblJ_1.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_1,
										"1, 16, fill, fill");
							}
							{
								textField_14 = new JTextField();
								panelFutbolCentro.add(textField_14,
										"2, 16, fill, fill");
								textField_14.setColumns(10);
							}
							{
								JLabel lblJ_2 = new JLabel("E2-J3");
								lblJ_2.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_2,
										"1, 17, fill, fill");
							}
							{
								textField_15 = new JTextField();
								panelFutbolCentro.add(textField_15,
										"2, 17, fill, fill");
								textField_15.setColumns(10);
							}
							{
								JLabel lblJ_4 = new JLabel("E2-J4");
								lblJ_4.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_4,
										"1, 18, fill, fill");
							}
							{
								textField_16 = new JTextField();
								panelFutbolCentro.add(textField_16,
										"2, 18, fill, fill");
								textField_16.setColumns(10);
							}
							{
								JLabel lblJ_5 = new JLabel("E2-J5");
								lblJ_5.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_5,
										"1, 19, fill, fill");
							}
							{
								textField_17 = new JTextField();
								panelFutbolCentro.add(textField_17,
										"2, 19, fill, fill");
								textField_17.setColumns(10);
							}
							{
								JLabel lblJ_6 = new JLabel("E2-J6");
								lblJ_6.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_6,
										"1, 20, fill, fill");
							}
							{
								textField_18 = new JTextField();
								panelFutbolCentro.add(textField_18,
										"2, 20, fill, fill");
								textField_18.setColumns(10);
							}
							{
								JLabel lblJ_7 = new JLabel("E2-J7");
								lblJ_7.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_7,
										"1, 21, fill, fill");
							}
							{
								textField_19 = new JTextField();
								panelFutbolCentro.add(textField_19,
										"2, 21, fill, fill");
								textField_19.setColumns(10);
							}
							{
								JLabel lblJ_8 = new JLabel("E2-J8");
								lblJ_8.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_8,
										"1, 22, fill, fill");
							}
							{
								textField_20 = new JTextField();
								panelFutbolCentro.add(textField_20,
										"2, 22, fill, fill");
								textField_20.setColumns(10);
							}
							{
								JLabel lblJ_9 = new JLabel("E2-J9");
								lblJ_9.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_9,
										"1, 23, fill, fill");
							}
							{
								textField_21 = new JTextField();
								panelFutbolCentro.add(textField_21,
										"2, 23, fill, fill");
								textField_21.setColumns(10);
							}
							{
								JLabel lblJ_10 = new JLabel("E2-J10");
								lblJ_10.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_10,
										"1, 24, fill, fill");
							}
							{
								textField_22 = new JTextField();
								panelFutbolCentro.add(textField_22,
										"2, 24, fill, fill");
								textField_22.setColumns(10);
							}
							{
								JLabel lblJ_11 = new JLabel("E2-J11");
								lblJ_11.setHorizontalAlignment(SwingConstants.CENTER);
								panelFutbolCentro.add(lblJ_11,
										"1, 25, fill, fill");
							}
							{
								textField_23 = new JTextField();
								panelFutbolCentro.add(textField_23,
										"2, 25, fill, fill");
								textField_23.setColumns(10);
							}

						}
					}
					{
						JPanel panelF1 = new JPanel();
						panelF1.setBorder(new MatteBorder(1, 1, 1, 1,
								(Color) new Color(0, 0, 0)));
						panelParticipantes.add(panelF1);
						panelF1.setLayout(new BorderLayout(0, 0));
						{
							JPanel panelF1Norte = new JPanel();
							panelF1.add(panelF1Norte, BorderLayout.NORTH);
							{
								JLabel lblCorredoresDeF = new JLabel(
										"Corredores de F1:");
								panelF1Norte.add(lblCorredoresDeF);
							}
						}
						{
							JPanel panelF1Centro = new JPanel();
							panelF1.add(panelF1Centro, BorderLayout.CENTER);
							panelF1Centro
									.setLayout(new FormLayout(
											new ColumnSpec[] {
													ColumnSpec.decode("76px"),
													ColumnSpec.decode("105px"), },
											new RowSpec[] {
													FormFactory.NARROW_LINE_GAP_ROWSPEC,
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"),
													RowSpec.decode("19px"), }));
							{
								JLabel lblCorredor = new JLabel("Corredor 1:");
								lblCorredor
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor,
										"1, 2, fill, fill");
							}
							{
								textField_24 = new JTextField();
								panelF1Centro.add(textField_24,
										"2, 2, fill, fill");
								textField_24.setColumns(10);
							}
							{
								JLabel lblCorredor_1 = new JLabel("Corredor 2:");
								lblCorredor_1
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_1,
										"1, 3, fill, fill");
							}
							{
								textField_25 = new JTextField();
								panelF1Centro.add(textField_25,
										"2, 3, fill, fill");
								textField_25.setColumns(10);
							}
							{
								JLabel lblCorredor_2 = new JLabel("Corredor 3:");
								lblCorredor_2
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_2,
										"1, 4, fill, fill");
							}
							{
								textField_26 = new JTextField();
								panelF1Centro.add(textField_26,
										"2, 4, fill, fill");
								textField_26.setColumns(10);
							}
							{
								JLabel lblCorredor_3 = new JLabel("Corredor 4:");
								lblCorredor_3
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_3,
										"1, 5, fill, fill");
							}
							{
								textField_27 = new JTextField();
								panelF1Centro.add(textField_27,
										"2, 5, fill, fill");
								textField_27.setColumns(10);
							}
							{
								JLabel lblCorredor_4 = new JLabel("Corredor 5:");
								lblCorredor_4
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_4,
										"1, 6, fill, fill");
							}
							{
								textField_28 = new JTextField();
								panelF1Centro.add(textField_28,
										"2, 6, fill, fill");
								textField_28.setColumns(10);
							}
							{
								JLabel lblCorredor_5 = new JLabel("Corredor 6:");
								lblCorredor_5
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_5,
										"1, 7, fill, fill");
							}
							{
								textField_29 = new JTextField();
								panelF1Centro.add(textField_29,
										"2, 7, fill, fill");
								textField_29.setColumns(10);
							}
							{
								JLabel lblCorredor_6 = new JLabel("Corredor 7:");
								lblCorredor_6
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_6,
										"1, 8, fill, fill");
							}
							{
								textField_30 = new JTextField();
								panelF1Centro.add(textField_30,
										"2, 8, fill, fill");
								textField_30.setColumns(10);
							}
							{
								JLabel lblCorredor_7 = new JLabel("Corredor 8:");
								lblCorredor_7
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_7,
										"1, 9, fill, fill");
							}
							{
								textField_31 = new JTextField();
								panelF1Centro.add(textField_31,
										"2, 9, fill, fill");
								textField_31.setColumns(10);
							}
							{
								JLabel lblCorredor_8 = new JLabel("Corredor 9:");
								lblCorredor_8
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_8,
										"1, 10, fill, fill");
							}
							{
								textField_32 = new JTextField();
								panelF1Centro.add(textField_32,
										"2, 10, fill, fill");
								textField_32.setColumns(10);
							}
							{
								JLabel lblCorredor_9 = new JLabel(
										"Corredor 10:");
								lblCorredor_9
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_9,
										"1, 11, fill, fill");
							}
							{
								textField_33 = new JTextField();
								panelF1Centro.add(textField_33,
										"2, 11, fill, fill");
								textField_33.setColumns(10);
							}
							{
								JLabel lblCorredor_10 = new JLabel(
										"Corredor 11:");
								lblCorredor_10
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_10,
										"1, 12, fill, fill");
							}
							{
								textField_34 = new JTextField();
								panelF1Centro.add(textField_34,
										"2, 12, fill, fill");
								textField_34.setColumns(10);
							}
							{
								JLabel lblCorredor_11 = new JLabel(
										"Corredor 12:");
								lblCorredor_11
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_11,
										"1, 13, fill, fill");
							}
							{
								textField_35 = new JTextField();
								panelF1Centro.add(textField_35,
										"2, 13, fill, fill");
								textField_35.setColumns(10);
							}
							{
								JLabel lblCorredor_12 = new JLabel(
										"Corredor 13:");
								lblCorredor_12
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_12,
										"1, 14, fill, fill");
							}
							{
								textField_36 = new JTextField();
								panelF1Centro.add(textField_36,
										"2, 14, fill, fill");
								textField_36.setColumns(10);
							}
							{
								JLabel lblCorredor_13 = new JLabel(
										"Corredor 14:");
								lblCorredor_13
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_13,
										"1, 15, fill, fill");
							}
							{
								textField_37 = new JTextField();
								panelF1Centro.add(textField_37,
										"2, 15, fill, fill");
								textField_37.setColumns(10);
							}
							{
								JLabel lblCorredor_14 = new JLabel(
										"Corredor 15:");
								lblCorredor_14
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_14,
										"1, 16, fill, fill");
							}
							{
								textField_38 = new JTextField();
								panelF1Centro.add(textField_38,
										"2, 16, fill, fill");
								textField_38.setColumns(10);
							}
							{
								JLabel lblCorredor_15 = new JLabel(
										"Corredor 16:");
								lblCorredor_15
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_15,
										"1, 17, fill, fill");
							}
							{
								textField_39 = new JTextField();
								panelF1Centro.add(textField_39,
										"2, 17, fill, fill");
								textField_39.setColumns(10);
							}
							{
								JLabel lblCorredor_16 = new JLabel(
										"Corredor 17:");
								lblCorredor_16
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_16,
										"1, 18, fill, fill");
							}
							{
								textField_40 = new JTextField();
								panelF1Centro.add(textField_40,
										"2, 18, fill, fill");
								textField_40.setColumns(10);
							}
							{
								JLabel lblCorredor_17 = new JLabel(
										"Corredor 18:");
								lblCorredor_17
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_17,
										"1, 19, fill, fill");
							}
							{
								textField_41 = new JTextField();
								panelF1Centro.add(textField_41,
										"2, 19, fill, fill");
								textField_41.setColumns(10);
							}
							{
								JLabel lblCorredor_18 = new JLabel(
										"Corredor 19:");
								lblCorredor_18
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_18,
										"1, 20, fill, fill");
							}
							{
								textField_42 = new JTextField();
								panelF1Centro.add(textField_42,
										"2, 20, fill, fill");
								textField_42.setColumns(10);
							}
							{
								JLabel lblCorredor_19 = new JLabel(
										"Corredor 20:");
								lblCorredor_19
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_19,
										"1, 21, fill, fill");
							}
							{
								textField_43 = new JTextField();
								panelF1Centro.add(textField_43,
										"2, 21, fill, fill");
								textField_43.setColumns(10);
							}
							{
								JLabel lblCorredor_20 = new JLabel(
										"Corredor 21:");
								lblCorredor_20
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_20,
										"1, 22, fill, fill");
							}
							{
								textField_44 = new JTextField();
								panelF1Centro.add(textField_44,
										"2, 22, fill, fill");
								textField_44.setColumns(10);
							}
							{
								JLabel lblCorredor_21 = new JLabel(
										"Corredor 22:");
								lblCorredor_21
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_21,
										"1, 23, fill, fill");
							}
							{
								textField_45 = new JTextField();
								panelF1Centro.add(textField_45,
										"2, 23, fill, fill");
								textField_45.setColumns(10);
							}
							{
								JLabel lblCorredor_22 = new JLabel(
										"Corredor 23:");
								lblCorredor_22
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_22,
										"1, 24, fill, fill");
							}
							{
								textField_46 = new JTextField();
								panelF1Centro.add(textField_46,
										"2, 24, fill, fill");
								textField_46.setColumns(10);
							}
							{
								JLabel lblCorredor_23 = new JLabel(
										"Corredor 24:");
								lblCorredor_23
										.setHorizontalAlignment(SwingConstants.CENTER);
								panelF1Centro.add(lblCorredor_23,
										"1, 25, fill, fill");
							}
							{
								textField_47 = new JTextField();
								panelF1Centro.add(textField_47,
										"2, 25, fill, fill");
								textField_47.setColumns(10);
							}
							limpiarFutbol();
							limpiarTenis();
							limpiarF1();
						}
					}
				}
				{
					JPanel panelEventosBotones = new JPanel();
					panelEventos.add(panelEventosBotones, BorderLayout.SOUTH);
					{
						JButton botonRegistrarEvento = new JButton(
								"Registrar Evento");
						botonRegistrarEvento
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										// COMPROBAR QUE TODOS LOS CAMPOS ESTEN
										// BIEN
										// COMOROBAR QUE NO ESTEN VACIOS
										if (textFechaEvento.getText().length() != 0
												&& textHoraEvento.getText()
														.length() != 0
												&& textFechaEvento.getText()
														.length() != 0) {
											// COMPROBAR QUE ESTE LA FECHA Y
											// HORA BIEN
											Date diaEvento = MetodosVentana
													.parseFecha(textFechaEvento
															.getText());
											Date horaEvento = MetodosVentana
													.parseHora(textHoraEvento
															.getText());

											Cuota cuota = MetodosVentana
													.cuotaSeleccionada((String) comboCuota
															.getSelectedItem());

											if (diaEvento != null
													&& horaEvento != null) {

												// COMPROBAR QUE SE HA
												// SELECCIONADO
												// AL MENOS UN DEPORTE
												List<Participante> participantes = new LinkedList<Participante>();

												if (esFutbol) {
													if (futbolRelleno()) {

														List<ParticipanteJugador> jugadoresLocal = new LinkedList<ParticipanteJugador>();
														List<ParticipanteJugador> jugadoresVisitante = new LinkedList<ParticipanteJugador>();
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_1
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_2
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_3
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_4
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_5
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_6
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_7
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_8
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_9
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_10
																				.getText()));
														jugadoresLocal
																.add(new ParticipanteJugador(
																		textField_11
																				.getText()));

														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_13
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_14
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_15
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_16
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_17
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_18
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_19
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_20
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_21
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_22
																				.getText()));
														jugadoresVisitante
																.add(new ParticipanteJugador(
																		textField_23
																				.getText()));

														ParticipanteEquipo local = new ParticipanteEquipo(
																textField_0
																		.getText(),
																jugadoresLocal);
														ParticipanteEquipo visitante = new ParticipanteEquipo(
																textField_12
																		.getText(),
																jugadoresVisitante);

														participantes
																.add(local);
														participantes
																.add(visitante);

														ApuestasYa
																.getUnicaInstancia()
																.registrarEvento(
																		textNombreEvento
																				.getText(),
																		diaEvento,
																		horaEvento,
																		new DeporteFutbol(),
																		participantes,
																		cuota);

													} else {
														registradoCorrecto = false;
														VentanaError
																.mostrarMensaje(11);
													}
												} else if (esF1) {
													if (F1Relleno()) {
														participantes
																.add(new ParticipanteJugador(
																		textField_24
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_25
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_26
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_27
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_28
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_29
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_30
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_31
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_32
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_33
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_34
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_35
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_36
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_37
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_38
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_39
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_40
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_41
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_42
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_43
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_44
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_45
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_46
																				.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textField_47
																				.getText()));
														ApuestasYa
																.getUnicaInstancia()
																.registrarEvento(
																		textNombreEvento
																				.getText(),
																		diaEvento,
																		horaEvento,
																		new DeporteFormula1(),
																		participantes,
																		cuota);

													} else {
														registradoCorrecto = false;
														VentanaError
																.mostrarMensaje(11);
													}

												} else if (esTenis) {
													if (tenisRelleno()) {
														participantes
																.add(new ParticipanteJugador(
																		textJug1.getText()));
														participantes
																.add(new ParticipanteJugador(
																		textJug2.getText()));
														ApuestasYa
																.getUnicaInstancia()
																.registrarEvento(
																		textNombreEvento
																				.getText(),
																		diaEvento,
																		horaEvento,
																		new DeporteTenis(),
																		participantes,
																		cuota);

													} else {
														registradoCorrecto = false;
														VentanaError
																.mostrarMensaje(11);
													}
												} else {
													registradoCorrecto = false;
													VentanaError
															.mostrarMensaje(9);
												}
											} else {
												registradoCorrecto = false;
												VentanaError.mostrarMensaje(10);
											}
										} else {
											registradoCorrecto = false;
											VentanaError.mostrarMensaje(1);
										}
										if (registradoCorrecto) {
											reset();
											VentanaError.mostrarMensaje(12);
										} else
											registradoCorrecto = true;
									}

								});
						panelEventosBotones.add(botonRegistrarEvento);
					}
				}
			}
			{
				JPanel panelApuestas = new JPanel();
				panelCentral.addTab("Administrar Eventos", null, panelApuestas,
						"Crear Apuestas");
				panelApuestas.setLayout(new BorderLayout(0, 0));

				JPanel panelBotones = new JPanel();
				panelApuestas.add(panelBotones, BorderLayout.SOUTH);

				JButton botonActualizar = new JButton("Actualizar Eventos");
				botonActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						repintarEventos();
					}
				});
				panelBotones.add(botonActualizar);
				{
					JButton btnEliminarSeleccionado_1 = new JButton(
							"Eliminar Seleccionado");
					btnEliminarSeleccionado_1
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {

									if (listaEventos.getSelectedValue() != null) {
										// Obtenemos el identificador del evento
										String eventoSeleccionado = (String) listaEventos
												.getSelectedValue();
										StringTokenizer strTok = new StringTokenizer(
												eventoSeleccionado, "-");
										int identificador = Integer
												.parseInt((String) strTok
														.nextElement());

										// Lo eliminamos obteniendolo del
										// catalogo
										ApuestasYa
												.getUnicaInstancia()
												.borrarEvento(
														CatalogoEventos
																.getUnicaInstancia()
																.getEvento(
																		identificador));
										repintarEventos();
									}
								}
							});
					panelBotones.add(btnEliminarSeleccionado_1);
				}

				JPanel panelCentral = new JPanel();
				panelApuestas.add(panelCentral, BorderLayout.CENTER);
				panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
				listaEventos = new JList();
				listaEventos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (listaEventos.getSelectedValue() != null) {
							String eventoSeleccionado = (String) listaEventos
									.getSelectedValue();
							StringTokenizer strTok = new StringTokenizer(
									eventoSeleccionado, "-");
							int codEvento = Integer.parseInt((String) strTok
									.nextElement());

							// En eventoApuestas vamos a tener en todo momento
							// el
							// evento sobre el que estamos operando
							// Con esto cada vez que lo necesitemos no tenemos
							// que
							// volver a recuperarlo
							eventoApuestas = CatalogoEventos
									.getUnicaInstancia().getEvento(codEvento);

							// Dependiendo de la seleccion configuramos los
							// combobox

							if (eventoApuestas.getDeporte() instanceof DeporteFutbol) {
								apostarFutbol();
								preguntaFutbol.setSelectedIndex(0);
								ponerParticipantesFutbol();
							} else if (eventoApuestas.getDeporte() instanceof DeporteTenis) {
								apostarTenis();
								preguntaTenis.setSelectedIndex(0);
								ponerSets();
							} else if (eventoApuestas.getDeporte() instanceof DeporteFormula1) {
								apostarF1();
								preguntaF1.setSelectedIndex(0);
								ponerParticipantesF1();
							}
						}
					}
				});
				repintarEventos();

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
				txtTituloDelEvento
						.setHorizontalAlignment(SwingConstants.CENTER);
				txtTituloDelEvento.setEditable(false);
				txtTituloDelEvento.setBackground(null);
				txtTituloDelEvento.setBorder(null);
				txtTituloDelEvento.setText("Crear Apuesta Especial");
				txtTituloDelEvento.setColumns(25);

				JPanel panelCentralInfo = new JPanel();
				panelInfoEvento.add(panelCentralInfo, BorderLayout.CENTER);
				panelCentralInfo.setLayout(new BoxLayout(panelCentralInfo,
						BoxLayout.Y_AXIS));
				{
					JPanel panelTenis = new JPanel();
					panelCentralInfo.add(panelTenis);
					panelTenis.setLayout(new BorderLayout(0, 0));
					{
						JPanel tituloTenis = new JPanel();
						tituloTenis
								.setBorder(new LineBorder(new Color(0, 0, 0)));
						panelTenis.add(tituloTenis, BorderLayout.NORTH);
						{
							JLabel lblTenis = new JLabel("Apuestas de Tenis");
							lblTenis.setFont(new Font("Tahoma", Font.BOLD, 13));
							tituloTenis.add(lblTenis);
						}
					}
					{

						JPanel centralTenis = new JPanel();
						panelTenis.add(centralTenis, BorderLayout.CENTER);
						{

							preguntaTenis = new JComboBox();
							preguntaTenis
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent arg0) {
											// Cuando seleccionas pregunta tenis

											// Si seleccionas quien hare el
											// primer ace te
											// deja elegir de
											// respuestas los jugadores
											if (preguntaTenis
													.getSelectedIndex() == 1) {
												// Comprobacion siempre de si no
												// es null para cuando se
												// realiza la primera pasada no
												// de null pointer
												if (respuestaTenis != null)
													ponerParticipantesTenis();

											}

											// Si seleccionas cuantos sets
											// durara deja elegir un valor
											// numerico
											else if (preguntaTenis
													.getSelectedIndex() == 0) {
												if (respuestaTenis != null)
													ponerSets();

											}

										}
									});
							centralTenis.setLayout(new GridLayout(0, 1, 0, 0));
							{
								JLabel lblPreguntaEspecial = new JLabel(
										"Pregunta Especial:");
								lblPreguntaEspecial
										.setBackground(Color.LIGHT_GRAY);
								lblPreguntaEspecial
										.setHorizontalAlignment(SwingConstants.CENTER);
								centralTenis.add(lblPreguntaEspecial);
							}
							centralTenis.add(preguntaTenis);

							preguntaTenis
									.addItem("�Cu�ntos sets durar� el partido?");
							preguntaTenis.addItem("�Qui�n har� m�s ace's?");
						}
						{
							JLabel lblPosiblesPronosticos = new JLabel(
									"Posibles Pron\u00F3sticos:");
							lblPosiblesPronosticos
									.setHorizontalAlignment(SwingConstants.CENTER);
							centralTenis.add(lblPosiblesPronosticos);
						}
						respuestaTenis = new JComboBox();
						ponerSets();
						centralTenis.add(respuestaTenis);

					}
				}
				{
					JPanel panelFutbol = new JPanel();
					panelCentralInfo.add(panelFutbol);
					panelFutbol.setLayout(new BorderLayout(0, 0));
					{
						JPanel tituloFutbol = new JPanel();
						tituloFutbol.setBorder(new LineBorder(
								new Color(0, 0, 0)));
						panelFutbol.add(tituloFutbol, BorderLayout.NORTH);
						{
							JLabel lblApuestasDeFtbol = new JLabel(
									"Apuestas de F\u00FAtbol");
							lblApuestasDeFtbol.setFont(new Font("Tahoma",
									Font.BOLD, 13));
							tituloFutbol.add(lblApuestasDeFtbol);
						}
					}
					{
						JPanel centralFutbol = new JPanel();
						panelFutbol.add(centralFutbol, BorderLayout.CENTER);
						{
							preguntaFutbol = new JComboBox();
							preguntaFutbol
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent arg0) {
											// acciones para la apuesta de
											// futbol

											// Preguntas relacionadas con el
											// jugador
											if (preguntaFutbol
													.getSelectedIndex() == 0) {
												if (respuestaFutbol != null)
													ponerParticipantesFutbol();
												// Preguntas relacionadas con el
												// minuto de juego
											} else if (preguntaFutbol
													.getSelectedIndex() == 1) {
												if (respuestaFutbol != null)
													ponerMinutos();

											}
										}
									});
							centralFutbol.setLayout(new GridLayout(0, 1, 0, 0));
							{
								JLabel lblPreguntaEspecial_1 = new JLabel(
										"Pregunta Especial:");
								lblPreguntaEspecial_1
										.setHorizontalAlignment(SwingConstants.CENTER);
								centralFutbol.add(lblPreguntaEspecial_1);
							}
							centralFutbol.add(preguntaFutbol);
							preguntaFutbol
									.addItem("�Qui�n ser� el primer goleador?");
							preguntaFutbol
									.addItem("�En qu� minuto se marcara el primer gol?");
						}
						{
							respuestaFutbol = new JComboBox();
							ponerParticipantesFutbol();
							{
								JLabel lblPosiblesPronsticos = new JLabel(
										"Posibles Pron\u00F3sticos:");
								lblPosiblesPronsticos
										.setHorizontalAlignment(SwingConstants.CENTER);
								centralFutbol.add(lblPosiblesPronsticos);
							}
							centralFutbol.add(respuestaFutbol);
						}
					}
				}
				{
					JPanel panelFormula = new JPanel();
					panelCentralInfo.add(panelFormula);
					panelFormula.setLayout(new BorderLayout(0, 0));
					{
						JPanel tituloF1 = new JPanel();
						tituloF1.setBorder(new LineBorder(new Color(0, 0, 0)));
						panelFormula.add(tituloF1, BorderLayout.NORTH);
						{
							JLabel lblApuestasDeF = new JLabel("Apuestas de F1");
							lblApuestasDeF.setFont(new Font("Tahoma",
									Font.BOLD, 13));
							tituloF1.add(lblApuestasDeF);
						}
					}
					{
						JPanel centralF1 = new JPanel();
						panelFormula.add(centralF1, BorderLayout.CENTER);
						{
							preguntaF1 = new JComboBox();
							preguntaF1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									// todos tienen que ver con los
									// participantes
									// se deja el codigo por si en el futuro hay
									// que poner nuevas preguntas
									if (respuestaF1 != null)
										ponerParticipantesF1();
								}
							});
							centralF1.setLayout(new GridLayout(0, 1, 0, 0));
							{
								JLabel lblPreguntaEspecial_2 = new JLabel(
										"Pregunta Especial:");
								lblPreguntaEspecial_2
										.setHorizontalAlignment(SwingConstants.CENTER);
								centralF1.add(lblPreguntaEspecial_2);
							}
							centralF1.add(preguntaF1);
							preguntaF1
									.addItem("�Qui�n quedar� el �ltima posici�n");
							preguntaF1
									.addItem("�Qui�n har� la vuelta m�s r�pida?");
						}
						{
							respuestaF1 = new JComboBox();

							ponerParticipantesF1();
							{
								JLabel lblPosiblesPronsticos_1 = new JLabel(
										"Posibles Pron\u00F3sticos:");
								lblPosiblesPronsticos_1
										.setHorizontalAlignment(SwingConstants.CENTER);
								centralF1.add(lblPosiblesPronsticos_1);
							}
							centralF1.add(respuestaF1);
						}
					}
				}
				// Bloqueamos las apuestas hasta que elijamos un evento
				bloquearApuestas();

				JPanel panelInfoBoton = new JPanel();
				panelInfoEvento.add(panelInfoBoton, BorderLayout.SOUTH);

				JButton botonApostar = new JButton("Crear Apuesta");
				panelInfoBoton.add(botonApostar);
				botonApostar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						// Cogemos los seleccionados
						Object[] seleccionados = listaEventos
								.getSelectedValues();
						// Solo se permite que se seleccione un evento
						if (seleccionados.length == 1) {
							// Cada vez que seleccionamos tenemos en
							// eventoApuestas el evento seleccionado
							// se comprueba que solo haya una eleccion y ya
							// podemos operar sobre ese evento al darle al boton
							// de apostar
							String tituloApuesta = "";
							List<Pronostico> listaPronosticos = new LinkedList<Pronostico>();
							Pronostico pronostico;
							ApuestaEspecial apuestaEspecial;

							// Vemos que tipo de evento hemos cogido para coger
							// los combo box correctos
							if (eventoApuestas.getDeporte() instanceof DeporteFutbol) {
								// obtenemos el titulo de la apuesta especial
								// que estamos realizando
								tituloApuesta = (String) preguntaFutbol
										.getSelectedItem();
								// creamos la lista de pronosticos disponibles
								// para esa apuesta

								for (int i = 0; i < respuestaFutbol
										.getItemCount(); i++) {
									pronostico = ApuestasYa
											.getUnicaInstancia()
											.registrarPronostico(
													(Math.random() * 3 + 1),
													String.valueOf(respuestaFutbol
															.getItemAt(i)));
									listaPronosticos.add(pronostico);
								}

							} else if (eventoApuestas.getDeporte() instanceof DeporteTenis) {

								tituloApuesta = (String) preguntaTenis
										.getSelectedItem();
								for (int i = 0; i < respuestaTenis
										.getItemCount(); i++) {
									pronostico = ApuestasYa.getUnicaInstancia()
											.registrarPronostico(
													(Math.random() * 3 + 1),
													(String) respuestaTenis
															.getItemAt(i));
									listaPronosticos.add(pronostico);
								}

							} else if (eventoApuestas.getDeporte() instanceof DeporteFormula1) {
								tituloApuesta = (String) preguntaF1
										.getSelectedItem();

								for (int i = 0; i < respuestaF1.getItemCount(); i++) {
									pronostico = ApuestasYa.getUnicaInstancia()
											.registrarPronostico(
													(Math.random() * 3 + 1),
													(String) respuestaF1
															.getItemAt(i));
									listaPronosticos.add(pronostico);
								}

							}

							// ya podemos crear la apuesta y hacerla
							// persistente
							apuestaEspecial = new ApuestaEspecial(
									listaPronosticos,
									eventoApuestas.getCuota(), tituloApuesta);
							// Lo registramos en la base de datos
							apuestaEspecial = (ApuestaEspecial) ApuestasYa
									.getUnicaInstancia()
									.registrarApuestaOfrecida(apuestaEspecial);

							// Para que la apuesta conozca al evento
							apuestaEspecial.setCodEvento(eventoApuestas
									.getCodigo());
							apuestaEspecial = (ApuestaEspecial) ApuestasYa
									.getUnicaInstancia().meterCodEvento(
											apuestaEspecial);
							// Lo metemos en nuestro evento actual
							eventoApuestas.addApuesta(apuestaEspecial);

							// modificamos el evento tanto en la base de
							// datos como en el catalogo

							// mensaje si todo ha sido registrado
							// correctamente
							eventoApuestas = ApuestasYa.getUnicaInstancia()
									.modificarEvento(eventoApuestas);
							VentanaError.mostrarMensaje(28);

						} else
							VentanaError.mostrarMensaje(27);

					}
				});
			}
			{
				JPanel panelUsuarios = new JPanel();
				panelCentral.addTab("Administrar Usuarios", null,
						panelUsuarios, null);
				{
					panelUsuarios.setLayout(new BorderLayout(0, 0));
				}
				{
					JPanel panelUsuariosNorte = new JPanel();
					panelUsuarios.add(panelUsuariosNorte, BorderLayout.NORTH);
					{
						JLabel lblTituloUsuarios = new JLabel(
								"Relacion de Usuarios Registrados");
						lblTituloUsuarios.setFont(new Font("Tahoma", Font.BOLD,
								16));
						panelUsuariosNorte.add(lblTituloUsuarios);
					}
				}
				{
					JPanel panelUsuariosCentral = new JPanel();
					panelUsuarios
							.add(panelUsuariosCentral, BorderLayout.CENTER);
					tablaUsuarios = new JTable();
					repintarUsuarios();
					panelUsuariosCentral.setLayout(new BorderLayout(0, 0));
					{
						JScrollPane panelTablaUsuarios = new JScrollPane(
								tablaUsuarios);
						panelUsuariosCentral.add(panelTablaUsuarios,
								BorderLayout.CENTER);
					}
					{
						{
							JPanel panelUsuariosSur = new JPanel();
							panelUsuarios.add(panelUsuariosSur,
									BorderLayout.SOUTH);
							JButton btnEliminarSeleccionado = new JButton(
									"Eliminar Seleccionado");
							panelUsuariosSur.add(btnEliminarSeleccionado);
							{
								final JPanel panelResultados = new JPanel();
								panelCentral.addTab("Cargador Resultados",
										null, panelResultados, null);
								panelResultados
										.setLayout(new BorderLayout(0, 0));
								{
									JPanel panelBoton = new JPanel();
									panelResultados.add(panelBoton,
											BorderLayout.CENTER);
									{
										final Luz luz = new Luz();
										luz.setColor(Color.YELLOW);
										luz.addEncendidoListener(new IEncendidoListener() {
											public void enteradoCambioEncendido(
													EventObject arg0) {
												if (luz.isEncendido()) {
													JFileChooser fileChooser = new JFileChooser();
													FileNameExtensionFilter filter = new FileNameExtensionFilter(
															"xml", "xml", "xml");
													fileChooser
															.setFileFilter(filter);
													fileChooser
															.showOpenDialog(panelResultados);
													File file = fileChooser
															.getSelectedFile();

													if (file != null) {
														ComponenteResultadosEventos
																.getUnicaInstancia()
																.asignarArchivo(
																		file.getAbsolutePath());
													} else {
														luz.setEncendido(false);
													}
												}
											}
										});
										panelBoton.add(luz);

									}
								}
								{
									JLabel tituloResultado = new JLabel(
											"Cargador de Resultados");
									tituloResultado.setFont(new Font("Tahoma",
											Font.BOLD, 16));
									tituloResultado
											.setHorizontalAlignment(SwingConstants.CENTER);
									panelResultados.add(tituloResultado,
											BorderLayout.NORTH);
								}
							}
							btnEliminarSeleccionado
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent e) {
											// No se podra borrar al usuario que
											// este
											// conectado en ese momento
											int codigoUsuario;
											if (tablaUsuarios.getSelectedRow() != -1) {
												codigoUsuario = (Integer) modelo.getValueAt(
														tablaUsuarios
																.getSelectedRow(),
														0);

												// Si no es el usuario conectado
												// lo
												// eliminamos
												if (codigoUsuario != usuLogin
														.getCodigo()) {
													ApuestasYa
															.getUnicaInstancia()
															.borrarUsuario(
																	CatalogoUsuarios
																			.getUnicaInstancia()
																			.getUsuario(
																					codigoUsuario));

													VentanaError
															.mostrarMensaje(22);
													repintarUsuarios();
												} else
													VentanaError
															.mostrarMensaje(21);
											}
										}
									});
						}
					}
				}
			}
		}
	}

	private boolean futbolRelleno() {
		if (textField_0.getText().length() != 0
				&& textField_1.getText().length() != 0
				&& textField_2.getText().length() != 0
				&& textField_3.getText().length() != 0
				&& textField_4.getText().length() != 0
				&& textField_5.getText().length() != 0
				&& textField_6.getText().length() != 0
				&& textField_7.getText().length() != 0
				&& textField_8.getText().length() != 0
				&& textField_9.getText().length() != 0
				&& textField_10.getText().length() != 0
				&& textField_11.getText().length() != 0
				&& textField_12.getText().length() != 0
				&& textField_13.getText().length() != 0
				&& textField_14.getText().length() != 0
				&& textField_15.getText().length() != 0
				&& textField_16.getText().length() != 0
				&& textField_17.getText().length() != 0
				&& textField_18.getText().length() != 0
				&& textField_19.getText().length() != 0
				&& textField_20.getText().length() != 0
				&& textField_21.getText().length() != 0
				&& textField_22.getText().length() != 0
				&& textField_23.getText().length() != 0)
			return true;

		return false;
	}

	private boolean tenisRelleno() {
		if (textJug1.getText().length() != 0
				&& textJug2.getText().length() != 0)
			return true;
		return false;
	}

	private boolean F1Relleno() {
		if (textField_24.getText().length() != 0
				&& textField_25.getText().length() != 0
				&& textField_26.getText().length() != 0
				&& textField_27.getText().length() != 0
				&& textField_28.getText().length() != 0
				&& textField_29.getText().length() != 0
				&& textField_30.getText().length() != 0
				&& textField_31.getText().length() != 0
				&& textField_32.getText().length() != 0
				&& textField_33.getText().length() != 0
				&& textField_34.getText().length() != 0
				&& textField_35.getText().length() != 0
				&& textField_36.getText().length() != 0
				&& textField_37.getText().length() != 0
				&& textField_38.getText().length() != 0
				&& textField_39.getText().length() != 0
				&& textField_40.getText().length() != 0
				&& textField_41.getText().length() != 0
				&& textField_42.getText().length() != 0
				&& textField_43.getText().length() != 0
				&& textField_44.getText().length() != 0
				&& textField_45.getText().length() != 0
				&& textField_46.getText().length() != 0
				&& textField_47.getText().length() != 0)
			return true;
		return false;
	}

	private void ponerTenis() {
		textJug1.setEditable(true);
		textJug2.setEditable(true);
	}

	private void ponerFutbol() {
		textField_0.setEditable(true);
		textField_1.setEditable(true);
		textField_2.setEditable(true);
		textField_3.setEditable(true);
		textField_4.setEditable(true);
		textField_5.setEditable(true);
		textField_6.setEditable(true);
		textField_7.setEditable(true);
		textField_8.setEditable(true);
		textField_9.setEditable(true);
		textField_10.setEditable(true);
		textField_11.setEditable(true);
		textField_12.setEditable(true);
		textField_13.setEditable(true);
		textField_14.setEditable(true);
		textField_15.setEditable(true);
		textField_16.setEditable(true);
		textField_17.setEditable(true);
		textField_18.setEditable(true);
		textField_19.setEditable(true);
		textField_20.setEditable(true);
		textField_21.setEditable(true);
		textField_22.setEditable(true);
		textField_23.setEditable(true);
	}

	private void ponerF1() {
		textField_24.setEditable(true);
		textField_25.setEditable(true);
		textField_26.setEditable(true);
		textField_27.setEditable(true);
		textField_28.setEditable(true);
		textField_29.setEditable(true);
		textField_30.setEditable(true);
		textField_31.setEditable(true);
		textField_32.setEditable(true);
		textField_33.setEditable(true);
		textField_34.setEditable(true);
		textField_35.setEditable(true);
		textField_36.setEditable(true);
		textField_37.setEditable(true);
		textField_38.setEditable(true);
		textField_39.setEditable(true);
		textField_40.setEditable(true);
		textField_41.setEditable(true);
		textField_42.setEditable(true);
		textField_43.setEditable(true);
		textField_44.setEditable(true);
		textField_45.setEditable(true);
		textField_46.setEditable(true);
		textField_47.setEditable(true);
	}

	private void limpiarTenis() {
		textJug1.setText("");
		textJug2.setText("");
		textJug1.setEditable(false);
		textJug2.setEditable(false);
	}

	private void limpiarFutbol() {
		textField_0.setText("");
		textField_0.setEditable(false);
		textField_1.setText("");
		textField_1.setEditable(false);
		textField_2.setText("");
		textField_2.setEditable(false);
		textField_3.setText("");
		textField_3.setEditable(false);
		textField_4.setText("");
		textField_4.setEditable(false);
		textField_5.setText("");
		textField_5.setEditable(false);
		textField_6.setText("");
		textField_6.setEditable(false);
		textField_7.setText("");
		textField_7.setEditable(false);
		textField_8.setText("");
		textField_8.setEditable(false);
		textField_9.setText("");
		textField_9.setEditable(false);
		textField_10.setText("");
		textField_10.setEditable(false);
		textField_11.setText("");
		textField_11.setEditable(false);
		textField_12.setText("");
		textField_12.setEditable(false);
		textField_13.setText("");
		textField_13.setEditable(false);
		textField_14.setText("");
		textField_14.setEditable(false);
		textField_15.setText("");
		textField_15.setEditable(false);
		textField_16.setText("");
		textField_16.setEditable(false);
		textField_17.setText("");
		textField_17.setEditable(false);
		textField_18.setText("");
		textField_18.setEditable(false);
		textField_19.setText("");
		textField_19.setEditable(false);
		textField_20.setText("");
		textField_20.setEditable(false);
		textField_21.setText("");
		textField_21.setEditable(false);
		textField_22.setText("");
		textField_22.setEditable(false);
		textField_23.setText("");
		textField_23.setEditable(false);
	}

	private void limpiarF1() {
		textField_24.setText("");
		textField_24.setEditable(false);
		textField_25.setText("");
		textField_25.setEditable(false);
		textField_26.setText("");
		textField_26.setEditable(false);
		textField_27.setText("");
		textField_27.setEditable(false);
		textField_28.setText("");
		textField_28.setEditable(false);
		textField_29.setText("");
		textField_29.setEditable(false);
		textField_30.setText("");
		textField_30.setEditable(false);
		textField_31.setText("");
		textField_31.setEditable(false);
		textField_32.setText("");
		textField_32.setEditable(false);
		textField_33.setText("");
		textField_33.setEditable(false);
		textField_34.setText("");
		textField_34.setEditable(false);
		textField_35.setText("");
		textField_35.setEditable(false);
		textField_36.setText("");
		textField_36.setEditable(false);
		textField_37.setText("");
		textField_37.setEditable(false);
		textField_38.setText("");
		textField_38.setEditable(false);
		textField_39.setText("");
		textField_39.setEditable(false);
		textField_40.setText("");
		textField_40.setEditable(false);
		textField_41.setText("");
		textField_41.setEditable(false);
		textField_42.setText("");
		textField_42.setEditable(false);
		textField_43.setText("");
		textField_43.setEditable(false);
		textField_44.setText("");
		textField_44.setEditable(false);
		textField_45.setText("");
		textField_45.setEditable(false);
		textField_46.setText("");
		textField_46.setEditable(false);
		textField_47.setText("");
		textField_47.setEditable(false);
	}

	private void reset() {
		textJug1.setText("");
		textJug2.setText("");
		textField_0.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		textField_9.setText("");
		textField_10.setText("");
		textField_11.setText("");
		textField_12.setText("");
		textField_13.setText("");
		textField_14.setText("");
		textField_15.setText("");
		textField_16.setText("");
		textField_17.setText("");
		textField_18.setText("");
		textField_19.setText("");
		textField_20.setText("");
		textField_21.setText("");
		textField_22.setText("");
		textField_23.setText("");
		textField_24.setText("");
		textField_25.setText("");
		textField_26.setText("");
		textField_27.setText("");
		textField_28.setText("");
		textField_29.setText("");
		textField_30.setText("");
		textField_31.setText("");
		textField_32.setText("");
		textField_33.setText("");
		textField_34.setText("");
		textField_35.setText("");
		textField_36.setText("");
		textField_37.setText("");
		textField_38.setText("");
		textField_39.setText("");
		textField_40.setText("");
		textField_41.setText("");
		textField_42.setText("");
		textField_43.setText("");
		textField_44.setText("");
		textField_45.setText("");
		textField_46.setText("");
		textField_47.setText("");
		textFechaEvento.setText("");
		textHoraEvento.setText("");
		textNombreEvento.setText("");
	}

	public class MiModelo extends DefaultTableModel {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	private void repintarUsuarios() {
		List<Usuario> usuarios = CatalogoUsuarios.getUnicaInstancia()
				.getAllUsuarios();
		modelo = new MiModelo();

		modelo.addColumn("Codigo de usuario");
		modelo.addColumn("Nombre Usuario");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellidos");
		modelo.addColumn("Email");
		modelo.addColumn("Fecha Nacimiento");

		for (Usuario usuario : usuarios) {
			Object[] objetoUsuario = new Object[6];
			objetoUsuario[0] = usuario.getCodigo();
			objetoUsuario[1] = usuario.getNombreUsuario();
			objetoUsuario[2] = usuario.getNombre();
			objetoUsuario[3] = usuario.getApellidos();
			objetoUsuario[4] = usuario.getEmail();
			objetoUsuario[5] = fechaDia.format(usuario.getFechaNac());
			modelo.addRow(objetoUsuario);
		}

		tablaUsuarios.setModel(modelo);
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

		for (int i = 0; i < eventosPrueba.length; i++)
			datos.addElement(eventosPrueba[i]);

		listaEventos.setModel(datos);
	}

	private void ponerParticipantesTenis() {
		respuestaTenis.removeAllItems();
		if (listaEventos.getSelectedIndex() != -1) {

			List<Participante> listaParticipantes = eventoApuestas
					.getParticipantes();

			for (Participante participante : listaParticipantes)
				respuestaTenis.addItem(participante.getNombre());
		}
	}

	private void ponerParticipantesFutbol() {
		respuestaFutbol.removeAllItems();

		if (listaEventos.getSelectedIndex() != -1) {
			List<Participante> listaEquipos = eventoApuestas.getParticipantes();

			List<ParticipanteJugador> listaParticipantes;

			for (Participante participante : listaEquipos) {
				if (participante instanceof ParticipanteEquipo) {
					listaParticipantes = ((ParticipanteEquipo) participante)
							.getJugadores();

					for (ParticipanteJugador jugador : listaParticipantes)
						respuestaFutbol.addItem(jugador.getNombre());
				}

			}

		}
	}

	private void ponerParticipantesF1() {
		respuestaF1.removeAllItems();
		if (listaEventos.getSelectedIndex() != -1) {

			List<Participante> listaParticipantes = eventoApuestas
					.getParticipantes();

			for (Participante participante : listaParticipantes)
				respuestaF1.addItem(participante.getNombre());
		}
	}

	private void ponerSets() {
		respuestaTenis.removeAllItems();
		respuestaTenis.addItem("3");
		respuestaTenis.addItem("4");
		respuestaTenis.addItem("5");
		respuestaTenis.addItem("6");
		respuestaTenis.addItem("7");
		respuestaTenis.addItem("8");
		respuestaTenis.addItem("9");
	}

	private void ponerMinutos() {
		respuestaFutbol.removeAllItems();
		for (int i = 1; i <= 90; i++)
			respuestaFutbol.addItem(i);
	}

	private void bloquearApuestas() {
		preguntaFutbol.setEnabled(false);
		preguntaF1.setEnabled(false);
		preguntaTenis.setEnabled(false);
		respuestaFutbol.setEnabled(false);
		respuestaF1.setEnabled(false);
		respuestaTenis.setEnabled(false);
	}

	private void apostarFutbol() {
		preguntaFutbol.setEnabled(true);
		preguntaF1.setEnabled(false);
		preguntaTenis.setEnabled(false);
		respuestaFutbol.setEnabled(true);
		respuestaF1.setEnabled(false);
		respuestaTenis.setEnabled(false);
	}

	private void apostarTenis() {
		preguntaFutbol.setEnabled(false);
		preguntaF1.setEnabled(false);
		preguntaTenis.setEnabled(true);
		respuestaFutbol.setEnabled(false);
		respuestaF1.setEnabled(false);
		respuestaTenis.setEnabled(true);
	}

	private void apostarF1() {
		preguntaFutbol.setEnabled(false);
		preguntaF1.setEnabled(true);
		preguntaTenis.setEnabled(false);
		respuestaFutbol.setEnabled(false);
		respuestaF1.setEnabled(true);
		respuestaTenis.setEnabled(false);
	}

}
