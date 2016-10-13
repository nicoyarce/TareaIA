package ia;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Laberinto extends JComponent implements Constantes {

    public Celda[][] celdas;
    public Celda celdaMovimiento;
    public int anchuraLaberinto;
    public int alturaLaberinto;   
    
    public Laberinto() {
        celdas = new Celda[ANCHO_CELDAS][ALTO_CELDAS];
        //inicializar el array de celdas
        for (int i = 0; i < ANCHO_CELDAS; i++) {
            for (int j = 0; j < ALTO_CELDAS; j++) {
                celdas[i][j] = new Celda(i + (i * TAMANIO_CELDA), j + (j * TAMANIO_CELDA), 'V');
            }
        }
        
        //celdaMovimiento = new Celda(11, 11, 'A');
        //celdas[11][11].tipo = 'A';        
        
        for (int i = 5; i < 9; i++) {
            for (int j = 5; j < 9; j++) {
                celdaMovimiento = new Celda(i, j, 'O');
                celdas[i][j].tipo = 'O';
            }
        }
        celdaMovimiento = new Celda(15, 15, 'P');
        celdas[15][15].tipo = 'P';
        
        celdaMovimiento = new Celda(0, 0, 'V');
        celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'J';
        
        //ancho y largo del laberinto
        this.anchuraLaberinto = ANCHO_CELDAS * TAMANIO_CELDA;
        this.alturaLaberinto = ALTO_CELDAS * TAMANIO_CELDA;
        this.setSize(anchuraLaberinto, alturaLaberinto);
    }
    @Override
    public void update(Graphics g){
        for (int i = 0; i < ANCHO_CELDAS; i++) {
            for (int j = 0; j < ALTO_CELDAS; j++) {
                celdas[i][j].paintComponent(g);
            }
        }
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
        if (celdaMovimiento.Y > 0 && celdas[celdaMovimiento.X][celdaMovimiento.Y - 1].tipo !='O') {
            celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'V';
            celdaMovimiento.Y = celdaMovimiento.Y - 1;
            celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'J';
        }else if(celdas[celdaMovimiento.X][celdaMovimiento.Y - 1].tipo =='O'){
            JOptionPane.showMessageDialog(null, "Choque");
        }
    }

    private void moverCeldaAbajo() {
        if (celdaMovimiento.Y < ALTO_CELDAS - 1 && celdas[celdaMovimiento.X][celdaMovimiento.Y + 1].tipo !='O') {
            celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'V';
            celdaMovimiento.Y = celdaMovimiento.Y + 1;
            celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'J';
        }else if(celdas[celdaMovimiento.X][celdaMovimiento.Y + 1].tipo =='O'){
            JOptionPane.showMessageDialog(null, "Choque");
        }
    }

    private void moverCeldaIzquierda() {
        if (celdaMovimiento.X > 0 && celdas[celdaMovimiento.X - 1][celdaMovimiento.Y].tipo !='O') {
            celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'V';
            celdaMovimiento.X = celdaMovimiento.X - 1;
            celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'J';
        }else if(celdas[celdaMovimiento.X - 1][celdaMovimiento.Y].tipo =='O'){
            JOptionPane.showMessageDialog(null, "Choque");
        }
    }

    private void moverCeldaDerecha() {
        if (celdaMovimiento.X < ANCHO_CELDAS - 1 && celdas[celdaMovimiento.X + 1][celdaMovimiento.Y].tipo !='O') {
            celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'V';
            celdaMovimiento.X = celdaMovimiento.X + 1;
            celdas[celdaMovimiento.X][celdaMovimiento.Y].tipo = 'J';
        }else if(celdas[celdaMovimiento.X + 1][celdaMovimiento.Y].tipo == 'O'){
            JOptionPane.showMessageDialog(null, "Choque");
        }
    }
}
