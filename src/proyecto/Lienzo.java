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
import java.util.Timer;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes {

    public Laberinto laberinto;
    public Image fondo;

    public Vehiculo auto1, auto2, auto3, auto4;
    public Peaton peaton, peaton2;
    public Jugador jugador;
    public Micro micro1;
    public Timer lanzadorTareas;
    public Carta carta;
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
        //micro1 = new Micro(laberinto, p11, p12);

        jugador = new Jugador(laberinto);
        carta = new Carta(laberinto);
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
        /*Busqueda multiobjetivos
        jugador.inteligencia.destinos.add(new Estado(13, 2, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(40, 17, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(13, 21, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(28, 2, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(5, 2, 'N', null));*/
        
        //Busqueda informada
        jugador.inteligenciainf.buscar(0,0,40,17);
        jugador.inteligenciainf.calcularRuta(); 
        
        lanzadorTareas = new Timer();
        lanzadorTareas.scheduleAtFixedRate(auto1, 0, 400);
        lanzadorTareas.scheduleAtFixedRate(auto2, 0, 300);
        lanzadorTareas.scheduleAtFixedRate(auto3, 0, 600);
        lanzadorTareas.scheduleAtFixedRate(auto4, 0, 400);
        lanzadorTareas.scheduleAtFixedRate(peaton, 0, 300);
        //lanzadorTareas.scheduleAtFixedRate(micro1, 0, 1000);
        //lanzadorTareas.scheduleAtFixedRate(jugador.inteligencia, 0, 300);
        lanzadorTareas.scheduleAtFixedRate(jugador.inteligenciainf, 0, 300);
    }
    
    public void imprimirMapa(){
        for(int i=0; i<N_CELDAS_ANCHO;i++){
            for(int j=0; j<N_CELDAS_ALTO;j++){
                System.out.print(laberinto.celdas[i][j].npeatones + " ");
            }
            System.out.println("");
        }
        
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
