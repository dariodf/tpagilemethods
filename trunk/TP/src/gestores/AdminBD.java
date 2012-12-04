package gestores;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import entidades.Licencia;
import entidades.Usuario;

import entidades.Titular;
import excepciones.GeneralException;


public class AdminBD {
	private static Connection conexion;
	private static Statement st;
	
	protected AdminBD(){}
	private final static AdminBD instancia = new AdminBD();
	// Metodo encargado de devolver el singleton
	public static AdminBD getInstance(){
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
		String consulta = "SELECT * FROM contribuyente WHERE TipoDoc LIKE '"+ tipoDoc+"' AND NumDoc LIKE '"+numDoc+"';";
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
		AdminBD.getInstance().hacerConsulta(consulta);
		
		Usuario usuarioCreado = AdminBD.getInstance().recuperarUsuario(unNombre);
		Usuario usuarioLogueado = AdminBD.getInstance().recuperarUsuario(GestorUsuario.getInstance().getUsuarioLogueado().getNombre());
		String descripcion = "El usuario "+usuarioLogueado.getNombre()+" ha creado al usuario "+usuarioCreado.getNombre();
		consulta =  "INSERT INTO `agiles`.`auditoriausuario` (`id`, `id_usuario_creado`, `Descripcion`, `Fecha`, `id_usuario_logueado`) VALUES (NULL, '"+usuarioLogueado.getId()+"','"+descripcion+"','" +fechaHora  +"', '"+usuarioCreado.getId()+"'); ";
		AdminBD.getInstance().hacerConsulta(consulta);
		
		
	}
	
