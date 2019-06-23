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
public class ImagePPM {
    public int largura;
    public int altura;
    public int[][][] matriz;
    
    
    public ImagePPM(int largura,int altura){
        this.largura = largura;
        this.altura = altura;
        matriz = new int[this.largura][this.altura][3];
        
    }
    
    public void addMatriz(int x,int y,int r, int g, int b){
        matriz[x][y][0] = r;
        matriz[x][y][1] = g;
        matriz[x][y][2] = b;   
    }
}
