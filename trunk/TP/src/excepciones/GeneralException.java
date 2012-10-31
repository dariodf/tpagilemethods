package excepciones;

import javax.swing.JOptionPane;

public class GeneralException extends Exception {
	
	String mensaje = new String(" ");

	public GeneralException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public void lanzarMensaje()
	{
		JOptionPane.showMessageDialog(null, mensaje,"Error",JOptionPane.ERROR_MESSAGE);
	}
	
	
}
