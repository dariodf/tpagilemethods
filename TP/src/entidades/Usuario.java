package entidades;

public class Usuario {

	/*Atributos*/
	private int id;
	private boolean superUsuario;
	private String nombre;
	private String password;
	
	
	/*Constructores*/
	public Usuario(int id, boolean unSuperUsuario, String unNombre, String unaPassword){
		
		super();
		this.setId(id);
		this.setSuperUsuario(unSuperUsuario);
		this.setNombre(unNombre);
		this.setPassword(unaPassword);

	}

	/*Getters & Setters*/
		public boolean isSuperUsuario() {
		return superUsuario;
	}

	public void setSuperUsuario(boolean superUsuario) {
		this.superUsuario = superUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
