/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopdi;

import pgm.ConvertImageMatrizPGM;
import pgm.OperacoesPGM;
import pgm.ImagePGM;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import ppm.ConvertImageMatrizPPM;
import ppm.ImagePPM;
import ppm.OperacoesPPM;

/**
 *
 * @author gusta
 */
public class FXMLMainController implements Initializable {
    
    public static FXMLMainController instancia;
    
    @FXML
    private BorderPane borderpane;
    @FXML
    private TextArea textArea;
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private Button btnOpenImage1;
    
 
    @FXML
    private Menu menuOperacoesPPM;
    
    @FXML
    private Menu menuOperacoesPGM;

    
    
    private File file;
    
    private ImagePGM imagePGM;
    private ImagePGM imageResultadoPGM;
    
    private ImagePPM imagePPM;
    private ImagePPM imageResultadoPPM;
    
    private int type; //0 -> pgm 1-> ppm
    
    private FXMLPopupController popup = FXMLPopupController.instancia;
    
    // Menu 
    
    
    
   @FXML
    void MenuSoma(ActionEvent event) throws IOException {
        if(imageResultadoPGM != null){
             Console.setConsole("Operacoes","Somando Image1 em Resultado");
            imageResultadoPGM = OperacoesPGM.somar(imagePGM, imageResultadoPGM);
            imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
        }else{
            Console.setConsole("ERRO","É necessario uma imagem Resultado para aplicar a Soma");
        }
       
    }
    
    @FXML
    void MenuBrilho(ActionEvent event) throws IOException {
         Console.setConsole("Operacoes","Aplicando Brilho");
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/screens/Popup.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
       
       
        popup().popupLabel1.setText("Valor do Brilho"); 
        
        stage.showAndWait();
       
        int valor = Integer.parseInt(popup().input1);

        imageResultadoPGM = OperacoesPGM.brilhoSoma(imagePGM, valor);
   
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
        
    }
    
    @FXML
    void MenuSubtrair(ActionEvent event) throws IOException {
        if(imageResultadoPGM != null){
            Console.setConsole("Operacoes","Subtraindo Image1 em Resultado");
            imageResultadoPGM = OperacoesPGM.subtrair(imagePGM, imageResultadoPGM);
            imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
        }
        
        
    }
    
      @FXML
    void MenuFiltroMedia(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Aplicando filtro Media ");
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/screens/Popup.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
       
       
        popup().popupLabel1.setText("Dimensão da Mascara"); 
        
        stage.showAndWait();
       
        int valor = Integer.parseInt(popup().input1);

        imageResultadoPGM = OperacoesPGM.filtroMedia(imagePGM, valor);
   
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }
    
    
    @FXML
    void MenuFiltroMediana(ActionEvent event) throws IOException {
         Console.setConsole("Operacoes","Aplicando filtro Mediana ");
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/screens/Popup.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);

        popup().popupLabel1.setText("Dimensão da Mascara: "); 

        stage.showAndWait();
       
        int dim = Integer.parseInt(popup().input1);

