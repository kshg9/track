package com.fin.track.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class statsController implements Initializable {

    @FXML
    private ToggleButton button_1;
    @FXML
    private ToggleButton button_2;
    @FXML
    private ToggleButton button_3;
    @FXML
    private ToggleGroup group1;
    @FXML
    private LineChart<String, Number> stats;

    @FXML
    void toggleButton(ActionEvent event) {
        try {
            if (stats == null) {
                System.out.println("Error: LineChart not initialized");
                return;
            }
            
            stats.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            
            if (event.getSource() == button_1) {
                series.setName("Income");
                series.getData().addAll(
                    new XYChart.Data<>("Jan", 2300),
                    new XYChart.Data<>("Feb", 2900),
                    new XYChart.Data<>("Mar", 2600),
                    new XYChart.Data<>("Apr", 3200)
                );
            } else if (event.getSource() == button_2) {
                series.setName("Expenses");
                series.getData().addAll(
                    new XYChart.Data<>("Jan", 1500),
                    new XYChart.Data<>("Feb", 1800),
                    new XYChart.Data<>("Mar", 1200),
                    new XYChart.Data<>("Apr", 1900)
                );
            } else if (event.getSource() == button_3) {
                series.setName("Savings");
                series.getData().addAll(
                    new XYChart.Data<>("Jan", 800),
                    new XYChart.Data<>("Feb", 1100),
                    new XYChart.Data<>("Mar", 1400),
                    new XYChart.Data<>("Apr", 1300)
                );
            }
            
            stats.getData().add(series);
        } catch (Exception e) {
            System.out.println("Error updating chart: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListners();
    }

    private void addListners() {
        group1.selectedToggleProperty().addListener((obs, old, newtoggle) -> {
            if (newtoggle == null) {
                old.setSelected(true);
            }
        });
    }
}
