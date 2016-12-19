package proyecto;

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

        crearEdificio(2, 8, 0, 3); //edificio0
        crearPeatones(0, 9, 0, 4);
        crearEdificio(12, 20, 0, 3); //edificio1
        crearPeatones(11, 21, 0, 4);
        crearEdificio(24, 32, 0, 3); //edificio2
        crearPeatones(23, 33, 0, 4);
        crearEdificio(4, 8, 7, 19); //construccion y edificio abajo
        crearPeatones(3, 9, 6, 21);
        crearEdificio(12, 20, 7, 10); //edificio3
        crearPeatones(11, 21, 6, 11);
        crearEdificio(24, 32, 7, 10); //edificio4
        crearPeatones(23, 33, 6, 11);
        crearEdificio(4, 5, 19, 20); //grifo
        crearEdificio(12, 20, 14, 16); //edificio5
        crearPeatones(11, 21, 13, 17);
        crearEdificio(24, 32, 14, 16); //edificio6
        crearPeatones(23, 33, 13, 17);
        crearEdificio(24, 32, 14, 16); //obs
        crearEdificio(12, 20, 20, 22); //edificio7
        crearPeatones(11, 21, 19, 23);
        crearEdificio(24, 32, 20, 22); //edificio8
        crearPeatones(23, 33, 19, 23);
        crearEdificio(36, 40, 14, 22); //edificio rejas
        crearEdificio(40, 41, 14, 18);  //puerta edificio rejas
        crearPeatones(35, 41, 13, 23);
        crearPeatones(35, 41, 0, 4);
        crearPeatones(35, 41, 6, 11);

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
        crearCalle(1, 3, 6, 21);
        crearCalle(9, 11, 0, 21);
        crearCalle(33, 35, 0, 23);
        crearCalle(21, 23, 0, 23);

        celdas[5][2].tipoCelda = PORTAL; //correo
        celdas[6][18].tipoCelda = PORTAL;
        celdas[38][9].tipoCelda = PEATON;
        celdas[13][2].tipoCelda = PORTAL;
        celdas[16][2].tipoCelda = PORTAL;
        celdas[25][2].tipoCelda = PORTAL;
        celdas[28][2].tipoCelda = PORTAL;
        celdas[15][9].tipoCelda = PORTAL;
        celdas[25][9].tipoCelda = PORTAL;
        celdas[28][9].tipoCelda = PORTAL;
        celdas[15][15].tipoCelda = PORTAL;
        celdas[18][15].tipoCelda = PORTAL;
        celdas[27][15].tipoCelda = PORTAL;
        celdas[30][15].tipoCelda = PORTAL;
        celdas[13][21].tipoCelda = PORTAL;
        celdas[16][21].tipoCelda = PORTAL;
        celdas[28][21].tipoCelda = PORTAL;
        celdas[40][17].tipoCelda = PORTAL;

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

    private void crearPeatones(int x1, int x2, int y1, int y2) {
        int random = (int) Math.floor(Math.random() * (101));
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].npeatones = random;
            }
        }
    }

    private void crearCalle(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].tipoCelda = CALLE;
                celdas[i][j].npeatones = 200;
            }
        }
    }
}
