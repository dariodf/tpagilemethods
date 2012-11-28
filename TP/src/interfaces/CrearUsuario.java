package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import gestores.GestorUsuario;
import excepciones.GeneralException;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;



public class CrearUsuario extends JDialog 
{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuariox;
	
	int idUsuarioDisponible = GestorUsuario.getInstance().getIdUsuarioDisponible();
	
	/**
	 * Create the dialog.
	 */
	public CrearUsuario() 
	{
		setResizable(false);
		setTitle("Crear Usuario");
		setBounds(100, 100, 290, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtUsuariox = new JTextField();
		txtUsuariox.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuariox.setEditable(false);
		txtUsuariox.setBounds(101, 22, 122, 20);
		contentPanel.add(txtUsuariox);
		txtUsuariox.setColumns(10);
		
		//Muestra el número de usuario disponible cuando se crea la ventana
		txtUsuariox.setText("Usuario-"+idUsuarioDisponible);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Simple", "Administrador"}));
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(101, 58, 122, 20);
		contentPanel.add(comboBox);
		
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					boolean superUsuario=false;
					if(comboBox.getSelectedIndex()==1)
						superUsuario=true;
						
					String password = GestorUsuario.getInstance().crearUsuario(superUsuario, "Usuario-"+idUsuarioDisponible);
					dispose();
					
					JOptionPane.showMessageDialog(null, "Se ha creado con Exito el usuario \n"+"Usuario-"+idUsuarioDisponible+" Contraseña: "+password, "Informacion",JOptionPane.INFORMATION_MESSAGE);
									
				} 
				catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null,e.getMessage() , "Error en la Base de Datos",JOptionPane.ERROR_MESSAGE);
				}
				catch (GeneralException e) 
				{
					e.lanzarMensaje();
				}
				
			}
		});
		
		btnAceptar.setBounds(22, 113, 83, 23);
		btnAceptar.setActionCommand("OK");
		contentPanel.add(btnAceptar);
	
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		
		btnCancelar.setBounds(169, 113, 90, 23);
		btnCancelar.setActionCommand("Cancel");
		contentPanel.add(btnCancelar);
		
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(22, 25, 69, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblPrivilegio = new JLabel("Privilegio:");
		lblPrivilegio.setBounds(22, 61, 69, 14);
		contentPanel.add(lblPrivilegio);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBox, btnAceptar, btnCancelar}));
		
			
	}
}
