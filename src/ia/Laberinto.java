package ia;

import java.awt.Graphics;
import javax.swing.JComponent;

public class Laberinto extends JComponent implements Constantes {

    public Celda[][] celdas;
    public Lienzo lienzoPadre;
    public Jugador jugador;

    public Laberinto(Lienzo lienzoPadre) {
        this.lienzoPadre = lienzoPadre;
        celdas = new Celda[N_CELDAS_ANCHO][N_CELDAS_ALTO];
        //inicializar el array de celdas
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j] = new Celda(i + (i * TAMANIO_CELDA), j + (j * TAMANIO_CELDA), CAMINO);
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
        crearEdificio(12, 32, 20, 22);
        crearEdificio(36, 40, 14, 22); //edificio rejas
        crearEdificio(40, 41, 14, 18);

        crearEdificio(36, 37, 9, 10); //obs
        crearEdificio(37, 38, 7, 9); //estatua
        crearEdificio(40, 41, 8, 9); //carro
        crearEdificio(36, 37, 0, 2); //banca
        crearEdificio(39, 41, 0, 2); //fuente

        crearCalle(0, 41, 4, 6);
        crearCalle(9, 41, 11, 13);
        crearCalle(9, 35, 17, 19);
        crearCalle(1, 11, 21, 23);
        crearCalle(35, 36, 4, 6);
        crearCalle(1, 3, 12, 21);
        crearCalle(9, 11, 0, 21);
        crearCalle(33, 35, 0, 23);

        celdas[38][9].tipoCelda = PEATON;
        celdas[13][2].tipoCelda = PORTAL;
        celdas[28][2].tipoCelda = PORTAL;

    }

    @Override
    public void update(Graphics g) {
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j].paintComponent(g);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

    private void crearEdificio(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].tipoCelda = OBSTACULO;
            }
        }
    }

    private void crearCalle(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].tipoCelda = CALLE;
            }
        }
    }
}
