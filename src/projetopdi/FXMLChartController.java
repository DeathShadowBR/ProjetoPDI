/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopdi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author gusta
 */
public class FXMLChartController implements Initializable {

    public static FXMLChartController instancia;
    public int[] data;
    public int[] data1;
    public int[] data2;
    @FXML
    private LineChart<String,Number> lineChart;
    /**
     * Initializes the controller class.
     * @param type
     */
    
    public void chart(int type){

        if(type == 0){ //pgm
           XYChart.Series<String,Number> series = new XYChart.Series<>();
           for(int i=0;i<data.length;i++){
                series.getData().add(new XYChart.Data<>(Integer.toString(i),data[i]));
           }
           lineChart.getData().add(series); 
        }else{ //ppm
           XYChart.Series<String,Number> series = new XYChart.Series<>();
           XYChart.Series<String,Number> series1 = new XYChart.Series<>();
           XYChart.Series<String,Number> series2 = new XYChart.Series<>();
           
           for(int i=0;i<data.length;i++){
                series.getData().add(new XYChart.Data<>(Integer.toString(i),data[i]));
                series1.getData().add(new XYChart.Data<>(Integer.toString(i),data1[i]));
                series2.getData().add(new XYChart.Data<>(Integer.toString(i),data2[i]));
           }
           lineChart.getData().add(series);
           lineChart.getData().add(series1);  
           lineChart.getData().add(series2);  
        }
        
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        instancia = this;
    }    
    
}
