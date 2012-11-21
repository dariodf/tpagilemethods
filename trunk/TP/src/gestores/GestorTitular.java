package gestores;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entidades.Contribuyente;
import entidades.Titular;
import excepciones.GeneralException;

public class GestorTitular {

	protected GestorTitular(){}
	private final static GestorTitular instancia = new GestorTitular();
	private ResultSet busquedaTitular;
	
	// Metodo encargado de devolver el singleton
	public static GestorTitular getInstance(){
		return instancia;
	}
	
	// Busca un contribuyente en la base de datos y lo retorna
	public Contribuyente buscarContribuyente(String tipoDoc, String numDoc) throws SQLException, GeneralException
	{
		// Realiza la busqueda del contribuyente en la base de datos
		ResultSet rs = AdminBD.getInstance().buscarContribuyente(tipoDoc, numDoc);
		// Si no encuentra el contribuyente, lanza una excepcion
		if(!rs.next()){
			throw new GeneralException("No se encontro un contribuyente con el tipo y documento ingresado.");
		}
		// Instancia el contribuyente y lo retorna
		Contribuyente contribuyente = new Contribuyente(rs.getString("nombre"), rs.getString("apellido"), rs.getString("tipodoc"), rs.getString("numdoc"), rs.getString("sexo"), rs.getString("estadocivil"), rs.getDate("fechanacimiento"), rs.getString("direccion"), rs.getString("gruposanguineo"),rs.getString("factorrh"), rs.getBoolean("donante"), rs.getString("localidad"));
		return contribuyente;
	}
	

	public void CrearTitular(Titular unTitular) throws SQLException, GeneralException 
	{
		AdminBD.getInstance().crearTitular(unTitular);
	}

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
			// TODO Auto-generated catch block
		}
	}
	
	


}
