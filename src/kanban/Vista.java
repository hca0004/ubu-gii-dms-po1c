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
			System.out.println("Introduzca n�meros enteros");
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
			System.out.println("Introduzca n�meros enteros");
			return i;
		}
		return i;
	}
	
	public void imprimirMenu() {
		int i = -1;
		while (i < 0 || i > 4) {
			System.out.println("----------------------------------------------------");
			System.out.println("Introduzca el n�mero de la opci�n que desea realizar");
			System.out.println();
			System.out.println("1. Gesti�n de miembros del equipo");
			System.out.println("2. Gesti�n de requisitos");
			System.out.println("3. A�adir tareas");
			System.out.println("4. Gesti�n de backlogs");
			System.out.println("0. Salir de la aplicaci�n");
			System.out.println("----------------------------------------------------");
			System.out.println();
			i = recogerInt();
		}
		switch(i) {
		case 1:
			imprimirGestionMiembros();
			break;
		case 2:
			imprimirGestionRequisitos();
			break;
		case 3:
			imprimirNuevaTarea();
			imprimirMenu();
			break;
		case 4: 
			imprimirGestionBacklogs();
			break;
		default:
			scanner.close();
			break;
		}
	}
	
	public void imprimirGestionMiembros() {
		int i = -1;
		while (i < 0 || i > 2) {
		System.out.println("----------------------------------------------------");
		System.out.println("Introduzca el n�mero de la opci�n que desea realizar");
		System.out.println();
		System.out.println("1. Ver miembros del equipo");
		System.out.println("2. A�adir miembros del equipo");
		System.out.println("0. Volver al men� principal");
		System.out.println("----------------------------------------------------");
		System.out.println();
		i = recogerInt();
		}
		switch(i) {
		case 1:
			imprimirGestionMiembros();
			break;
		case 2:
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
		System.out.println("Introduzca el n�mero de la opci�n que desea realizar");
		System.out.println();
		System.out.println("1. Ver requisitos");
		System.out.println("2. A�adir requisitos");
		System.out.println("0. Volver al men� principal");
		System.out.println("----------------------------------------------------");
		System.out.println();
		i = recogerInt();
		}
		switch(i) {
		case 1:
			imprimirGestionRequisitos();
			break;
		case 2:
			imprimirGestionRequisitos();
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
		System.out.println("Introduzca el n�mero de la opci�n que desea realizar");
		System.out.println();
		System.out.println("1. Ver tareas en Product Backlog");
		System.out.println("2. Mover tareas de Product Backlog a Sprint Backlog");
		System.out.println("3. Ver tareas en Sprint Backlog");
		System.out.println("4. Mover tareas de TODO a DOING");
		System.out.println("5. Mover tareas de DOING a EVALUATING");
		System.out.println("6. Mover tareas de EVALUATING A FINISHED");
		System.out.println("7. Finalizar el Sprint");
		System.out.println("0. Volver al men� principal");
		System.out.println("----------------------------------------------------");
		System.out.println();
		i = recogerInt();
		}
		switch(i) {
		case 1:
			imprimirGestionBacklogs();
			break;
		case 2:
			imprimirGestionBacklogs();
			break;
		case 3:
			imprimirGestionBacklogs();
			break;
		case 4:
			imprimirGestionBacklogs();
			break;
		case 5:
			imprimirGestionBacklogs();
			break;
		case 6:
			imprimirGestionBacklogs();
			break;
		case 7:
			imprimirGestionBacklogs();
			break;
		default: 
			imprimirMenu();
			break;
		}
	}
	
	public void imprimirNuevaTarea() {
		System.out.println("Introduzca una ID de uno de los siguientes requisitos:");
		for(Requisito r : m.getRequisitos().values()) {
			System.out.println("ID: "+r.getID()+", Titulo: "+r.getTitulo()+", Descripcion: " + r.getDescripcion());
		}
		int id = recogerInt();
		System.out.println("Introduzca el t�tulo:");
		String t = recogerString();
		System.out.println("Introduzca la descripci�n:");
		String d = recogerString();
		System.out.println("Introduzca el coste:");
		float c = recogerFloat();
		System.out.println("Introduzca el beneficio:");
		float b = recogerFloat();
		m.nuevaTarea(id, t, d, c, b);
	}

}