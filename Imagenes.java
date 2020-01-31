import javax.swing.*;

public class Imagenes {

    private ImageIcon [] f = new ImageIcon [21];   

    public Imagenes() {
        for (int i = 0; i < f.length; i++) {         
            f[i] = new ImageIcon(getClass().getResource("/imag/f"+i+".png")); //cargamos imagenes           
        }         
    }

    public ImageIcon getImagen(int i) {
        return f[i];
    }
 
}
