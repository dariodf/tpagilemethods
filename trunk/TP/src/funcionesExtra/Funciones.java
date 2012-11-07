package funcionesExtra;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

	public class Funciones {
	
	private final static Funciones instancia = new Funciones();
	// Metodo encargado de devolver el singleton
	public static Funciones getIstance(){
		return instancia;
	}
	
	// Cambia el formato de una Fecha al tipo dia-mes-año
	public Date cambiarFormatoFecha(Date unaFecha)
	{
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    try {
	    	return df.parse(unaFecha.toString());
		} catch (ParseException ex) {
	    }
	    return null;
	
	}
}
