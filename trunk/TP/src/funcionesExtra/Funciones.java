package funcionesExtra;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

	public class Funciones {
	
	private final static Funciones instancia = new Funciones();
	// Metodo encargado de devolver el singleton
	public static Funciones getInstance(){
		return instancia;
	}
	
	// Devuelve un string de la fecha con el formato dd-MM-yyy
	public String dateToString(Date unaFecha)
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        return formato.format(unaFecha);
        
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
