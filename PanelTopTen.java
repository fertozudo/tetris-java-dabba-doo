import javax.swing.*;
import java.awt.*;
public class PanelTopTen extends JPanel {
    private JLabel eti =new JLabel("Introduce tu nombre (Máximo 10 caracteres)");
    private JLabel eti2 =new JLabel("            ");
    private JTextField nombre = new JTextField();
    private JButton aceptar = new JButton("Aceptar");  
    private JPanel p = new JPanel();
    public PanelTopTen()    {
        super. setBackground(new java.awt.Color(80,80,80));    
        p.setBackground(new java.awt.Color(80,80,80));    
        p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
        
        p.add(Box.createRigidArea(new Dimension(0,250)));     
        
        eti.setBackground(new java.awt.Color(80,80,80));
        eti.setForeground(new java.awt.Color(146,208,80));
        eti.setFont(new java.awt.Font("Lucida Console", 1, 14));
        eti.setAlignmentX(new Float(0.5));
        p.add(eti);     
        
        
        
        p.add(Box.createRigidArea(new Dimension(0,50)));     
        nombre.setHorizontalAlignment(0);           
        p.add(nombre);      
        
        
        p.add(Box.createRigidArea(new Dimension(0,50)));     

        aceptar.setBackground(new java.awt.Color(146,208,80));
        aceptar.setForeground(new java.awt.Color(50,50,50));
        aceptar.setAlignmentX(new Float(0.5));
        p.add(aceptar);
        
        p.add(Box.createVerticalGlue());
        
        add(p);
     
    }
    public JButton getAceptar (){
        return aceptar;
    }  
    public JTextField getNombre (){
        return nombre;
    }
}