package ia;

import java.util.TimerTask;

public class Peaton extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda peaton;

    public Peaton(Laberinto laberinto, int x, int y) {
        this.laberinto = laberinto;
        peaton = new Celda(x, y, PEATON);
        laberinto.celdas[peaton.x][peaton.y].tipoCelda = PEATON;
    }

    private void moverPeaton() {

    }

    @Override
    public void run() {
        moverPeaton();
        laberinto.lienzoPadre.repaint();
    }
}
