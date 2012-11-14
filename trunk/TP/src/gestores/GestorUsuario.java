package gestores;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import entidades.Usuario;
import excepciones.GeneralException;

public class GestorUsuario 
{
	
	//Usuario temporal para pruebas
	private Usuario usuarioLogueado = new Usuario(0,true, "Usuario-0", "1234");
		
	protected GestorUsuario(){}
	private final static GestorUsuario instancia = new GestorUsuario();
	
	// Metodo encargado de devolver el singleton
	public static GestorUsuario getInstance()
	{
		return instancia;
	}
	
	public Usuario getUsuarioLogueado()
	{
		return usuarioLogueado;	
	}
	
	
	//Crea un nuevo usuario en la BD y devuelve un String con la contraseña asignada al usuario creado
	public String crearUsuario(boolean unSuperUsuario, String unNombre)throws SQLException, GeneralException
	{
		
		if(!GestorUsuario.getInstance().getUsuarioLogueado().isSuperUsuario())
			throw new GeneralException("El usuario logueado no posee los privilegios para esta acción");
		else
		{
			String password = this.generarPasswordUsuario();
			AdminBD.getInstance().crearUsuario(unSuperUsuario, "Usuario-"+this.getIdUsuarioDisponible(), password);
			return password;
		}
			
	}

	//Genera una Contraseña aleatoria de 8 dígitos
	private String generarPasswordUsuario()
	{
		
		Random r;
		r=new Random();
		r.setSeed(new Date().getTime());
		return String.valueOf(r.nextInt(90000000)+10000000);
		
	}

	//Devuelve el id de usuario que se puede usar para un nuevo usuario
	public int getIdUsuarioDisponible()
	{
		int id=0;
		try {
			id = AdminBD.getInstance().getIdUsuarioDisponible()+1;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage() , "Error en la Base de Datos",JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}
	
	
}
