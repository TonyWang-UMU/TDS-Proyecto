package view;

import java.awt.BorderLayout;

import controler.*;
import java.awt.FlowLayout;

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

import model.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaCobro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textMail;
	private JTextField textNumCuenta;
	private Usuario usuLogin;

	/**
	 * Create the dialog.
	 */
	public VentanaCobro(Usuario usuario) {
		setTitle("Opciones de Cobro");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		usuLogin = usuario;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JTabbedPane panelPestCobro = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(panelPestCobro);
			{
				JPanel panelCobroPaypal = new JPanel();
				panelPestCobro.addTab("Paypal", null, panelCobroPaypal, null);
				panelCobroPaypal.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("left:max(200dlu;default):grow"), },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
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
					JLabel lblIntroduzcaSuEmail = new JLabel(
							"Introduzca su email:");
					panelCobroPaypal.add(lblIntroduzcaSuEmail, "8, 4");
				}
				{
					textMail = new JTextField();
					panelCobroPaypal.add(textMail, "8, 6");
					textMail.setColumns(20);
				}
				{
					JButton btnGuardar = new JButton("Guardar");
					btnGuardar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// Al igual que en el ingreso, tan solo comprobamos
							// que exista algo en el campo
							if (textMail.getText().length() != 0) {

								usuLogin.setCobroPorDefecto(new Paypal());
								usuLogin = ApuestasYa.getUnicaInstancia()
										.modificarTipoPago(usuLogin);

								VentanaCobro.this.dispose();
								VentanaError.mostrarMensaje(20);
							}

							else
								VentanaError.mostrarMensaje(19);
						}
					});
					panelCobroPaypal.add(btnGuardar, "8, 10");
				}
			}
			{
				JPanel panelCobroCredito = new JPanel();
				panelPestCobro.addTab("Transferencia", null, panelCobroCredito,
						null);
				panelCobroCredito.setLayout(new FormLayout(new ColumnSpec[] {
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
						FormFactory.DEFAULT_ROWSPEC, }));
				{
					JLabel lblIngreseSuNmero = new JLabel(
							"Ingrese su n\u00FAmero de cuenta");
					panelCobroCredito.add(lblIngreseSuNmero, "8, 4");
				}
				{
					textNumCuenta = new JTextField();
					panelCobroCredito.add(textNumCuenta, "8, 6, left, default");
					textNumCuenta.setColumns(30);
				}
				{
					JButton btnGuardar_1 = new JButton("Guardar");
					btnGuardar_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (textNumCuenta.getText().length() == 20
									&& MetodosVentana.sonNumeros(textNumCuenta
											.getText())) {

								usuLogin.setCobroPorDefecto(new Transferencia());
								usuLogin = ApuestasYa.getUnicaInstancia()
										.modificarTipoPago(usuLogin);

								VentanaCobro.this.dispose();
								VentanaError.mostrarMensaje(20);
							}

							else
								VentanaError.mostrarMensaje(19);
						}
					});
					panelCobroCredito.add(btnGuardar_1, "8, 10, left, default");
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
						VentanaCobro.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
