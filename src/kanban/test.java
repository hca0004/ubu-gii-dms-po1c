package kanban;

import java.util.HashMap;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		HashMap<String,MiembroDeEquipo> r;
		CSV csv;
		csv=(CSV) CSV.getInstance();
		r=csv.selectMiembrosDeEquipo();
		for(MiembroDeEquipo i:r.values()) {
			System.out.println(i.apellido+i.dni+i.nick+i.nombre+i.tlf);
		}

	}

}
