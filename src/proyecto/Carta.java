/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.awt.Point;

public class Carta implements Constantes {

    public Laberinto laberinto;
    public Celda carta, celdaMovimiento;
    public Point xp;

    public Carta(Laberinto laberinto, Point xp) {
        this.xp = new Point(xp.x, xp.y);
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(this.xp.x, this.xp.y, laberinto.celdas[this.xp.x][this.xp.y].tipoCelda);
        carta = new Celda(this.xp.x, this.xp.y, CARTA);
        laberinto.celdas[carta.x][carta.y].tipoCelda = CARTA;
    }

    public void moverCarta(char mov) {
        switch (mov) {
            case 'D':
                moverAbajo();
                break;
            case 'U':
                moverArriba();
                break;
            case 'R':
                moverDerecha();
                break;
            case 'L':
                moverIzquierda();
                break;
        }
    }

    public void moverAbajo() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = CARTA;
    }

    public void moverArriba() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = CARTA;
    }

    public void moverDerecha() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = CARTA;
    }

    public void moverIzquierda() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = CARTA;
    }

    public boolean noHayPared(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != VEHICULO
                && laberinto.celdas[x][y].tipoCelda != JUGADOR
                && laberinto.celdas[x][y].tipoCelda != PEATON
                && laberinto.celdas[x][y].tipoCelda != MICRO
                && laberinto.celdas[x][y].tipoCelda != CARTA;
    }

}
