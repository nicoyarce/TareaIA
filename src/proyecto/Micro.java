package proyecto;

import java.awt.Point;
import java.util.ArrayList;
import java.util.TimerTask;

public class Micro extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda micro, celdaMovimiento;
    public Point p1, p2, p3, p4;
    public ArrayList<Peaton> pasajeros = new ArrayList<>();

    public Micro(Laberinto laberinto, Point xp, Point yp) {
        p1 = new Point(xp.x, xp.y);
        p2 = new Point(yp.x, xp.y);
        p3 = new Point(yp.x, yp.y);
        p4 = new Point(xp.x, yp.y);
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(p1.x, p1.y, laberinto.celdas[p1.x][p1.y].tipoCelda);
        micro = new Celda(celdaMovimiento.x, celdaMovimiento.y, MICRO);

        for (int i = 0; i < NPEATONES; i++) {
            xp.x--;
            pasajeros.add(new Peaton(laberinto, xp, p1, yp));
        }
        laberinto.repaint();
    }

    private void moverMicro() {
        if (celdaMovimiento.x == p2.x && celdaMovimiento.y < p3.y && celdaMovimiento.y >= p2.y) {
            if (noHayPersona(celdaMovimiento.x, celdaMovimiento.y + 1)) {
                moverAbajo();
            }
        } else if (celdaMovimiento.x >= p1.x && celdaMovimiento.y == p1.y && celdaMovimiento.x < p2.x) {
            if (noHayPersona(celdaMovimiento.x + 1, celdaMovimiento.y)) {
                moverDerecha();
            }
        } else if (celdaMovimiento.x <= p3.x && celdaMovimiento.y == p3.y && celdaMovimiento.x > p4.x) {
            if (noHayPersona(celdaMovimiento.x - 1, celdaMovimiento.y)) {
                moverIzquierda();
            }
        } else if (celdaMovimiento.x == p4.x && celdaMovimiento.y <= p4.y && celdaMovimiento.y >= p1.y) {
            if (noHayPersona(celdaMovimiento.x, celdaMovimiento.y - 1)) {
                moverArriba();
            }
        }
    }

    private void moverAbajo() {
        if (celdaMovimiento.y < N_CELDAS_ALTO - 1 && noHayPersona(celdaMovimiento.x, celdaMovimiento.y + 1)) {
            char t = celdaMovimiento.tipoCelda;
            celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = t;

            celdaMovimiento.y = celdaMovimiento.y + 1;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;

        }
    }

    private void moverDerecha() {
        if (celdaMovimiento.x < N_CELDAS_ANCHO && noHayPersona(celdaMovimiento.x + 1, celdaMovimiento.y)) {
            char t = celdaMovimiento.tipoCelda;
            celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipoCelda;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = t;
            celdaMovimiento.x = celdaMovimiento.x + 1;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;

        }
    }

    private void moverIzquierda() {
        if (celdaMovimiento.x > 0 && noHayPersona(celdaMovimiento.x - 1, celdaMovimiento.y)) {

            char t = celdaMovimiento.tipoCelda;
            celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = t;

            celdaMovimiento.x = celdaMovimiento.x - 1;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;

        }
    }

    private void moverArriba() {
        if (celdaMovimiento.y > 0 && noHayPersona(celdaMovimiento.x, celdaMovimiento.y - 1)) {
            char t = celdaMovimiento.tipoCelda;
            celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;

            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = t;

            celdaMovimiento.y = celdaMovimiento.y - 1;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;

        }
    }

    @Override
    public void run() {
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
        moverMicro();
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
        for (int i = 0; i < NPEATONES; i++) {
            pasajeros.get(i).moverPeaton();
        }
    }

    private boolean noHayPersona(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != JUGADOR
                && laberinto.celdas[x][y].tipoCelda != PEATON
                && laberinto.celdas[x][y].tipoCelda != VEHICULO
                && laberinto.celdas[x][y].tipoCelda != MICRO
                && laberinto.celdas[x][y].tipoCelda != CARTA;

    }
}
