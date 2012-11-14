package gestores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import entidades.Licencia;
import entidades.Titular;

public class GestorLicencia {

		protected GestorLicencia(){}
		private final static GestorLicencia instancia = new GestorLicencia();
		// Metodo encargado de devolver el singleton
		public static GestorLicencia getInstance(){
			return instancia;
		}
		
		public static Date calcularVigencia (entidades.Titular unTitular) {

				// Edad representa la edad calculada del titular
				// Vigencia representa  
				int edad=0, vigencia=0;
				
				//Variable temporal que almacen la fecha en string para despues parsearla a date
				String cadenaFecha;
				
				//Calendarios de Fecha de nacimiento y de Fecha Limite
				Calendar cFechaNac =  new GregorianCalendar();
			
				
				//Date FechaHoy representa el current de la fecha
			
				//Date fechaVencimiento es el valor a retornar
				Date fechaVencimiento = new Date();
				//Fecha Limite es la fecha de cumpleaños del titular del año actual
				Date fechaLimite = new Date();
				
				//Formateador
				SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
				
				//El formateador devuelve un string por eso lo asignamos a la cadenFecha
						//A los calendarios cFechaNac y cFechaLimite les asignamos la fecha de nacimiento del titular		
				cFechaNac.setTime(unTitular.getFechaNac());
			
				fechaLimite = calcularFechaCumple(cFechaNac.getTime());
				edad=calcularEdad(cFechaNac);
				
				if ((edad<21) && (edad>=17))
					if(unTitular.getLicencias().isEmpty()==true) 
						vigencia=1;
					else 
						vigencia=3;

				
				else if (edad>=21 && edad<46)
					vigencia=5;
				else if (edad>=46 && edad<60) 
					vigencia=4;
				else if (edad>=60 && edad<70)
					vigencia=3;
				else if (edad>=70 && edad<100)
					vigencia=1;

				//Creamos un nuevo calendario para obtener la fecha de vencimiento de la licencia
				Calendar cFechaVencimiento = new GregorianCalendar();
				
				//y le seteamos la fecha límite
				cFechaVencimiento.setTime(fechaLimite);
				
				//Sumamos en la posicion 1 (año) de la fecha de vencimiento la vigencia calculada
				cFechaVencimiento.add(1, vigencia);
				
				cadenaFecha = formateador.format(cFechaVencimiento.getTime());
				try {
					fechaVencimiento = formateador.parse(cadenaFecha);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return fechaVencimiento;
						

		}
		
		public static void validarLicencia (Titular unTitular, String unaClaseLicencia){
			
			int edad=0;
			Calendar cFechaNac =  new GregorianCalendar();
			//Calendar FechaVenLic = new GregorianCalendar();
			
			cFechaNac.setTime(unTitular.getFechaNac());
			
			edad=calcularEdad(cFechaNac);
			
			//**CONDICIONES PARA QUE SE PUEDA EMITIR LICENCIA
			
			//si ya tiene una licencia activa de ese mismo tipo
			
			if(unTitular.getLicencias().isEmpty()==false)
			{
				for (Licencia lic : unTitular.getLicencias()) 
				{
					//FechaVenLic.setTime(lic.fechaVencimiento);
					if((lic.clase==unaClaseLicencia) && 
							(lic.fechaVencimiento.after(Calendar.getInstance().getTime())))
							{
						         //Se tira la excepción
							}
						 
  			    }
			}
			
			
			//No se puede dar una licencia profesional por primera vez a una persona de más de 65 años.
			
			if ((unTitular.getLicencias().isEmpty()==true) && (edad>65))
			{ 
				//se tira excpción
			}
			
			
			//Una persona menor a 21 años sólo puede tener licencias de clase A, B, F, G. Y debe ser mayor a 17 años.
			
			if (edad<21 && (unaClaseLicencia=="C" || unaClaseLicencia=="D" || unaClaseLicencia=="E"))
			{
				//se tira excepción
			}
			
			
			
			//si se quiere otorgar una licencia profesional (C, D o E) a una persona a la cual no se le haya 
			//otorgado previamente (al menos un año antes) una licencia de clase B.
			
			
			
			
			
		}
		
		
		//unaFecha es la fecha de nacimiento del Titular
		public static int calcularEdad(Calendar CalendarioNac){
			String cadenaFecha;
			//Date FechaHoy representa el current de la fecha
			Date fechaHoy = Calendar.getInstance().getTime();
			//Formateador
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			
			//El formateador devuelve un string por eso lo asignamos a la cadenFecha
			cadenaFecha = formateador.format(fechaHoy);
			//Luego se parsea el string a date
			try {
				fechaHoy = formateador.parse(cadenaFecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int edad=0;
			
			//Obtenemos la edad del titular
			
			if (fechaHoy.before(CalendarioNac.getTime()))
				edad=(Calendar.getInstance().get(1) - CalendarioNac.get(1)) -1;
			else
				edad=(Calendar.getInstance().get(1) - CalendarioNac.get(1));
			
			return edad;
		}
		
		
		public static Date calcularFechaCumple(Date fechaNacimiento){
			//Variable temporal que almacen la fecha en string para despues parsearla a date
			String cadenaFecha;
			
			//Calendarios de Fecha de nacimiento y de Fecha Limite
			
			Calendar cFechaLimite = new GregorianCalendar();
			
			
			//Date fechaVencimiento es el valor a retornar
			
			//Fecha Limite es la fecha de cumpleaños del titular del año actual
			Date fechaLimite = new Date();
			
			//Formateador
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			
			//A los calendarios cFechaNac y cFechaLimite les asignamos la fecha de nacimiento del titular		
			
			cFechaLimite.setTime(fechaNacimiento);
			
			//Asignamos al calendario cFechaLimite el año actual. 1 es la posición del año.
			cFechaLimite.set(1, Calendar.getInstance().get(1));

			//Cadena fecha es un string con la fecha límite
			cadenaFecha = formateador.format(cFechaLimite.getTime());
			
			try {
				fechaLimite = formateador.parse(cadenaFecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

			
			
			return fechaLimite;
		}
}


