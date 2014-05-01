package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import dao.AdaptadorTDSUsuarioDAO;
import dao.FactoriaDAO;
import javax.swing.JTextField;
import javax.swing.JButton;

import controler.*;
import model.CatalogoUsuarios;
import model.FormaPagoCobro;
import model.Paypal;
import model.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;

@SuppressWarnings({ "serial", "deprecation", "unused" })
public class VentanaLogin extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textUsu;
	private JPasswordField textPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					VentanaLogin vl = new VentanaLogin();
					vl.frame.setVisible(true);
					vl.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() throws IOException {
		initialize();
	}

	public void initialize() throws IOException {
		frame = new JFrame("Apuesta YA!");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setBounds(100, 100, 689, 405);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelImagen = new JPanel();
		panelCentral.add(panelImagen);

		JLabel etiqImg = new JLabel("");
		panelImagen.add(etiqImg);
		etiqImg.setIcon(new ImageIcon("inicio.jpg"));

		JPanel panelDatos = new JPanel();
		panelCentral.add(panelDatos);
		panelDatos.setLayout(new BorderLayout(0, 0));

		JPanel panelIntroducir = new JPanel();
		panelDatos.add(panelIntroducir, BorderLayout.NORTH);
		panelIntroducir
				.setLayout(new FormLayout(new ColumnSpec[] {
						ColumnSpec.decode("97px"),
						ColumnSpec.decode("83px:grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("46px"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("120px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel etiqUsu = new JLabel("Usuario   ");
		panelIntroducir.add(etiqUsu, "1, 4, right, center");

		textUsu = new JTextField();
		panelIntroducir.add(textUsu, "2, 4, fill, top");

		JLabel etiqPass = new JLabel("Contrase\u00F1a   ");
		panelIntroducir.add(etiqPass, "1, 6, right, center");

		textPass = new JPasswordField();
		textPass.setEchoChar('*');
		panelIntroducir.add(textPass, "2, 6, fill, default");

		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		JButton btnAceptar = new JButton("Aceptar");

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int codigo = CatalogoUsuarios.getUnicaInstancia().getKey(
						textUsu.getText());

				// Comprobar que el usuario exista en la base de datos
				if (codigo != 0) {
					Usuario usuario = CatalogoUsuarios.getUnicaInstancia()
							.getUsuario(codigo);
					// Comprobar que la contraseÃ±a introducida para ese usuario
					if (usuario.getContraseña().equals(textPass.getText())) {
						// En caso de que todo sea correcto cambiar de ventana a
						// la ventana principal de ese usuario

						VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(
								usuario);
						ventanaPrincipal.setLocationRelativeTo(frame);
						ventanaPrincipal.setVisible(true);

						textPass.setText("");
						textUsu.setText("");

					} else {
						VentanaError.mostrarMensaje(8);
					}

				} else
					VentanaError.mostrarMensaje(7);

			}
		});

		panelBotones.add(btnAceptar);

		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro ventanaRegistro = new VentanaRegistro();
				ventanaRegistro.setLocationRelativeTo(frame);
				ventanaRegistro.setVisible(true);
				textPass.setText("");
				textUsu.setText("");
			}
		});
		panelBotones.add(btnRegistrar);
	}
}
