package proyecto;

import inteligencia.*;
import java.awt.Point;
import static proyecto.Constantes.N_CELDAS_ALTO;
import static proyecto.Constantes.N_CELDAS_ANCHO;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Jugador implements Constantes {

    public Laberinto laberinto;
    public Celda jugador, celdaMovimiento;
    public BusquedaAnchura inteligencia;
    public static ArrayList<Portal> portales;
    public static int nCartas;

    public Jugador(Laberinto laberinto, int x, int y, ArrayList<Portal> portales) {
        this.laberinto = laberinto;
        this.portales = portales;
        celdaMovimiento = new Celda(x, y, laberinto.celdas[x][y].tipoCelda);
        jugador = new Celda(x, y, JUGADOR);
        laberinto.celdas[jugador.x][jugador.y].tipoCelda = JUGADOR;
        inteligencia = new BusquedaAnchura(laberinto, this);
        nCartas = NCARTAS;
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

    public boolean moverCeldaArriba() {
        if (jugador.y > 0) {
            if (noHayPared(jugador.x, jugador.y - 1) && noVieneVehiculo(jugador.x, jugador.y, 'U')) {
                avanzar(jugador.x, jugador.y - 1, 'U');
                return true;
            }
        }
        return false;
    }

    public boolean moverCeldaAbajo() {
        if (jugador.y + 1 < N_CELDAS_ALTO) {
            if (noHayPared(jugador.x, jugador.y + 1) && noVieneVehiculo(jugador.x, jugador.y, 'D')) {
                avanzar(jugador.x, jugador.y + 1, 'D');
                return true;
            }
        }
        return false;
    }

    public boolean moverCeldaIzquierda() {
        if (jugador.x > 0) {
            if (noHayPared(jugador.x - 1, jugador.y) && noVieneVehiculo(jugador.x, jugador.y, 'L')) {
                avanzar(jugador.x - 1, jugador.y, 'L');
                return true;
            }
        }
        return false;
    }

    public boolean moverCeldaDerecha() {
        if (jugador.x + 1 < N_CELDAS_ANCHO) {
            if (noHayPared(jugador.x + 1, jugador.y) && noVieneVehiculo(jugador.x, jugador.y, 'R')) {
                avanzar(jugador.x + 1, jugador.y, 'R');
                return true;
            }
        }
        return false;
    }

    public boolean noHayPared(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != OBSTACULO
                && laberinto.celdas[x][y].tipoCelda != VEHICULO
                && laberinto.celdas[x][y].tipoCelda != PEATON
                && laberinto.celdas[x][y].tipoCelda != MICRO;
    }

    public boolean esPortal(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda == PORTAL;
    }

    private boolean noVieneVehiculo(int x, int y, char mov) {
        if (celdaMovimiento.tipoCelda == CAMINO) {
            switch (mov) {
                case 'D':
                    if (x - 4 > 0 && x + 4 < N_CELDAS_ANCHO && y > 0 && y + 2 < N_CELDAS_ALTO) {
                        if (laberinto.celdas[x][y + 1].tipoCelda == CALLE) {
                            return laberinto.celdas[x + 1][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 3][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 4][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 1][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 3][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 4][y + 2].tipoCelda != VEHICULO;
                        }
                    }
                case 'U':
                    if (x - 4 > 0 && x + 4 < N_CELDAS_ANCHO && y - 2 > 0 && y < N_CELDAS_ALTO) {
                        if (laberinto.celdas[x][y - 1].tipoCelda == CALLE) {
                            return laberinto.celdas[x - 1][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 3][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 4][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 1][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 3][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 4][y - 2].tipoCelda != VEHICULO;
                        }
                    }
                case 'R':
                    if (x > 0 && x + 2 < N_CELDAS_ANCHO && y - 4 > 0 && y + 4 < N_CELDAS_ALTO) {
                        if (laberinto.celdas[x + 1][y].tipoCelda == CALLE) {
                            return laberinto.celdas[x + 1][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 1][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 1][y - 3].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 1][y - 4].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 3].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 4].tipoCelda != VEHICULO;
                        }
                    }
                case 'L':
                    if (x - 2 > 0 && x < N_CELDAS_ANCHO && y - 4 > 0 && y + 4 < N_CELDAS_ALTO) {
                        if (laberinto.celdas[x - 1][y].tipoCelda == CALLE) {
                            return laberinto.celdas[x - 1][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 1][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 1][y + 3].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 1][y + 4].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 3].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 4].tipoCelda != VEHICULO;
                        }
                    }
            }
        }
        return true;
    }

    private void avanzar(int x, int y, char mov) {
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
        char temp;
        System.out.println(x + " " + y + " Cartas: " + nCartas);
        switch (mov) {
            case 'D':
                temp = celdaMovimiento.tipoCelda;
                celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                laberinto.celdas[x][y - 1].tipoCelda = temp;
                laberinto.celdas[x][y].tipoCelda = JUGADOR;
                jugador.y = jugador.y + 1;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 0;
                break;
            case 'U':
                entregaCartas();
                if (celdaMovimiento.x == 5 && celdaMovimiento.y == 2 && nCartas == 0) {
                    JOptionPane.showMessageDialog(null, "Cartero en correos, entregas listas");
                }
                temp = celdaMovimiento.tipoCelda;
                celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                laberinto.celdas[x][y + 1].tipoCelda = temp;
                laberinto.celdas[x][y].tipoCelda = JUGADOR;
                jugador.y = jugador.y - 1;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 2;
                break;
            case 'R':
                temp = celdaMovimiento.tipoCelda;
                celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                laberinto.celdas[x - 1][y].tipoCelda = temp;
                laberinto.celdas[x][y].tipoCelda = JUGADOR;
                jugador.x = jugador.x + 1;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 3;
                break;
            case 'L':
                temp = celdaMovimiento.tipoCelda;
                celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                laberinto.celdas[x + 1][y].tipoCelda = temp;
                laberinto.celdas[x][y].tipoCelda = JUGADOR;
                jugador.x = jugador.x - 1;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 1;
                break;
        }
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
    }

    private void entregaCartas() {
        if (esPortal(jugador.x, jugador.y - 1)) {
            for (int i = 0; i < portales.size(); i++) {
                if (portales.get(i).portal.x == jugador.x && portales.get(i).portal.y == jugador.y - 1) {
                    for (int j = 0; j < portales.get(i).nCartas; j++) {
                        if (nCartas == 0) {
                            break;
                        }
                        if (portales.get(i).listo == true) {
                            break;
                        }
                        nCartas--;
                        portales.get(i).listo = true;
                    }
                }
            }
        }
    }
}
