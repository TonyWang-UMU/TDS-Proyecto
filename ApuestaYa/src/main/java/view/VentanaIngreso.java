package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import controler.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import model.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaIngreso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNumTarjeta;
	private JTextField textCV;
	private JTextField textFechaCad;
	private JTextField textTitular;
	private JTextField textMail;
	private JPasswordField passwordField;
	private JTextField textTelefono;
	private Usuario usuLogin;

	/**
	 * Create the dialog.
	 */
	public VentanaIngreso(Usuario usuario) {
		setTitle("Opciones de Ingreso");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		usuLogin = usuario;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane);
			{
				JPanel panelIngPaypal = new JPanel();
				tabbedPane.addTab("Paypal", null, panelIngPaypal, null);
				panelIngPaypal.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						FormFactory.DEFAULT_ROWSPEC, }));
				{
					JLabel lblEmail = new JLabel("Email");
					panelIngPaypal.add(lblEmail, "6, 6, right, default");
				}
				{
					textMail = new JTextField();
					panelIngPaypal.add(textMail, "10, 6, left, default");
					textMail.setColumns(20);
				}
				{
					JLabel lblContrasea = new JLabel("Contrase\u00F1a");
					panelIngPaypal.add(lblContrasea, "6, 8, right, default");
				}
				{
					passwordField = new JPasswordField();
					passwordField.setColumns(20);
					panelIngPaypal.add(passwordField, "10, 8, left, default");
				}
				{
					JButton btnGuardar_1 = new JButton("Guardar");
					btnGuardar_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// comprobar que los campos de correo y contraseña
							// no esten vacios
							// No se comprueba nada mas puesto que el programa
							// no esta diseñado 100% robusto
							// y esto es externo al sistema de apuestas
							// Tendria que comprobar el servidor de paypal
							// y el correo se puede evaluar con una expresion
							// regular
							if (textMail.getText().length() != 0
									&& passwordField.getPassword().length != 0) {
								usuLogin.setIngresoPorDefecto(new Paypal());
								usuLogin = ApuestasYa.getUnicaInstancia()
										.modificarTipoIngreso(usuLogin);

								VentanaIngreso.this.dispose();
								VentanaError.mostrarMensaje(18);

							} else
								VentanaError.mostrarMensaje(17);
						}
					});
					panelIngPaypal.add(btnGuardar_1, "10, 12, left, default");
				}
			}
			{
				JPanel panelIngCredito = new JPanel();
				tabbedPane.addTab("Credito", null, panelIngCredito, null);
				panelIngCredito.setLayout(new FormLayout(new ColumnSpec[] {
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
						FormFactory.DEFAULT_ROWSPEC, }));
				{
					JLabel lblTitular = new JLabel("Titular");
					panelIngCredito.add(lblTitular, "4, 4, right, default");
				}
				{
					textTitular = new JTextField();
					panelIngCredito.add(textTitular, "8, 4, left, default");
					textTitular.setColumns(30);
				}
				{
					JLabel lblNumCuenta = new JLabel("N\u00FAmero de tarjeta");
					panelIngCredito.add(lblNumCuenta, "4, 6, right, default");
				}
				{
					textNumTarjeta = new JTextField();
					panelIngCredito.add(textNumTarjeta, "8, 6, left, default");
					textNumTarjeta.setColumns(30);
				}
				{
					JLabel lblCv = new JLabel("CV");
					panelIngCredito.add(lblCv, "4, 8, right, default");
				}
				{
					textCV = new JTextField();
					panelIngCredito.add(textCV, "8, 8, left, default");
					textCV.setColumns(10);
				}
				{
					JLabel lblFechaDeCaducidad = new JLabel(
							"Fecha de caducidad");
					panelIngCredito.add(lblFechaDeCaducidad,
							"4, 10, right, default");
				}
				{
					textFechaCad = new JTextField();
					panelIngCredito.add(textFechaCad, "8, 10, left, default");
					textFechaCad.setColumns(10);
				}
				{
					JButton btnGuardar = new JButton("Guardar");
					btnGuardar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// Guardar una tarjeta de credito
							// Comprobar que los campos sean correctos
							if (textCV.getText().length() == 3
									&& MetodosVentana.sonNumeros(textCV
											.getText())
									&& textNumTarjeta.getText().length() == 16
									&& MetodosVentana.sonNumeros(textNumTarjeta
											.getText())
									&& MetodosVentana
											.fechaTarjetaCorrecta(textFechaCad
													.getText())
									&& textTitular.getText().length() >= 3) {
								usuLogin.setIngresoPorDefecto(new Tarjeta());
								usuLogin = ApuestasYa.getUnicaInstancia()
										.modificarTipoIngreso(usuLogin);

								VentanaIngreso.this.dispose();
								VentanaError.mostrarMensaje(18);
							} else
								VentanaError.mostrarMensaje(17);
						}
					});
					panelIngCredito.add(btnGuardar, "8, 14, left, default");
				}
			}
			{
				JPanel panelIngSMS = new JPanel();
				tabbedPane.addTab("SMS", null, panelIngSMS, null);
				panelIngSMS.setLayout(new FormLayout(new ColumnSpec[] {
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
						FormFactory.DEFAULT_ROWSPEC, }));
				{
					JLabel lblIngreseSuNmero = new JLabel(
							"Ingrese su n\u00FAmero de tel\u00E9fono");
					panelIngSMS.add(lblIngreseSuNmero, "8, 6, left, default");
				}
				{
					textTelefono = new JTextField();
					panelIngSMS.add(textTelefono, "8, 8, left, default");
					textTelefono.setColumns(20);
				}
				{
					JButton btnGuardar_2 = new JButton("Guardar");
					btnGuardar_2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// Guardar SMS
							if (textTelefono.getText().length() == 9) {
								if (MetodosVentana.sonNumeros(textTelefono
										.getText())) {
									// Como son numeros entonces cambiamos el
									// tipo de pago
									usuLogin.setIngresoPorDefecto(new SMS());
									usuLogin = ApuestasYa.getUnicaInstancia()
											.modificarTipoIngreso(usuLogin);
									VentanaError.mostrarMensaje(18);
									VentanaIngreso.this.dispose();

								}
							} else
								VentanaError.mostrarMensaje(17);
						}
					});
					panelIngSMS.add(btnGuardar_2, "8, 12, left, default");
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						VentanaIngreso.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
