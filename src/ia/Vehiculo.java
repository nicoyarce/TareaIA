package ia;

import java.util.TimerTask;

public class Vehiculo extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda auto;

    public Vehiculo(Laberinto laberinto) {
        this.laberinto = laberinto;
        auto = new Celda(N_CELDAS_ANCHO - 1, numeroAleatorio(0, N_CELDAS_ALTO - 1), 0, 0, 'A');
        laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'A';
    }

    public void moverAdversario() {
        if (auto.coordenadaX > 0) {
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'V';
            auto.coordenadaX = auto.coordenadaX - 1;
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'A';
        } else {
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'V';
            auto.coordenadaX = N_CELDAS_ANCHO - 1;
            auto.coordenadaY = numeroAleatorio(0, N_CELDAS_ALTO - 1);
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'A';
        }
    }

    @Override
    public void run() {
        moverAdversario();
        laberinto.lienzoPadre.repaint();
    }
}
