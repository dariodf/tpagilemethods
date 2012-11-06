package gestores;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import entidades.Usuario;
import excepciones.GeneralException;

public class GestorUsuario {
	
	private Usuario usuarioLogueado = new Usuario(true, "AdminJuan", "1234");
	
	
	protected GestorUsuario(){}
	private final static GestorUsuario instancia = new GestorUsuario();
	// Metodo encargado de devolver el singleton
	public static GestorUsuario getIstance(){
		return instancia;
	}
	
	public Usuario getUsuarioLogueado()
	{
		return usuarioLogueado;	
	}
	
	public String crearUsuario(boolean unSuperUsuario, String unNombre)throws GeneralException
	{
		if(!GestorUsuario.getIstance().getUsuarioLogueado().isSuperUsuario())
			throw new GeneralException("El usuario logueado no posee los privilegios para esta acción");
		else
		{
			String pass = this.generarPaswordUsuario();
			AdminBD.getIstance().crearUsuario(unSuperUsuario, "Usuario-"+this.getIdUsuarioDisponible(), pass);
			return pass;
		}
			
	}

	private String generarPaswordUsuario() {
		Random r;
		r=new Random();
		r.setSeed(new Date().getTime());
		return String.valueOf(r.nextInt(90000000)+10000000);
	}

	public int getIdUsuarioDisponible() {
		
		int id = -1;
		
		try {
			
			id = AdminBD.getIstance().getIdUsuarioDisponible();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
		
	}
	
	
}
