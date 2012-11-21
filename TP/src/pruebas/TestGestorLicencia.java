package pruebas;



import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;



import entidades.Licencia;
import entidades.Titular;
import excepciones.GeneralException;
import gestores.GestorLicencia;

public class TestGestorLicencia {

	@Test
	public void test() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("16-12-1985");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Agust�n", "Paolantonio", "DNI",
				"34731722","M","Soltero", fechaEntrada, "Lavalle 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("02-10-2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		
		titular1.getLicencias().add(new Licencia("B",null,null));
		//assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		assertEquals(true,test.validarLicencia(titular1,"C"));

	}

	
	
}