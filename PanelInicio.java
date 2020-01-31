import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel{
    private  JButton comenzar = new JButton("Comenzar");
    private  JRadioButton [] botonesNivel = new JRadioButton[10];
    private  JRadioButton [] botonesEscombro = new JRadioButton[5];
    private  JRadioButton [] botonesMusica = new JRadioButton[2];
    private  JLabel [] etiqueta = new JLabel[4];
    private ImageIcon inicio = new ImageIcon("imag/inicio.png");
    
    private JPanel[] casilla = new JPanel[5];
   
    public PanelInicio() {            
        super.setBackground(new java.awt.Color(80,80,80));
        ButtonGroup grupoNivel = new ButtonGroup ();
        ButtonGroup grupoEscombro = new ButtonGroup ();
        ButtonGroup grupoMusica = new ButtonGroup ();
        etiqueta[0]=new JLabel("Nivel");
        etiqueta[1]=new JLabel("Escombro");
        etiqueta[2]=new JLabel("Musica");    
        for (int i = 0 ; i < etiqueta.length-1; i++)  etiqueta[i].setForeground(new java.awt.Color(255, 255, 255));
        etiqueta[3]=new JLabel(inicio);
        for (int i = 0 ; i < casilla.length; i++) {
            casilla[i] = new JPanel();     
            casilla[i]. setBackground(new java.awt.Color(80,80,80));      
        }                
        casilla[0].add(etiqueta[0]);
        for (int i = 0 ; i < botonesNivel.length; i++) {
            botonesNivel[i] = new JRadioButton();
            botonesNivel[i].setText(""+(1+i));
            botonesNivel[i].setBackground(new java.awt.Color(80,80,80));
            botonesNivel[i].setForeground(new java.awt.Color(255, 255, 255));
            grupoNivel.add(botonesNivel[i]);
            casilla[0].add(botonesNivel[i]);
        }
        casilla[1].add(etiqueta[1]);
        for (int i = 0 ; i < botonesEscombro.length; i++) {
            botonesEscombro[i] = new JRadioButton();
            botonesEscombro[i].setText(""+(i));
            botonesEscombro[i].setBackground(new java.awt.Color(80,80,80));
            botonesEscombro[i].setForeground(new java.awt.Color(255, 255, 255));
            grupoEscombro.add(botonesEscombro[i]);
            casilla[1].add(botonesEscombro[i]);
        }
        casilla[2].add(etiqueta[2]);
        for (int i = 0 ; i < botonesMusica.length; i++) {
            botonesMusica[i] = new JRadioButton();
            if (i==0) botonesMusica[i].setText("Si"); 
            else botonesMusica[i].setText("No");         
            botonesMusica[i].setBackground(new java.awt.Color(80,80,80));
            botonesMusica[i].setForeground(new java.awt.Color(255, 255, 255));
            grupoMusica.add(botonesMusica[i]);
            casilla[2].add(botonesMusica[i]);
        }         
        comenzar.setBackground(new java.awt.Color(146,208,80));
        casilla[3].add(comenzar);    
                
        casilla[4].setLayout(new GridLayout(4,1));        
        casilla[4].add(casilla[0]);
        casilla[4].add(casilla[1]);
        casilla[4].add(casilla[2]);
        casilla[4].add(casilla[3]);
        
        setLayout(new GridLayout(2,1));
        add(etiqueta[3]);
        add(casilla[4]);

    }
    
    public JButton getComenzar (){
        return comenzar;
    }             
    public JRadioButton []  getBotonesNivel (){
        return botonesNivel;
    }     
    public JRadioButton []  getBotonesEscombro(){
        return botonesEscombro;
    } 
    
}
