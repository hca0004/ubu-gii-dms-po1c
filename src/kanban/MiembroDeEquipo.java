package kanban;

/**
 * 
 */
public class MiembroDeEquipo {

	public String nombre;
	public String apellido;
	public String dni;
	public String tlf;
	public String nick;
	
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