package inteligencia;


public class EstadoInf implements Comparable{
    
    public int x;
    public int y;
    //'N'=nada, 'L': izquierda, 'R': derecha, 'U': Arriba, 'D': abajo
    public char oper; 
    public EstadoInf predecesor;
    public double prioridad;
    
    
    public EstadoInf(int x, int y, char oper,EstadoInf predecesor) {
        this.x=x;
        this.y=y;
        this.oper=oper;
        this.predecesor=predecesor;
        
    }
    
    @Override
    public boolean equals(Object x) {
        EstadoInf e=(EstadoInf)x;
        return this.x==e.x && this.y==e.y;
    }
        
    @Override
    public String toString() {
        return "("+x+","+y+"): Prioridad= "+this.prioridad;
    }
    
    @Override
    public int compareTo(Object o) {
        EstadoInf e=(EstadoInf)o;
        if ( this.prioridad == e.prioridad ) return 0;
        else {
            if ( this.prioridad > e.prioridad ) return 1;
            else return -1;
        }
    }
}
