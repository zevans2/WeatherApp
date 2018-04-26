import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.WeatherStation;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/sample.fxml"));
        primaryStage.setTitle("Weather");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //Create Weather station that holds functions needed to interface with GUI
        System.out.println("Connecting to Weather Station");
        WeatherStation station = new WeatherStation();
        System.out.println("Connected: "+station.status);
        launch(args);
    }
}
