package entidades;

import java.util.Date;



public class Licencia {
	
/*Atributos*/
	
	public String clase;
	public String observacion;
	public Date fechaVencimiento;
	public Date fechaEmision;
	

	public int id;
	
/*Constructores*/
	
	public Licencia(String unaClase, String unaObservacion, Date fechaVencimiento, Date fechaEmision){

		super();
		this.clase=unaClase;
		this.observacion=unaObservacion;
		this.fechaVencimiento=fechaVencimiento;
		this.fechaEmision=fechaEmision;


	}
	
/*Getters and Setters*/

	public String getClase() {
		return clase;
	}


	public void setClase(String clase) {
		this.clase = clase;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public int getId() {
		return id;
	}

}