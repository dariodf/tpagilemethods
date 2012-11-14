package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

import entidades.Titular;
import gestores.AdminBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmitirLicenciaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_3;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmitirLicenciaDialog dialog = new EmitirLicenciaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmitirLicenciaDialog() {
		setTitle("Emitir Licencia");
		setBounds(100, 100, 769, 695);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 29, 730, 260);
		contentPanel.add(panel);
		
		JLabel label = new JLabel("Tipo");
		label.setBounds(10, 24, 46, 14);
		panel.add(label);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DNI", "LC", "LE", "PPTE"}));
		comboBox.setSelectedIndex(0);
		Border title = BorderFactory.createTitledBorder("Búsqueda");
		panel.setBorder(title);
		comboBox.setBounds(45, 21, 83, 20);
		panel.add(comboBox);
		
		//ENMASCARA LA ENTRADA PARA EL CAMPO DE NUMERO_DNI
		MaskFormatter mascara;
		try {
			mascara = new MaskFormatter("########");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,e.getMessage() , "Error en la entrada de los datos",JOptionPane.ERROR_MESSAGE);
		}
		
		////////////////////////////////////////////////////////////
		
		textField_2 = new JTextField(20);
		textField_2.setColumns(10);
		textField_2.setBounds(485, 21, 99, 20);
		panel.add(textField_2);
		
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(textField_2.getText().length() > 20)
					e.consume();
				
				else
				{
					
					if((e.getKeyChar() <= 'z') && (e.getKeyChar() >= 'a'))
					{
						e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
					}
					
					if(((e.getKeyChar() < 'A') || (e.getKeyChar() > 'Z')) && (e.getKeyChar() != '\b') && (e.getKeyChar() != ' '))
					{
					   e.consume();  // ignorar el evento de teclado
					}
					
					
				}
				
			}
		});
		
		////////////////////////////////////////////////////////////
		
		
		//TABLA
		
		//titulos columnas
		final String[] titulosColumnas = {"Tipo Doc.","N° Documento","Apellido","Nombre","F. Nacimiento","Dirección"};
		//formatodedatos
		Object[][] datos = {};
		//a la tabla se le agrega el set de datos y los titulos de las columnas
		DefaultTableModel model = new DefaultTableModel(datos,titulosColumnas);		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setBounds(97, 279, 579, 126);
		contentPanel.add(table);
		table.setFillsViewportHeight(true);
		
		//meto la tabla dentro del panel de scroll
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(45, 73, 648, 163);		
		panel.add(scrollPane);
		
		
		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() 
			{
			public void actionPerformed(ActionEvent arg0)
			{
				
				try {
					String numero = textField_12.getText();
					String apellido = textField_1.getText();
					String nombre =  textField_2.getText();
					String tipoDoc =  (String) comboBox.getSelectedItem();
					
					Titular titularBuscado = new Titular(nombre,apellido, tipoDoc,numero, null, null, null, null, null, null, false, null);
					ResultSet rs = AdminBD.getInstance().buscarTitular(titularBuscado);
					rs.first();
					int i = 0;
					Object[][] datos = {}; 
					DefaultTableModel model = new DefaultTableModel(datos,titulosColumnas);
					table.setModel(model);
					while (true)
					{
						
						Object[] fila = {rs.getString("TipoDoc"), rs.getString("NumDoc"), rs.getString("Apellido"), rs.getString("Nombre"), rs.getString("FechaNacimiento"), rs.getString("Direccion")};
						model.insertRow(i, fila);
						i++;
						if (rs.isLast()) break;
						rs.next();
					}
					;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		button.setBounds(604, 20, 89, 23);
		panel.add(button);
		
		JLabel label_1 = new JLabel("N\u00FAmero");
		label_1.setBounds(138, 24, 46, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Apellido");
		label_2.setBounds(290, 24, 46, 14);
		panel.add(label_2);
		
		
		//ENMASCARA LA ENTRADA PARA EL CAMPO DE NUMERO_DNI
		textField_1 = new JTextField("",20);
		textField_1.setColumns(10);
		textField_1.setBounds(333, 21, 99, 20);
		panel.add(textField_1);
		
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(textField_1.getText().length() > 20)
					e.consume();
				else
				{
					
					if((e.getKeyChar() <= 'z') && (e.getKeyChar() >= 'a'))
					{
						e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
					}
					
					if(((e.getKeyChar() < 'A') || (e.getKeyChar() > 'Z')) && (e.getKeyChar() != '\b') && (e.getKeyChar() != ' '))
					{
					   e.consume();  // ignorar el evento de teclado
					}
					
					
				}
				
				
				
			}
		});
		
		
	
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setBounds(442, 24, 46, 14);
		panel.add(lblNombre_1);
		
		textField_12 = new JTextField("", 10);
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			
			
				if(((e.getKeyChar() < '0') || (e.getKeyChar() > '9')) && (e.getKeyChar() != '\b'))
				{
				   e.consume();  // ignorar el evento de teclado
				}


			
			}
		});
		textField_12.setBounds(181, 21, 99, 20);
		panel.add(textField_12);
		
		
		
		
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Contribuyente Seleccionado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 300, 730, 271);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 29, 37, 14);
		panel_1.add(lblTipo);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 57, 37, 14);
		panel_1.add(lblApellido);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(52, 54, 86, 20);
		panel_1.add(textField_4);
		
		JLabel lblFNacimiento = new JLabel("F. Nacimiento");
		lblFNacimiento.setBounds(10, 85, 68, 14);
		panel_1.add(lblFNacimiento);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(88, 79, 86, 20);
		panel_1.add(textField_5);
		
		JLabel lblGSanguineo = new JLabel("G. Sanguineo");
		lblGSanguineo.setBounds(10, 113, 68, 14);
		panel_1.add(lblGSanguineo);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(88, 107, 86, 20);
		panel_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(199, 26, 144, 20);
		panel_1.add(textField_7);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(157, 29, 37, 14);
		panel_1.add(lblNmero);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(199, 54, 144, 20);
		panel_1.add(textField_8);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(157, 57, 37, 14);
		panel_1.add(lblNombre);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(257, 79, 86, 20);
		panel_1.add(textField_9);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(197, 82, 50, 14);
		panel_1.add(lblDireccion);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(257, 107, 86, 20);
		panel_1.add(textField_10);
		
		JLabel lblFactor = new JLabel("Factor RH");
		lblFactor.setBounds(197, 110, 50, 14);
		panel_1.add(lblFactor);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(424, 79, 86, 20);
		panel_1.add(textField_11);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(368, 82, 46, 14);
		panel_1.add(lblLocalidad);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Donante");
		chckbxNewCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		chckbxNewCheckBox.setBounds(368, 106, 97, 23);
		panel_1.add(chckbxNewCheckBox);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(10, 138, 79, 14);
		panel_1.add(lblObservaciones);
		
				
		JTextArea textArea_1 = new JTextArea();
		Border bordecito = BorderFactory.createLineBorder(new Color(0,0,0));
		textArea_1.setBorder(bordecito);
		textArea_1.setBounds(87, 143, 604, 111);
		panel_1.add(textArea_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(52, 26, 50, 20);
		panel_1.add(textField_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Clase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 582, 114, 74);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(43, 29, 52, 22);
		panel_2.add(comboBox_1);
		
		JLabel lblClase = new JLabel("Clase");
		lblClase.setBounds(10, 33, 68, 14);
		panel_2.add(lblClase);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(444, 633, 91, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(553, 633, 91, 23);
		contentPanel.add(btnCancelar);
		
		//acomodo el ancho de los titulos
		TableColumn column = null;
		for (int i = 0; i < 5; i++) {
		    column = table.getColumnModel().getColumn(i);
		    
		    switch(i) {
		    case 1: 
		    	column.setPreferredWidth(10);
		        break;
		    case 2: 
		    	column.setPreferredWidth(10);
		        break;
		    case 3: 
		    	column.setPreferredWidth(10);
		        break;
		    case 4: 
		    	column.setPreferredWidth(10);
		        break;
		    case 5: 
		    	column.setPreferredWidth(10);
		        break;
		    default: 
		    	column.setPreferredWidth(10);
		        break;
		    } 
		    
		    
		}
		
		
		//LISTENERS DE LA TABLA
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//
			}
		});
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//
			}
		});
		
		
		
		
		
		
		
		
	}
}
