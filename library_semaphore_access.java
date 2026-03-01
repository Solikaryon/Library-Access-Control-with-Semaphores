import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Biblioteca {
    
    private final Semaphore aforo; // Controla el número máximo de personas dentro
    private int personasDentro = 0; // Contador de personas actualmente dentro
    
    public Biblioteca(int maxPersonas) {
        aforo = new Semaphore(maxPersonas, true); // true => acceso justo (FIFO)
    }

    // Método para intentar entrar
    public void entrar(String nombreVisitante) {
        try {
            System.out.println(nombreVisitante + " pulsa el botón de entrada...");
            if (!aforo.tryAcquire()) {
                System.out.println("ESPERA: " + nombreVisitante + " debe esperar. La sala está llena.");
                aforo.acquire(); // Espera hasta que alguien salga
            }
            
            synchronized (this) {
                personasDentro++;
                System.out.println("ENTRO: " + nombreVisitante + " ha entrado. Personas dentro: " + personasDentro);
                mostrarEstado();
            }
            
            // Simula el tiempo que el visitante permanece dentro
            Thread.sleep((long) (Math.random() * 4000 + 2000));
            
            salir(nombreVisitante);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para salir
    public void salir(String nombreVisitante) {
        synchronized (this) {
            personasDentro--;
            System.out.println("SALIO: " + nombreVisitante + " ha salido. Personas dentro: " + personasDentro);
            mostrarEstado();
        }
        aforo.release(); // Libera un permiso (espacio libre)
    }

    // Muestra un "letrero" visual del estado
    private void mostrarEstado() {
        if (aforo.availablePermits() == 0) {
            System.out.println("LLENO Letrero: SALA LLENA. Espere por favor...");
        } else {
            System.out.println("DISPONIBLE Letrero: HAY ESPACIO DISPONIBLE. Puede entrar.");
        }
        System.out.println("-------------------------------------------");
    }

    // Clase interna para simular un visitante
    static class Visitante extends Thread {
        private final Biblioteca biblioteca;
        private final String nombre;
        
        public Visitante(Biblioteca biblioteca, String nombre) {
            this.biblioteca = biblioteca;
            this.nombre = nombre;
        }
        
        @Override
        public void run() {
            biblioteca.entrar(nombre);
        }
    }

    // Método main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Ingrese el número máximo de personas permitidas en la biblioteca: ");
        int maxPersonas = sc.nextInt();
        
        System.out.print("Ingrese el número total de visitantes a simular: ");
        int totalVisitantes = sc.nextInt();
        
        Biblioteca biblioteca = new Biblioteca(maxPersonas);
        
        System.out.println("\n--- INICIANDO SIMULACIÓN ---\n");
        
        for (int i = 1; i <= totalVisitantes; i++) {
            Visitante v = new Visitante(biblioteca, "Visitante-" + i);
            v.start();
            try {
                Thread.sleep((long) (Math.random() * 1500)); // Pequeño retraso entre visitantes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
