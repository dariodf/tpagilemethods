package interfaces;

import entidades.Contribuyente;
import entidades.Titular;
import excepciones.GeneralException;
import funcionesExtra.Funciones;
import gestores.GestorTitular;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Font;
import java.awt.Toolkit;

public class CrearTitular extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
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
	private Contribuyente contribuyenteSeleccionado;
	private JTextField textField_11;
	private JTextField textField_12;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearTitular dialog = new CrearTitular();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearTitular() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\WS\\TP\\icono.png"));
		setTitle("Crear Titular");
		setBounds(100, 100, 610, 338);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		Border title = BorderFactory.createTitledBorder("Búsqueda");
		panel.setBorder(title);
		panel.setLayout(null);
		panel.setBounds(10, 26, 568, 64);
		contentPanel.add(panel);
		
		JLabel lblTipoDeDocumento = new JLabel("Tipo de Documento");
		lblTipoDeDocumento.setBounds(10, 22, 113, 23);
		panel.add(lblTipoDeDocumento);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addItem("DNI");
		comboBox.addItem("LE");
		comboBox.addItem("LC");
		comboBox.setBounds(128, 23, 83, 20);
		panel.add(comboBox);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(291, 23, 99, 20);
		panel.add(textField);
		
		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// Se instancia un titular con la consulta devuelta por el gestor.
					contribuyenteSeleccionado = GestorTitular.getIstance().buscarContribuyente((String) comboBox.getSelectedItem(), textField.getText());
					
					// Se cargan los datos del contribuyente en pantalla. 
					textField_1.setText(contribuyenteSeleccionado.getTipoDoc());
					textField_2.setText(contribuyenteSeleccionado.getNumeroDoc());
					textField_3.setText(contribuyenteSeleccionado.getApellido());
					textField_4.setText(contribuyenteSeleccionado.getNombre());
					// Cambia el formato de la fecha y lo carga al textfield
					textField_5.setText(Funciones.getIstance().cambiarFormatoFecha(contribuyenteSeleccionado.getFechaNac()).toString());
					textField_6.setText(contribuyenteSeleccionado.getDireccion());
					textField_7.setText(contribuyenteSeleccionado.getLocalidad());
					textField_8.setText(contribuyenteSeleccionado.getGrupoSanguineo());
					textField_9.setText(contribuyenteSeleccionado.getFactorRH());
					textField_11.setText(contribuyenteSeleccionado.getSexo());
					textField_12.setText(contribuyenteSeleccionado.getEstadoCivil());
					if(contribuyenteSeleccionado.isDonante()== true){
						textField_10.setText("Si");
					}
					else{
						textField_10.setText("No");
					} 
				} catch (SQLException e) {
					// Muestra un error de la base de datos
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				} catch (GeneralException e){
					// En el caso de no encontrarse un contribuyente con el tipo y documento seleccionado, se muestra un mensaje de error
					e.lanzarMensaje("Error");
				}
			
			}
		});
		button.setBounds(469, 22, 89, 23);
		panel.add(button);
		
		JLabel label_1 = new JLabel("N\u00FAmero");
		label_1.setBounds(234, 26, 62, 14);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		Border title1 = BorderFactory.createTitledBorder("Contribuyente Seleccionado");
		panel_1.setBorder(title1);
		panel_1.setLayout(null);
		panel_1.setBounds(10, 101, 568, 152);
		contentPanel.add(panel_1);
		
		JLabel lblTipoDoc = new JLabel("Tipo Doc");
		lblTipoDoc.setBounds(10, 23, 61, 14);
		panel_1.add(lblTipoDoc);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(68, 20, 100, 20);
		panel_1.add(textField_1);
		
		JLabel label_3 = new JLabel("N\u00FAmero");
		label_3.setBounds(178, 23, 56, 14);
		panel_1.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(234, 20, 110, 20);
		panel_1.add(textField_2);
		
		JLabel label_4 = new JLabel("Apellido");
		label_4.setBounds(10, 58, 46, 14);
		panel_1.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(61, 55, 117, 20);
		panel_1.add(textField_3);
		
		JLabel label_5 = new JLabel("Nombre");
		label_5.setBounds(188, 58, 46, 14);
		panel_1.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(244, 55, 126, 20);
		panel_1.add(textField_4);
		
		JLabel label_6 = new JLabel("Fecha de nacimiento");
		label_6.setBounds(10, 90, 126, 14);
		panel_1.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(135, 86, 99, 20);
		panel_1.add(textField_5);
		
		JLabel label_7 = new JLabel("Direcci\u00F3n");
		label_7.setBounds(10, 119, 61, 14);
		panel_1.add(label_7);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(68, 115, 126, 20);
		panel_1.add(textField_6);
		
		JLabel label_8 = new JLabel("Localidad");
		label_8.setBounds(214, 119, 61, 14);
		panel_1.add(label_8);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(275, 115, 143, 20);
		panel_1.add(textField_7);
		
		JLabel label_9 = new JLabel("Grupo sangu\u00EDneo");
		label_9.setBounds(283, 90, 100, 14);
		panel_1.add(label_9);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(393, 87, 37, 20);
		panel_1.add(textField_8);
		
		JLabel label_10 = new JLabel("Factor RH");
		label_10.setBounds(451, 90, 85, 14);
		panel_1.add(label_10);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(528, 87, 29, 20);
		panel_1.add(textField_9);
		
		JLabel label_11 = new JLabel("Es donante");
		label_11.setBounds(428, 119, 73, 14);
		panel_1.add(label_11);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setBounds(511, 116, 46, 20);
		panel_1.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(375, 23, 29, 14);
		panel_1.add(lblSexo);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(412, 20, 18, 20);
		panel_1.add(textField_11);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil");
		lblEstadoCivil.setBounds(395, 58, 79, 14);
		panel_1.add(lblEstadoCivil);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBounds(484, 56, 73, 20);
		panel_1.add(textField_12);
		
		JButton button_1 = new JButton("Aceptar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(null == contribuyenteSeleccionado)
						throw new GeneralException("Para crear un titular necesita seleccionar un contribuyente");
					Titular titular = new Titular(contribuyenteSeleccionado);
					GestorTitular.getIstance().CrearTitular(titular);
					
					//Limpia campos
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_6.setText("");
					textField_7.setText("");
					textField_8.setText("");
					textField_9.setText("");
					textField_11.setText("");
					textField_12.setText("");
					
					// Limpia el contribuyente seleccionado
					contribuyenteSeleccionado = null;
					
					// Cierra la ventana
					dispose();
					
					//Muestra mensaje de informacion
					JOptionPane.showMessageDialog(null, "Se ha creado con exito el titular "+titular.getNombre()+" "+titular.getApellido()+"." ,"Crear Titular",JOptionPane.INFORMATION_MESSAGE);
									
					
				} catch (SQLException e) {
					// Muestra un error de la base de datos
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				} catch (GeneralException e){
					// En el caso de no se haya seleccionado un contribuyente o o el titular ya exista, se muestra un error por pantalla
					e.lanzarMensaje("Error");
				}
			}
		});
		button_1.setBounds(390, 264, 89, 23);
		contentPanel.add(button_1);
		
		JButton button_2 = new JButton("Cancelar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Limpia campos
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_11.setText("");
				textField_12.setText("");
				
				// Limpia el contribuyente seleccionado
				contribuyenteSeleccionado = null;
				
				dispose();
				
			}
		});
		button_2.setBounds(489, 264, 89, 23);
		contentPanel.add(button_2);
	}
}
