package entidades;

import java.sql.Date;

public class Contribuyente {

/*Atributos*/	
	
		private String nombre;
		private String apellido;
		private String tipoDoc;
		private String numDoc;
		private Date fechaNac;
		private String direccion;
		private String grupoSanguineo;
		private String factorRH;
		private boolean donante;
		private String localidad;
		
/*Constructores*/
		
		public Contribuyente(String nombre, String apellido, String tipoDoc,
				String numeroDoc, Date fechaNac, String direccion,
				String grupoSanguineo, String factorRH, boolean donante, String unaLocalidad) {
			
			super();
			this.nombre = nombre;
			this.apellido = apellido;
			this.tipoDoc = tipoDoc;
			this.numDoc = numeroDoc;
			this.fechaNac = fechaNac;
			this.direccion = direccion;
			this.grupoSanguineo = grupoSanguineo;
			this.factorRH = factorRH;
			this.donante = donante;
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


		
}
