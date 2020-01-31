import java.io.*;

public class BaseTopTen {

    private String [] ten= new String[10];
  
    public BaseTopTen() {     
        try{
        FileReader inF = new FileReader("topten.txt");
        BufferedReader inB =new BufferedReader(inF);
         for (int i=0; i<10; i++) {
                    ten[i] =inB.readLine();                   
         }
          inB.close();
         } catch( IOException ex) {
                    System.out.println("error: " +ex);
         }

    }
    
    public void introduce(int puntosTetris, String nombre ) {
        int i = 9;
        while(puntosTetris>getPuntos(i) && i>0){
            i--;
        }
        i++;
        int j=ten.length-1;
        while(j>i){
            ten[j]=ten[j-1];
            j--;
        }
        ten[i]=nombre+".";
        while (ten[i].length()<16)  ten[i]+=".";
        ten[i]+=" "+puntosTetris;
        actualiza();
    }
    public void actualiza(){
        try{
        FileWriter inF = new FileWriter("topten.txt");
        BufferedWriter inB =new BufferedWriter(inF);         
         for (int i=0; i<10; i++) {
            inB.write(ten[i]); 
            inB.newLine();
         }
          inB.close();
         } catch( IOException ex) {
                    System.out.println("error: " +ex);
         }
                
    }
    public boolean apto(int pt) {
        if (pt>getPuntos(9)){
            return true;
        }
        else{
            return false;
        }
    }
    public String [] getTopTen (){
        return ten;
    }
    public int getPuntos(int indice) {
        String s=" " ;
        for(int i = 0; i< ten[indice].length(); i++){
            if ( ten[indice].charAt(i)==' '){
                s= ten[indice].substring(i+1,ten[indice].length());
                i=ten[indice].length();
            }
        }
        int puntos=Integer.parseInt(s);
        return puntos;
    }
}
