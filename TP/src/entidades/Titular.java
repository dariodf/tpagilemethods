package entidades;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


public class Titular {

/*Atributos*/
	
	private String nombre;
	private String apellido;
	private String tipoDoc;
	private String numDoc;
	private String sexo;
	private String estadoCivil;
	private Date fechaNac;
	private String direccion;
	private String grupoSanguineo;
	private String factorRH;
	private boolean donante;
	private Collection<Licencia> licencias;
	private String localidad;
	

/*Constructores*/
	
	public Titular (String unNombre, String unApellido, String unTipoDoc,
			String unNumeroDoc, String unSexo, String unEstadoCivil, Date unaFechaNac, String unaDireccion,
			String unGrupoSanguineo, String factorRH, boolean esDonante, String unaLocalidad){
	
		super();
		this.nombre=unNombre;
		this.apellido=unApellido;
		this.tipoDoc=unTipoDoc;
		this.numDoc=unNumeroDoc;
		this.sexo = unSexo;
		this.estadoCivil = unEstadoCivil;
		this.fechaNac= unaFechaNac;
		this.direccion = unaDireccion;
		this.grupoSanguineo = unGrupoSanguineo;
		this.factorRH = factorRH;
		this.donante= esDonante;
		this.localidad = unaLocalidad;
		this.licencias = new HashSet();
		
	}
	
	public Titular (String unNombre, String unApellido, String unTipoDoc,
			String unNumeroDoc, String unSexo, String unEstadoCivil, Date unaFechaNac, String unaDireccion,
			String unGrupoSanguineo, String factorRH, boolean esDonante, String unaLocalidad, Collection<Licencia> licencias ){
	
		super();
		this.nombre=unNombre;
		this.apellido=unApellido;
		this.tipoDoc=unTipoDoc;
		this.numDoc=unNumeroDoc;
		this.sexo = unSexo;
		this.estadoCivil = unEstadoCivil;
		this.fechaNac= unaFechaNac;
		this.direccion = unaDireccion;
		this.grupoSanguineo = unGrupoSanguineo;
		this.factorRH = factorRH;
		this.donante= esDonante;
		this.localidad = unaLocalidad;
		this.licencias = licencias;
		
	}
	
	public Titular (Contribuyente unContribuyente){
		super();
		this.nombre=unContribuyente.getNombre();
		this.apellido=unContribuyente.getApellido();
		this.tipoDoc=unContribuyente.getTipoDoc();
		this.numDoc=unContribuyente.getNumeroDoc();
		this.sexo = unContribuyente.getSexo();
		this.estadoCivil = unContribuyente.getEstadoCivil();
		this.fechaNac= unContribuyente.getFechaNac();
		this.direccion = unContribuyente.getDireccion();
		this.grupoSanguineo = unContribuyente.getGrupoSanguineo();
		this.factorRH = unContribuyente.getFactorRH();
		this.donante= unContribuyente.isDonante();
		this.localidad = unContribuyente.getLocalidad();
		this.licencias = new HashSet();
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



	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
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

	public String getFactorRH() {
		return factorRH;
	}



	public void setFactorRH(String factorRH) {
		this.factorRH = factorRH;
	}

	public boolean isDonante() {
		return donante;
	}



	public void setDonante(boolean donante) {
		this.donante = donante;
	}



	public Collection<Licencia> getLicencias() {
		return licencias;
	}



	public void setLicencia(Collection<Licencia> licencias) {
		this.licencias = licencias;
	}




	





}