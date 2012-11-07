package excepciones;

import javax.swing.JOptionPane;

public class GeneralException extends Exception {
	
	String mensaje = new String(" ");

	public GeneralException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public void lanzarMensaje(String tipo)
	{
		if(0 == tipo.compareTo("Error"))
			JOptionPane.showMessageDialog(null, mensaje,tipo,JOptionPane.ERROR_MESSAGE);
		else if(0 == tipo.compareTo("Information"))
			JOptionPane.showMessageDialog(null, mensaje,tipo,JOptionPane.ERROR_MESSAGE);
		else if(0 == tipo.compareTo("Warning"))
			JOptionPane.showMessageDialog(null, mensaje,tipo,JOptionPane.ERROR_MESSAGE);
	}
	
	
}
