package kanban;

/**
 * 
 */
public class Controlador {

	private static Controlador c;
	private Modelo m;
    /**
     * Default constructor
     */
    private Controlador() {
    	m = Modelo.getInstance();
    }
    
    public static Controlador getInstance() {
    	if(c == null)
    		c = new Controlador();
    	return c;
    }


}