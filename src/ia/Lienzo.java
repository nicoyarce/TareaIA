package ia;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes {

    public Laberinto laberinto;
    public Image fondo;
    public Vehiculo auto, auto2;
    public Jugador jugador;
    public Timer lanzadorTareas;

    public Lienzo() {
        laberinto = new Laberinto(this);
        auto = new Vehiculo(laberinto);
        auto2 = new Vehiculo(laberinto);
        //jugador = new Jugador(laberinto);
        try {
            fondo = ImageIO.read(new File("images/fondo.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        this.setSize(N_CELDAS_ANCHO, N_CELDAS_ALTO);

        //escuchador eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                laberinto.moverCelda(e);
                repaint();
            }
        });
        lanzadorTareas = new Timer();
        lanzadorTareas.scheduleAtFixedRate(auto, 0, 1000);
        lanzadorTareas.scheduleAtFixedRate(auto2, 0, 500);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(fondo, 0, 0, null);
        laberinto.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }
}
