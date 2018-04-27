import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("app/views/sample.fxml"));
        primaryStage.setTitle("WeatherScope");
        primaryStage.setScene(new Scene(root, 620, 395));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //Create Weather station that holds functions needed to interface with GUI
        launch(args);
    }



}
