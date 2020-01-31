import javax.swing.*;

public class Imagenes {

    private ImageIcon [] f = new ImageIcon [21];   

    public Imagenes() {
        for (int i = 0; i < f.length; i++) {   
            try {
                f[i] = new ImageIcon(getClass().getResource("/imag/f"+i+".png")); //cargamos imagenes                           
            } catch (Exception e) {
                System.out.print("Loading images failed!!! ::: "+e);            
            }      
        }         
    }

    public ImageIcon getImagen(int i) {
        return f[i];
    }
 
}
