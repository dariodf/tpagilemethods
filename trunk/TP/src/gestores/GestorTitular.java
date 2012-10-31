package gestores;

import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Contribuyente;

public class GestorTitular {

	protected GestorTitular(){}
	private final static GestorTitular instancia = new GestorTitular();
	// Metodo encargado de devolver el singleton
	public static GestorTitular getIstance(){
		return instancia;
	}
	
	public Contribuyente buscarContribuyente(String tipoDoc, String numDoc) throws SQLException{
		ResultSet rs = AdminBD.getIstance().buscarContribuyente(tipoDoc, numDoc);
		if(!rs.next()){}
			//lanzar ventana
		
		Contribuyente contribuyente = new Contribuyente(rs.getString("nombre"), rs.getString("apellido"), rs.getString("tipodoc"), rs.getString("numdoc"), rs.getDate("fechanacimiento"), rs.getString("direccion"), rs.getString("factorrh"), rs.getBoolean("donante"), rs.getString("observaciones"), rs.getString("localidad"));
		
		return contribuyente;
		
			
	}
}
