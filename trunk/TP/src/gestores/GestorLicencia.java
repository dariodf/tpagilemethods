package gestores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GestorLicencia {

		protected GestorLicencia(){}
		private final static GestorLicencia instancia = new GestorLicencia();
		// Metodo encargado de devolver el singleton
		public static GestorLicencia getIstance(){
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
				Calendar cFechaLimite = new GregorianCalendar();
				
				//Date FechaHoy representa el current de la fecha
				Date fechaHoy = Calendar.getInstance().getTime();
				//Date fechaVencimiento es el valor a retornar
				Date fechaVencimiento = new Date();
				//Fecha Limite es la fecha de cumpleaños del titular del año actual
				Date fechaLimite = new Date();
				
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
	
				//A los calendarios cFechaNac y cFechaLimite les asignamos la fecha de nacimiento del titular		
				cFechaNac.setTime(unTitular.getFechaNac());
				cFechaLimite.setTime(unTitular.getFechaNac());
				
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
				//Obtenemos la edad del titular
				
				if (fechaHoy.before(fechaLimite))
					edad=(Calendar.getInstance().get(1) - cFechaNac.get(1)) -1;
				else
					edad=(Calendar.getInstance().get(1) - cFechaNac.get(1));
				
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
}
