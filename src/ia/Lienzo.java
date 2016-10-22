package ia;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes {

    public Laberinto laberinto;
    public Image fondo;

    public Lienzo() {
        laberinto = new Laberinto();
        try {
            fondo = ImageIO.read(new File("images/fondo.jpg"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        //escuchador eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                laberinto.moverCelda(e);
                repaint();
            }
        });
    }

    //metodo llamada la primera vez que se pinta
    @Override
    public void update(Graphics g) {
        g.drawImage(fondo,0,0, null);
        laberinto.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }
}
