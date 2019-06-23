/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

import java.util.Arrays;

/**
 *
 * @author gusta
 */
public class OperacoesPGM {

    
    public static ImagePGM somar(ImagePGM imageA, ImagePGM matrizB){
   
        int soma;
        
        ImagePGM imageResult = new ImagePGM(imageA.largura,imageA.altura);
        
        for(int y=0;y<imageA.altura;y++){
            for(int x=0; x<matrizB.largura;x++){
              soma = imageA.matriz[x][y] + matrizB.matriz[x][y];
              if(soma>255){
                  soma = 255;
              }
              imageResult.matriz[x][y] = soma;
            }
        }
        return imageResult;
    }
    
    public static ImagePGM subtrair(ImagePGM imageA, ImagePGM imageB){
        
        int soma;
        
        ImagePGM imageResult = new ImagePGM(imageA.largura,imageA.altura);
        
        for(int y=0;y<imageA.altura;y++){
            for(int x=0; x<imageA.largura;x++){
              
             soma = Math.abs(imageA.matriz[x][y] - imageB.matriz[x][y]);
             //if(soma<0){
               //  soma=0;
             //}
             imageResult.matriz[x][y] = soma;
            }
        }
        return imageResult;
    }
    
    public static ImagePGM brilhoSoma(ImagePGM image, int valor){
        
       
        ImagePGM imageResult = new ImagePGM(image.largura,image.altura);
        int novoValor;

        for(int y=0;y<image.altura;y++){
            for(int x=0; x<image.largura;x++){
                novoValor = image.matriz[x][y] + valor;
                if(novoValor < 0){
                    imageResult.matriz[x][y] = 0;
                }else if(novoValor > 255){
                    imageResult.matriz[x][y] = 255;
                }else{
                    imageResult.matriz[x][y] = novoValor;
                }
            }
        }
        return imageResult;
    }
    
    public static ImagePGM mascara(int dim){
        
        ImagePGM result = new ImagePGM(dim,dim);
        
        for(int y=0;y<dim;y++){    
            for(int x=0; x<dim;x++){
                 result.matriz[x][y] = 1;
            }
        }
                
               
        
        return result;
    }
    public static ImagePGM filtroMedia(ImagePGM image,int dim){
        
        ImagePGM result = new ImagePGM(image.largura,image.altura);
        ImagePGM mascara = mascara(dim);
        
        int meio = ((mascara.altura-1)/2);
        int soma;
        int div;
        for(int y=0;y<image.altura;y++){    
            for(int x=0; x<image.largura;x++){
               div=0;
               soma=0;
                for(int my=0;my<mascara.altura;my++){    
                     for(int mx=0; mx<mascara.largura;mx++){
                         div+= mascara.matriz[mx][my];
                         int posx = x+(mx-meio);
                         int posy = y+(my-meio);
                         if(posx > 0 && posx < image.largura && posy > 0 && posy < image.altura){
                             soma+=image.matriz[posx][posy]*mascara.matriz[mx][my];
                         } 
                     }
                }
                soma= Math.abs(soma/div);
                if(soma<255)
                    result.matriz[x][y] = soma;
                else 
                   result.matriz[x][y] = 255;
           }
        }
        
        return result;
    }
    
    public static ImagePGM filtroMediana(ImagePGM image,int dim){
        
        ImagePGM result = new ImagePGM(image.largura,image.altura);
        ImagePGM mascara = mascara(dim);
        
        int meio = ((mascara.altura-1)/2);
        int[] valores = new int[mascara.altura*mascara.largura];
        int ind=0;
        int mediana = Math.abs((mascara.largura*mascara.altura)/2);
        
        for(int y=0;y<image.altura;y++){    
            for(int x=0; x<image.largura;x++){
               
                for(int my=0;my<mascara.altura;my++){    
                     for(int mx=0; mx<mascara.largura;mx++){
                        
                         int posx = x+(mx-meio);
                         int posy = y+(my-meio);
                         if(posx > 0 && posx < image.largura && posy > 0 && posy < image.altura){
                             valores[ind]=image.matriz[posx][posy];
                             ind++;
      
                         }
                     }
                }
                Arrays.sort(valores);
                result.matriz[x][y] = valores[mediana];
                Arrays.fill(valores, 0);
                ind=0;
           }
        }
        
        return result;
    }
    
    public static int[] histograma(ImagePGM image){
        
   
        int histograma[]  = new int[256];   
        
        for(int x=0;x<256;x++){
            histograma[x] = 0;
        }
        
         for(int y=0;y<image.altura;y++){    
            for(int x=0; x<image.largura;x++){
                 histograma[image.matriz[x][y]]++;
                
            }
        }  
         return histograma;
    }
}
