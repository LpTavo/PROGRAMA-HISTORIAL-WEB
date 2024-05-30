import java.util.Scanner;
import java.util.EmptyStackException;

public class Navegador {

    private static final int MAX_PAGES = 10; // Tamaño máximo del historial
    private static final String[] historial = new String[MAX_PAGES]; // Arreglo para almacenar las páginas visitadas
    private static int top = -1; // Índice del elemento superior del historial

    // Método para verificar si el historial está vacío
    private static boolean isEmpty() {
        return top == -1;
    }

    // Método para verificar si el historial está lleno
    private static boolean isFull() {
        return top == MAX_PAGES - 1;
    }

    // Método para agregar una página al historial
    private static void agregarPagina(String pagina) {
        if (isFull()) {
            // Si el historial está lleno, eliminar la página más antigua antes de agregar una nueva
            pop();
        }
        historial[++top] = pagina;
    }

    // Método para retroceder a la página anterior en el historial
    private static String retroceder() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return historial[top--];
    }

    // Método para imprimir el historial de navegación
    private static void imprimirHistorial() {
        if (isEmpty()) {
            System.out.println("El historial está vacío.");
        } else {
            System.out.println("Historial de navegación:");
            for (int i = top; i >= 0; i--) {
                System.out.println(historial[i]);
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            
            while (continuar) {
                System.out.println("\n1. Navegar a una nueva página.");
                System.out.println("2. Retroceder a la página anterior.");
                System.out.println("3. Ver historial de navegación.");
                System.out.println("4. Salir del navegador.");
                System.out.print("Seleccione una opción: ");
                
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese la URL de la página a visitar: ");
                        String pagina = scanner.nextLine();
                        agregarPagina(pagina);
                        System.out.println("Se ha visitado la página: " + pagina);
                        break;
                    case 2:
                        try {
                            String paginaAnterior = retroceder();
                            System.out.println("Retrocediendo a la página anterior: " + paginaAnterior);
                        } catch (EmptyStackException e) {
                            System.out.println("No hay páginas para retroceder.");
                        }
                        break;
                    case 3:
                        imprimirHistorial();
                        break;
                    case 4:
                        continuar = false;
                        System.out.println("Saliendo del navegador. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            }
        }
    }

    private static void pop() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
