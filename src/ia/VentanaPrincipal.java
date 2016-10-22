package ia;

import java.awt.Dimension;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements Constantes {
//nuestra clase se compone de un lienzo de dibujo (herada de canvas)

    public Lienzo lienzo;
    public Dimension sizeScreen;
//constructor

    public VentanaPrincipal() {
        lienzo = new Lienzo();
        lienzo.setFocusable(true);
        sizeScreen = super.getToolkit().getScreenSize();
        this.getContentPane().add(lienzo);
        this.setSize(SIZE_WIDTH, SIZE_HEIGHT);    
    }
}
