package ia;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class Laberinto extends JComponent implements Constantes {

    public Celda[][] celdas;
    public Celda celdaMovimiento;
    public Lienzo lienzoPadre;

    public Laberinto(Lienzo lienzoPadre) {
        this.lienzoPadre = lienzoPadre;
        celdas = new Celda[N_CELDAS_ANCHO][N_CELDAS_ALTO];
        //inicializar el array de celdas
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j] = new Celda(i + (i * TAMANIO_CELDA), j + (j * TAMANIO_CELDA), i, j, 'V');
            }
        }
        //pongo unos edificios
        crearEdificio(1, 2, 2, 3); //obs
        crearEdificio(2, 8, 0, 3); //edificio1
        crearEdificio(2, 8, 3, 3);
        crearEdificio(12, 31, 0, 3);
        crearEdificio(31, 32, 2, 3); //obs
        crearEdificio(0, 8, 7, 11);
        crearEdificio(1, 3, 11, 12); //conos
        crearEdificio(12, 30, 7, 10);
        crearEdificio(12, 30, 7, 10);
        crearEdificio(4, 5, 19, 20); //grifo
        crearEdificio(4, 8, 11, 19);
        crearEdificio(13, 32, 14, 16);
        crearEdificio(12, 13, 14, 15); //obs
        crearEdificio(12, 32, 20, 23);
        crearEdificio(36, 40, 14, 23); //edificio rejas
        crearEdificio(40, 41, 14, 18);

        crearEdificio(36, 37, 9, 10); //obs
        crearEdificio(37, 38, 7, 9); //estatua
        crearEdificio(40, 41, 8, 9); //carro
        crearEdificio(36, 37, 0, 2); //banca
        crearEdificio(39, 41, 0, 2); //fuente

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
        if (celdaMovimiento.j < N_CELDAS_ALTO - 3) {
            if (noHayPared(celdaMovimiento.i, celdaMovimiento.j + 1)) {
                avanzar(celdaMovimiento.i, celdaMovimiento.j + 1, 'D');
            }
        }
    }

    private void moverCeldaIzquierda() {
        if (celdaMovimiento.i > 0) {
            if (noHayPared(celdaMovimiento.i - 1, celdaMovimiento.j)) {
                avanzar(celdaMovimiento.i - 1, celdaMovimiento.j, 'L');
            }
        }
    }

    private void moverCeldaDerecha() {
        if (celdaMovimiento.i < N_CELDAS_ANCHO - 3) {
            if (noHayPared(celdaMovimiento.i + 1, celdaMovimiento.j)) {
                avanzar(celdaMovimiento.i + 1, celdaMovimiento.j, 'R');
            }
        }
    }

    private boolean noHayPared(int x, int y) {
        return celdas[x][y].tipoCelda != 'O';
    }

    private void avanzar(int x, int y, char mov) {

        System.out.println(x + " " + y);
        switch (mov) {
            case 'D':
                celdaMovimiento.j = celdaMovimiento.j + 1;
                celdaMovimiento.coordenadaY = celdaMovimiento.coordenadaY + TAMANIO_CELDA + 1;
                celdas[celdaMovimiento.i][celdaMovimiento.j].indexSprite = 0;
                break;
            case 'U':
                celdaMovimiento.j = celdaMovimiento.j - 1;
                celdaMovimiento.coordenadaY = celdaMovimiento.coordenadaY - TAMANIO_CELDA - 1;
                celdas[celdaMovimiento.i][celdaMovimiento.j].indexSprite = 1;
                break;
            case 'R':
                celdaMovimiento.i = celdaMovimiento.i + 1;
                celdaMovimiento.coordenadaX = celdaMovimiento.coordenadaX + TAMANIO_CELDA + 1;
                celdas[celdaMovimiento.i][celdaMovimiento.j].indexSprite = 3;
                break;
            case 'L':
                celdaMovimiento.i = celdaMovimiento.i - 1;
                celdaMovimiento.coordenadaX = celdaMovimiento.coordenadaX - TAMANIO_CELDA - 1;
                celdas[celdaMovimiento.i][celdaMovimiento.j].indexSprite = 2;
                break;
        }
    }

    private void crearEdificio(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].tipoCelda = 'O';
            }
        }
    }
}
