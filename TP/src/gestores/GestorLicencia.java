package gestores;

public class GestorLicencia {

		protected GestorLicencia(){}
		private final static GestorLicencia instancia = new GestorLicencia();
		// Metodo encargado de devolver el singleton
		public static GestorLicencia getIstance(){
			return instancia;
		}
}
