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
			fechaEntrada = formateador.parse("02-10-1989");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Agustín", "Paolantonio", "DNI",
				"34731722","M","Soltero", fechaEntrada, "Lavalle 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("02-10-2017");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		
		//titular1.getLicencias().add(new Licencia("B",null,null));
		assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		//assertEquals(true,test.validarLicencia(titular1,"A"));

	}

	@Test
	public void test1() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("31-12-1993");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("31-12-2013");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		
		//titular1.getLicencias().add(new Licencia("B",null,null));
		assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		//assertEquals(true,test.validarLicencia(titular1,"A"));

	}
	@Test
	public void test2() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("03-03-1959");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("03-03-2016");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		
		//titular1.getLicencias().add(new Licencia("B",null,null));
		assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		//assertEquals(true,test.validarLicencia(titular1,"A"));

	}
	
	@Test
	public void test3() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("05-08-1949");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("05-08-2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		
		//titular1.getLicencias().add(new Licencia("B",null,null));
		assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		//assertEquals(true,test.validarLicencia(titular1,"A"));

	}
	
	@Test
	public void test4() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("05-08-1930");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("05-08-2013");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		
		//titular1.getLicencias().add(new Licencia("B",null,null));
		assertEquals(fechaResultado,test.calcularVigencia(titular1));
		
		//assertEquals(true,test.validarLicencia(titular1,"A"));

	}
	
	@Test
	public void test5() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("05-08-1930");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("05-08-2013");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
		            

			test.validarLicencia(titular1,"A");
			fail();
		} catch (GeneralException e) {
			//TODO: assertEquals("El titular no puede obtener una licencia por primera vez a los 65 años",e.lanzarMensaje());
		           
		}
		 
	}
	

	
	@Test
	public void test7() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("05-08-1930");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("05-08-2014");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		titular1.getLicencias().add(new Licencia("B",null,fechaResultado,null));
		try {
		            

			test.validarLicencia(titular1,"B");
			fail();
		} catch (GeneralException e) {
			//TODO: assertEquals("El titular ya tiene una licencia activa de esta clase",e.lanzarMensaje());
		           
		}
		 
	}
	@Test
	public void test8() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("05-08-1930");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("05-08-2012");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		titular1.getLicencias().add(new Licencia("B",null,null,fechaResultado));
		try {
		            

			test.validarLicencia(titular1,"C");
			fail();
		} catch (GeneralException e) {
			//TODO:assertEquals("El titular debe tener una licencia de clase B con un año de antiguedad",e.lanzarMensaje());
		           
		}
		 
	}
	
	@Test
	public void test9() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("05-08-1993");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("05-08-2014");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//titular1.getLicencias().add(new Licencia("B",null,fechaResultado,null));
		try {
		            

			test.validarLicencia(titular1,"C");
			fail();
		} catch (GeneralException e) {
			//TODO:assertEquals("El titular no puede recibir esta licencia hasta los 21 años",e.lanzarMensaje());
		           
		}
		 
	}
	
	@Test
	public void test10() throws GeneralException {
		GestorLicencia test= GestorLicencia.getInstance();
		String cadenaFecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEntrada =  new Date();
		try {		
			fechaEntrada = formateador.parse("05-08-1987");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Titular titular1 = new Titular("Nehemías", "David", "DNI",
				"34723454","M","Soltero", fechaEntrada, "Lavaise 5309",
				"a+", "blablabla", true, "Santa Fe"); 
		
		
		Date fechaResultado = new Date();
		
		try {
			
			fechaResultado = formateador.parse("05-08-2014");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//titular1.getLicencias().add(new Licencia("B",null,fechaResultado,null));
		try {
		            

			test.validarLicencia(titular1,"A");
			fail();
		} catch (GeneralException e) {
			//TODO:assertEquals("El titular no puede recibir esta licencia hasta los 21 años",e.lanzarMensaje());
		           
		}
		 
	}
	
	
}  