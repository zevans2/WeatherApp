package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import app.models.WeatherStation;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class MainController {

    @FXML
    public ImageView logo;
    @FXML
    protected TextArea outputDisplayArea;
    @FXML
    protected TextField cityInput;
    @FXML
    protected TextField stateInput;
    @FXML
    protected TextField zipInput;
    @FXML
    protected Button submitButton;
    /*@FXML
    protected Image image;
    */

    public void handleSubmission() {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(stateInput.getText());
        inputs.add(cityInput.getText());
        inputs.add(zipInput.getText());

        for (String input:inputs) {
            System.out.println(input);
        }

        WeatherStation station = new WeatherStation(inputs);
        outputDisplayArea.setText(station.generateReport());
    }
}
