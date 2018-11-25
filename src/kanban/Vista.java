package kanban;

import java.util.*;

/**
 * 
 */
public class Vista {

	Scanner scanner;
	Modelo m;

	/**
	 * Default constructor
	 */
	private Vista() {
		scanner = new Scanner(System.in);
		m = Controlador.getInstance().getModelo();
	}

	public static void main(String[] args) {
		Vista v = new Vista();
		v.imprimirMenu();
	}

	public int recogerInt() {
		int i;
		try {
			i = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Introduzca números enteros");
			return -1;
		}
		return i;
	}

	public String recogerString() {
		return scanner.nextLine();
	}

	public Float recogerFloat() {
		float i = 0;
		try {
			i = Float.parseFloat(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Introduzca números enteros");
			return i;
		}
		return i;
	}

	public void imprimirMenu() {
		int i = -1;
		while (i < 0 || i > 4) {
			System.out.println("----------------------------------------------------");
			System.out.println("Introduzca el número de la opción que desea realizar");
			System.out.println();
			System.out.println("1. Gestión de miembros del equipo");
			System.out.println("2. Gestión de requisitos");
			System.out.println("3. Gestión de tareas");
			System.out.println("4. Gestión de backlogs");
			System.out.println("0. Salir de la aplicación");
			System.out.println("----------------------------------------------------");
			System.out.println();
			i = recogerInt();
		}
		switch (i) {
		case 1:
			imprimirGestionMiembros();
			break;
		case 2:
			imprimirGestionRequisitos();
			break;
		case 3:
			imprimirGestionTareas();
			break;
		case 4:
			imprimirGestionBacklogs();
			break;
		default:
			m.guardarDB();
			scanner.close();
			break;
		}
	}

	public void imprimirGestionMiembros() {
		int i = -1;
		while (i < 0 || i > 2) {
			System.out.println("----------------------------------------------------");
			System.out.println("Introduzca el número de la opción que desea realizar");
			System.out.println();
			System.out.println("1. Ver miembros del equipo");
			System.out.println("2. Añadir miembros del equipo");
			System.out.println("0. Volver al menú principal");
			System.out.println("----------------------------------------------------");
			System.out.println();
			i = recogerInt();
		}
		switch (i) {
		case 1:
			imprimirMiembros();
			imprimirEspera();
			imprimirGestionMiembros();
			break;
		case 2:
			imprimirNuevoMiembro();
			imprimirEspera();
			imprimirGestionMiembros();
			break;
		default:
			imprimirMenu();
			break;
		}
	}

	public void imprimirGestionRequisitos() {
		int i = -1;
		while (i < 0 || i > 2) {
			System.out.println("----------------------------------------------------");
			System.out.println("Introduzca el número de la opción que desea realizar");
			System.out.println();
			System.out.println("1. Ver requisitos");
			System.out.println("2. Añadir requisitos");
			System.out.println("0. Volver al menú principal");
			System.out.println("----------------------------------------------------");
			System.out.println();
			i = recogerInt();
		}
		switch (i) {
		case 1:
			imprimirRequisitos();
			imprimirEspera();
			imprimirGestionRequisitos();
			break;
		case 2:
			imprimirNuevoRequisito();
			imprimirEspera();
			imprimirGestionRequisitos();
			break;
		default:
			imprimirMenu();
			break;
		}
	}

	public void imprimirGestionTareas() {
		int i = -1;
		while (i < 0 || i > 3) {
			System.out.println("----------------------------------------------------");
			System.out.println("Introduzca el número de la opción que desea realizar");
			System.out.println();
			System.out.println("1. Añadir tarea");
			System.out.println("2. Asignar miembro a una tarea");
			System.out.println("3. Modificar tarea");
			System.out.println("0. Volver al menú principal");
			System.out.println("----------------------------------------------------");
			System.out.println();
			i = recogerInt();
		}
		switch (i) {
		case 1:
			imprimirNuevaTarea();
			imprimirEspera();
			imprimirGestionTareas();
			break;
		case 2:
			imprimirAsignarTarea();
			imprimirEspera();
			imprimirGestionTareas();
			break;
		case 3:
			imprimirModificarTarea();
			imprimirEspera();
			imprimirGestionTareas();
			break;
		default:
			imprimirMenu();
			break;
		}
	}

	public void imprimirGestionBacklogs() {
		int i = -1;
		while (i < 0 || i > 7) {
			System.out.println("----------------------------------------------------");
			System.out.println("Introduzca el número de la opción que desea realizar");
			System.out.println();
			System.out.println("1. Ver tareas en Product Backlog");
			System.out.println("2. Mover tareas de Product Backlog a Sprint Backlog");
			System.out.println("3. Ver tareas en Sprint Backlog");
			System.out.println("4. Mover tareas de TODO a DOING");
			System.out.println("5. Mover tareas de DOING a EVALUATING");
			System.out.println("6. Mover tareas de EVALUATING A FINISHED");
			System.out.println("7. Finalizar el Sprint");
			System.out.println("0. Volver al menú principal");
			System.out.println("----------------------------------------------------");
			System.out.println();
			i = recogerInt();
		}
		switch (i) {
		case 1:
			imprimirPB();
			imprimirEspera();
			imprimirGestionBacklogs();
			break;
		case 2:
			imprimirMoverAlSB();
			imprimirEspera();
			imprimirGestionBacklogs();
			break;
		case 3:
			imprimirSB();
			imprimirEspera();
			imprimirGestionBacklogs();
			break;
		case 4:
			imprimirMoverDoing();
			imprimirGestionBacklogs();
			break;
		case 5:
			imprimirMoverTesting();
			imprimirGestionBacklogs();
			break;
		case 6:
			imprimirMoverFinished();
			imprimirGestionBacklogs();
			break;
		case 7:
			imprimirFinalizarSprint();
			imprimirEspera();
			imprimirGestionBacklogs();
			break;
		default:
			imprimirMenu();
			break;
		}
	}

	public boolean imprimirError() {
		int i = -1;
		while (i < 0 || i > 1) {
			System.out.println("Los datos introducidos son invalidos. \nIntroduzca 0 para salir o 1 para continuar");
			i = recogerInt();
		}
		if (i == 1)
			return true;
		return false;
	}

	public void imprimirEspera() {
		System.out.println("Pulse enter para continuar");
		scanner.nextLine();
	}

	public void imprimirNuevaTarea() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca una ID de uno de los siguientes requisitos:");
		for (Requisito r : m.getRequisitos().values()) {
			System.out.println(
					"ID: " + r.getID() + ", Titulo: " + r.getTitulo() + ", Descripcion: " + r.getDescripcion());
		}
		System.out.println("-------------------------------------------------------");
		int id = recogerInt();
		System.out.println("Introduzca el título:");
		String t = recogerString();
		System.out.println("Introduzca la descripción:");
		String d = recogerString();
		System.out.println("Introduzca el coste:");
		float c = recogerFloat();
		System.out.println("Introduzca el beneficio:");
		float b = recogerFloat();
		if (!m.nuevaTarea(id, t, d, c, b)) {
			if (imprimirError())
				imprimirNuevaTarea();
		}

	}

