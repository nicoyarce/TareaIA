package proyecto;

import java.awt.Point;
import java.util.TimerTask;

public class Peaton extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda peaton, celdaMovimiento;
    public Point p1, p2, p3, p4;
    public int npeaton;

    public Peaton(Laberinto laberinto, Point p, int n) {
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(p.x, p.y, laberinto.celdas[p.x][p.y].tipoCelda);
        peaton = new Celda(p.x, p.y, laberinto.celdas[p.x][p.y].tipoCelda);
        laberinto.celdas[peaton.x][peaton.y].tipoCelda = PEATON;
        npeaton = n;
        laberinto.repaint();
    }

    public Peaton(Laberinto laberinto, Point xp, Point yp) {
        p1 = new Point(xp.x, xp.y);
        p2 = new Point(yp.x, xp.y);
        p3 = new Point(yp.x, yp.y);
        p4 = new Point(xp.x, yp.y);
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(p1.x, p1.y, laberinto.celdas[p1.x][p1.y].tipoCelda);
        peaton = new Celda(p1.x, p1.y, PEATON);
    }

    public void moverPeaton() {
        if (celdaMovimiento.x == p2.x && celdaMovimiento.y < p3.y && celdaMovimiento.y >= p2.y) {
            if (noHayPared(celdaMovimiento.x, celdaMovimiento.y + 1)) {
                moverAbajo();
            }
        } else if (celdaMovimiento.x >= p1.x && celdaMovimiento.y == p1.y && celdaMovimiento.x < p2.x) {
            if (noHayPared(celdaMovimiento.x + 1, celdaMovimiento.y)) {
                moverDerecha();
            }
        } else if (celdaMovimiento.x <= p3.x && celdaMovimiento.y == p3.y && celdaMovimiento.x > p4.x) {
            if (noHayPared(celdaMovimiento.x - 1, celdaMovimiento.y)) {
                moverIzquierda();
            }
        } else if (celdaMovimiento.x == p4.x && celdaMovimiento.y <= p4.y && celdaMovimiento.y >= p1.y) {
            if (noHayPared(celdaMovimiento.x, celdaMovimiento.y - 1)) {
                moverArriba();
            }
        }
    }

    public void moverAbajo() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
    }

    public void moverArriba() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
    }

    public void moverDerecha() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
    }

    public void moverIzquierda() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
    }

    public void moverAbajo(Celda micro) {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[micro.x][micro.y - 1 - npeaton].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
        celdaMovimiento.y = micro.y - 1 - npeaton;
        celdaMovimiento.x = micro.x;
        //laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
    }

    public void moverArriba(Celda micro) {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[micro.x][micro.y + 1 + npeaton].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
        celdaMovimiento.y = micro.y + 1 + npeaton;
        celdaMovimiento.x = micro.x;
        //laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;

    }

    public void moverDerecha(Celda micro) {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[micro.x - 1 - npeaton][micro.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda = temp;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
        celdaMovimiento.x = micro.x - 1 - npeaton;
        celdaMovimiento.y = micro.y;
        //laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;

    }

    public void moverIzquierda(Celda micro) {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[micro.x + 1 + npeaton][micro.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
        celdaMovimiento.x = micro.x + 1 + npeaton;
        celdaMovimiento.y = micro.y;
        //laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;

    }

    public boolean noHayPared(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != VEHICULO
                && laberinto.celdas[x][y].tipoCelda != JUGADOR;
    }

    @Override
    public void run() {
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
        moverPeaton();
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
    }
}
