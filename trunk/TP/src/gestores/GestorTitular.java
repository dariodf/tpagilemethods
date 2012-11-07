package gestores;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import entidades.Contribuyente;
import excepciones.GeneralException;

public class GestorTitular {

	protected GestorTitular(){}
	private final static GestorTitular instancia = new GestorTitular();
	// Metodo encargado de devolver el singleton
	public static GestorTitular getIstance(){
		return instancia;
	}
	
	public Contribuyente buscarContribuyente(String tipoDoc, String numDoc) throws SQLException, GeneralException{
		ResultSet rs = AdminBD.getIstance().buscarContribuyente(tipoDoc, numDoc);
		if(!rs.next()){
			throw new GeneralException("No se encontro un contribuyente con el tipo y numero de documento ingresado.");
		}
			
		
		Contribuyente contribuyente = new Contribuyente(rs.getString("nombre"), rs.getString("apellido"), rs.getString("tipodoc"), rs.getString("numdoc"), rs.getDate("fechanacimiento"), rs.getString("direccion"), rs.getString("gruposanguineo"),rs.getString("factorrh"), rs.getBoolean("donante"), rs.getString("localidad"));
		
		return contribuyente;
		
			
	}
	
	public java.util.Date getDate(String date){
		DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
	    try {
	    	return df.parse(date);
		} catch (ParseException ex) {
	    }
	    return null;
	
	}


}
