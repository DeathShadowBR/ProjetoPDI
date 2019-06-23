/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

import java.util.Arrays;
import static pgm.OperacoesPGM.histograma;

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
    
    public static ImagePGM binarizacao(ImagePGM matriz,int valor){
        
        ImagePGM result = new ImagePGM(matriz.largura,matriz.altura);
        
        for(int y=0;y<matriz.altura;y++){
            for(int x=0; x<matriz.largura;x++){
                if(valor > matriz.matriz[x][y] ){
                    result.matriz[x][y] = 0;
                }else{
                    result.matriz[x][y] = 255;
                }
                
            }
        }
        return result;
    }
    
    public static ImagePGM gammaLog(ImagePGM matriz,float c){
        
        ImagePGM result = new ImagePGM(matriz.largura,matriz.altura);
        float s;
        float intervalo;
        
        
        for(int y=0;y<matriz.altura;y++){    
            for(int x=0; x<matriz.largura;x++){
                intervalo = (float) matriz.matriz[x][y]/255;
                s =  (float) (c * Math.log10(1 + intervalo));
                int g = (int) (s*255);
                if(g>255){
                    result.matriz[x][y] = 255;
                }else{
                    result.matriz[x][y] = g;
                }
                
                
            }
         }
        
        return result;
        
    }
    
    public static ImagePGM zoom2x(ImagePGM image){
        
        
        ImagePGM result = new ImagePGM(image.largura*2,image.altura*2);
        
        int nx,ny;
        
        for(int y=0;y<image.altura;y++){    
            for(int x=0; x<image.largura;x++){
                nx = x*2;
                ny = y*2;
                result.matriz[nx][ny] = image.matriz[x][y];
                result.matriz[nx][ny+1] = image.matriz[x][y];
                result.matriz[nx+1][ny] = image.matriz[x][y];
                result.matriz[nx+1][ny+1] = image.matriz[x][y];
            }
         }
        return result;
        
    }
    
    public static ImagePGM dzoom2x(ImagePGM image){
        
        
        ImagePGM result = new ImagePGM(image.largura/2,image.altura/2);
        
        int nx,ny,nv;
        
        for(int y=0;y<image.altura;y = y+2){    
            for(int x=0; x<image.largura;x = x+2){
                nx = x/2;
                ny = y/2;
                nv = image.matriz[x][y] + image.matriz[x+1][y] + image.matriz[x][y+1] + image.matriz[x+1][y+1];
                result.matriz[nx][ny] = nv/4;  
            }
         }
        return result;
        
    }
    
    public static ImagePGM negativo(ImagePGM image){
    
        ImagePGM matrizNegativo = new ImagePGM(image.largura,image.altura);
        int novoValor;
        for(int y=0;y<image.altura;y++){
            for(int x=0; x<image.largura;x++){
                novoValor = 255 - image.matriz[x][y];
                matrizNegativo.matriz[x][y] = novoValor;
                
            }
        }
        return matrizNegativo;
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
    
    public static ImagePGM filtroLaplaciano(ImagePGM image){
        
        ImagePGM result = new ImagePGM(image.largura,image.altura);
        ImagePGM mascara = new ImagePGM(3,3);
        mascara.matriz[0][0] = 0;
        mascara.matriz[0][1] = 1;
        mascara.matriz[0][2] = 0;
        mascara.matriz[1][0] = 0;
        mascara.matriz[1][1] = -4;
        mascara.matriz[1][2] = 0;
        mascara.matriz[2][0] = 0;
        mascara.matriz[2][1] = 1;
        mascara.matriz[2][2] = 0;
                
             
        
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
    

    
    public static ImagePGM filtroHighboost(ImagePGM image,int dim,int k){
        
        ImagePGM imageResult = new ImagePGM(image.largura,image.altura);
        ImagePGM masc;
        
        masc = subtrair(image,filtroMedia(image,dim));
        
        int soma;  
        for(int y=0;y<image.altura;y++){
            for(int x=0; x<image.largura;x++){
              soma = image.matriz[x][y] + (k * masc.matriz[x][y]);
              if(soma>255){
                  soma = 255;
              }
              imageResult.matriz[x][y] = soma;
            }
        }
        return imageResult;
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
    
    public static ImagePGM eqHistograma(ImagePGM image){
        
        float histograma[]  = new float[256];   
        int hist[] = histograma(image);
        
        ImagePGM imageEq = new ImagePGM(image.largura,image.altura);
        
        float soma=0;
         for(int x=0;x<256;x++){
             histograma[x] = ((float)hist[x]/(float)(image.altura*image.largura));
          
             soma=0;
             for(int y=0;y<=x;y++){
                 soma+=histograma[y];
             }
             hist[x] = (int) Math.abs(255*soma);
             
         }
         
        for(int y=0;y<image.altura;y++){
            for(int x=0; x<image.largura;x++){
               imageEq.matriz[x][y] = hist[image.matriz[x][y]];
            }
        }
        
        return imageEq;
    }
}
