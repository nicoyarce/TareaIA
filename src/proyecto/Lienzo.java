package proyecto;

import inteligencia.Estado;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes {

    public Laberinto laberinto;
    public Image fondo;
    public Vehiculo auto1, auto2, auto3, auto4;
    public Peaton peaton, peaton2;
    public Jugador jugador;
    public Micro micro;
    public ArrayList<Portal> portales;
    public Timer lanzadorTareas;
    public Graphics graficoBuffer;
    public Image imagenBuffer;

    public Lienzo() {
        Point p1 = new Point(2, 5);
        Point p2 = new Point(9, 21);
        Point p3 = new Point(10, 5);
        Point p4 = new Point(21, 11);
        Point p5 = new Point(10, 12);
        Point p6 = new Point(21, 17);
        Point p7 = new Point(22, 12);
        Point p8 = new Point(33, 17);

        Point p9 = new Point(11, 13);
        Point p10 = new Point(32, 19);

        Point p11 = new Point(22, 5);
        Point p12 = new Point(33, 11);

        laberinto = new Laberinto(this);
        auto1 = new Vehiculo(laberinto, p1, p2);
        auto2 = new Vehiculo(laberinto, p3, p4);
        auto3 = new Vehiculo(laberinto, p5, p6);
        auto4 = new Vehiculo(laberinto, p7, p8);

        peaton = new Peaton(laberinto, p9, p10);

        micro = new Micro(laberinto, p11, p12);

        portales = new ArrayList<>();
        jugador = new Jugador(laberinto, 1, 1, portales);

        crearPortales();

        try {
            fondo = ImageIO.read(new File("images/fondo.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        //escuchador eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                jugador.moverCelda(e);
                laberinto.lienzoPadre.repaint();
            }
        });

        jugador.inteligencia.destinos.add(new Estado(13, 2, 'N', null));
        //jugador.inteligencia.destinos.add(new Estado(40, 17, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(13, 21, 'N', null));
        //jugador.inteligencia.destinos.add(new Estado(28, 2, 'N', null));
        //jugador.inteligencia.destinos.add(new Estado(5, 2, 'N', null));

        jugador.inteligencia.buscar(jugador.celdaMovimiento.x, jugador.celdaMovimiento.y, jugador.inteligencia.destinos.get(jugador.inteligencia.nDestinos - 1));
        jugador.inteligencia.calcularRuta();
        jugador.inteligencia.nDestinos--;

        lanzadorTareas = new Timer();
        lanzadorTareas.scheduleAtFixedRate(auto1, 0, 400);
        lanzadorTareas.scheduleAtFixedRate(auto2, 0, 300);
        lanzadorTareas.scheduleAtFixedRate(auto3, 0, 600);
        lanzadorTareas.scheduleAtFixedRate(auto4, 0, 400);
        lanzadorTareas.scheduleAtFixedRate(peaton, 0, 300);
        lanzadorTareas.scheduleAtFixedRate(micro, 0, 300);
        //lanzadorTareas.scheduleAtFixedRate(jugador.inteligencia, 0, 300);
    }

    private void crearPortales() {
        crearPortal(5, 2);
        crearPortal(6, 18);
        crearPortal(13, 2);
        crearPortal(16, 2);
        crearPortal(25, 2);
        crearPortal(28, 2);
        crearPortal(15, 9);
        crearPortal(25, 9);
        crearPortal(28, 9);
        crearPortal(15, 15);
        crearPortal(18, 15);
        crearPortal(27, 15);
        crearPortal(30, 15);
        crearPortal(13, 21);
        crearPortal(16, 21);
        crearPortal(28, 21);
        crearPortal(38, 21);
    }

    private void crearPortal(int x, int y) {
        portales.add(new Portal(laberinto, x, y));
    }

    private void crearPortal(int x, int y, int n) {
        portales.add(new Portal(laberinto, x, y, n));
    }

    @Override
    public void update(Graphics g) {
        if (graficoBuffer == null) {
            imagenBuffer = createImage(this.getWidth(), this.getHeight());
            graficoBuffer = imagenBuffer.getGraphics();
        }
        //volcamos color de fondo e imagen en el nuevo buffer grafico
        graficoBuffer.setColor(getBackground());
        graficoBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
        graficoBuffer.drawImage(fondo, 0, 0, null);
        laberinto.update(graficoBuffer);
        //pintamos la imagen previa
        g.drawImage(imagenBuffer, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }
}
