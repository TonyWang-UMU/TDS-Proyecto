package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import controler.*;
import model.Usuario;
import javax.swing.JPasswordField;

@SuppressWarnings({ "serial", "deprecation" })
public class VentanaPass extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passActual;
	private JPasswordField passNuevo;
	private JPasswordField passRepite;
	private Usuario usuLogin;

	public VentanaPass(Usuario usuario) {
		setTitle("Cambiar de contraseña");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 328, 152);
		usuLogin = usuario;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("64dlu:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));
		{
			JLabel lblContraseaActual = new JLabel("Contrase\u00F1a actual");
			lblContraseaActual.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblContraseaActual, "2, 2, right, default");
		}
		{
			passActual = new JPasswordField();
			contentPanel.add(passActual, "6, 2, fill, default");
		}
		{
			JLabel lblNuevaContrasea = new JLabel("Nueva contrase\u00F1a");
			lblNuevaContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNuevaContrasea, "2, 4, right, default");
		}
		{
			passNuevo = new JPasswordField();
			contentPanel.add(passNuevo, "6, 4, fill, default");
		}
		{
			JLabel lblRepiteNuevaContrasea = new JLabel(
					"Repite la nueva contrase\u00F1a");
			lblRepiteNuevaContrasea
					.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblRepiteNuevaContrasea, "2, 6, right, default");
		}
		{
			passRepite = new JPasswordField();
			contentPanel.add(passRepite, "6, 6, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// Comprobar campos y actualizar contraseña
						// Si la contraseña es la del usuario
						if (usuLogin.getContraseña().equals(
								passActual.getText())) {

							// Si coinciden las dos contraseñas
							if (passNuevo.getText()
									.equals(passRepite.getText())) {
								// Cambiamos la contraseña
								usuLogin.setContraseña(passNuevo.getText());
								usuLogin = ApuestasYa.getUnicaInstancia()
										.modificarContrasena(usuLogin);
								VentanaError.mostrarMensaje(15);
								VentanaPass.this.dispose();

							} else
								VentanaError.mostrarMensaje(4);

						} else
							VentanaError.mostrarMensaje(13);
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
						VentanaPass.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
