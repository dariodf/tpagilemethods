package gestores;

public class GestorTitular {

	protected GestorTitular(){}
	private final static GestorTitular instancia = new GestorTitular();
	// Metodo encargado de devolver el singleton
	public static GestorTitular getIstance(){
		return instancia;
	}
}
