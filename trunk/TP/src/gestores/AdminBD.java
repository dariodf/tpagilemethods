package gestores;

import java.sql.*;

import javax.swing.JOptionPane;

public class AdminBD {
	private static Connection conexion;
	private static Statement st;
	
	protected AdminBD(){}
	private final static AdminBD instancia = new AdminBD();
	// Metodo encargado de devolver el singleton
	public static AdminBD getIstance(){
		return instancia;
	}
	
	private static Connection establecerConexion() {		
		try{
        	if(conexion==null){
        		Class.forName("com.mysql.jdbc.Driver");
        		conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agiles", "root", "root");
        	}
        	return conexion;
        } 
        catch (SQLException e) {
        	System.out.println("Error de MySQL: " + e.getMessage());
        } 
        catch (Exception e){
        	System.out.println("Error inesperado: " + e.getMessage());
        }
		
		return null;
	}
	
	private static ResultSet devolverConsulta(String consulta) throws SQLException {
		//Ejecuta y devuelve consultas del tipo select
		ResultSet rs = null;
		
		if(conexion==null)
			conexion = establecerConexion();
		
		st = conexion.createStatement();
		rs = st.executeQuery(consulta);
		
		
		return rs;
	}	
	
	private static void hacerConsulta(String sql) throws SQLException{	
		//Ejecuta consultas del tipo update/delete
		if(conexion==null || conexion.isClosed())
			conexion = establecerConexion();
		if(st==null || st.isClosed())
			st = conexion.createStatement();
		st.executeUpdate(sql);
		
		st.close();
		st = null;
	}

	private static void cerrarConexion() throws SQLException {
		conexion.close();
		conexion = null;
	}
	
	public ResultSet buscarContribuyente(String tipoDoc, String numDoc) throws SQLException{
		ResultSet rs = null;
		String consulta = "SELECT * FROM contribuyente WHERE tipodoc LIKE " + tipoDoc + " AND numdoc LIKE " + numDoc;
		rs = devolverConsulta(consulta);
		
		return rs;
	}
	
	
	//AGREGAR USUARIO
	public void crearUsuario(boolean unSuperUsuario, String unNombre, String unaPassword)
	{
		String superUser = "0";
		if(unSuperUsuario) superUser = "1";
		String consulta = "INSERT INTO `agiles`.`usuario` (`id`, `nombre`, `password`, `superUsuario`) VALUES (NULL, '"+unNombre+"', '" +unaPassword+"', '"+superUser+"'); ";
		
		
		
		try {
			
			AdminBD.getIstance().hacerConsulta(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIdUsuarioDisponible() throws SQLException{
		
		ResultSet rs = null;
		String consulta = "SELECT MAX(id) FROM usuario";
		rs = devolverConsulta(consulta);
		if(null == rs){
			return 0;
		}
		else{
			rs.first();
			
			return rs.getInt("MAX(id)");
		}
		
		
		
					
	}
	
	
	
	
}