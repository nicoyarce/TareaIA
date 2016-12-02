package ia;

import java.util.ArrayList;
import java.util.TimerTask;

class BusquedaRutaAmplitud extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public ArrayList<Estado> colaEstados;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;
    public int index_pasos;
    public Estado inicial;
    public Estado objetivo;
    public Estado temp;
    public boolean exito;

    public BusquedaRutaAmplitud(Laberinto laberinto) {

        this.laberinto = laberinto;
        colaEstados = new ArrayList<>();
        historial = new ArrayList<>();
        pasos = new ArrayList<>();
        index_pasos = 0;
        //digo cual es el estado inicial y el final
        inicial = new Estado(0, 0, 'N', null);
        colaEstados.add(inicial);
        historial.add(inicial);
        objetivo = new Estado(13, 2, 'N', null);
        exito = false;
    }

    public void buscar() {

        if (inicial.equals(objetivo)) {
            exito = true;
        }

        while (!colaEstados.isEmpty() && !exito) {
            temp = colaEstados.get(0);
            colaEstados.remove(0);
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }

        if (exito) {
            System.out.println("Ruta calculada");
        } else {
            System.out.println("La ruta no pudo calcularse");
        }

    }

    private void moverArriba(Estado e) {

        if (e.y > 0) {
            if (laberinto.celdas[e.x][e.y - 1].tipoCelda != OBSTACULO) {
                Estado arriba = new Estado(e.x, e.y - 1, 'U', e);
                if (!historial.contains(arriba)) {
                    colaEstados.add(arriba);
                    historial.add(arriba);
                    laberinto.celdas[e.x][e.y - 1].tipoCelda = 'A';
                    if (arriba.equals(objetivo)) {
                        laberinto.celdas[e.x][e.y - 1].tipoCelda = PORTAL;
                        objetivo = arriba;
                        exito = true;
                    }

                }
            }
        }
    }

    private void moverAbajo(Estado e) {
        System.out.println(e.toString());
        if (e.y != N_CELDAS_ALTO - 1) {
            if (laberinto.celdas[e.x][e.y + 1].tipoCelda != OBSTACULO) {
                Estado abajo = new Estado(e.x, e.y + 1, 'D', e);
                if (!historial.contains(abajo)) {
                    colaEstados.add(abajo);
                    historial.add(abajo);
                    laberinto.celdas[e.x][e.y + 1].tipoCelda = 'A';
                    if (abajo.equals(objetivo)) {
                        laberinto.celdas[e.x][e.y + 1].tipoCelda = PORTAL;
                        objetivo = abajo;
                        exito = true;
                    }
                }
            }
        }
    }

    private void moverIzquierda(Estado e) {

        if (e.x > 0) {
            if (laberinto.celdas[e.x - 1][e.y].tipoCelda != OBSTACULO) {
                Estado izquierda = new Estado(e.x - 1, e.y, 'L', e);
                if (!historial.contains(izquierda)) {
                    colaEstados.add(izquierda);
                    historial.add(izquierda);
                    laberinto.celdas[e.x - 1][e.y].tipoCelda = 'A';
                    if (izquierda.equals(objetivo)) {
                        laberinto.celdas[e.x - 1][e.y].tipoCelda = PORTAL;
                        objetivo = izquierda;
                        exito = true;
                    }
                }
            }
        }
    }

    private void moverDerecha(Estado e) {

        if (e.x < N_CELDAS_ANCHO - 1) {
            if (laberinto.celdas[e.x + 1][e.y].tipoCelda != OBSTACULO) {
                Estado derecha = new Estado(e.x + 1, e.y, 'R', e);
                if (!historial.contains(derecha)) {
                    colaEstados.add(derecha);
                    historial.add(derecha);
                    laberinto.celdas[e.x + 1][e.y].tipoCelda = 'A';
                    if (derecha.equals(objetivo)) {
                        laberinto.celdas[e.x + 1][e.y].tipoCelda = PORTAL;
                        objetivo = derecha;
                        exito = true;
                    }
                }
            }
        }
    }

    public void calcularRuta() {
        Estado predecesor = objetivo;
        do {
            pasos.add(predecesor.oper);
            predecesor = predecesor.predecesor;
        } while (predecesor != null);
        index_pasos = pasos.size() - 1;

    }

    @Override
    public synchronized void run() {
        if (index_pasos >= 0) {
            switch (pasos.get(index_pasos)) {
                case 'D':
                    laberinto.lienzoPadre.jugador.moverCeldaAbajo();
                    break;
                case 'U':
                    laberinto.lienzoPadre.jugador.moverCeldaArriba();
                    break;
                case 'R':
                    laberinto.lienzoPadre.jugador.moverCeldaDerecha();
                    break;
                case 'L':
                    laberinto.lienzoPadre.jugador.moverCeldaIzquierda();
                    break;
            }
            laberinto.lienzoPadre.repaint();
            index_pasos--;
        } else {
            this.cancel();
        }
    }
}
