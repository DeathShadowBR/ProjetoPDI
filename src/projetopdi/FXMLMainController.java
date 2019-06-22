/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopdi;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

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
 
    
    
    private File file;
    private ImagePGM image1;
    private ImagePGM imageResultado;
    private int type; //0 -> pgm 1-> ppm
    private FXMLPopupController popup = FXMLPopupController.instancia;
    
    // Menu 
    
    @FXML
    void menuOpen(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
  
        fileChooser.setTitle("Abrir Arquivo");
    
        file =  fileChooser.showOpenDialog(((MenuItem)event.getTarget()).getParentPopup().getScene().getWindow());
        
        
        
        if(file!=null){
            try {
                this.image1 = ConvertImageMatriz.lerArq(file);
                imageView1.setImage(convertMatrizToPNG(this.image1.matriz));
            } catch (IOException ex) {
                Console.setConsole("ERRO","Erro ao abrir a Imagem");
            }
        }
        
    }
    
    @FXML
    void MenuSave(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PGM - Portable GrayMap (*.pgm)", "*.pgm"));
        File file = chooser.showSaveDialog(((MenuItem)event.getTarget()).getParentPopup().getScene().getWindow());
        if(file!=null){
            ConvertImageMatriz.salvarArq(file, imageResultado);
        }
        
    }
    
   @FXML
    void MenuSoma(ActionEvent event) throws IOException {
     

        imageResultado = OperacoesPGM.somar(image1, imageResultado);
        imageView2.setImage(convertMatrizToPNG(this.imageResultado.matriz));
    }
    
    @FXML
    void MenuBrilho(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/screens/Popup.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
       
       
        popup().popupLabel1.setText("Valor do Brilho"); 
        
        stage.showAndWait();
       
        int valor = Integer.parseInt(popup().input1);

        imageResultado = OperacoesPGM.brilhoSoma(image1, valor);
   
        imageView2.setImage(convertMatrizToPNG(this.imageResultado.matriz));
        
    }
    
    @FXML
    void MenuSubtrair(ActionEvent event) throws IOException {
        imageResultado = OperacoesPGM.subtrair(image1, imageResultado);
        imageView2.setImage(convertMatrizToPNG(this.imageResultado.matriz));
        
    }
    
    
    //------------------------------------------------------------------------>
    
    
    @FXML
    void onClickOpen(ActionEvent event) {
       System.out.println(((Button) event.getTarget()).getId());
       
        FileChooser fileChooser = new FileChooser();
  
        fileChooser.setTitle("Abrir Arquivo");
    
        file =  fileChooser.showOpenDialog(((Button)event.getTarget()).getScene().getWindow());

        if(file!=null){
            try {
                this.image1 = ConvertImageMatriz.lerArq(file);
     
               imageView1.setImage(convertMatrizToPNG(this.image1.matriz));
          
            } catch (IOException ex) {
                Console.setConsole("ERRO","Erro ao abrir a Imagem");
            }
        }
    }
    
    @FXML
    void onClickSave(ActionEvent event) {
       FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PGM - Portable GrayMap (*.pgm)", "*.pgm"));
        File file = chooser.showSaveDialog(((MenuItem)event.getTarget()).getParentPopup().getScene().getWindow());
        if(file!=null){
            ConvertImageMatriz.salvarArq(file, imageResultado);
        }
    }
    
    @FXML
    void onClickChange(ActionEvent event) throws IOException {
        trocar();
    }
    
    //popup
    
   

    
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
    
    private FXMLPopupController popup(){
        return FXMLPopupController.instancia;
    }
    
    private void trocar() throws IOException{
        
        image1 = imageResultado;
        imageResultado = null;
        imageView1.setImage(convertMatrizToPNG(this.image1.matriz));
        imageView2.setImage(null);
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
        
    }    
    
}
