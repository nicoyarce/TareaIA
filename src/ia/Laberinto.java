package ia;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class Laberinto extends JComponent implements Constantes {

    public Celda[][] celdas;
    public Celda celdaMovimiento;

    public Laberinto() {
        celdas = new Celda[N_CELDAS_ANCHO][N_CELDAS_ALTO];
        //inicializar el array de celdas
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j] = new Celda(i + (i * TAMANIO_CELDA), j + (j * TAMANIO_CELDA), i, j, 'V');
            }
        }
        //pongo un edificio
        for (int i = 5; i < 9; i++) {
            for (int j = 5; j < 9; j++) {
                celdas[i][j].tipoCelda = 'E';
            }
        }

        celdaMovimiento = new Celda(0, 0, 0, 0, 'J');
    }

    @Override
    public void update(Graphics g) {
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j].paintComponent(g);
            }
        }
        celdaMovimiento.paintComponent(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        update(g);
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
        if (celdaMovimiento.j > 0) {
            if (noHayPared(celdaMovimiento.i, celdaMovimiento.j - 1)) {
                avanzar(celdaMovimiento.i, celdaMovimiento.j - 1, 'U');
            }
        }
    }

    private void moverCeldaAbajo() {
        if (celdaMovimiento.j + 1 < N_CELDAS_ALTO) {
            if (noHayPared(celdaMovimiento.i, celdaMovimiento.j + 1)) {
                avanzar(celdaMovimiento.i, celdaMovimiento.j + 1, 'D');
            }
        }
    }

    private void moverCeldaIzquierda() {
        if (celdaMovimiento.j > 0) {
            if (noHayPared(celdaMovimiento.i - 1, celdaMovimiento.j)) {
                avanzar(celdaMovimiento.i - 1, celdaMovimiento.j, 'L');
            }
        }
    }

    private void moverCeldaDerecha() {
        if (celdaMovimiento.i + 1 < N_CELDAS_ANCHO) {
            if (noHayPared(celdaMovimiento.i + 1, celdaMovimiento.j)) {
                avanzar(celdaMovimiento.i + 1, celdaMovimiento.j, 'R');
            }
        }
    }

    private boolean noHayPared(int x, int y) {
        return celdas[x][y].tipoCelda != 'E';
    }

    private void avanzar(int x, int y, char mov) {
        switch (mov) {
            case 'D':
                celdaMovimiento.j = celdaMovimiento.j + 1;
                celdaMovimiento.coordenadaY = celdaMovimiento.coordenadaY + TAMANIO_CELDA + 1;
                break;
            case 'U':
                celdaMovimiento.j = celdaMovimiento.j - 1;
                celdaMovimiento.coordenadaY = celdaMovimiento.coordenadaY - TAMANIO_CELDA - 1;
                break;
            case 'R':
                celdaMovimiento.i = celdaMovimiento.i + 1;
                celdaMovimiento.coordenadaX = celdaMovimiento.coordenadaX + TAMANIO_CELDA + 1;
                break;
            case 'L':
                celdaMovimiento.i = celdaMovimiento.i - 1;
                celdaMovimiento.coordenadaX = celdaMovimiento.coordenadaX - TAMANIO_CELDA - 1;
                break;
        }
    }

}
