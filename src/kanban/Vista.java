package kanban;

import java.util.*;

/**
 * 
 */
public class Vista {

	/**
	 * Default constructor
	 */
	public Vista() {
	}

	public static void main(String[] args) {
		Controlador c = Controlador.getInstance();
		Vista v = new Vista();
		v.imprimirMenu(c);
	}

	public int recogerInt() {
		int i;
		Scanner scanner = new Scanner(System.in);
		try {
			i = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Introduzca números enteros");
			i = -1;
		}
		scanner.close();
		return i;
	}
	public void imprimirMenu(Controlador c) {
		int i = -1;
		while (i < 0 && i > 4) {
			System.out.println("----------------------------------------------------");
			System.out.println("Introduzca el número de la opción que desea realizar");
			System.out.println();
			System.out.println("1. Gestión de miembros del equipo");
			System.out.println("2. Gestión de requisitos");
			System.out.println("3. Añadir tareas");
			System.out.println("4. Gestión de backlogs");
			System.out.println("0. Salir de la aplicación");
			System.out.println("----------------------------------------------------");
			System.out.println();
			i = recogerInt();
		}
		switch(i) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			imprimirMenu(c);
			break;
		case 4: 
			break;
		default:
			break;
		}
	}
	
	public void imprimirGestionMiembros(Controlador c) {
		int i = -1;
		while (i < 0 && i > 2) {
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
		switch(i) {
		case 1:
			imprimirGestionMiembros(c);
			break;
		case 2:
			imprimirGestionMiembros(c);
			break;
		default: 
			imprimirMenu(c);
			break;
		}
	}
	
	public void imprimirGestionRequisitos(Controlador c) {
		int i = -1;
		while (i < 0 && i > 2) {
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
		switch(i) {
		case 1:
			imprimirGestionRequisitos(c);
			break;
		case 2:
			imprimirGestionRequisitos(c);
			break;
		default: 
			imprimirMenu(c);
			break;
		}
	}
	
	public void imprimirGestionBacklogs(Controlador c) {
		int i = -1;
		while (i < 0 && i > 7) {
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
		switch(i) {
		case 1:
			imprimirGestionBacklogs(c);
			break;
		case 2:
			imprimirGestionBacklogs(c);
			break;
		case 3:
			imprimirGestionBacklogs(c);
			break;
		case 4:
			imprimirGestionBacklogs(c);
			break;
		case 5:
			imprimirGestionBacklogs(c);
			break;
		case 6:
			imprimirGestionBacklogs(c);
			break;
		case 7:
			imprimirGestionBacklogs(c);
			break;
		default: 
			imprimirMenu(c);
			break;
		}
	}

}