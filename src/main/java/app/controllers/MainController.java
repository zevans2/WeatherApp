package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import app.models.WeatherStation;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MainController {

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
    @FXML
    protected Image image;


    public void handleSubmission(javafx.event.ActionEvent actionEvent) {
        /*System.out.println(stateInput.getText());
        System.out.println(cityInput.getText());
        System.out.println(zipInput.getText());*/

        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(stateInput.getText());
        inputs.add(cityInput.getText());
        inputs.add(zipInput.getText());

        for (String input:inputs) {
            System.out.println(input);
        }

        WeatherStation station = new WeatherStation(inputs);
        outputDisplayArea.setText(station.printWeatherReport());
    }
}