	//Agregar titular
	public void crearTitular(Titular unTitular)throws SQLException, GeneralException
	{
		
		ResultSet rs = null;
		//Realiza la consulta para testear si el titular existe en la BD
		String consulta1 = "SELECT * FROM `agiles`.`titular` WHERE TipoDoc LIKE '"+unTitular.getTipoDoc()+"' AND NumDoc LIKE '"+unTitular.getNumeroDoc()+"' AND Sexo LIKE '"+unTitular.getSexo()+"';";
		rs = AdminBD.getInstance().devolverConsulta(consulta1);
		
		// Si el titular ya existe, se lanza una excepcion
		if (rs.next()){
			throw new GeneralException("Ya existe el titular");
		}
		else{
			
			//Agrega el titular a la BD
			Integer donante;
			if(unTitular.isDonante())
				donante = 1;
			else
				donante = 0;
			
			String consulta2 = "INSERT INTO `agiles`.`titular` (`Id`, `Nombre`, `Apellido`, `Sexo`, `EstadoCivil`, `FechaNacimiento`, `Localidad`, `Direccion`, `GrupoSanguineo`, `FactorRH`, `Donante`, `NumDoc`, `TipoDoc` ) VALUES (NULL, '"+unTitular.getNombre()+"', '"+unTitular.getApellido()+"', '"+unTitular.getSexo()+"', '" +unTitular.getEstadoCivil()+"','"+unTitular.getFechaNac().toString()+"','"+unTitular.getLocalidad()+"','"+unTitular.getDireccion()+"', '"+unTitular.getGrupoSanguineo()+"', '"+unTitular.getFactorRH()+"', '"+donante.toString()+"', '"+unTitular.getNumeroDoc()+"', '" +unTitular.getTipoDoc()+"'); ";
			AdminBD.getInstance().hacerConsulta(consulta2);
			
			
			//Auditoria
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String fechaHora = dateformat.format(cal.getTime());
			
			int id = recuperarIdTitular(unTitular.getTipoDoc(), unTitular.getNumeroDoc(), unTitular.getSexo());
			Usuario usuario = GestorUsuario.getInstance().getUsuarioLogueado();
			
			String consulta3 = "INSERT INTO `agiles`.`auditoriatitular` (`id`, `Descripcion`, `Fecha`, `Id_titular`, `id_usuario`) VALUES (NULL, 'El usuario "+usuario.getNombre()+" creó el titular "+unTitular.getNombre()+" "+unTitular.getApellido()+"', '"+fechaHora+"', '"+id+"', '"+usuario.getId()+"' ) ;";
			AdminBD.getInstance().hacerConsulta(consulta3);
		
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

	// Recupera el id de un titular especifico
	public int recuperarIdTitular(String unTipoDoc, String unNumDoc, String unSexo)throws SQLException
	{
		
		ResultSet rs = null;
		String consulta = "SELECT id FROM `agiles`.`titular` WHERE TipoDoc LIKE '"+unTipoDoc+"' AND NumDoc LIKE '"+unNumDoc+"' AND Sexo LIKE '"+unSexo+"';";
		rs = devolverConsulta(consulta);
	
		rs.first();
	
		int id = rs.getInt("id");
		return id;
	
	}
	
	
	public ResultSet buscarTitular(Titular titularBuscado) throws SQLException, GeneralException 
	{
		ResultSet rs;
		String consulta = "SELECT * FROM titular WHERE TipoDoc LIKE '"+titularBuscado.getTipoDoc()+"%' AND NumDoc LIKE '"+titularBuscado.getNumeroDoc()+"%' AND Apellido LIKE '"+titularBuscado.getApellido()+"%' AND Nombre LIKE '"+titularBuscado.getNombre()+"%'";
		rs = devolverConsulta(consulta);
		
		if(!rs.first()) throw new GeneralException("La búsqueda no genero resultados.");
		
		return rs;
	}

	public ResultSet buscarLicenciasTitular(String id_titular) throws SQLException {
		ResultSet rs;
		String consulta = "SELECT * FROM licencia WHERE id_titular LIKE '"+id_titular+"';";
		rs = devolverConsulta(consulta);
		return rs;
	}
	
	public Licencia recuperarLicencia(String id_titular) throws SQLException {
		ResultSet rs;
		String consulta = "SELECT * FROM licencia WHERE id_titular LIKE '"+id_titular+"' ORDER BY FechaEmision DESC;";
		rs = devolverConsulta(consulta);
		rs.first();
		return new Licencia(rs.getInt("NumeroLicencia"), rs.getString("Clase"),  rs.getString("Observacion"), rs.getDate("FechaVencimiento"), rs.getDate("FechaEmision"));
				
	}
	
	public void agregarLicencia(Licencia nuevaLicencia, String id_titular) throws SQLException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaVencimiento = dateFormat.format(nuevaLicencia.getFechaVencimiento());
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaEmision = dateFormat.format(nuevaLicencia.getFechaEmision());
		
		String consulta =  "INSERT INTO `agiles`.`licencia` (`Clase`, `FechaVencimiento`, `Observacion`, `id_titular`, `FechaEmision`) VALUES ('"+nuevaLicencia.getClase()+"','"+fechaVencimiento+"','"+nuevaLicencia.observacion+"','"+id_titular+"','"+fechaEmision+"'); ";
		AdminBD.getInstance().hacerConsulta(consulta);
		
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String fechaHora = dateformat.format(cal.getTime());
		
		
		String descripcion = "El usuario "+GestorUsuario.getInstance().getUsuarioLogueado().getNombre()+" ha creado la licencia";
		Licencia lic = recuperarLicencia(id_titular);
		
		consulta = "INSERT INTO `agiles`.`auditorialicencia` (`id`, `Descripcion`, `Fecha`, `NumeroLicencia`, `id_Usuario`) VALUES (NULL,'"+descripcion+"','"+fechaHora+"','"+lic.getId()+"','"+GestorUsuario.getInstance().getUsuarioLogueado().getId()+"');";
		AdminBD.getInstance().hacerConsulta(consulta);		
		
	}

	// Retorna un usuario de la base de datos a partir de los datos cargados: usuario y contraseña
	public Usuario verificarUsuario(String usuario, String contrasenya) throws SQLException, GeneralException {
		ResultSet rs;
		Usuario unUsuario;
		String consulta = "SELECT * FROM usuario WHERE nombre LIKE '"+usuario+"' AND password LIKE '"+contrasenya+"';";
		rs = devolverConsulta(consulta);
		
		// Si no encuentra el usuario, lanza una excepcion
		if(!rs.next()){
			throw new GeneralException("Usuario o Contraseña Incorrectos");
		}
		// Si encuentra el usuario, lo instancia y lo retorna
		else{
			boolean esSuperUsuario = true;
			if (rs.getInt("superUsuario") == 0)
				esSuperUsuario = false;
				
			unUsuario = new Usuario(rs.getInt("id"), esSuperUsuario, rs.getString("nombre"), rs.getString("password"));
		}
		return unUsuario;
	}



}
