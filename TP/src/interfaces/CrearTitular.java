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
import javax.swing.ImageIcon;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class CrearTitular extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDocumentoBuscar;
	private JTextField tfTipoDocumento;
	private JTextField tfDocumento;
	private JTextField tfApellido;
	private JTextField tfNombre;
	private JTextField tfFechaNacimiento;
	private JTextField tfDireccion;
	private JTextField tfLocalidad;
	private JTextField tfGrupoSanguineo;
	private JTextField tFactorRH;
	private JTextField tfEsDonante;
	private Contribuyente contribuyenteSeleccionado;
	private JTextField tfSexo;
	private JTextField tfEstadoCivil;


	/**
	 * Create the dialog.
	 */
	public CrearTitular() 
	{
		setResizable(false);
		
		ImageIcon icono = new ImageIcon("./ICONO.png");
		this.setIconImage(icono.getImage());
		
		setTitle("Crear Titular");
		setBounds(100, 100, 610, 338);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelBusqueda = new JPanel();
		Border title = BorderFactory.createTitledBorder("Búsqueda");
		panelBusqueda.setBorder(title);
		panelBusqueda.setLayout(null);
		panelBusqueda.setBounds(10, 26, 568, 64);
		contentPanel.add(panelBusqueda);
		
		JLabel label = new JLabel("Tipo de Documento");
		label.setBounds(10, 22, 113, 23);
		panelBusqueda.add(label);
		
		
		final KeyAdapter soloNumerosDNI = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			
		
				if(( (e.getKeyChar() < '0') || (e.getKeyChar() > '9') || tfDocumentoBuscar.getText().length() == 10 ) && (e.getKeyChar() != '\b'))
				{
				   e.consume();  // ignorar el evento de teclado
				}
			
			}
		};
		
		final KeyAdapter soloNumerosYLetrasDNI = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
		
				if((e.getKeyChar() <= 'z') && (e.getKeyChar() >= 'a'))
				{
					e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
				}
				if (( (((e.getKeyChar() < '0') || (e.getKeyChar() > '9')) && ((e.getKeyChar() < 'A') || (e.getKeyChar() > 'Z')) ) && (e.getKeyChar() != '\b')) || tfDocumentoBuscar.getText().length() == 10)
				{
				   e.consume();  // ignorar el evento de teclado
				}
			
			}
		};
		
		
		
		final JComboBox cbTipoDocumento = new JComboBox();
		cbTipoDocumento.addItem("DNI");
		cbTipoDocumento.addItem("LE");
		cbTipoDocumento.addItem("LC");
		cbTipoDocumento.addItem("PPTE");
		cbTipoDocumento.setBounds(128, 23, 83, 20);
		panelBusqueda.add(cbTipoDocumento);
		
		cbTipoDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tfDocumentoBuscar.removeKeyListener(soloNumerosDNI);								
				if(cbTipoDocumento.getSelectedIndex()==3)
					tfDocumentoBuscar.addKeyListener(soloNumerosYLetrasDNI);
													
				else {
					tfDocumentoBuscar.removeKeyListener(soloNumerosYLetrasDNI);
					tfDocumentoBuscar.addKeyListener(soloNumerosDNI);
				}
				tfDocumentoBuscar.setText(""); 
			}
		});
		
		
		
		tfDocumentoBuscar = new JTextField();
		tfDocumentoBuscar.setColumns(10);
		tfDocumentoBuscar.addKeyListener(soloNumerosDNI);
		tfDocumentoBuscar.setBounds(291, 23, 99, 20);
		panelBusqueda.add(tfDocumentoBuscar);
		
		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					if(tfDocumentoBuscar.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Debe ingresar un documento." ,"Campo nulo",JOptionPane.WARNING_MESSAGE);
						limpiarVentana();
					}
					else if(tfDocumentoBuscar.getText().length() < 7 ){
						JOptionPane.showMessageDialog(null, "El documento debe contener al menos 7 caracteres." ,"Longitud errónea",JOptionPane.WARNING_MESSAGE);
						limpiarVentana();
					}
					else{
						// Se instancia un titular con la consulta devuelta por el gestor.
						contribuyenteSeleccionado = GestorTitular.getInstance().buscarContribuyente((String) cbTipoDocumento.getSelectedItem(), tfDocumentoBuscar.getText());
						
						// Se cargan los datos del contribuyente en pantalla. 
						tfTipoDocumento.setText(contribuyenteSeleccionado.getTipoDoc());
						tfDocumento.setText(contribuyenteSeleccionado.getNumeroDoc());
						tfApellido.setText(contribuyenteSeleccionado.getApellido());
						tfNombre.setText(contribuyenteSeleccionado.getNombre());
						// Cambia el formato de la fecha y lo carga al textfield
						tfFechaNacimiento.setText(Funciones.getInstance().dateToString(contribuyenteSeleccionado.getFechaNac()));
						tfDireccion.setText(contribuyenteSeleccionado.getDireccion());
						tfLocalidad.setText(contribuyenteSeleccionado.getLocalidad());
						tfGrupoSanguineo.setText(contribuyenteSeleccionado.getGrupoSanguineo());
						tFactorRH.setText(contribuyenteSeleccionado.getFactorRH());
						tfSexo.setText(contribuyenteSeleccionado.getSexo());
						tfEstadoCivil.setText(contribuyenteSeleccionado.getEstadoCivil());
						if(contribuyenteSeleccionado.isDonante()== true){
							tfEsDonante.setText("Si");
						}
						else{
							tfEsDonante.setText("No");
						}
					}
					
					
					 
				} catch (SQLException e) {
					// Muestra un error de la base de datos
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				} catch (GeneralException e){
					// En el caso de no encontrarse un contribuyente con el tipo y documento seleccionado, se muestra un mensaje de error
					e.lanzarMensaje();
					limpiarVentana();
				}
			
			}
		});
		button.setBounds(469, 22, 89, 23);
		panelBusqueda.add(button);
		
		JLabel label_1 = new JLabel("N\u00FAmero");
		label_1.setBounds(234, 26, 62, 14);
		panelBusqueda.add(label_1);
		
		JPanel panelContribuyenteSeleccionado = new JPanel();
		Border title1 = BorderFactory.createTitledBorder("Contribuyente Seleccionado");
		panelContribuyenteSeleccionado.setBorder(title1);
		panelContribuyenteSeleccionado.setLayout(null);
		panelContribuyenteSeleccionado.setBounds(10, 101, 568, 152);
		contentPanel.add(panelContribuyenteSeleccionado);
		
		JLabel label_2 = new JLabel("Tipo Doc");
		label_2.setBounds(10, 23, 61, 14);
		panelContribuyenteSeleccionado.add(label_2);
		
		tfTipoDocumento = new JTextField();
		tfTipoDocumento.setEditable(false);
		tfTipoDocumento.setColumns(10);
		tfTipoDocumento.setBounds(68, 20, 100, 20);
		panelContribuyenteSeleccionado.add(tfTipoDocumento);
		
		JLabel label_3 = new JLabel("N\u00FAmero");
		label_3.setBounds(178, 23, 56, 14);
		panelContribuyenteSeleccionado.add(label_3);
		
		tfDocumento = new JTextField();
		tfDocumento.setEditable(false);
		tfDocumento.setColumns(10);
		tfDocumento.setBounds(234, 20, 110, 20);
		panelContribuyenteSeleccionado.add(tfDocumento);
		
		JLabel label_4 = new JLabel("Apellido");
		label_4.setBounds(10, 58, 46, 14);
		panelContribuyenteSeleccionado.add(label_4);
		
		tfApellido = new JTextField();
		tfApellido.setEditable(false);
		tfApellido.setColumns(10);
		tfApellido.setBounds(61, 55, 117, 20);
		panelContribuyenteSeleccionado.add(tfApellido);
		
		JLabel label_5 = new JLabel("Nombre");
		label_5.setBounds(188, 58, 46, 14);
		panelContribuyenteSeleccionado.add(label_5);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setColumns(10);
		tfNombre.setBounds(244, 55, 126, 20);
		panelContribuyenteSeleccionado.add(tfNombre);
		
		JLabel label_6 = new JLabel("Fecha de nacimiento");
		label_6.setBounds(10, 90, 126, 14);
		panelContribuyenteSeleccionado.add(label_6);
		
		tfFechaNacimiento = new JTextField();
		tfFechaNacimiento.setEditable(false);
		tfFechaNacimiento.setColumns(10);
		tfFechaNacimiento.setBounds(135, 86, 99, 20);
		panelContribuyenteSeleccionado.add(tfFechaNacimiento);
		
		JLabel label_7 = new JLabel("Direcci\u00F3n");
		label_7.setBounds(10, 119, 61, 14);
		panelContribuyenteSeleccionado.add(label_7);
		
		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(68, 115, 126, 20);
		panelContribuyenteSeleccionado.add(tfDireccion);
		
		JLabel label_8 = new JLabel("Localidad");
		label_8.setBounds(214, 119, 61, 14);
		panelContribuyenteSeleccionado.add(label_8);
		
		tfLocalidad = new JTextField();
		tfLocalidad.setEditable(false);
		tfLocalidad.setColumns(10);
		tfLocalidad.setBounds(275, 115, 143, 20);
		panelContribuyenteSeleccionado.add(tfLocalidad);
		
		JLabel label_9 = new JLabel("Grupo sangu\u00EDneo");
		label_9.setBounds(283, 90, 100, 14);
		panelContribuyenteSeleccionado.add(label_9);
		
		tfGrupoSanguineo = new JTextField();
		tfGrupoSanguineo.setEditable(false);
		tfGrupoSanguineo.setColumns(10);
		tfGrupoSanguineo.setBounds(393, 87, 37, 20);
		panelContribuyenteSeleccionado.add(tfGrupoSanguineo);
		
		JLabel label_10 = new JLabel("Factor RH");
		label_10.setBounds(451, 90, 85, 14);
		panelContribuyenteSeleccionado.add(label_10);
		
		tFactorRH = new JTextField();
		tFactorRH.setEditable(false);
		tFactorRH.setColumns(10);
		tFactorRH.setBounds(528, 87, 29, 20);
		panelContribuyenteSeleccionado.add(tFactorRH);
		
		JLabel label_11 = new JLabel("Es donante");
		label_11.setBounds(428, 119, 73, 14);
		panelContribuyenteSeleccionado.add(label_11);
		
		tfEsDonante = new JTextField();
		tfEsDonante.setEditable(false);
		tfEsDonante.setBounds(511, 116, 46, 20);
		panelContribuyenteSeleccionado.add(tfEsDonante);
		tfEsDonante.setColumns(10);
		
		JLabel label_13 = new JLabel("Sexo");
		label_13.setBounds(375, 23, 29, 14);
		panelContribuyenteSeleccionado.add(label_13);
		
		tfSexo = new JTextField();
		tfSexo.setEditable(false);
		tfSexo.setColumns(10);
		tfSexo.setBounds(412, 20, 18, 20);
		panelContribuyenteSeleccionado.add(tfSexo);
		
		JLabel label_12 = new JLabel("Estado Civil");
		label_12.setBounds(395, 58, 79, 14);
		panelContribuyenteSeleccionado.add(label_12);
		
		tfEstadoCivil = new JTextField();
		tfEstadoCivil.setEditable(false);
		tfEstadoCivil.setColumns(10);
		tfEstadoCivil.setBounds(484, 56, 73, 20);
		panelContribuyenteSeleccionado.add(tfEstadoCivil);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(null == contribuyenteSeleccionado)
						throw new GeneralException("Para crear un titular necesita seleccionar un contribuyente");
					Titular titular = new Titular(contribuyenteSeleccionado);
					GestorTitular.getInstance().CrearTitular(titular);
					
					limpiarVentana();
					
					// Cierra la ventana
					dispose();
					
					//Muestra mensaje de informacion
					JOptionPane.showMessageDialog(null, "Se ha creado con exito el titular "+titular.getNombre()+" "+titular.getApellido()+"." ,"Crear Titular",JOptionPane.INFORMATION_MESSAGE);
									
					
				} catch (SQLException e) {
					// Muestra un error de la base de datos
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				} catch (GeneralException e){
					// En el caso de no se haya seleccionado un contribuyente o o el titular ya exista, se muestra un error por pantalla
					e.lanzarMensaje();
				}
			}
		});
		botonAceptar.setBounds(390, 264, 89, 23);
		contentPanel.add(botonAceptar);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarVentana();
				
				dispose();
				
			}
		});
		botonCancelar.setBounds(489, 264, 89, 23);
		contentPanel.add(botonCancelar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{cbTipoDocumento, tfDocumentoBuscar, button, botonAceptar, botonCancelar}));
	}
	
	public void limpiarVentana()
	{
		//Limpia campos
		tfTipoDocumento.setText("");
		tfDocumento.setText("");
		tfApellido.setText("");
		tfNombre.setText("");
		tfFechaNacimiento.setText("");
		tfDireccion.setText("");
		tfLocalidad.setText("");
		tfGrupoSanguineo.setText("");
		tFactorRH.setText("");
		tfEsDonante.setText("");
		tfSexo.setText("");
		tfEstadoCivil.setText("");
		
		// Limpia el contribuyente seleccionado
		contribuyenteSeleccionado = null;
	}
}
