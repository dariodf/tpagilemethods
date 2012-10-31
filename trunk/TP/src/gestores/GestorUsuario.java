package gestores;

public class GestorUsuario {
	protected GestorUsuario(){}
	private final static GestorUsuario instancia = new GestorUsuario();
	// Metodo encargado de devolver el singleton
	public static GestorUsuario getIstance(){
		return instancia;
	}
}
