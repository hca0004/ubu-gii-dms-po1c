package kanban;

/**
 * 
 */
public class MiembroDeEquipo {

	private String nombre;
	private String apellido;
	private String dni;
	private String tlf;
	private String nick;
	
    public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	public String getTlf() {
		return tlf;
	}

	public String getNick() {
		return nick;
	}

	/**
     * Default constructor
     */
    public MiembroDeEquipo(String n, String a, String d, String t, String nick) {
    	this.nombre = n;
    	this.apellido = a;
    	this.dni = d;
    	this.tlf = t;
    	this.nick = nick;
    }

}