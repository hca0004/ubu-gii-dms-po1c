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
			System.out.println(i.getNombre()+i.getApellido()+i.getDni()+i.getTlf()+i.getNick());
		}
		System.out.println(csv.insertUsuario(new MiembroDeEquipo("Jose miguel","Apellido","71222222c","947999999","josemi")));
		

	}

}
