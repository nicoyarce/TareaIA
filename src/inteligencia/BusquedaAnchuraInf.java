package inteligencia;

import proyecto.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TimerTask;

public class BusquedaAnchuraInf extends TimerTask implements Constantes{
    
    public Laberinto laberinto;
    public Queue<EstadoInf> colaEstados;
    public ArrayList<EstadoInf> historial;
    public ArrayList<Character> pasos;
    public int index_pasos;
    public EstadoInf inicial;
    public EstadoInf objetivo;
    public EstadoInf temp;
    public boolean exito;
    
    public BusquedaAnchuraInf(Laberinto laberinto) {
        
        this.laberinto=laberinto;
        colaEstados=new PriorityQueue<>();
        historial=new ArrayList<>();
        pasos=new ArrayList<>();
        index_pasos=0;
        exito=false;
    }
    
    public void buscar(int x1,int y1,int x2,int y2) {
        inicial=new EstadoInf(x1,y1,'N',null);
        inicial.prioridad=laberinto.celdas[laberinto.lienzoPadre.jugador.jugador.x][laberinto.lienzoPadre.jugador.jugador.y].npeatones;
        objetivo=new EstadoInf(x2,y2,'P',null);
        
        colaEstados.add(inicial);
        historial.add(inicial);
        
        if ( inicial.equals(objetivo)) exito=true;
        
        while ( !colaEstados.isEmpty() && !exito ){            
            temp=colaEstados.poll();
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }
        
        if ( exito ) System.out.println("Ruta calculada");
        else System.out.println("La ruta no pudo calcularse");
        
    }
    
    //distancia adversario
    public double distancia(int x1,int y1, int x2, int y2) {
        double valor;
        double parte1=Math.pow(Math.abs(x1-x2),2);
        double parte2=Math.pow(Math.abs(y1-y2),2);
        parte1+=parte2;
        valor=Math.sqrt(parte1);
        return valor;
    }
    
    private void moverArriba(EstadoInf e) {  
        if ( e.y > 0 ) {
            if ( laberinto.celdas[e.x][e.y-1].tipoCelda != 'O' ) { 
                EstadoInf arriba=new EstadoInf(e.x,e.y-1,'U',e);
                arriba.prioridad=laberinto.celdas[laberinto.lienzoPadre.jugador.jugador.x][laberinto.lienzoPadre.jugador.jugador.y-1].npeatones;
                 if ( !historial.contains(arriba)) {
                    colaEstados.add(arriba);
                    historial.add(arriba);
                    if ( arriba.equals(objetivo)) {
                        objetivo=arriba;
                        exito=true;
                    }
                    
                 }
            }     
        }
    }
    
    private void moverAbajo(EstadoInf e) {
       
        if ( e.y+1 < N_CELDAS_ALTO ) { 
            if ( laberinto.celdas[e.x][e.y+1].tipoCelda != 'O' ) {
                 EstadoInf abajo=new EstadoInf(e.x,e.y+1,'D',e);  
                 abajo.prioridad=laberinto.celdas[laberinto.lienzoPadre.jugador.jugador.x][laberinto.lienzoPadre.jugador.jugador.y+1].npeatones;
                 
                 
                 if ( !historial.contains(abajo)) {
                    colaEstados.add(abajo);
                    historial.add(abajo);
                    //laberinto.celdas[e.x][e.y+1].tipo='A';
                    if ( abajo.equals(objetivo)) {
                        //laberinto.celdas[e.x][e.y+1].tipo='P';
                        objetivo=abajo;
                        exito=true;
                    }
                 }   
            }     
        }
    }    
    
    private void moverIzquierda(EstadoInf e) {
        if ( e.x > 0 ) {
            if ( laberinto.celdas[e.x-1][e.y].tipoCelda != 'O' ) {
                EstadoInf izquierda=new EstadoInf(e.x-1,e.y,'L',e);
                izquierda.prioridad=laberinto.celdas[laberinto.lienzoPadre.jugador.jugador.x-1][laberinto.lienzoPadre.jugador.jugador.y].npeatones;
                
                
                if ( !historial.contains(izquierda)) {
                    
                   colaEstados.add(izquierda);
                   historial.add(izquierda);
                   
                   if ( izquierda.equals(objetivo)) {
                       
                       objetivo=izquierda;
                       exito=true;
                   }
                }   
            }    
        }
    }    
    
    private void moverDerecha(EstadoInf e) {
        
        if ( e.x < N_CELDAS_ANCHO-1 ) {
            if ( laberinto.celdas[e.x+1][e.y].tipoCelda != 'O' ) {
               EstadoInf derecha=new EstadoInf(e.x+1,e.y,'R',e); 
               derecha.prioridad=laberinto.celdas[laberinto.lienzoPadre.jugador.jugador.x+1][laberinto.lienzoPadre.jugador.jugador.y].npeatones;
               
               if ( !historial.contains(derecha)){
                 colaEstados.add(derecha);
                 historial.add(derecha);
                 
                 if ( derecha.equals(objetivo)) {
                     objetivo=derecha;
                     exito=true;
                 }
               }  
            }     
        }
    }    
    
    public void calcularRuta() {
        EstadoInf predecesor=objetivo;
        do{
            pasos.add(predecesor.oper);
            predecesor=predecesor.predecesor;
        }while ( predecesor != null);
        index_pasos=pasos.size()-1;
        
    }
    
    @Override
    public synchronized void run() {
       if ( index_pasos >= 0 ) {
          switch(pasos.get(index_pasos)) {
             case 'D': laberinto.lienzoPadre.jugador.moverCeldaAbajo();break;
             case 'U': laberinto.lienzoPadre.jugador.moverCeldaArriba(); break;
             case 'R': laberinto.lienzoPadre.jugador.moverCeldaDerecha();break;
             case 'L': laberinto.lienzoPadre.jugador.moverCeldaIzquierda();break;
          }
          laberinto.lienzoPadre.repaint();  
        index_pasos--;
       }else {
           this.cancel();
       }
    }
}