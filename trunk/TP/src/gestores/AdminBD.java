package gestores;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import entidades.Usuario;

import entidades.Titular;
import excepciones.GeneralException;


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
	public void crearUsuario(boolean unSuperUsuario, String unNombre, String unaPassword)throws SQLException
	{
		// Datos Fecha Auditoría
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String fechaHora = dateFormat.format(cal.getTime());
		

		String superUser = "0";
		if(unSuperUsuario) superUser = "1";
		String consulta = "INSERT INTO `agiles`.`usuario` (`id`, `nombre`, `password`, `superUsuario`) VALUES (NULL, '"+unNombre+"', '" +unaPassword+"', '"+superUser+"'); ";
		AdminBD.getIstance().hacerConsulta(consulta);
		
		Usuario usuarioCreado = AdminBD.getIstance().recuperarUsuario(unNombre);
		Usuario usuarioLogueado = AdminBD.getIstance().recuperarUsuario(GestorUsuario.getIstance().getUsuarioLogueado().getNombre());
		String descripcion = "El usuario "+usuarioLogueado.getNombre()+" ha creado al usuario "+usuarioCreado.getNombre();
		consulta =  "INSERT INTO `agiles`.`auditoriausuario` (`id`, `id_usuario_creado`, `Descripcion`, `Fecha`, `id_usuario_logueado`) VALUES (NULL, '"+usuarioLogueado.getId()+"','"+descripcion+"','" +fechaHora  +"', '"+usuarioCreado.getId()+"'); ";
		AdminBD.getIstance().hacerConsulta(consulta);
		
	}
	
	//Agregar titular
	public void crearTitular(Titular unTitular)throws SQLException, GeneralException
	{
		
		ResultSet rs = null;
		//Realiza la consulta para testear si el titular existe en la BD
		String consulta1 = "SELECT * FROM 'agiles'.'titular' WHERE tipodoc LIKE '"+unTitular.getTipoDoc()+"' AND numdoc LIKE '"+unTitular.getNumeroDoc()+"' AND sexo LIKE '"+unTitular.getSexo()+"';";
		rs = AdminBD.getIstance().devolverConsulta(consulta1);
		
		if (rs.next()){
			throw new GeneralException("Ya existe el titular");
		}
		else{
			
			//Agrega el titular a la BD
			String consulta2 = "INSERT INTO 'agiles'.'titular' ('id', 'nombre', 'apellido', 'sexo', 'estadocivil', 'fechanacimiento', 'localidad', 'direccion', ''gruposanguineo', 'factorrh', 'donante', 'numdoc', 'tipodoc' ) VALUES (NULL, '"+unTitular.getNombre()+"', '"+unTitular.getApellido()+"', '"+unTitular.getSexo()+"', '" +unTitular.getEstadoCivil()+"','"+unTitular.getFechaNac().toString()+"','"+unTitular.getLocalidad()+"','"+unTitular.getDireccion()+"', '"+unTitular.getGrupoSanguineo()+"', '"+unTitular.getFactorRH()+"', '"+unTitular.isDonante()+"', '"+unTitular.getNumeroDoc()+"', '" +unTitular.getTipoDoc()+"'); ";
			AdminBD.getIstance().hacerConsulta(consulta2);
			
			
			//Auditoria
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String fechaHora = dateformat.format(cal.getTime());
			
			int id = recuperarIdTitular(unTitular.getTipoDoc(), unTitular.getNumeroDoc(), unTitular.getSexo());
			entidades.Usuario usuario = recuperarUsuario(GestorUsuario.getIstance().getUsuarioLogueado().getNombre());
			
			String consulta3 = "INSERT INTO 'agiles'.'auditoriatitular' ('id', 'descripcion', 'fecha', 'id_titular', 'id_usuario') VALUES (NULL, 'El usuario "+GestorUsuario.getIstance().getUsuarioLogueado().getNombre()+" creó el titular "+unTitular.getNombre()+" "+unTitular.getApellido()+"', '"+fechaHora+"', '"+id+"', '"+usuario.getId()+"' ) ;";
		}

		
	}

	//Devuelve el id de usuario que se puede usar para un nuevo usuario
	public int getIdUsuarioDisponible() throws SQLException
	{
		
		ResultSet rs = null;
		String consulta = "SELECT MAX(id) FROM usuario";
		rs = devolverConsulta(consulta);
		
		if(null == rs)
		{
			return 0;
		}
		else
		{
			rs.first();
			return rs.getInt("MAX(id)");
		}	
					
	}
	
	public Usuario recuperarUsuario(String unNombre)throws SQLException
	{
		ResultSet rs = null;
		String consulta = "SELECT* FROM usuario WHERE nombre LIKE '"+unNombre+"';";
		rs = devolverConsulta(consulta);
		
		rs.first();

		int id = rs.getInt("id");
		String nombreUsuario = rs.getString("nombre");
		String password = rs.getString("password");
		boolean superUsuario = rs.getBoolean("superUsuario");
	 	
		Usuario user = new Usuario(id, superUsuario,nombreUsuario, password);
		return user;
	}


	public int recuperarIdTitular(String unTipoDoc, String unNumDoc, String unSexo)throws SQLException
	{
		
		ResultSet rs = null;
		String consulta = "SELECT 'id' FROM 'agiles'.'titular' WHERE tipodoc LIKE '"+unTipoDoc+"' AND numdoc LIKE '"+unNumDoc+"' AND sexo LIKE '"+unSexo+"';";
		rs = devolverConsulta(consulta);
	
		rs.first();
	
		int id = rs.getInt("id");
		return id;
	
	}


}
