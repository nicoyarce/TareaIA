package proyecto;

import java.awt.Color;
import java.util.Random;

public interface Constantes {

    public final int TAMANIO_CELDA = 31;
    public final int N_CELDAS_ANCHO = 41;
    public final int N_CELDAS_ALTO = 23;
    public final int SIZE_WIDTH = 16 + (TAMANIO_CELDA + 1) * N_CELDAS_ANCHO;
    public final int SIZE_HEIGHT = 9 + (TAMANIO_CELDA + 1) * (N_CELDAS_ALTO + 1);

    //Para manejar los tipos de celdas
    public final char JUGADOR = 'J';
    public final char CAMINO = 'V';
    public final char OBSTACULO = 'O';
    public final char VEHICULO = 'H';
    public final char MICRO = 'M';
    public final char PORTAL = 'R';
    //nuevas constantes
    public final char EDIFICIO = 'E';
    public final char PEATON = 'P';
    public final char CALLE = 'C';
    public final char PARADA = 'D';
    public final char CARTA = 'A';

    public final int NPEATONES = 2;

    public final int NPORTALES = 2;

    public final int NCARTAS = 3;

    public final int ALFA = 150;
    public final Color COLORGRIS = new Color(192, 192, 192, ALFA);
    public final Color COLORAMARILLO = new Color(255, 255, 128, ALFA);
    public final Color COLORAZUL = new Color(0, 191, 255, ALFA);

    default int numeroAleatorio(int minimo, int maximo) {
        Random random = new Random();
        int numero_aleatorio = random.nextInt((maximo - minimo) + 1) + minimo;
        return numero_aleatorio;
    }
}
