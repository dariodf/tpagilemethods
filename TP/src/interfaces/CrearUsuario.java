package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuariox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearUsuario dialog = new CrearUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearUsuario() {
		//super(new JFrame(),true);
		setResizable(false);
		setTitle("Crear Usuario");
		setBounds(100, 100, 290, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(22, 113, 83, 23);
		btnAceptar.setActionCommand("OK");
		contentPanel.add(btnAceptar);
	
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(169, 113, 90, 23);
		btnCancelar.setActionCommand("Cancel");
		contentPanel.add(btnCancelar);
		
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(22, 25, 69, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblPrivilegio = new JLabel("Privilegio:");
		lblPrivilegio.setBounds(22, 61, 69, 14);
		contentPanel.add(lblPrivilegio);
		
		txtUsuariox = new JTextField();
		txtUsuariox.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuariox.setEditable(false);
		txtUsuariox.setText("Usuario-X");
		txtUsuariox.setBounds(101, 22, 122, 20);
		contentPanel.add(txtUsuariox);
		txtUsuariox.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Simple", "Administrador"}));
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(101, 58, 122, 20);
		contentPanel.add(comboBox);
	}
}
