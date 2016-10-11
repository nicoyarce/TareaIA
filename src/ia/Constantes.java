package ia;

public interface Constantes {
    public final int TAMANIO_CELDA = 32;
    public final int ANCHO_CELDAS = 41;
    public final int ALTO_CELDAS = 20;
    public final int SIZE_WIDTH = TAMANIO_CELDA * ANCHO_CELDAS;
    public final int SIZE_HEIGHT = TAMANIO_CELDA * ALTO_CELDAS;

    //Para manejar los tipos de celdas
    public final char JUGADOR = 'J';
    public final char CAMINO = 'V';
    public final char OBSTACULO = 'O';
    public final char ADVERSARIO = 'A';
    public final char PORTAL = 'P';
}
