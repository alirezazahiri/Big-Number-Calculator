package com.main_dir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class GraphController extends MainController implements Initializable {

    @FXML
    protected AreaChart<Double, Double> areaGraph;
    @FXML
    protected MyGraph areaMathsGraph;
    @FXML
    protected TextField x_coef, constant;
    @FXML Button backBtn;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        areaMathsGraph = new MyGraph(areaGraph, 10);
        areaGraph.setVisible(true);
    }
    private void plotLine(Function<Double, Double> function) {
        if (areaGraph.isVisible()) {
            areaMathsGraph.plotLine(function);
        }
    }
    @FXML
    public void plot_function(ActionEvent event){
        System.out.println(x_coef.getText() + "x + " + constant.getText());
        plotLine(x -> Double.parseDouble(x_coef.getText()) * x + Double.parseDouble(constant.getText()));
    }
    @FXML
    private void handleBack(ActionEvent event) {
        backBtn.getScene().getWindow().hide();
    }
}