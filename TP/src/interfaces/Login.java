package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import excepciones.GeneralException;
import gestores.GestorUsuario;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField tfContrasenya;

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
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				// Comprobaciones
				
					try {
						// Si el campo de usuario es nulo, muestra un mensaje de error
						if(tfUsuario.getText().isEmpty())
							throw new GeneralException("El campo de usuario no puede estar en blanco.");
						// Si el campo de contraseña es nulo, muestra un mensaje de error
						if(tfContrasenya.getText().isEmpty())
							throw new GeneralException("El campo de contraseña no puede estar en blanco.");
						
						GestorUsuario.getInstance().verificarUsuario(tfUsuario.getText(), tfContrasenya.getText());
						
						PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
						pantallaPrincipal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						pantallaPrincipal.setVisible(true);
						dispose();
					} catch (GeneralException e1) {
						e1.lanzarMensaje();
					} catch (SQLException e1) {
						// Muestra un error de la base de datos
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				
			}
		});
		btnIniciar.setBounds(40, 140, 85, 30);
		contentPane.add(btnIniciar);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(40, 61, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(40, 94, 80, 14);
		contentPane.add(lblContrasea);
		
		tfUsuario = new JTextField();
		tfUsuario.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (tfUsuario.getText().length() == 20 || !(Character.isAlphabetic(arg0.getKeyChar()) || Character.isDigit(arg0.getKeyChar()) || arg0.getKeyChar() == '-' )) 
					 arg0.consume();
				
			}
		});
		tfUsuario.setBounds(149, 55, 130, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfContrasenya = new JPasswordField();
		tfContrasenya.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tfUsuario.getText().length() == 8 || !Character.isDigit(e.getKeyChar())){
					 e.consume();
				}
				
				if(e.getKeyChar() == '\n')
				{
				
					try {
						// Si el campo de usuario es nulo, muestra un mensaje de error
						if(tfUsuario.getText().isEmpty())
							throw new GeneralException("El campo de usuario no puede estar en blanco.");
						// Si el campo de contraseña es nulo, muestra un mensaje de error
						if(tfContrasenya.getText().isEmpty())
							throw new GeneralException("El campo de contraseña no puede estar en blanco.");
						
						GestorUsuario.getInstance().verificarUsuario(tfUsuario.getText(), tfContrasenya.getText());
						
						PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
						pantallaPrincipal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						pantallaPrincipal.setVisible(true);
						dispose();
					} catch (GeneralException e1) {
						e1.lanzarMensaje();
					} catch (SQLException e1) {
						// Muestra un error de la base de datos
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		tfContrasenya.setBounds(149, 88, 130, 20);
		tfContrasenya.setColumns(10);
		contentPane.add(tfContrasenya);
		
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
