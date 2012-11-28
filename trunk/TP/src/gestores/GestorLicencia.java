package gestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import entidades.Licencia;
import entidades.Titular;
import excepciones.GeneralException;

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
				SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
				
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
		
		public static void validarLicencia (Titular unTitular, String unaClaseLicencia) throws GeneralException{
			
			int edad=0;
			
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
			String cadenaFecha;
			Calendar cFechaNac =  new GregorianCalendar();
			Calendar fechaHoyAux = Calendar.getInstance();
			fechaHoyAux.add(5,-1);
						
			
			cFechaNac.setTime(unTitular.getFechaNac());
			
			edad=calcularEdad(cFechaNac);
			
			//**CONDICIONES PARA QUE SE PUEDA EMITIR LICENCIA
			
			if(edad<17)
			{
				throw new GeneralException ("El titular debe ser mayor a 17 años para obtener una licencia");
			}
			//si ya tiene una licencia activa de ese mismo tipo
			
			//recorre la colección de licencias que tiene el titular y compara la clase de cada una con la que viene como parámetro.
			//Si son iguales y si la fecha de vencimiento de la licencia del titular es después de la fecha de hoy, da un mensaje de error.
			
			
			
			if(unTitular.getLicencias().isEmpty()==false)
			{
				for (Licencia lic : unTitular.getLicencias()) 
				{
					
					if((lic.clase.compareTo(unaClaseLicencia) ==0) && 
							(lic.fechaVencimiento.after(fechaHoyAux.getTime())))
							{
						       
						         throw new GeneralException ("El titular ya tiene una licencia activa de esta clase");
						         
							}
					
						 
  			    }
			}
			
			
			//No se puede dar una licencia profesional por primera vez a una persona de más de 65 años.
			//recorre la colección de licencias que tiene el titular, si esta es vacía es porque nunca tuvo una licencia activa.
			//Si el titular es mayor de 65 años y la colección es vacía, da un mensaje de error.
			if ((unTitular.getLicencias().isEmpty()==true) && (edad>65))
			{ 
				throw new GeneralException ("El titular no puede obtener una licencia por primera vez a los 65 años");  
			}
			
			
			//Una persona menor a 21 años sólo puede tener licencias de clase A, B, F, G. Y debe ser mayor a 17 años.
			//Si el titular es menos a 21 años y la clase de la licencia que ingresa como parámetro es C, D o E, da mensaje de error.
			
			if (edad<21 && (unaClaseLicencia.compareTo("C") == 0|| unaClaseLicencia.compareTo("D") == 0 || unaClaseLicencia.compareTo("E") == 0))
			{
				throw new GeneralException ("El titular no puede recibir esta licencia hasta los 21 años");
			}
			
			
			
			//si se quiere otorgar una licencia profesional (C, D o E) a una persona a la cual no se le haya 
			//otorgado previamente (al menos un año antes) una licencia de clase B.
			
			//Si la licencia que ingresó como parámetro es C, D o E, se recorre la colección para ver si tiene o tuvo una licencia 
			//de clase B. FALTA VER, SI ES LA PRIMERA VEZ, QUE TENGA UN AÑO DE ANTIGUEDAD.
			
			if (unaClaseLicencia.compareTo("C") == 0|| unaClaseLicencia.compareTo("D") == 0 || unaClaseLicencia.compareTo("E")==0)
			{
				int puede=0;
				Calendar calendarTemp = Calendar.getInstance();
				calendarTemp.add(1, -1);
				for (Licencia lic : unTitular.getLicencias()) 
				{
					
					if(lic.clase.compareTo("B") == 0) 
						{
							if(lic.fechaEmision.before(calendarTemp.getTime()))
							{
								puede=1;
							}
						}	
				}
				
				if (puede==0)
				{
					throw new GeneralException ("El titular debe tener una licencia de clase B con un año de antiguedad");
				}
			}			
		}
		
		
		//unaFecha es la fecha de nacimiento del Titular
		public static int calcularEdad(Calendar CalendarioNac){
			String cadenaFecha;
			//Date FechaHoy representa el current de la fecha
			Date fechaHoy = Calendar.getInstance().getTime();
			//Formateador
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
			
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
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
			
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

		public Collection<Licencia> getLicenciasAnteriores(String id_titular) {
			try 
			{
				// Recupera de la base de datos las licencias que ya pertenecen al titular seleccionado.
				ResultSet rs = AdminBD.getInstance().buscarLicenciasTitular(id_titular);
				Collection<Licencia> licenciasAnteriores = new HashSet<Licencia>();
				// Verifica que el titular tenga licencias anteriores. De no ser así, retorna la colección vacía.
				if(rs.next())
				{
					rs.first();
					while (true)
					{
						// Instancia una de las licencias.
						Licencia licencia = new Licencia(rs.getString("Clase"),rs.getString("Observacion"),rs.getDate("FechaVencimiento"),rs.getDate("FechaEmision"));
						// Agrega la instancia de Licencia a la colección.
						licenciasAnteriores.add(licencia);
						if (rs.isLast()) break;
						rs.next();
					}
				}
				return licenciasAnteriores; 
			} 
			
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
		}

		
		
		
		
}


