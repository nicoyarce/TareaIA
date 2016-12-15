/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Carta implements Constantes{
    public Laberinto laberinto;
    public BufferedImage carta;
    
    public Carta(Laberinto laberinto) {
        this.laberinto = laberinto;
        cargarCarta();
    }
    
    
    private void cargarCarta(){
        try {
            carta = ImageIO.read(new File("images/carta.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    public void update(Graphics g) {
           
    }
    
    public void paintComponent(Graphics g) {
        update(g);
    }
}
