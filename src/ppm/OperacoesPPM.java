/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppm;



/**
 *
 * @author gusta
 */
public class OperacoesPPM {
    
    public static ImagePPM extrairCanal(ImagePPM image, int canal){
        
       ImagePPM imageCanal = new ImagePPM(image.largura,image.altura);

       for(int y=0;y<image.altura;y++){
            for(int x=0; x<image.largura;x++){
               int c = image.matriz[x][y][canal];
               imageCanal.addMatriz(x, y, c,c,c);
            
            }
        }
       return imageCanal;
    }
    
    public static int[] histograma(ImagePPM image, int canal){

        int histograma[]  = new int[256];   
        
        for(int x=0;x<256;x++){
            histograma[x] = 0;
        }
        
         for(int y=0;y<image.altura;y++){    
            for(int x=0; x<image.largura;x++){
                histograma[image.matriz[x][y][canal]]++;
            }
        }  
         return histograma;
    }
    
    

}
