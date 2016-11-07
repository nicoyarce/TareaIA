package ia;

import static ia.Constantes.N_CELDAS_ALTO;
import static ia.Constantes.N_CELDAS_ANCHO;
import static ia.Constantes.TAMANIO_CELDA;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

public class Jugador implements Constantes {

    public Laberinto laberinto;
    public Celda jugador;

    public Jugador(Laberinto laberinto) {
        this.laberinto = laberinto;
        jugador = new Celda(0, 0, JUGADOR);
        laberinto.celdas[jugador.x][jugador.y].tipoCelda = JUGADOR;
    }

    void moverCelda(KeyEvent evento) {
        switch (evento.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Mover arriba");
                moverCeldaArriba();
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Mover abajo");
                moverCeldaAbajo();
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Mover izquierda");
                moverCeldaIzquierda();
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Mover derecha");
                moverCeldaDerecha();
                break;
        }
    }

    private void moverCeldaArriba() {
        if (jugador.y > 0) {
            if (noHayPared(jugador.x, jugador.y - 1) && noVieneVehiculo(jugador.x, jugador.y, 'U')) {
                avanzar(jugador.x, jugador.y - 1, 'U');
            }
        }
    }

    private void moverCeldaAbajo() {
        if (jugador.y + 1 < N_CELDAS_ALTO) {
            if (noHayPared(jugador.x, jugador.y + 1) && noVieneVehiculo(jugador.x, jugador.y, 'D')) {
                avanzar(jugador.x, jugador.y + 1, 'D');
            }
        }
    }

    private void moverCeldaIzquierda() {
        if (jugador.x > 0) {
            if (noHayPared(jugador.x - 1, jugador.y) && noVieneVehiculo(jugador.x, jugador.y, 'L')) {
                avanzar(jugador.x - 1, jugador.y, 'L');
            }
        }
    }

    private void moverCeldaDerecha() {
        if (jugador.x + 1 < N_CELDAS_ANCHO) {
            if (noHayPared(jugador.x + 1, jugador.y) && noVieneVehiculo(jugador.x, jugador.y, 'R')) {
                avanzar(jugador.x + 1, jugador.y, 'R');
            }
        }
    }

    private boolean noHayPared(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != OBSTACULO
                && laberinto.celdas[x][y].tipoCelda != VEHICULO;
    }

    private boolean noVieneVehiculo(int x, int y, char mov) {
        switch (mov) {
            case 'D':
                if (x - 1 > 0 && x + 3 < N_CELDAS_ANCHO && y - 2 > 0 && y + 2 < N_CELDAS_ALTO) {
                    if (laberinto.celdas[x][y + 1].tipoCelda == PASO) {
                        System.out.println("Paso de cebra al frente");
                        return laberinto.celdas[x + 1][y + 1].tipoCelda != VEHICULO
                                && laberinto.celdas[x - 1][y + 2].tipoCelda != VEHICULO
                                && laberinto.celdas[x + 2][y + 1].tipoCelda != VEHICULO
                                && laberinto.celdas[x + 3][y + 1].tipoCelda != VEHICULO;
                    }
                }
            case 'U':
                if (x - 3 > 0 && x + 2 < N_CELDAS_ANCHO && y - 2 > 0 && y + 2 < N_CELDAS_ALTO) {
                    if (laberinto.celdas[x][y - 1].tipoCelda == PASO) {
                        System.out.println("Paso de cebra al frente");
                        return laberinto.celdas[x - 1][y - 1].tipoCelda != VEHICULO
                                && laberinto.celdas[x + 1][y - 2].tipoCelda != VEHICULO
                                && laberinto.celdas[x - 2][y - 1].tipoCelda != VEHICULO
                                && laberinto.celdas[x - 3][y - 1].tipoCelda != VEHICULO;
                    }
                }
            case 'R':
                if (x - 2 > 0 && x + 2 < N_CELDAS_ANCHO && y - 3 > 0 && y + 2 < N_CELDAS_ALTO) {
                    if (laberinto.celdas[x + 1][y].tipoCelda == PASO) {
                        System.out.println("Paso de cebra al frente");
                        return laberinto.celdas[x + 1][y - 1].tipoCelda != VEHICULO
                                && laberinto.celdas[x + 2][y + 1].tipoCelda != VEHICULO
                                && laberinto.celdas[x + 1][y - 2].tipoCelda != VEHICULO
                                && laberinto.celdas[x + 1][y - 3].tipoCelda != VEHICULO;
                    }
                }
            case 'L':
                if (x - 2 > 0 && x + 2 < N_CELDAS_ANCHO && y - 2 > 0 && y + 3 < N_CELDAS_ALTO) {
                    if (laberinto.celdas[x - 1][y].tipoCelda == PASO) {
                        System.out.println("Paso de cebra al frente");
                        return laberinto.celdas[x - 1][y + 1].tipoCelda != VEHICULO
                                && laberinto.celdas[x - 2][y - 1].tipoCelda != VEHICULO
                                && laberinto.celdas[x - 1][y + 2].tipoCelda != VEHICULO
                                && laberinto.celdas[x - 1][y + 3].tipoCelda != VEHICULO;
                    }
                }
        }
        return true;
    }

    private void avanzar(int x, int y, char mov) {
        char temp;
        temp = laberinto.celdas[x][y].tipoCelda;
        System.out.println(x + " " + y);
        switch (mov) {
            case 'D':
                laberinto.celdas[jugador.x][jugador.y].tipoCelda = temp;
                //laberinto.celdas[jugador.x][jugador.y].tipoCelda = CAMINO;
                jugador.y = jugador.y + 1;
                laberinto.celdas[jugador.x][jugador.y].tipoCelda = JUGADOR;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 0;
                break;
            case 'U':
                laberinto.celdas[jugador.x][jugador.y].tipoCelda = temp;
                //laberinto.celdas[jugador.x][jugador.y].tipoCelda = CAMINO;
                jugador.y = jugador.y - 1;
                laberinto.celdas[jugador.x][jugador.y].tipoCelda = JUGADOR;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 2;
                break;
            case 'R':
                laberinto.celdas[jugador.x][jugador.y].tipoCelda = temp;
                //laberinto.celdas[jugador.x][jugador.y].tipoCelda = CAMINO;
                jugador.x = jugador.x + 1;
                laberinto.celdas[jugador.x][jugador.y].tipoCelda = JUGADOR;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 3;
                break;
            case 'L':
                laberinto.celdas[jugador.x][jugador.y].tipoCelda = temp;
                //laberinto.celdas[jugador.x][jugador.y].tipoCelda = CAMINO;
                jugador.x = jugador.x - 1;
                laberinto.celdas[jugador.x][jugador.y].tipoCelda = JUGADOR;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 1;
                break;
        }
    }

    /*@Override
    public void run() {
        laberinto.lienzoPadre.repaint();
    }*/
}
