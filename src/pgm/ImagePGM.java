/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

/**
 *
 * @author gusta
 */
public class ImagePGM {
    
    public int largura;
    public int altura;
    public int[][] matriz;
    
    
    public ImagePGM(int largura,int altura){
        this.largura = largura;
        this.altura = altura;
        matriz = new int[this.largura][this.altura];
    }
    
    
}
