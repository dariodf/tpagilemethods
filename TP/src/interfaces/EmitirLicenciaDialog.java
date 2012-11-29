package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

import java.awt.Dialog.ModalityType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

import entidades.Licencia;
import entidades.Titular;
import excepciones.GeneralException;
import gestores.AdminBD;
import gestores.GestorLicencia;
import gestores.GestorTitular;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class EmitirLicenciaDialog extends JDialog {

	private ResultSet busquedaTitular;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField apellidoContribuyenteSeleccionado;
	private JTextField fechaNacContribuyenteSeleccionado;
	private JTextField grupoSanguineoContribuyenteSeleccionado;
	private JTextField numeroDocContribuyenteSeleccionado;
	private JTextField nombreContribuyenteSeleccionado;
	private JTextField direccionContribuyenteSeleccionado;
	private JTextField factorRHContribuyenteSeleccionado;
	private JTextField localidadContribuyenteSeleccionado;
	private JTextField tipoDocContribuyenteSeleccionado;
	private JTextField textField_12;
	private JTextField donanteContribuyenteSeleccionado;

	
	public ResultSet getBusquedaTitular() {
		return busquedaTitular;
	}

	public void setBusquedaTitular(Titular titularBuscado) {
		try 
		{
			this.busquedaTitular = AdminBD.getInstance().buscarTitular(titularBuscado);
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null,e.getMessage() , "Error de Base de Datos",JOptionPane.ERROR_MESSAGE);
		} catch (GeneralException e) {
			e.lanzarMensaje();
		}
	}

	
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
		
		setResizable(false);
		setTitle("Emitir Licencia");
		
		ImageIcon icono = new ImageIcon("./ICONO.png");
		this.setIconImage(icono.getImage());
		
		setBounds(100, 100, 769, 695);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 29, 730, 260);
		contentPanel.add(panel);
		
		
		
		
		final KeyAdapter soloNumerosDNI = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			
				if(textField_12.getText().length() > 10) e.consume();
				
				if(((e.getKeyChar() < '0') || (e.getKeyChar() > '9')) && (e.getKeyChar() != '\b'))
				{
				   e.consume();  // ignorar el evento de teclado
				}
			
			}
		};
		
		final KeyAdapter soloNumerosYLetrasDNI = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
		
				if(textField_12.getText().length() > 10) e.consume();
				
				if((e.getKeyChar() <= 'z') && (e.getKeyChar() >= 'a'))
				{
					e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
				}
				if( (((e.getKeyChar() < '0') || (e.getKeyChar() > '9')) && ((e.getKeyChar() < 'A') || (e.getKeyChar() > 'Z')) ) && (e.getKeyChar() != '\b'))
				{
				   e.consume();  // ignorar el evento de teclado
				}
			
			}
		};
		
		textField_12 = new JTextField("", 10);
		textField_12.addKeyListener(soloNumerosDNI);
		textField_12.setBounds(181, 21, 99, 20);
		panel.add(textField_12);
		
		
		JLabel label = new JLabel("Tipo");
		label.setBounds(10, 24, 46, 14);
		panel.add(label);
		
		final JComboBox comboBox = new JComboBox();
				
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textField_12.removeKeyListener(soloNumerosDNI);								
				if(comboBox.getSelectedIndex()==3)
					textField_12.addKeyListener(soloNumerosYLetrasDNI);
													
				else {
					textField_12.removeKeyListener(soloNumerosYLetrasDNI);
					textField_12.addKeyListener(soloNumerosDNI);
				}
				textField_12.setText(""); 
			}
		});
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DNI", "LC", "LE", "PPTE"}));
		comboBox.setSelectedIndex(0);
		Border title = BorderFactory.createTitledBorder("Búsqueda");
		panel.setBorder(title);
		comboBox.setBounds(45, 21, 68, 20);
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
		textField_2.setBounds(522, 21, 99, 20);
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
		
		//formato de datos
		Object[][] datos = {};
		
		//a la tabla se le agrega el set de datos y los titulos de las columnas
		DefaultTableModel model = new DefaultTableModel(datos,titulosColumnas);		
		table = new JTable(model)
		{
			// Bloquea la edición de las celdas.
			public boolean isCellEditable (int row, int column)
			   {
			       return false;
			   }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setBounds(97, 279, 579, 126);
		contentPanel.add(table);
		table.setFillsViewportHeight(true);
		
		//meto la tabla dentro del panel de scroll
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(97, 279);
		scrollPane.setSize(579, 126);
		scrollPane.setBounds(45, 73, 648, 155);		
		panel.add(scrollPane);
		
		
		JButton button = new JButton("Buscar");

		button.setBounds(631, 20, 89, 23);
		panel.add(button);
		
		JLabel label_1 = new JLabel("N\u00FAmero");
		label_1.setBounds(123, 24, 55, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Apellido");
		label_2.setBounds(290, 24, 55, 14);
		panel.add(label_2);
		
		
		//ENMASCARA LA ENTRADA PARA EL CAMPO DE NUMERO_DNI
		textField_1 = new JTextField("",20);
		textField_1.setColumns(10);
		textField_1.setBounds(346, 21, 99, 20);
		panel.add(textField_1);
		
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(textField_1.getText().length() > 20)
					e.consume();
				else
				{
					
					
					if( (e.getKeyChar() == ' ') &&  (textField_1.getText().length()!=0) && ((textField_1.getText()).charAt(textField_1.getText().length()-1) ==' ' ))
					{
						e.consume();
					}
					
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
		lblNombre_1.setBounds(457, 24, 55, 14);
		panel.add(lblNombre_1);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Titular Seleccionado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 300, 730, 271);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 29, 37, 14);
		panel_1.add(lblTipo);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 57, 50, 14);
		panel_1.add(lblApellido);
		
		apellidoContribuyenteSeleccionado = new JTextField();
		apellidoContribuyenteSeleccionado.setEditable(false);
		apellidoContribuyenteSeleccionado.setColumns(10);
		apellidoContribuyenteSeleccionado.setBounds(62, 54, 144, 20);
		panel_1.add(apellidoContribuyenteSeleccionado);
		
		JLabel lblFNacimiento = new JLabel("F. Nacimiento");
		lblFNacimiento.setBounds(10, 85, 86, 14);
		panel_1.add(lblFNacimiento);
		
		fechaNacContribuyenteSeleccionado = new JTextField();
		fechaNacContribuyenteSeleccionado.setEditable(false);
		fechaNacContribuyenteSeleccionado.setColumns(10);
		fechaNacContribuyenteSeleccionado.setBounds(98, 82, 86, 20);
		panel_1.add(fechaNacContribuyenteSeleccionado);
		
		JLabel lblGSanguineo = new JLabel("G. Sanguineo");
		lblGSanguineo.setBounds(10, 113, 78, 14);
		panel_1.add(lblGSanguineo);
		
		grupoSanguineoContribuyenteSeleccionado = new JTextField();
		grupoSanguineoContribuyenteSeleccionado.setEditable(false);
		grupoSanguineoContribuyenteSeleccionado.setColumns(10);
		grupoSanguineoContribuyenteSeleccionado.setBounds(98, 110, 108, 20);
		panel_1.add(grupoSanguineoContribuyenteSeleccionado);
		
		numeroDocContribuyenteSeleccionado = new JTextField();
		numeroDocContribuyenteSeleccionado.setEditable(false);
		numeroDocContribuyenteSeleccionado.setColumns(10);
		numeroDocContribuyenteSeleccionado.setBounds(286, 26, 144, 20);
		panel_1.add(numeroDocContribuyenteSeleccionado);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(228, 29, 54, 14);
		panel_1.add(lblNmero);
		
		nombreContribuyenteSeleccionado = new JTextField();
		nombreContribuyenteSeleccionado.setEditable(false);
		nombreContribuyenteSeleccionado.setColumns(10);
		nombreContribuyenteSeleccionado.setBounds(286, 54, 191, 20);
		panel_1.add(nombreContribuyenteSeleccionado);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(228, 57, 54, 14);
		panel_1.add(lblNombre);
		
		direccionContribuyenteSeleccionado = new JTextField();
		direccionContribuyenteSeleccionado.setEditable(false);
		direccionContribuyenteSeleccionado.setColumns(10);
		direccionContribuyenteSeleccionado.setBounds(286, 82, 191, 20);
		panel_1.add(direccionContribuyenteSeleccionado);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(216, 85, 68, 14);
		panel_1.add(lblDireccion);
		
		factorRHContribuyenteSeleccionado = new JTextField();
		factorRHContribuyenteSeleccionado.setEditable(false);
		factorRHContribuyenteSeleccionado.setColumns(10);
		factorRHContribuyenteSeleccionado.setBounds(286, 110, 86, 20);
		panel_1.add(factorRHContribuyenteSeleccionado);
		
		JLabel lblFactor = new JLabel("Factor RH");
		lblFactor.setBounds(216, 113, 66, 14);
		panel_1.add(lblFactor);
		
		localidadContribuyenteSeleccionado = new JTextField();
		localidadContribuyenteSeleccionado.setEditable(false);
		localidadContribuyenteSeleccionado.setColumns(10);
		localidadContribuyenteSeleccionado.setBounds(562, 82, 86, 20);
		panel_1.add(localidadContribuyenteSeleccionado);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(488, 85, 64, 14);
		panel_1.add(lblLocalidad);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(10, 138, 103, 14);
		panel_1.add(lblObservaciones);
		
				
		final JTextArea observacionesContribuyenteSeleccionado = new JTextArea();
		observacionesContribuyenteSeleccionado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(observacionesContribuyenteSeleccionado.getText().length() > 100) arg0.consume();
			}
		});
		Border bordecito = BorderFactory.createLineBorder(new Color(0,0,0));
		observacionesContribuyenteSeleccionado.setBorder(bordecito);
		observacionesContribuyenteSeleccionado.setBounds(112, 143, 579, 111);
		panel_1.add(observacionesContribuyenteSeleccionado);
		
		tipoDocContribuyenteSeleccionado = new JTextField();
		tipoDocContribuyenteSeleccionado.setEditable(false);
		tipoDocContribuyenteSeleccionado.setColumns(10);
		tipoDocContribuyenteSeleccionado.setBounds(52, 26, 50, 20);
		panel_1.add(tipoDocContribuyenteSeleccionado);
		
		JLabel lblDonante = new JLabel("Donante");
		lblDonante.setBounds(409, 113, 50, 14);
		panel_1.add(lblDonante);
		
		donanteContribuyenteSeleccionado = new JTextField();
		donanteContribuyenteSeleccionado.setEditable(false);
		donanteContribuyenteSeleccionado.setBounds(461, 110, 37, 20);
		panel_1.add(donanteContribuyenteSeleccionado);
		donanteContribuyenteSeleccionado.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Clase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 582, 114, 74);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E"}));
		comboBox_1.setBounds(43, 29, 52, 22);
		panel_2.add(comboBox_1);
		
		JLabel lblClase = new JLabel("Clase");
		lblClase.setBounds(10, 33, 68, 14);
		panel_2.add(lblClase);
		
		final JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});


		aceptarButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			// Realiza la acción de hacer click en el botón de Aceptar.
			public void mouseClicked(MouseEvent e) 
			{

				try 
				{
					ResultSet rs = busquedaTitular;
					//Busca las Licencias anteriores y las instancia para las validaciones de la licencia a crear.
					Collection<Licencia> licenciasAnteriores = GestorLicencia.getInstance().getLicenciasAnteriores(rs.getString("Id"));
					
					// Crea una instancia de Titular del titular seleccionado.
					Titular titularNuevaLicencia = new Titular(rs.getString("Nombre"), rs.getString("Apellido"), 
							rs.getString("TipoDoc"),rs.getString("NumDoc"),rs.getString("Sexo"),rs.getString("EstadoCivil"),
							rs.getDate("FechaNacimiento"),rs.getString("Direccion"),rs.getString("GrupoSanguineo"),
							rs.getString("FactorRH"),rs.getBoolean("Donante"),rs.getString("Localidad"),licenciasAnteriores);
					
					// Verifica que el titular esté en condiciones de obtener la licencia requerida. En caso contrario, lanza excepciones.
					GestorLicencia.getInstance().validarLicencia(titularNuevaLicencia, (String)comboBox_1.getSelectedItem());						
					
					// Instancia la nueva Licencia que luego será ingresada en la base de datos. 
					Calendar cal = Calendar.getInstance();

					Licencia nuevaLicencia = new Licencia((String)comboBox_1.getSelectedItem(),observacionesContribuyenteSeleccionado.getText(),GestorLicencia.getInstance().calcularVigencia(titularNuevaLicencia),cal.getTime());
					
					// Ingresa la nueva licencia en la base de datos.
					AdminBD.getInstance().agregarLicencia(nuevaLicencia, rs.getString("Id"));
					
					if(JOptionPane.showConfirmDialog(null, "La Licencia se ha creado exitosamente \n ¿Desea Imprimir la Licencia?","Confirmación",JOptionPane.OK_CANCEL_OPTION) == 0)
					{
						ImprimirLicencia imprimirLicenciaDialog = new ImprimirLicencia(nuevaLicencia,titularNuevaLicencia);
						imprimirLicenciaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						imprimirLicenciaDialog.setModalityType(ModalityType.DOCUMENT_MODAL);
						imprimirLicenciaDialog.setLocationRelativeTo(null);
						imprimirLicenciaDialog.setModal(true);
						imprimirLicenciaDialog.setVisible(true);	
						dispose();
					}
					else dispose();
					
						
					
				}		
				 
				catch (SQLException e1) 
				{
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					
				} 
				catch (GeneralException e1)
				{
					e1.lanzarMensaje();
				}

			}
		});
		aceptarButton.setEnabled(false);
		aceptarButton.setBounds(443, 622, 91, 23);
		contentPanel.add(aceptarButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(552, 622, 91, 23);
		contentPanel.add(btnCancelar);
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBox, textField_12, textField_1, textField_2, button, table, comboBox_1, aceptarButton, btnCancelar, table}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBox, textField_12, textField_1, textField_2, button, comboBox_1, aceptarButton, btnCancelar}));
		
		//Acomoda el ancho de los titulos
		TableColumn column = null;
		table.getTableHeader().setResizingAllowed(false);
		
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
		
		//BOTON BUSCAR
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				try {
					
					// Asegura que cada vez que se presione el botón de buscar no se pueda crear una Licencia sin seleccionar un Titular.
					aceptarButton.setEnabled(false);
					String numero = textField_12.getText();
					String apellido = textField_1.getText();
					String nombre =  textField_2.getText();
					String tipoDoc =  (String) comboBox.getSelectedItem();
					
					// Instancia un titular con los valores ingresados en los TextFields.
					Titular titularBuscado = new Titular(nombre,apellido, tipoDoc,numero, null, null, null, null, null, null, false, null);
					
					// Guarda en el ResultSet de la interfaz todos los valores de todos los titulares que coinciden con los campos de búsqueda ingresados.
					setBusquedaTitular(titularBuscado);
					ResultSet rs = getBusquedaTitular();
					
					// Genera los objetos necesarios para cargar en la tabla los valores de los titulares encontrados.
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
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		//LISTENERS DE LA TABLA
		table.addMouseListener(new MouseAdapter() 
		{
			@Override
			// Se encarga de mostrar en los campos inferiores todos los valores del Titular seleccionado.
			public void mouseReleased(MouseEvent arg0) 
			{
				try 
				{
					int position = table.getSelectedRow();
					ResultSet rs = getBusquedaTitular();
					if (rs == null)	return;
					rs.first();
					for (int i=0; i<position;i++) rs.next();
					
					tipoDocContribuyenteSeleccionado.setText(rs.getString("TipoDoc"));
					numeroDocContribuyenteSeleccionado.setText(rs.getString("NumDoc"));
					apellidoContribuyenteSeleccionado.setText(rs.getString("Apellido"));
					nombreContribuyenteSeleccionado.setText(rs.getString("Nombre"));
					fechaNacContribuyenteSeleccionado.setText(rs.getString("FechaNacimiento"));
					direccionContribuyenteSeleccionado.setText(rs.getString("Direccion"));
					localidadContribuyenteSeleccionado.setText(rs.getString("Localidad"));
					grupoSanguineoContribuyenteSeleccionado.setText(rs.getString("GrupoSanguineo"));
					factorRHContribuyenteSeleccionado.setText(rs.getString("FactorRH"));
					donanteContribuyenteSeleccionado.setText(rs.getBoolean("Donante")?"SI":"NO");
					aceptarButton.setEnabled(true);
				} 
				catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
	}
}
