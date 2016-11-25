
package ia;

public class Portal implements Constantes{
    public Laberinto laberinto;
    public Celda portal;
    public int nCartas;
    
    public Portal(Laberinto laberinto, int x, int y, int nCartas){
        this.laberinto=laberinto;
        this.nCartas=nCartas; 
        portal = new Celda(x, y, PORTAL);
        laberinto.celdas[portal.x][portal.y].tipoCelda = PORTAL;
    }   
}
