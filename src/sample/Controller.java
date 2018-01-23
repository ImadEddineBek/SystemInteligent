package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Stage primaryStage = Main.primaryStage;

    public void loadMaze(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("Maze/sample.fxml"));
        sample.Maze.Controller controller = fxmlLoader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void loadThief(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Thief/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void loadFunctionOptimization(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FunctionMax/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void loadMapColoring(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MapColoring/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}

