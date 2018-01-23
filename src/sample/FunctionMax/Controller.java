package sample.FunctionMax;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {


    public TextField numberOfIndivTF;
    public ChoiceBox<String> typeCB;
    public ChoiceBox<Integer> lengthCB;
    public Label result;
    public ChoiceBox<Integer> iterations;

    public void solve() {
        int populationSize = Integer.parseInt(numberOfIndivTF.getText());
        int individualSize = lengthCB.getValue();
        int numberOfItertations = iterations.getValue();
        if (typeCB.getValue().equals("min")) {
            Individual.max = false;
        }
        List<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            individuals.add(new Individual(new Random().nextInt((int) Math.pow(10, individualSize))));
        }
        Collections.sort(individuals);
        for (int i = 0; i < numberOfItertations; i++) {
            for (int i1 = 0; i1 < individuals.size()-1; i1++) {
                Individual reproduce = Individual.reproduce(individuals.get(i1), individuals.get(i1 + 1), individualSize);
                if (new Random().nextInt() % (new Random().nextInt()+1) == 0)
                    reproduce.setValue((reproduce.getValue() + new Random().nextInt((int) Math.pow(10, individualSize))) % new Random().nextInt((int) Math.pow(10, individualSize)));
                individuals.add(reproduce);
                Collections.sort(individuals);
                individuals.remove(populationSize);
            }
        }
        result.setText("the best is "+individuals.get(0));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeCB.getItems().addAll(FXCollections.observableArrayList("max", "min"));
        typeCB.valueProperty().setValue("max");
        lengthCB.getItems().addAll(FXCollections.observableArrayList(2,3,4,5));
        lengthCB.valueProperty().setValue(3);
        iterations.getItems().addAll(FXCollections.observableArrayList(50, 100, 500, 1000, 5000));
        iterations.valueProperty().setValue(500);
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}

