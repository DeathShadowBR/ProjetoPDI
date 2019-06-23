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
    @FXML
    private LineChart<String,Number> lineChart;
    /**
     * Initializes the controller class.
     */
    
    public void chart(){
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        
        for(int i=0;i<data.length;i++){
            series.getData().add(new XYChart.Data<String,Number>(Integer.toString(i),data[i]));
        }
        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        instancia = this;
    }    
    
}
