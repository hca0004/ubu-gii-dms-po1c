package kanban;

/**
 * 
 */
public class Tarea {

    public int id;
    public String titulo;
    public String descripcion;
    public float coste;
    public float beneficio;
    private Requisito requisito;
    private MiembroDeEquipo miembro;
    
    /**
     * Default constructor
     */
    public Tarea(Requisito r,int id ,String t, String d, float c, float b) {
        this.requisito = r;
        this.id = id;
        this.modificarTitulo(t);
        this.modificarDescripcion(d);
        this.modificarCoste(c);
        this.modificarBeneficio(b);
    }

    public void asignarMiembro(MiembroDeEquipo m) {
    	this.miembro = m;
    }
    
    public void modificarTitulo(String t) {
    	if(!t.isEmpty())
    		this.titulo=t;
    }
    
    public void modificarDescripcion(String d) {
    	this.descripcion=d;
    }
    
    public void modificarCoste(float c) {
    	this.coste=c;
    }
    
    public void modificarBeneficio(float b) {
    	this.beneficio=b;
    }

}