/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import projetopdi.Console;

/**
 *
 * @author gusta
 */
public class ConvertImageMatrizPPM {
    public static ImagePPM lerArq(File file){
        
        ImagePPM image;
        
        try {
            Scanner scanner = new Scanner(file);
            String linha;
            int altura;
            int largura;
            int valorMaximo;
            
            linha = scanner.nextLine(); //tipo do formato
            Console.setConsole("Abrir", "Formato: " + linha);
            
    
            linha = scanner.nextLine(); //comment
            Console.setConsole("Abrir", "Comentario: " + linha);
            
            largura = scanner.nextInt();
            altura = scanner.nextInt();
            Console.setConsole("Abrir", "Dimensão: " + largura + "," + altura);

            valorMaximo = scanner.nextInt();
            Console.setConsole("Abrir", "Valor Maximo: " + valorMaximo);
            
            
            image = new ImagePPM(largura,altura);
            
            for(int y=0;y<altura;y++){
                for(int x=0; x<largura;x++){
                    image.addMatriz(x, y, scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
                }
            }
            return image;
       
        } catch (FileNotFoundException ex) {
            Console.setConsole("ERRO", "Arquivo não encontrado" );
            return null;
        }
  
    }
    public static void salvarArq (File file, ImagePPM image){
        try {
                FileWriter arq = new FileWriter(file);
                PrintWriter gravarArq = new PrintWriter(arq);
                gravarArq.printf("P3\n");
                gravarArq.printf("#Resultado Operação\n");
                gravarArq.printf(image.largura + " " + image.altura+"\n");
                gravarArq.printf("255\n");
                
                for(int y=0;y<image.altura;y++){
                  for(int x=0; x<image.largura;x++){
                       gravarArq.printf("%d %d %d\n",image.matriz[x][y][0],image.matriz[x][y][1],image.matriz[x][y][2]);
                    }
                }
                
                arq.close();
                Console.setConsole("Salvar", "Imagem Salva: " + file.getPath());
            } catch (IOException ex) {
                Console.setConsole("ERRO", "Arquivo não criado");
       
            }
        
    } 
}
