/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopdi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXMLPopupController implements Initializable{

    public static FXMLPopupController instancia;
    @FXML
    public Label popupLabel1;

    @FXML
    public TextField popupInput1;

    @FXML
    public Label popupLabel2;

    @FXML
    public TextField popupInput2;

    @FXML
    public Label popupLabel3;

    @FXML
    public TextField popupInput3;

    @FXML
    public Label popupLabel4;

    @FXML
    public TextField popupInput4;

    @FXML
    public Button popupBtnOk;
    
    public String input1;
    public String input2;
    public String input3;
    public String input4;

    
    @FXML
    void onClickOk(ActionEvent event) {
        input1 = popupInput1.getText();
        if(popupInput2.isVisible()){
            input2 = popupInput2.getText();
        }
         if(popupInput3.isVisible()){
            input3 = popupInput3.getText();
        }
        if(popupInput4.isVisible()){
            input4 = popupInput4.getText();
        }
       Stage stage = (Stage) ( (Button) event.getTarget()).getScene().getWindow();
       
       stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        instancia =this;
    }    
}