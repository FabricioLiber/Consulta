package modelo;

public class Usuario {

	private String user;
	private byte[] password;
	
	
	public Usuario(String user, byte[] password) {
		super();
		this.user = user;
		this.password = password;
	}


	public String getUser() {
		return user;
	}


	public byte[] getPassword() {
		return password;
	}
	
	
	
	
	
	
}
