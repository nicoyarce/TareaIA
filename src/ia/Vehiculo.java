package ia;

import java.awt.Point;
import java.util.TimerTask;

public class Vehiculo extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda auto;
    public Point p1, p2, p3, p4;

    public Vehiculo(Laberinto laberinto, Point xp, Point yp) {
        p1 = new Point(xp.x, xp.y);
        p2 = new Point(yp.x, xp.y);
        p3 = new Point(yp.x, yp.y);
        p4 = new Point(xp.x, yp.y);
        this.laberinto = laberinto;
        auto = new Celda(p1.x, p1.y, VEHICULO);
        laberinto.celdas[auto.x][auto.y].tipoCelda = VEHICULO;
    }

    private void moverVehiculo() {
        if (auto.x == p2.x && auto.y < p3.y && auto.y >= p2.y) {
            moverAbajo();
        } else if (auto.x >= p1.x && auto.y == p1.y && auto.x < p2.x) {
            moverDerecha();
        } else if (auto.x <= p3.x && auto.y == p3.y && auto.x > p4.x) {
            moverIzquierda();
        } else if (auto.x == p4.x && auto.y <= p4.y && auto.y >= p1.y) {
            moverArriba();
        }
    }

    private void moverAbajo() {
        laberinto.celdas[auto.x][auto.y].tipoCelda = CAMINO;
        auto.y = auto.y + 1;
        laberinto.celdas[auto.x][auto.y].tipoCelda = VEHICULO;
        laberinto.celdas[auto.x][auto.y].indexSprite = 0;
    }

    private void moverArriba() {
        laberinto.celdas[auto.x][auto.y].tipoCelda = CAMINO;
        auto.y = auto.y - 1;
        laberinto.celdas[auto.x][auto.y].tipoCelda = VEHICULO;
        laberinto.celdas[auto.x][auto.y].indexSprite = 2;
    }

    private void moverDerecha() {
        laberinto.celdas[auto.x][auto.y].tipoCelda = CAMINO;
        auto.x = auto.x + 1;
        laberinto.celdas[auto.x][auto.y].tipoCelda = VEHICULO;
        laberinto.celdas[auto.x][auto.y].indexSprite = 3;
    }

    private void moverIzquierda() {
        laberinto.celdas[auto.x][auto.y].tipoCelda = CAMINO;
        auto.x = auto.x - 1;
        laberinto.celdas[auto.x][auto.y].tipoCelda = VEHICULO;
        laberinto.celdas[auto.x][auto.y].indexSprite = 1;
    }

    @Override
    public void run() {
        moverVehiculo();
        laberinto.lienzoPadre.repaint();
    }
}
