package sample.FunctionMax;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {


    public ChoiceBox<String> typeCB;
    public ChoiceBox<Integer> lengthCB;
    public Label result;
    public ChoiceBox<Integer> iterations;
    public ChoiceBox<Integer> numberOfIndivTF;
    public LineChart lineChart;
    public ChoiceBox<String> functionCB;
    public NumberAxis xLineChart;
    public NumberAxis yLineChart;
    int individualSize =0;
    public ArrayList<Individual> bests = new ArrayList<>();
    public void solve() {
        int populationSize = numberOfIndivTF.getValue();
        individualSize = lengthCB.getValue();
        int numberOfItertations = iterations.getValue();
        if (typeCB.getValue().equals("min")) {
            Individual.max = false;
        }else Individual.max = true;
                List<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            individuals.add(new Individual(new Random().nextInt((int) Math.pow(10, individualSize))));
        }
        Collections.sort(individuals);
//        Thread thread = new Thread(()->{
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
//        });
        if (lineChart.getData().size()>1)
        lineChart.getData().remove(1);
        XYChart.Series point = new XYChart.Series();
        point.setName("Current");
        point.getData().add(new XYChart.Data(individuals.get(0).getValue(), individuals.get(0).getFitness()));
        lineChart.getData().add(point);
        point.getNode().setStyle("-fx-stroke-width: 10px;" +
                "-fx-stroke: #000000;");
        result.setText(""+individuals.get(0));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        typeCB.getItems().addAll(FXCollections.observableArrayList("max", "min"));
        typeCB.valueProperty().setValue("max");
        lengthCB.getItems().addAll(FXCollections.observableArrayList(2,3,4));
        lengthCB.valueProperty().setValue(3);
        iterations.getItems().addAll(FXCollections.observableArrayList(10, 20, 30, 40, 50));
        iterations.valueProperty().setValue(50);
        numberOfIndivTF.getItems().addAll(FXCollections.observableArrayList(50, 100, 500));
        numberOfIndivTF.valueProperty().setValue(500);
        individualSize = lengthCB.getValue();
        functionCB.valueProperty().addListener((observable, oldValue, newValue) -> {
            individualSize = lengthCB.getValue();
            lineChart.getData().clear();
            switch (newValue){
                case "0.5 * x^2 + x - 1": Individual.function=true;break;
                default: Individual.function=false;
            }
            System.out.println(Individual.function);
            //creating the chart
            lineChart.setTitle("Stock Monitoring, 2010");
            //defining a series
            XYChart.Series series = new XYChart.Series();
            series.setName("My portfolio");
            //populating the series with data
            for (int i = 0; i < (int) Math.pow(10, individualSize); i++) {
                series.getData().add(new XYChart.Data(i, Individual.getY(i)));
            }
            lineChart.getData().add(series);

        });
        functionCB.getItems().addAll(FXCollections.observableArrayList("100*sin(x/5) + 100*cos (sqrt(3)x/20) + ((-x)^2)/1000","0.5 * x^2 + x - 1"));
        functionCB.valueProperty().setValue("0.5 * x^2 + x - 1");

    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}

