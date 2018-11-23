package kanban;

import java.util.ArrayList;
import java.util.HashMap;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		HashMap<String,MiembroDeEquipo> r;
		CSV csv;
		csv=(CSV) CSV.getInstance();
		Tarea t1,t2,t3;
		t1=new Tarea(  new Defecto(1,"defecto1","soy un defecto"),2,"holi","descripcion de holi",1.1f,1.2f);
		t2=new Tarea(  new Defecto(1,"defecto1","soy un defecto"),3,"holi","descripcion de holi",1.1f,1.2f);
		t3=new Tarea(  new Defecto(1,"defecto5","soy un defectozzz"),4,"holi","descripcion de holi",5.1f,3.2f);
		ProductBacklog.getInstance().anadirTarea(t1);
		ProductBacklog pro=ProductBacklog.getInstance();
		pro.anadirTarea(t2);
		csv.updateProductBacklog(pro);
		SprintBacklog sp=new SprintBacklog();
		t1.asignarMiembro(new MiembroDeEquipo("hector","cxcxc","zcxca","adsDSFS","adsdas"));
		t2.asignarMiembro(new MiembroDeEquipo("jose","cxcxc","zcxca","adsDSFS","joceadsdas"));
		t3.asignarMiembro(new MiembroDeEquipo("luis","cxcxc","zcxca","adsDSFS","luluadsdas"));
		sp.anadirTarea(t1);
		sp.anadirTarea(t2);
		sp.anadirTarea(t3);
		sp.moveraDoing(2);
		sp.moveraDoing(3);
		sp.moveraTesting(3);
		csv.updateSprintBacklogActual(sp);
		System.out.println(sp.getDoing());
		ArrayList<SprintBacklog> sps=new ArrayList<SprintBacklog>();
		sps.add(sp);
		sps.add(sp);
		csv.updateSprintBacklog(sps);
		Requisito req1,req2;
		req1=new HistoriaDeUsuario(1,"h1","holiiiii");
		req2=new Defecto(2,"d1","holiiiiiwiiii");
		req1.anadirTarea(6, "aaa", "ggg", 1.0f, 2.0f);
		req2.anadirTarea(8, "sdsd", "ddfdggg", 3.4f, 5.2f);
		HashMap <Integer,Requisito> requis=new HashMap <Integer,Requisito>();
		requis.put(req1.getID(), req1);
		requis.put(req2.getID(), req2);
		csv.updateRequisito(requis);
		csv.selectRequisitos();
		

	}

}