        imageResultadoPGM = OperacoesPGM.filtroMediana(imagePGM, dim);
   
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }
    
    @FXML
    void MenuFiltroHighboost(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Aplicando filto HighBoost ");
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/screens/Popup.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
       
       
        popup().popupLabel1.setText("Dimensão da Mascara: "); 
        popup().popupLabel2.setVisible(true);
        popup().popupInput2.setVisible(true);
        popup().popupLabel2.setText("Valor K: "); 
        
        stage.showAndWait();
       
        int dim = Integer.parseInt(popup().input1);
        int k = Integer.parseInt(popup().input1);
        
        popup().popupLabel2.setVisible(false);
        popup().popupInput2.setVisible(false);
        
        imageResultadoPGM = OperacoesPGM.filtroHighboost(imagePGM, dim,k);
   
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }
    
    @FXML
    void MenuFiltroLaplaciano(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Aplicando Laplaciano ");
        imageResultadoPGM = OperacoesPGM.filtroLaplaciano(imagePGM);
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }
    
    @FXML
    void MenuHistograma(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Vizualização do Histograma ");
        if(imagePGM == null) return;
        
        int hist[] = OperacoesPGM.histograma(imagePGM);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/screens/ChartFXML.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLChartController.instancia.data = hist;
        
        FXMLChartController.instancia.chart(0);
        stage.showAndWait();
        
    }
    
     @FXML
    void MenuEqHistograma(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Aplicando Equalização de Histograma");
        imageResultadoPGM = OperacoesPGM.eqHistograma(imagePGM);
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }
    
    
    @FXML
    void MenuBinarizacao(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Aplicando Binarização ");
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/screens/Popup.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
       
       
        popup().popupLabel1.setText("Valor: "); 
        
        stage.showAndWait();
       
        int valor = Integer.parseInt(popup().input1);

        imageResultadoPGM = OperacoesPGM.binarizacao(imagePGM, valor);
   
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }

    @FXML
    void MenuDZoom(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Aplicando Zoom - ");
        imageResultadoPGM = OperacoesPGM.dzoom2x(imagePGM);
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }

    @FXML
    void MenuZoom2x(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Aplicando Zoom + ");
        imageResultadoPGM = OperacoesPGM.zoom2x(imagePGM);
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }
    @FXML
    void MenuNegativo(ActionEvent event) throws IOException {
         Console.setConsole("Operacoes","Aplicando Negativo");
         imageResultadoPGM = OperacoesPGM.negativo(imagePGM);
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));    
    }
    @FXML
    void MenuGamma(ActionEvent event) throws IOException {
        Console.setConsole("Operacoes","Aplicando Gamma");
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/screens/Popup.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
       
       
        popup().popupLabel1.setText("Valor: "); 
        
        stage.showAndWait();
       
        int valor = Integer.parseInt(popup().input1);

        imageResultadoPGM = OperacoesPGM.gammaLog(imagePGM, valor);
   
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPGM.matriz));
    }


    @FXML
    void MenuCanalB(ActionEvent event) throws IOException {
        imageResultadoPPM = OperacoesPPM.extrairCanal(imagePPM,2);
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPPM.matriz));  
    }

    @FXML
    void MenuCanalG(ActionEvent event) throws IOException {
        imageResultadoPPM = OperacoesPPM.extrairCanal(imagePPM,1);
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPPM.matriz));  
    }

    @FXML
    void MenuCanalR(ActionEvent event) throws IOException {
        imageResultadoPPM = OperacoesPPM.extrairCanal(imagePPM,0);
        imageView2.setImage(convertMatrizToPNG(this.imageResultadoPPM.matriz));  
    }
        @FXML
    void MenuHistogramaPPM(ActionEvent event) throws IOException {
        if(imagePPM == null) return;

            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/screens/ChartFXML.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            

            FXMLChartController.instancia.data = OperacoesPPM.histograma(imagePPM,0);
            FXMLChartController.instancia.data1 = OperacoesPPM.histograma(imagePPM,1);
            FXMLChartController.instancia.data2 = OperacoesPPM.histograma(imagePPM,2);

            FXMLChartController.instancia.chart(1);
            stage.showAndWait();
    }
    //------------------------------------------------------------------------>
    
    
    @FXML
    void onClickOpen(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
  
        fileChooser.setTitle("Abrir Arquivo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PGM - Portable GrayMap (*.pgm)", "*.pgm"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PPM - Portable PixelMap (*.ppm)", "*.ppm"));
        file =  fileChooser.showOpenDialog(((Button)event.getTarget()).getScene().getWindow());

        
        if(file!=null){
            try {
                String name = file.getName();
                String extension = name.substring(name.lastIndexOf("."));
                
                if(extension.equals(".pgm")){
                   Console.setConsole("Abrir","Imagem PGM escolhida do diretorio: " + file.getAbsolutePath());
                   this.imagePGM = ConvertImageMatrizPGM.lerArq(file);
                   imageView1.setImage(convertMatrizToPNG(this.imagePGM.matriz));
                   menuOperacoesPGM.setDisable(false);
                   menuOperacoesPPM.setDisable(true);
                   type = 0;
                   
                }else{
                   Console.setConsole("Abrir","Imagem PPM escolhida do diretorio: " + file.getAbsolutePath());
                   this.imagePPM = ConvertImageMatrizPPM.lerArq(file);
                   imageView1.setImage(convertMatrizToPNG(this.imagePPM.matriz));
                   menuOperacoesPPM.setDisable(false);
                   menuOperacoesPGM.setDisable(true); 
                   type = 1;
                }
                
          
            } catch (IOException ex) {
                Console.setConsole("ERRO","Erro ao abrir a Imagem");
            }
        }
    }
    
    @FXML
    void onClickSave(ActionEvent event) {
       FileChooser chooser = new FileChooser();
       if(type == 0){
           chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PGM - Portable GrayMap (*.pgm)", "*.pgm"));
           File file = chooser.showSaveDialog(((Button)event.getTarget()).getScene().getWindow());
            if(file!=null){
                ConvertImageMatrizPGM.salvarArq(file, imageResultadoPGM);
                 Console.setConsole("Salvar","Imagem Salva PGM no caminho" + file.getAbsolutePath());
            }
       }else{
          chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PPM - Portable PixelMap (*.ppm)", "*.ppm"));
             File file = chooser.showSaveDialog(((Button)event.getTarget()).getScene().getWindow());
            if(file!=null){
                ConvertImageMatrizPPM.salvarArq(file, imageResultadoPPM);
                Console.setConsole("Salvar","Imagem Salva PPM no caminho" + file.getAbsolutePath());
            }
       }
        
        
    }
    
    @FXML
    void onClickChange(ActionEvent event) throws IOException {
        Console.setConsole("Troca","Imagem Resultado trocada com Imagem 1");
        trocar();
    }
 

    //--------------------------------------------------------------------->
    
    
    //necessario para exibir na interface ( JavaFX não suporta .pgm e .ppm )
    public Image convertMatrizToPNG(int[][] matriz) throws IOException{
        int largura = matriz.length;
        int altura = matriz[0].length;
        
        BufferedImage image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        for(int y=0; y<altura; y++){
            for(int x=0; x<largura; x++){
                 int u = matriz[x][y];
                 Color color = new Color(u,u,u);
                 image.setRGB(x,y,color.getRGB());
            }
        }

          File output = new File("image.png");
          ImageIO.write(image, "png", output);
          Image img = SwingFXUtils.toFXImage(ImageIO.read(output), null);
          
          return img;
    }
    
    //necessario para exibir na interface ( JavaFX não suporta .pgm e .ppm )
    public Image convertMatrizToPNG(int[][][] matriz) throws IOException{
        int largura = matriz.length;
        int altura = matriz[0].length;
        
        BufferedImage image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        for(int y=0; y<altura; y++){
            for(int x=0; x<largura; x++){
                 int r = matriz[x][y][0];
                 int g = matriz[x][y][1];
                 int b = matriz[x][y][2];
                 Color color = new Color(r,g,b);
                 image.setRGB(x,y,color.getRGB());
            }
        }

          File output = new File("image.png");
          ImageIO.write(image, "png", output);
          Image img = SwingFXUtils.toFXImage(ImageIO.read(output), null);
          
          return img;
    }
    
    private FXMLPopupController popup(){
        return FXMLPopupController.instancia;
    }
    
    private void trocar() throws IOException{
        if(type == 0){
            if(imageResultadoPGM == null) return;
            imagePGM = imageResultadoPGM;
            imageResultadoPGM = null;
            imageView1.setImage(convertMatrizToPNG(this.imagePGM.matriz));
            imageView2.setImage(null);
        }else{
           if(imageResultadoPPM == null) return;
            imagePPM = imageResultadoPPM;
            imageResultadoPPM = null;
            imageView1.setImage(convertMatrizToPNG(this.imagePPM.matriz));
            imageView2.setImage(null); 
        }
        
    }
    //console da aplicação
    public void setConsole(String message){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
        Platform.runLater(new Runnable() {
            @Override public void run() {
                textArea.appendText( ft.format(dNow) + "->" + message);
            }
        });  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        instancia = this;
        
        menuOperacoesPGM.setDisable(true);
        menuOperacoesPPM.setDisable(true);

        
    }    
    
}
