package ia;

import java.awt.Point;
import java.util.TimerTask;

public class Micro extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda micro, celdaMovimiento;
    public Point p1, p2, p3, p4;
    public Peaton[] p;
    public int npeatones;

    public Micro(Laberinto laberinto, Point xp, Point yp) {
        p1 = new Point(xp.x, xp.y);
        p2 = new Point(yp.x, xp.y);
        p3 = new Point(yp.x, yp.y);
        p4 = new Point(xp.x, yp.y);
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(p1.x, p1.y, laberinto.celdas[p1.x][p1.y].tipoCelda);
        micro = new Celda(p1.x, p1.y, MICRO);        
        npeatones=PEATONESMICRO;
        p = new Peaton [PEATONESMICRO];
        for (int i = 1; i <= npeatones; i++) {
            xp.x=xp.x-i;
            p[i-1] = new Peaton(laberinto, xp, yp);
        }
    }

    private void moverVehiculo() {
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
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 0;
        for (int i = 0; i < npeatones; i++) {
            p[i].moverAbajo();
        }        
    }

    private void moverArriba() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 2;
        for (int i = 0; i < npeatones; i++) {
            p[i].moverArriba();
        }
    }

    private void moverDerecha() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 3;
        for (int i = 0; i < npeatones; i++) {
            p[i].moverDerecha();
        }
    }

    private void moverIzquierda() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 1;
        for (int i = 0; i < npeatones; i++) {
            p[i].moverIzquierda();
        }
    }

    @Override
    public void run() {
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
        moverVehiculo();
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
    }

    private boolean noHayPersona(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != JUGADOR
                && laberinto.celdas[x][y].tipoCelda != PEATON
                && laberinto.celdas[x][y].tipoCelda != VEHICULO
                && laberinto.celdas[x][y].tipoCelda != MICRO;

    }
}
