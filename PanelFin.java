import javax.swing.*;
import java.awt.*;

public class PanelFin extends JPanel{
    private  JButton rejuego = new JButton("Volver a jugar");
    private  JButton salir = new JButton("Salir");
    private ImageIcon creditos = new ImageIcon("imag/creditos.png");    
    private JLabel lienzo =new JLabel(creditos);
    private JPanel superior = new JPanel();
    private JPanel inferior = new JPanel();    
    private JPanel inferiordcha = new JPanel();      
    private JLabel [] tt = new JLabel[10];
   
    public PanelFin() {              
        super.setBackground(new java.awt.Color(80,80,80));
        superior.setLayout(new GridLayout(10,1));
        superior.setBackground(new java.awt.Color(146,208,80));
        for (int i =0 ;i<tt.length; i++){
            tt[i]= new JLabel("");      
            tt[i].setFont(new java.awt.Font("Lucida Console", 1, 14));
            tt[i].setForeground(new java.awt.Color(146,208,80));
            tt[i].setBackground(new java.awt.Color(80,80,80));
            tt[i].setOpaque(true);
            tt[i].setHorizontalAlignment(0);           
            superior.add(tt[i]);
        }
        
        inferiordcha.setLayout(new BoxLayout(inferiordcha,BoxLayout.PAGE_AXIS));
        inferiordcha.setBackground(new java.awt.Color(80,80,80));
        
        inferiordcha.add(Box.createVerticalGlue());          
        rejuego.setBackground(new java.awt.Color(146,208,80));
        rejuego.setForeground(new java.awt.Color(50,50,50));
        rejuego.setAlignmentX(new Float(0.5));
        inferiordcha.add(rejuego);
        inferiordcha.add(Box.createVerticalGlue());
        salir.setBackground(new java.awt.Color(247,59,59));
        salir.setForeground(new java.awt.Color(50,50,50));
        salir.setAlignmentX(new Float(0.5));
        inferiordcha.add(salir);         
        inferiordcha.add(Box.createVerticalGlue());
        
        inferior.setLayout(new GridLayout(1,2,15,10));
        inferior.setBackground(new java.awt.Color(146,208,80));
        inferior.add(lienzo);  
        inferior.add(inferiordcha); 
        
        setLayout(new GridLayout(2,1));        
        add(superior);
        add(inferior);
    }   
    
    public JButton getRejuego (){
        return rejuego;
    }    
    public JButton getSalir (){
        return salir;
    }     
    public JLabel [] gettt (){
        return tt;
    }
    
}