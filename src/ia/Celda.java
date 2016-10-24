package ia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Celda extends JComponent implements Constantes {

    //atributos
    public int coordenadaX;
    public int coordenadaY;
    public int i;
    public int j;
    public char tipoCelda;
    public int indexSprite;
    public BufferedImage sprites[], jugador2;
    //nuevos atributos para manejar imagenes
    public BufferedImage jugador, obstaculo, camino, vehiculo, portal;
    public BufferedImage edificio, acera, carretera;

    //constructor, inicializa los atributos
    public Celda(int x, int y, int i, int j, char tipo) {
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.i = i;
        this.j = j;
        this.tipoCelda = tipo;
        indexSprite = 0;
        try {
            //jugador = ImageIO.read(new File("images/jugador.png"));
            //obstaculo = ImageIO.read(new File("images/obstaculo.png"));
            //camino = ImageIO.read(new File("images/camino.png"));
            vehiculo = ImageIO.read(new File("images/vehiculo.png"));
            //portal = ImageIO.read(new File("images/portal.png"));
            edificio = ImageIO.read(new File("images/edificio.png"));
            acera = ImageIO.read(new File("images/acera.png"));
            carretera = ImageIO.read(new File("images/carretera.png"));
            //gestion de sprites
            //cargo la imagen de grupo de imagenes
            jugador2 = ImageIO.read(new File("images/jugador2.png"));
            //creo una array de 2 x 2
            sprites = new BufferedImage[4];
            //lo recorro separando las imagenes
            for (int is = 0; is < 2; is++) {
                for (int js = 0; js < 2; js++) {
                    sprites[(is * 2) + js] = jugador2.getSubimage(is * TAMANIO_CELDA, js * TAMANIO_CELDA, TAMANIO_CELDA, TAMANIO_CELDA);
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void update(Graphics g) {
        switch (tipoCelda) {
            case 'J':
                g.drawImage(sprites[indexSprite], coordenadaX, coordenadaY, this);
                break;
            case 'O':
                g.drawImage(obstaculo, coordenadaX, coordenadaY, this);
                break;
            case 'V':
                //g.setColor(COLORFONDO);
                // g.fillRect(coordenadaX, coordenadaY, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
            case 'H':
                g.drawImage(vehiculo, coordenadaX, coordenadaY, this);
                break;
            case 'A':
                g.drawImage(acera, coordenadaX, coordenadaY, this);
                break;
            case 'E':
                g.drawImage(edificio, coordenadaX, coordenadaY, this);
                break;
            /*case 'P':
                g.drawImage(portal, X, Y, this);
                break;*/
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

}
