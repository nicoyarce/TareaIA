package ia;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Celda extends JComponent implements Constantes {
    //atributos
    public int X;
    public int Y;
    //nuevos atributos para manejar imagenes
    public char tipo;
    public BufferedImage jugador, obstaculo, camino, adversario, portal;
    
    //constructor, inicializa los atributos
    public Celda(int x, int y, char tipo) {
        this.X = x;
        this.Y = y;
        this.tipo = tipo;
        try {
            jugador = ImageIO.read(new File("images/jugador.png"));
            obstaculo = ImageIO.read(new File("images/obstaculo.png"));
            camino = ImageIO.read(new File("images/camino.png"));
            adversario = ImageIO.read(new File("images/adversario.png"));
            portal = ImageIO.read(new File("images/portal.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    @Override
    public void update(Graphics g){
        switch (tipo) {
            case 'J':
                g.drawImage(jugador, X, Y, null);
                break;
            case 'O':
                g.drawImage(obstaculo, X, Y, this);
                break;
            case 'V':
                g.drawImage(camino, X, Y, this);
                break;
            case 'A':
                g.drawImage(adversario, X, Y, this);
                break;
            case 'P':
                g.drawImage(portal, X, Y, this);
                break;
        }
    }
    //metodo para dibujar celda, hace uso de drawRect   
    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }
    //si el click esta sobre la celda
    public boolean celdaSeleccionada(int xp, int yp) {
        return contains(new Point(xp, yp));
    }
}
