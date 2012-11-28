package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import excepciones.GeneralException;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		// Elimina la barra de titulo
		this.setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 280, 322, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				// Comprobaciones
				PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
				pantallaPrincipal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				pantallaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(40, 140, 85, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(40, 61, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(40, 94, 80, 14);
		contentPane.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(149, 55, 130, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 88, 130, 20);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(194, 140, 85, 30);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("Inicio de Sesi\u00F3n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(118, 26, 130, 14);
		contentPane.add(lblNewLabel_1);
	}
}
