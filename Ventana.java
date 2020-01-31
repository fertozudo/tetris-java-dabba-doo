import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame  {
 
    private Tetris panel = new Tetris();
    private PanelInicio panini= new PanelInicio();
    private PanelFin panfin = new PanelFin();    
    private PanelTopTen top= new PanelTopTen();        

    
    public Ventana () {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TETRIS");                                        
        add(panini);
        panini.getComenzar().addActionListener(new AccionBotonComenzar());
        
        top.getAceptar().addActionListener(new AccionBotonAceptar());
        top.getNombre().addActionListener(new AccionBotonAceptar());
        panfin.getRejuego().addActionListener(new AccionBotonRejuego());
        panfin.getSalir().addActionListener(new AccionBotonSalir());     
        
        setSize(600, 680);              
        setVisible(true);        
    }
     class AccionBotonComenzar implements ActionListener {
        public void actionPerformed(ActionEvent e) {   
            for (int i=0; i<panini.getBotonesNivel().length; i++) {
                if (panini.getBotonesNivel()[i].isSelected()) {
                   panel.setNivel(i+1);
                   i=panini.getBotonesNivel().length;
                }
            }
            for (int i=0; i<panini.getBotonesEscombro().length; i++) {
                if (panini.getBotonesEscombro()[i].isSelected()) {
                   panel.setEscombro(i+1);
                   i=panini.getBotonesEscombro().length;
                }
            }
            remove(panini);
            add(panel);                      
            panel.requestFocus();
            repaint();
            setVisible(true);
            escucha();
            panel.juego();
           
        }   
    }   
   
    public void escucha() {
        Thread  t = new Thread (new Runnable(){
              public void run(){
                    while (!panel.getTopTen() && !panel.getFin()){
                        panel.delay(500);
                    }
                    if (panel.getTopTen()){
                        panel.delay(2000);
                        remove(panel);                        
                        add(top);                                
                        top.requestFocus();
                        top.getNombre().requestFocus();
                        repaint();
                        setVisible(true);
                    }
                    else {
                        panel.delay(2000);
                        remove(panel);                        
                        addPanFin();
                    }
                }
            });     
            t.start();
    }
    class AccionBotonRejuego implements ActionListener {
        public void actionPerformed(ActionEvent e) {   
            remove(panfin);
            add(panini);     
            panini.requestFocus();
            panel = new Tetris();
            repaint();
            setVisible(true);       
        }   
    }   
    class AccionBotonSalir implements ActionListener {
        public void actionPerformed(ActionEvent e) {   
            System.exit(0); 
        }
    }
    class AccionBotonAceptar implements ActionListener {
        public void actionPerformed(ActionEvent e) {   
            String s=top.getNombre().getText();
            top.getNombre().setText("");
            s.replaceAll(" ", "");
            if (s.length()>10 || s.length()<1) s="manolo";
            panel.getTopTenEntero().introduce(panel.getPuntosTetris(),s);
            remove(top);
            addPanFin();              
        }
    }
    public void addPanFin(){
        add(panfin);     
        for (int i =0; i<10; i++) {
            panfin.gettt()[i].setText(panel.getTopTenActual()[i]);
        }       
        panfin.requestFocus();
        repaint();
        setVisible(true);  
    }
    public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Ventana v = new Ventana();             
            }
        });
    }
}
