package entidades;

import java.sql.Date;
import java.util.Collection;


public class Titular {

/*Atributos*/
	
	private String nombre;
	private String apellido;
	private String tipoDoc;
	private String numDoc;
	private Date fechaNac;
	private String direccion;
	private String grupoSanguineo;
	private boolean donante;
	private Collection<Licencia> licencia;
	private String localidad;
	

/*Constructores*/
	
	public Titular (String unNombre, String unApellido, String unTipoDoc,
			String unNumeroDoc, Date unaFechaNac, String unaDireccion,
			String unGrupoSanguineo, String unObservaciones, boolean esDonante, String localidad, String unaLocalidad){
	
		super();
		this.nombre=unNombre;
		this.apellido=unApellido;
		this.tipoDoc=unTipoDoc;
		this.numDoc=unNumeroDoc;
		this.fechaNac= unaFechaNac;
		this.direccion = unaDireccion;
		this.grupoSanguineo = unGrupoSanguineo;
		this.donante= esDonante;
		this.localidad = unaLocalidad;
		
	}



/*Getters and Setters*/
	
	public String getLocalidad() {
		return localidad;
	}



	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getTipoDoc() {
		return tipoDoc;
	}



	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}



	public String getNumeroDoc() {
		return numDoc;
	}



	public void setNumeroDoc(String numeroDoc) {
		this.numDoc = numeroDoc;
	}



	public Date getFechaNac() {
		return fechaNac;
	}



	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}



	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}



	public boolean isDonante() {
		return donante;
	}



	public void setDonante(boolean donante) {
		this.donante = donante;
	}



	public Collection<Licencia> getLicencia() {
		return licencia;
	}



	public void setLicencia(Collection<Licencia> licencia) {
		this.licencia = licencia;
	}




	





}