	public void imprimirMiembros() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Estos son los miembros del equipo:");
		for (MiembroDeEquipo m : this.m.getMiembros().values()) {
			System.out.println("Nombre: " + m.getNombre() + ", Apellido: " + m.getApellido() + ", DNI: " + m.getDni()
					+ ", Tlf: " + m.getTlf() + ", Nick: " + m.getNick());
		}
		System.out.println("-------------------------------------------------------");
	}

	public void imprimirNuevoMiembro() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca el nombre:");
		String n = recogerString();
		System.out.println("Introduzca el apellido:");
		String a = recogerString();
		System.out.println("Introduzca el DNI:");
		String d = recogerString();
		System.out.println("Introduzca la tlf:");
		String t = recogerString();
		System.out.println("Introduzca el nick:");
		String nick = recogerString();
		System.out.println("-------------------------------------------------------");
		if (!m.nuevoMiembro(n, a, d, t, nick)) {
			if (imprimirError()) {
				System.out.println("Pruebe a rellenar todos los datos o a cambiar de nick");
				imprimirNuevoMiembro();
			}
		}
	}

	public void imprimirRequisitos() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Estos son los requisitos:");
		for (Requisito r : this.m.getRequisitos().values()) {
			System.out.println("ID: " + r.getID() + ", Título: " + r.getTitulo() + ", Descripción: "
					+ r.getDescripcion() + ", Tipo de requisito: " + r.getClass().toString());
		}
		System.out.println("-------------------------------------------------------");
	}

	public void imprimirNuevoRequisito() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca 1 si quiere crear una Historia de Usuario y 2 si quiere crear un Defecto");
		int id = recogerInt();
		System.out.println("Introduzca el título:");
		String t = recogerString();
		System.out.println("Introduzca la descripción:");
		String d = recogerString();
		System.out.println("-------------------------------------------------------");
		if (!m.nuevoRequisito(id, t, d)) {
			if (imprimirError()) {
				imprimirNuevoRequisito();
			}
		}
	}

	public void imprimirPB() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Estas son las tareas contenidas en el Product Backlog:");
		imprimirTareasPB();
		System.out.println("-------------------------------------------------------");
	}

	public void imprimirMoverAlSB() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca la ID de una de las siguientes tareas");
		imprimirTareasPB();
		System.out.println("-------------------------------------------------------");
		int id = recogerInt();
		if (!m.moverTareaPBaSB(id)) {
			if (imprimirError())
				imprimirMoverAlSB();
		}
	}

	public void imprimirSB() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Estas son las tareas contenidas en el Sprint Backlog:");
		System.out.println("En TO DO:");
		imprimirTareasTODO();
		System.out.println("En DOING:");
		imprimirTareasDOING();
		System.out.println("En TESTING:");
		imprimirTareasTESTING();
		System.out.println("En FINISHED:");
		imprimirTareasFINISHED();
		System.out.println("-------------------------------------------------------");
	}

	public void imprimirMoverDoing() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca la ID de una de las tareas contenidas en TO DO:");
		System.out.println();
		imprimirTareasTODO();
		System.out.println("-------------------------------------------------------");
		int id = recogerInt();
		if (!m.moverTareaTodoDoing(id)) {
			if (imprimirError()) {
				System.out.println(
						"Pruebe a introducir una ID de las mostradas o a seleccionar una tare con un miembro asignado");
				imprimirMoverDoing();
			}
		}
	}

	public void imprimirMoverTesting() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca la ID de una de las tareas contenidas en DOING:");
		System.out.println();
		imprimirTareasDOING();
		System.out.println("-------------------------------------------------------");
		int id = recogerInt();
		if (!m.moverTareaDoingTesting(id)) {
			if (imprimirError())
				imprimirMoverTesting();
		}
	}

	public void imprimirMoverFinished() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca la ID de una de las tareas contenidas en TESTING:");
		System.out.println();
		imprimirTareasTESTING();
		System.out.println("-------------------------------------------------------");
		int id = recogerInt();
		if (!m.moverTareaTestingFinished(id)) {
			if (imprimirError())
				imprimirMoverFinished();
		}
	}

	public void imprimirFinalizarSprint() {
		m.finalizarSprintBacklog();
		System.out.println("-------------------------------------------------------");
		System.out.println("Se finaliza el sprint backlog");
		System.out.println("-------------------------------------------------------");
	}

	private void imprimirModificarTarea() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Estas son las tareas contenidas en el Product Backlog (0)");
		imprimirTareasPB();
		System.out.println("-------------------------------------------------------");
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("Estas son las tareas contenidas en TODO (1)");
		imprimirTareasTODO();
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca 0 si la tarea está en el PB, o introduzca 1 si está en el TODO");
		int backlog = recogerInt();
		System.out.println("Introduzca la ID de la tarea");
		int id = recogerInt();
		System.out.println("Introduzca el nuevo título:");
		String t = recogerString();
		System.out.println("Introduzca la nuevo descripción:");
		String d = recogerString();
		System.out.println("Introduzca el nuevo coste:");
		float c = recogerFloat();
		System.out.println("Introduzca el nuevo beneficio:");
		float b = recogerFloat();
		if (!m.modificarTarea(backlog, id, t, d, c, b)) {
			if (imprimirError())
				imprimirModificarTarea();
		}
	}

	private void imprimirAsignarTarea() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Estas son las tareas contenidas en el Product Backlog (0)");
		imprimirTareasPB();
		System.out.println("-------------------------------------------------------");
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("Estas son las tareas contenidas en TODO (1)");
		imprimirTareasTODO();
		System.out.println("-------------------------------------------------------");
		System.out.println("Introduzca 0 si la tarea está en el PB, o introduzca 1 si está en el TODO");
		int backlog = recogerInt();
		System.out.println("Introduzca la ID de la tarea");
		int id = recogerInt();
		imprimirMiembros();
		System.out.println("Introduzca el nick del miembro del equipo a seleccionar:");
		String nick = recogerString();
		if (!m.asignarMiembroTarea(backlog, id, nick)) {
			if (imprimirError())
				imprimirAsignarTarea();
		}
	}

	private void imprimirTareasPB() {
		for (Tarea t : m.getPB().getTareas().values()) {
			System.out.print("ID: " + t.getID() + ", Titulo: " + t.getTitulo() + ", Descripcion: " + t.getDescripcion()
					+ ", Coste: " + t.getCoste() + ", Beneficio: " + t.getBeneficio() + ", Miembro asignado: ");
			if (t.getMiembro() != null)
				System.out.println(t.getMiembro().getNick());
			else
				System.out.println("-");
		}
	}

	private void imprimirTareasTODO() {
		for (Tarea t : m.getSB().getTareasTodo().values()) {
			System.out.print("ID: " + t.getID() + ", Titulo: " + t.getTitulo() + ", Descripcion: " + t.getDescripcion()
					+ ", Coste: " + t.getCoste() + ", Beneficio: " + t.getBeneficio() + ", Miembro asignado: ");
			if (t.getMiembro() != null)
				System.out.println(t.getMiembro().getNick());
			else
				System.out.println("-");
		}
	}

	private void imprimirTareasDOING() {
		for (Tarea t : m.getSB().getDoing().values()) {
			System.out.println("ID: " + t.getID() + ", Titulo: " + t.getTitulo() + ", Descripcion: "
					+ t.getDescripcion() + ", Coste: " + t.getCoste() + ", Beneficio: " + t.getBeneficio()
					+ ", Miembro asignado: " + t.getMiembro().getNick());
		}
	}

	private void imprimirTareasTESTING() {
		for (Tarea t : m.getSB().getTesting().values()) {
			System.out.println("ID: " + t.getID() + ", Titulo: " + t.getTitulo() + ", Descripcion: "
					+ t.getDescripcion() + ", Coste: " + t.getCoste() + ", Beneficio: " + t.getBeneficio()
					+ ", Miembro asignado: " + t.getMiembro().getNick());
		}
	}

	private void imprimirTareasFINISHED() {
		for (Tarea t : m.getSB().getFinished().values()) {
			System.out.println("ID: " + t.getID() + ", Titulo: " + t.getTitulo() + ", Descripcion: "
					+ t.getDescripcion() + ", Coste: " + t.getCoste() + ", Beneficio: " + t.getBeneficio()
					+ ", Miembro asignado: " + t.getMiembro().getNick());
		}
	}

}