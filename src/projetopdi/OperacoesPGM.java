/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopdi;

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
}
