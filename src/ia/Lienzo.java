package ia;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Lienzo extends Canvas implements Constantes {

    public Laberinto laberinto;

    public Lienzo() {
        laberinto = new Laberinto();
        this.setBackground(Color.orange);

        //añadimos el escuchador
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                activarCelda(evt);
                repaint();
            }
        });
        //escuchador eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                laberinto.moverCelda(e);
                repaint();
            }
        });
    }

///metodo llamada la primera vez que se pinta
    @Override
    public void update(Graphics g) {
        laberinto.paintComponent(g);
    }
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    private void activarCelda(MouseEvent evt) {
        for (int i = 0; i < ANCHO_CELDAS; i++) {
            for (int j = 0; j < ALTO_CELDAS; j++) {
                if (laberinto.celdas[i][j].celdaSeleccionada(evt.getX(), evt.getY())) {
                    //Para saber si se pulso
                    if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
                        System.out.println("Boton derecho - Poner obstaculo");
                        laberinto.celdas[i][j].tipo = 'O';
                    } else {
                        System.out.println("Boton izquierdo - Poner adversario");
                        laberinto.celdas[i][j].tipo = 'A';
                    }
                }
            }
        }
    }
}
