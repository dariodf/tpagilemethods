package interfaces;

import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() 
	{
		
		ImageIcon icono = new ImageIcon("./ICONO.png");
		this.setIconImage(icono.getImage());
		
		setTitle("Municipalidad de Santa Fe - Registro del conductor");
		// Causa la apertura en modo maximizado de la ventana
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		// Setea la accion por defecto en la salida
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Setea el comienzo y el tama�o de la ventana en pixeles
		setBounds(37, 0, 1280, 720);
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		
		// Definicion del JMenu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuarios = new JMenu("Usuario");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmCrearUsuario = new JMenuItem("Crear Usuario");
		mntmCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CrearUsuario crearUsuarioDialog = new CrearUsuario();
					crearUsuarioDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					crearUsuarioDialog.setModalityType(ModalityType.DOCUMENT_MODAL);
					crearUsuarioDialog.setLocationRelativeTo(null);
					crearUsuarioDialog.setModal(true);
					crearUsuarioDialog.setVisible(true);
			
					
			}
		});
		
			
		
		mnUsuarios.add(mntmCrearUsuario);
		
		JMenuItem mntmModificarUsuario = new JMenuItem("Modificar Usuario");
		mnUsuarios.add(mntmModificarUsuario);
		
		JMenu mnLicencia = new JMenu("Licencia");
		menuBar.add(mnLicencia);
		
		JMenuItem mntmEmitirLicencia = new JMenuItem("Emitir Licencia");
		mntmEmitirLicencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmitirLicenciaDialog emitirLicenciaDialog = new EmitirLicenciaDialog();
				emitirLicenciaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				emitirLicenciaDialog.setModalityType(ModalityType.DOCUMENT_MODAL);
				emitirLicenciaDialog.setLocationRelativeTo(null);
				emitirLicenciaDialog.setModal(true);
				emitirLicenciaDialog.setVisible(true);
			}
		});
		mnLicencia.add(mntmEmitirLicencia);
		
		JMenuItem mntmRenovarLicencia = new JMenuItem("Renovar Licencia");
		mnLicencia.add(mntmRenovarLicencia);
		
		JMenuItem mntmEmitirCopiaDe = new JMenuItem("Emitir Copia de Licencia");
		mnLicencia.add(mntmEmitirCopiaDe);
		
		JMenu mnTitular = new JMenu("Titular");
		menuBar.add(mnTitular);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Crear Titular");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearTitular crearTitularDialog = new CrearTitular();
				crearTitularDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				crearTitularDialog.setModalityType(ModalityType.DOCUMENT_MODAL);
				crearTitularDialog.setLocationRelativeTo(null);
				crearTitularDialog.setModal(true);
				crearTitularDialog.setVisible(true);
			}
		});
		
		
		
		
		mnTitular.add(mntmNewMenuItem);
		
		JMenuItem mntmModificarTitular = new JMenuItem("Modificar Titular");
		mnTitular.add(mntmModificarTitular);
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Licencias Expiradas");
		mnListados.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Licencias Vigentes por Criterios");
		mnListados.add(mntmNewMenuItem_2);
		
		// Adjunta el contenedor definido para la imagen de fondo
		this.setContentPane(new Contenedor());
		
		
	}
	
	public class Contenedor extends Container
	{
		// La imagen que queremos de fondo, un fichero .gif
		public ImageIcon icono = new ImageIcon ("./fondo.gif");
	
		// Redefinici�n del m�todo paint()
		public void paint (Graphics g)
		{
			// Borra todo y lo pinta del color de fondo por defecto.
			Rectangle r = g.getClipBounds();
			g.setColor(this.getBackground());
			g.fillRect (r.x, r.y, r.width, r.height);
			
			// Pinta la imagen
			g.drawImage (icono.getImage(), 0,0,this);
			
			//  Pinta los botones dentro de este contenedor
			super.paint(g);
		}
	}

}
