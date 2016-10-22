/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.TimerTask;

public class Jugador extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda jugador;
    public int direccion;//para saber hacia donde me muevo

    public Jugador(Laberinto laberinto) {
        this.laberinto = laberinto;
        jugador = new Celda(0, numeroAleatorio(0, N_CELDAS_ALTO - 1), 0, 0, 'J');
        laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'J';
        direccion = 0;//mover derecha
    }

    public void moverJugadorDerecha() {
        if (jugador.coordenadaX < N_CELDAS_ANCHO - 1) {
            //si al mover a la derecha no es adversario
            //me muevo normal
            if (laberinto.celdas[jugador.coordenadaX + 1][jugador.coordenadaY].tipoCelda != 'A') {
                laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'V';
                jugador.coordenadaX = jugador.coordenadaX + 1;
                laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'J';
                //coordenadaY si no cambio de direccion a la izquierda => direccion=1
            } else {
                direccion = 1;
            }
        } else {
            //para re-aparecer
            laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'V';
            jugador.coordenadaX = 0;
            jugador.coordenadaY = numeroAleatorio(0, N_CELDAS_ALTO - 1);
            laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'J';
        }
    }

    public void moverJugadorIzquierda() {
        //si no estocoordenadaY al inicio
        if (jugador.coordenadaX > 0) {
            //compruebo que al moverme a la izquierda no
            //este el adversario
            if (laberinto.celdas[jugador.coordenadaX - 1][jugador.coordenadaY].tipoCelda != 'A') {
                laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'V';
                jugador.coordenadaX = jugador.coordenadaX - 1;
                laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'J';
                //en caso contrario cambio de direccion
            } else {
                direccion = 0;
            }
        } else {
            laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'V';
            jugador.coordenadaX = N_CELDAS_ANCHO - 1;
            jugador.coordenadaY = numeroAleatorio(0, N_CELDAS_ALTO - 1);
            laberinto.celdas[jugador.coordenadaX][jugador.coordenadaY].tipoCelda = 'J';
        }
    }

    @Override
    public void run() {
        if (direccion == 0) {
            moverJugadorDerecha();
        } else {
            moverJugadorIzquierda();
        }
        laberinto.lienzoPadre.repaint();
    }
}
