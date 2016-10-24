package ia;

import java.util.TimerTask;

public class Vehiculo extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda auto;

    public Vehiculo(Laberinto laberinto, int x, int y) {
        this.laberinto = laberinto;
        auto = new Celda(x, y, 0, 0, 'H');
        laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'H';
    }

    private void moverVehiculoIzq() {
        if (auto.coordenadaX > 0) {
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'V';
            auto.coordenadaX = auto.coordenadaX - 1;
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'H';
        } else {
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'V';
            auto.coordenadaX = N_CELDAS_ANCHO - 1;
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'H';
        }
    }

    private void moverVehiculoArr() {
        if (auto.coordenadaY > 0) {
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'V';
            auto.coordenadaY = auto.coordenadaY - 1;
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'H';
        } else {
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'V';
            auto.coordenadaY = N_CELDAS_ALTO - 1;
            laberinto.celdas[auto.coordenadaX][auto.coordenadaY].tipoCelda = 'H';
        }
    }

    @Override
    public void run() {
        moverVehiculoIzq();
        //moverVehiculoArr();
        laberinto.lienzoPadre.repaint();
    }
}
