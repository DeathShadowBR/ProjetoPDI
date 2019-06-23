/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;


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
public class ConvertImageMatrizPGM {
    public static ImagePGM lerArq(File file){
               
        ImagePGM image = null;
 
        try {
            Scanner scanner = new Scanner(file);
            String linha;
            int valor;
            int largura;
            int altura;
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
            
            image = new ImagePGM(largura,altura);
            
            for(int y=0;y<altura;y++){
                for(int x=0; x<largura;x++){
                    valor = scanner.nextInt();
                    image.matriz[x][y] = valor;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado");
        }
        return image;
    }
    
   
    
    public static void salvarArq (File file, ImagePGM image){
        try {
            
                FileWriter arq = new FileWriter(file);
                PrintWriter gravarArq = new PrintWriter(arq);
                
                gravarArq.printf("P2\n");
                gravarArq.printf("#Resultado Operação\n");
                gravarArq.printf( image.largura + " " + image.altura +"\n");
                gravarArq.printf("255\n");
                
                for(int y=0;y<image.altura;y++){
                  for(int x=0; x<image.largura;x++){
                       gravarArq.printf("%d\n",image.matriz[x][y]);
                    }
                }
                arq.close();
                Console.setConsole("Salvar", "Imagem Salva: " + file.getPath());
            } catch (IOException ex) {
                 Console.setConsole("ERRO", "Arquivo não criado");
       
            }
        
    }
}
