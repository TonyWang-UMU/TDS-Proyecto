package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import model.ApuestaMultiple;
import model.ApuestaRealizada;
import model.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class VentanaVerMultiples extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablaMultiples;
	private Usuario usuLogin;
	private MiModelo modelo;

	/**
	 * Create the dialog.
	 */
	public VentanaVerMultiples(Usuario usuario) {
		setTitle("Apuestas m�ltiples de " + usuario.getNombre());
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 782, 410);
		usuLogin = usuario;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			tablaMultiples = new JTable();
			JScrollPane panelApuestasScroll = new JScrollPane(tablaMultiples);
			contentPanel.add(panelApuestasScroll);
			repintarApuestas();
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						VentanaVerMultiples.this.dispose();
					}
				});

				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel(
						"Relaci\u00F3n de Apuestas M\u00FAltiples");
				panel.add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			}
		}
	}

	public class MiModelo extends DefaultTableModel {

		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

	/**
	 * Metodo para pintar las apuestas multiples que tenemos disponibles Nos
	 * muestra la informacion de cada apuesta dependiendo del tipo de apuesta
	 */

	private void repintarApuestas() {
		List<ApuestaRealizada> apuestasRealizadas = usuLogin
				.getApuestasRealizadas();
		modelo = new MiModelo();

		modelo.addColumn("Apuestas (C�digos)");
		modelo.addColumn("Tipo");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Pronostico");
		modelo.addColumn("Finalizada");
		modelo.addColumn("Ganadora");

		// poner mas informacion

		for (ApuestaRealizada aR : apuestasRealizadas) {
			Object[] objetoApuesta = new Object[6];
			// La apuesta ofrecida debe de tener informacion del evento
			// para poder coger el titulo, se hace igual, con un codigo nos
			// sobraria

			if (aR.getTipoApuesta() == 1) {
				List<ApuestaRealizada> listaApuestas = ((ApuestaMultiple) aR)
						.getApuestasRealizadas();
				String codigosApuesta = "";
				for (ApuestaRealizada multipleRealizada : listaApuestas) {
					codigosApuesta += multipleRealizada.getCodigo() + ", ";
				}
				objetoApuesta[0] = codigosApuesta.substring(0,
						codigosApuesta.length() - 2);
				objetoApuesta[1] = "Combinada";
				objetoApuesta[2] = aR.getCantidad();
				objetoApuesta[3] = aR.getPronostico().getPronostico();
				if (aR.isFinalizada()) {
					objetoApuesta[4] = "Si";
				} else {
					objetoApuesta[4] = "No";
				}
				if (aR.isGanadora()) {
					objetoApuesta[5] = "Si";
				} else {
					objetoApuesta[5] = "No";
				}

				modelo.addRow(objetoApuesta);

			} else if (aR.getTipoApuesta() == 2) {
				List<ApuestaRealizada> listaApuestas = ((ApuestaMultiple) aR)
						.getApuestasRealizadas();
				String codigosApuesta = "";
				for (ApuestaRealizada multipleRealizada : listaApuestas) {
					codigosApuesta += multipleRealizada.getCodigo() + ", ";
				}
				objetoApuesta[0] = codigosApuesta.substring(0,
						codigosApuesta.length() - 2);
				objetoApuesta[1] = "Sistema";
				objetoApuesta[2] = aR.getCantidad();
				objetoApuesta[3] = aR.getPronostico().getPronostico();
				if (aR.isFinalizada()) {
					objetoApuesta[4] = "Si";
				} else {
					objetoApuesta[4] = "No";
				}
				if (aR.isGanadora()) {
					objetoApuesta[5] = "Si";
				} else {
					objetoApuesta[5] = "No";
				}
				modelo.addRow(objetoApuesta);
			}
			tablaMultiples.setModel(modelo);
			// modificar el tama�o de las columnas
			tablaMultiples.getColumnModel().getColumn(0).setPreferredWidth(300);
			tablaMultiples.getColumnModel().getColumn(1).setPreferredWidth(75);
			tablaMultiples.getColumnModel().getColumn(2).setPreferredWidth(75);
			tablaMultiples.getColumnModel().getColumn(3).setPreferredWidth(75);
		}
	}
}
