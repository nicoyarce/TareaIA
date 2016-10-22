package ia;

public interface Constantes {
    public final int TAMANIO_CELDA = 32;
    public final int N_CELDAS_ANCHO = 40;
    public final int N_CELDAS_ALTO = 30;
    public final int SIZE_WIDTH = TAMANIO_CELDA * N_CELDAS_ANCHO;
    public final int SIZE_HEIGHT = TAMANIO_CELDA * N_CELDAS_ALTO;

    //Para manejar los tipos de celdas
    public final char JUGADOR = 'J';
    public final char CAMINO = 'V';
    public final char OBSTACULO = 'O';
    public final char ADVERSARIO = 'A';
    //public final char PORTAL = 'P';
    //nuevas constantes
    public final char E = 'E';
    public final char A = 'A';
    public final char C = 'C';
}
