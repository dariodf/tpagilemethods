package gestores;

import entidades.Usuario;

public class GestorUsuario {
	
	private Usuario usuarioLogueado = new Usuario(false, "AdminJuan", "1234");
	
	
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
	
	
}
