import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Tetris extends JPanel implements KeyListener {

    private int  x,y,sgte,fig,p  ;        
    private boolean   fin,ganas,pro,keyON,keyFin,pausa ;
    private int   nivel,escombro,tiempo,lineas0,puntos;
    private Imagenes img;
    private Piezas pz = new Piezas();
    private int [][]  campo =new int [13][23];
    Random gen = new Random();    
    private BaseTopTen tt;
    
    public Tetris()  {           
        super.setBackground(new java.awt.Color(68,68,68));
        tt= new BaseTopTen();        
        img = new Imagenes();
        fin = false;
        ganas=false;
        pro=false;
        keyFin=true;
        pausa=false;
        puntos=0;
        lineas0=0;
        nivel=1;    
        escombro=1;
        creaCampo();
        repaint();
        x=4;y=0;
        fig=gen.nextInt(7);
        sgte=gen.nextInt(7);
        p=0;
        addKeyListener(this);
        setFocusable(true);
    }
    
    public void keyPressed(KeyEvent e) {
         if (keyON==true)  actualiza(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e) { }
    
    public void keyTyped(KeyEvent e) { }
    
    private void actualiza(int keyCode) {
        keyFin=false;
        if (keyCode==KeyEvent.VK_P){
            if (pausa==false) pausa=true;    else pausa=false;
            repaint();
        }
        if (((keyCode==KeyEvent.VK_UP) || (keyCode==KeyEvent.VK_DOWN) ||(keyCode==KeyEvent.VK_LEFT) ||
        (keyCode==KeyEvent.VK_RIGHT) || (keyCode==KeyEvent.VK_SPACE)) && (!pausa)) {
            borrarFicha();   
            switch (keyCode) {
                case KeyEvent.VK_UP:            
                     if  (comprueba(x,y,(p+1)%4 ))    p=(p+1)%4;                                                                  
                    break;
                case KeyEvent.VK_DOWN:     
                     if  (comprueba(x,y+1,p)) { 
                         y++;
                         puntos+=2;
                     }
                    break;    
                 case KeyEvent.VK_SPACE:     
                     if  (comprueba(x,y+1,p)) { 
                         for(; (comprueba(x,y+1,p));borrarFicha()  ){                                                         
                             y++;
                             rellenarFicha();     
                             puntos+=3;
                             delay(1);                                                                              
                         }
                     }
                    break;    
                case KeyEvent.VK_LEFT:
                     if  (comprueba(x-1,y,p))   x--;
                    break;    
                case KeyEvent.VK_RIGHT:
                     if (comprueba(x+1,y,p))  x++;
                    break;
            }
            rellenarFicha();
            repaint();           
        }   
        keyFin=true;
    }

    public void  juego () {
        Thread  t = new Thread (new Runnable(){
              public void run(){
                    creaEscombro();
                    niveles();
                    if (nivel==10) pro=true;
                    delay(900);
                    while (!fin) {
                        if (comprueba(x,y,p)) {        
                            rellenarFicha();
                             repaint();
                             keyON=true;
                             delay(tiempo);     
                             if (pausa) pausaBucle();
                             keyON=false;
                             while (!keyFin) delay(1);                               
                             borrarFicha();   
                             if (comprueba(x,y+1,p)) {       
                                 y++;                                                                                 
                            }
                            else {
                                 rellenarFicha();
                                 linea();
                                 niveles();
                                 repaint();
                                 delay(200);                                
                                  y=0;   x=4; p=0;               
                                  fig=sgte;
                                  sgte=gen.nextInt(7);                                                                 
                            }
                        }
                        else {                                                                   
                              x=4; p=0;
                             for (int i=0;i<4;i++){
                                 rellenarFicha();
                                 repaint();                             
                                 delay(tiempo); 
                                 fig=sgte;
                                 sgte=gen.nextInt(7);                                                                             
                             }
                              fin=true;
                              repaint();                                  
                        }
                    }                        
            }
            });   
        t.start();
    }
         
    public boolean comprueba(int mx , int my, int mp) {
        boolean ok= true;
         for (int n =0; n<4; n++) {
                for (int m =0; m<4;m++) {
                    if  ((pz.getPiezas()[fig][mp][m][n]>0) && (campo[mx+n][my+m]>0)) {
                        ok=false;
                    }                      
                }   
         }  
        return ok;
     }
    
     public void linea(){ 
        boolean lineaOK=false;
        int lineas=0;
        for (int i =20; i>=0;i--){
                 lineaOK=true;
                for (int j =1; j<campo.length-2;j++){
                         if (campo[j][i]==0) {
                             lineaOK=false;
                            }
                }   
                if (lineaOK) {
                    lineas++;
                    for(int m=i;m>=1;m--){
                        for(int n=1;n<campo.length-2;n++){
                            campo[n][m]=campo[n][m-1];
                        }
                    }
                    i++;
                }
        }
        if ((lineas>0) &&(lineas<4)) {
               delay(100);     
                if  (lineas==1) puntos+=20*nivel;
                if  (lineas==2) puntos+=60*nivel;
                if  (lineas==3) puntos+=200*nivel;
        }
        if (lineas==4) {
                delay(500);    
                if  (lineas==4) puntos+=1000*nivel;
        }
        lineas0+=lineas;
        if (escombro>1 && lineas0>49) {
            fin=true;
            ganas=true;
        }
    }
    public void niveles(){
        if (nivel< (lineas0/10)+1) nivel=(lineas0/10)+1;
        if (nivel<4) tiempo=(700-(100*nivel));
        if ((nivel>3) && (nivel<7)) tiempo=400-(50*(nivel-3));
        if ((nivel>6) && (nivel <11)) tiempo=250-(25*(nivel-6));
        if (nivel>10) tiempo=150-(10*(nivel-10));
    }
   
    public void paintComponent (Graphics g) {
        super.paintComponent(g);  
        g.setFont( new Font( "SansSerif", Font.PLAIN, 12) );
        g.drawImage(img.getImagen(9).getImage(),1,1,null); 
        g.drawImage(img.getImagen(sgte+11).getImage(),421,67,null);
        g.setColor(Color.white);
        g.drawString(" "+nivel,465,323); 
        if (escombro==1) g.drawString(" "+lineas0,465,433);
        else g.drawString(" "+(50-lineas0),465,433);        
        g.drawString(" "+puntos,450,545);         
        for (int i =0; i<campo.length-1;i++){
           for (int j =0; j<campo[i].length-1;j++){           
                g.drawImage(img.getImagen((campo[i][j])).getImage(),(i*25)+50,(j*25)+50,null);              
           }   
        } 
        if (pausa) g.drawImage(img.getImagen(20).getImage(),80,250,null);      
        if (fin && !ganas)  g.drawImage(img.getImagen(10).getImage(),78,225,null);        
        if (ganas) {            
            if (pro && escombro==5) g.drawImage(img.getImagen(19).getImage(),73,220,null);
            else g.drawImage(img.getImagen(18).getImage(),67,226,null);
        }
    }
    
    public void rellenarFicha() {
           for (int n =0; n<4; n++) {
                for (int m =0; m<4;m++) {
                    if (pz.getPiezas()[fig][p][n][m]>0) {
                        campo[x+m][y+n]=pz.getPiezas()[fig][p][n][m];
                    }                               
                }   
         }        
    }
    
    public void borrarFicha() {
           for (int n =0; n<4; n++) {
                for (int m =0; m<4;m++) {
                    if (pz.getPiezas()[fig][p][n][m]>0) {
                        campo[x+m][y+n]=0;
                    }                             
                }   
         }        
    }

    public void creaCampo (){
        for (int i =0; i<campo.length;i++){
                for (int j =0; j<campo[i].length;j++){
                         campo[i][j]=0;  
                }   
        }
        for (int i =0; i<campo[11].length-1;i++){
            campo[0][i]=8;
            campo[11][i]=8;            
        }
         for (int i =0; i<campo.length-1;i++){
            campo[i][21]=8;      
        }      
    }
    public void creaEscombro() {
         for (int j =24-(3*escombro); j<21;j++){
                for (int i =1; i<11;i++){
                        int semilla=gen.nextInt(8);
                        campo[i][j]=semilla;  
                }   
                int seguro=gen.nextInt(10)+1;
                campo[seguro][j]=0;
                seguro=gen.nextInt(10)+1;
                campo[seguro][j]=0;
        }
    }
    public void delay (int d) {
         try {                                
            Thread.sleep(d);
        } catch (Exception e){};  
    }
    public void setNivel(int niv) {
        nivel=niv;
    }
    public void setEscombro(int esc) {
        escombro=esc;
    }
    public boolean getTopTen(){        
        if (fin && escombro==1 )  {
            if ( tt.apto(puntos)){            
                return true ;
            }
            else {
                escombro=2;
                return false;
            }
        }
        else {
            return false;
        }
    }
    public boolean getFin(){
        if (fin && escombro>1)  {
            return true ;
        }
        else {
            return false;
        }
    }
    public String [] getTopTenActual (){
        return tt.getTopTen();
    }
    public BaseTopTen getTopTenEntero(){
        return tt;
    }
    public int getPuntosTetris(){
        return puntos;
    }
    public void pausaBucle() {
        while (pausa){
            delay(100);
        }
    }
}

