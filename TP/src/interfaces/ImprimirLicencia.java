package interfaces;

import interfaces.PantallaPrincipal.Contenedor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

import entidades.Licencia;
import entidades.Titular;
import funcionesExtra.Funciones;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class ImprimirLicencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	
	/**
	 * Create the dialog.
	 */
	public ImprimirLicencia(Licencia unaLicencia, Titular unTitular) {
		
		
		// Elimina la barra de titulo
		this.setUndecorated(true);
		
		ImageIcon icono = new ImageIcon("./ICONO.png");
		this.setIconImage(icono.getImage());
		
		setBounds(100, 100, 467, 325);
		getContentPane().setLayout(new BorderLayout());
				
		//Para cerrar el dialogo con ESC
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0, false);
		AbstractAction escapeAction = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
				 dispose();
				}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke,
		"ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
		////////////////////////////////
		
		
		contentPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Municipalidad de Santa Fe");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(34, 11, 163, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Licencia de Conducir");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(45, 30, 125, 14);
		contentPanel.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TextField.border"));
		panel.setBounds(12, 64, 445, 250);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 101, 101);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		// Insertamos la imagen en el panel
		JLabel labelImagen = new JLabel(" "); 
		ImageIcon imagen = new ImageIcon("./usuario.png"); 
		labelImagen.setIcon(imagen); 
		panel_1.add(labelImagen);

		//Propiedades de la etiqueta 
		labelImagen.setSize(101,101); 
		labelImagen.setLocation(0,0); 
		labelImagen.setVisible(true); 
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(121, 11, 314, 228);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Licencia N\u00BA:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(11, 11, 64, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Nombre: ");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 36, 57, 14);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("Apellido:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(10, 61, 57, 14);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("Direcci\u00F3n:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 86, 65, 14);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("Localidad:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(10, 111, 57, 14);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("Fecha de Vencimiento:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(11, 136, 133, 14);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("Observaciones:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(11, 161, 94, 14);
		panel_2.add(label_6);
		
		String idLicencia = String.valueOf(unaLicencia.getId());
		JLabel lblNumeroLicencia = new JLabel(idLicencia);
		lblNumeroLicencia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumeroLicencia.setBounds(85, 11, 64, 14);
		panel_2.add(lblNumeroLicencia);
		
		JLabel lblNombre = new JLabel(unTitular.getNombre());
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(85, 36, 125, 14);
		panel_2.add(lblNombre);
		
		JLabel lblApellido = new JLabel(unTitular.getApellido());
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblApellido.setBounds(85, 61, 125, 14);
		panel_2.add(lblApellido);
		
		JLabel lblDireccion = new JLabel(unTitular.getDireccion());
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDireccion.setBounds(85, 86, 125, 14);
		panel_2.add(lblDireccion);
		
		JLabel lblLocalidad = new JLabel(unTitular.getLocalidad());
		lblLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLocalidad.setBounds(85, 111, 125, 14);
		panel_2.add(lblLocalidad);
		
		JLabel labelFechaVen = new JLabel(Funciones.getInstance().dateToString(unaLicencia.getFechaVencimiento()));
		labelFechaVen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelFechaVen.setBounds(145, 136, 71, 14);
		panel_2.add(labelFechaVen);
		
		JLabel label_7 = new JLabel("Clase:");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(174, 11, 45, 14);
		panel_2.add(label_7);
		
		JLabel lblClase = new JLabel(unaLicencia.getClase());
		lblClase.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClase.setBounds(229, 11, 56, 14);
		panel_2.add(lblClase);
		
		JTextArea textAreaObs = new JTextArea(unaLicencia.getObservacion());
		textAreaObs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textAreaObs.setLineWrap(true);
		textAreaObs.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textAreaObs.setBackground(SystemColor.menu);
		textAreaObs.setEditable(false);
		textAreaObs.setBounds(115, 156, 193, 63);
		panel_2.add(textAreaObs);
		
		
	}
}
