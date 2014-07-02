/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JOptionPane;

/**
 *
 * @author Fox
 */
public class validarrut {
    
    public static boolean cor = false;
    public static String msj;
    public void validarrut(String strnumero, String strverifi){
        msj = "";
           int veri;
        if(strnumero.isEmpty()){
            msj = "debe escribir correctamente el rut";
        }else{
            msj = "";
        }
        if(strverifi.isEmpty())
        {
            msj = "debe escribir correctamente el rut";
        }else{
       msj = "";
        if(strverifi.equals("k")){
            veri = 10;
            System.out.println(""+veri);
        }else{
        veri = Integer.parseInt(strverifi);     
        }
       
        int numint = 0;
        int numint1 = 0;
        int numint2 = 0;
        int numint3 = 0;
        int numint4 = 0;
        int numint5 = 0;
        int numint6 = 0;
        int numint7 = 0;
        String verificadork = "";
        int total = 0;
        boolean num10 = false; 
        int verificador = 0;
        for (int i = 0; i < strnumero.length(); i++) {

            if (i == 0) {
                char numchar = strnumero.charAt(0);
                String s = Character.toString(numchar);

                numint = Integer.parseInt(s);
         //       System.out.println(""+numint);
             
            }
            if (i == 1) {
                char numchar = strnumero.charAt(1);
                String s1 = Character.toString(numchar);
                

                numint1 = Integer.parseInt(s1);
       // System.out.println(""+numint1);
            }
            if (i == 2) {
                char numchar = strnumero.charAt(2);
                String s2 = Character.toString(numchar);

                numint2 = Integer.parseInt(s2);
             //System.out.println(""+numint2);
            }
            if (i == 3) {
                char numchar = strnumero.charAt(3);
                String s3 = Character.toString(numchar);

                numint3 = Integer.parseInt(s3);
             //System.out.println(""+numint3); 
            }
            
            if (i == 4) {
                char numchar = strnumero.charAt(4);
                String s4 = Character.toString(numchar);

                numint4 = Integer.parseInt(s4);
            //    System.out.println(""+numint4);
            }
            
                if (i == 5) {
                char numchar = strnumero.charAt(5);
                String s5 = Character.toString(numchar);
                
                numint5 = Integer.parseInt(s5);
                //System.out.println(""+numint5);
            }
                 if (i == 6) {
                     num10 =  true;
                char numchar = strnumero.charAt(6);
                String s6 = Character.toString(numchar);

                numint6 = Integer.parseInt(s6);
                 
                //System.out.println(""+numint6);
            }
                 if (i == 7) {
                     num10 =  false;
                char numchar = strnumero.charAt(7);
                String s7 = Character.toString(numchar);

                numint7 = Integer.parseInt(s7);
                //System.out.println(""+numint7);
               
            }
        }
if(num10 == false){
         
        total = (numint7 * 2) + (numint6 * 3) + (numint5 * 4) + (numint4 * 5) + (numint3 * 6) + (numint2 * 7) + (numint1 * 2)  + (numint * 3);

        System.out.println(""+total);
        int resto = total % 11;
        System.out.println(""+resto);
        verificador = 11 - resto;
           if(verificador == 11){
        verificador = 0;    
                System.out.println(""+verificador);
        }
         
           
        if(verificador == veri){
           cor = true;
           // JOptionPane.showMessageDialog(null, "correcto");
        }else{
            cor = false;
            msj = "codigo verificador incorrecto";
        }
      
        
       
        
       /*
        if(verificadork.equals("k")){
            JOptionPane.showMessageDialog(null, "correcto");
        }else{
            JOptionPane.showMessageDialog(null, "incorrecto");
        }
        */
        System.out.println("" + verificador);
        
        
       
        }else{
    System.out.println("entramos");
            total = (numint6 * 2) + (numint5 * 3) + (numint4 * 4) + (numint3 * 5) + (numint2 * 6) + (numint1 * 7) + (numint * 2);

        System.out.println(""+total);
        int resto = total % 11;
        System.out.println(""+resto);
        verificador = 11 - resto;
        
        
        if(verificador == 11){
        verificador = 0;    
        
        }
        
        
        
          if(verificador == veri ){
              cor = true;
              msj = "";
            // JOptionPane.showMessageDialog(null, "correcto");
        }else{
            cor = false;
            msj = "codigo verificador incorrecto";
        }
        
        if(verificador == 11){
        verificador = 0;    
        
        }
        /*if(verificador == 10){
       verificadork = "k";    
        
        }
        System.out.println("" + verificador);
        
         if(verificadork.equals("k")){
            JOptionPane.showMessageDialog(null, "correcto");
        }else{
            JOptionPane.showMessageDialog(null, "incorrecto");
        }
   */
      
    
}
        }       

        
    }
}
