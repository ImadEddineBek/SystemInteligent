package sample.Thief;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Controller {
    public TextArea objectsTA;
    public Label result;
    public TextField maxTF;
    final double K = 1.38064852 * Math.pow(10, -23);

    public void sample(ActionEvent actionEvent) {
        objectsTA.setText("gold : 50/100 \nTV: 20/3000 \nPC: 30 / 1000 \ncouch : 30 / 5000\ntoothPaste : 2 / 10\nring : 30 / 10\njacket : 20 / 20\nchair : 2 / 50");
    }

    public void solve(ActionEvent actionEvent) {
        String text = maxTF.getText();
        if (text.equals("")) result.setText("add the max");
        else {
            result.setText("click sample or manually add them");
            int max = Integer.parseInt(text);
            ArrayList<Object> objects = new ArrayList<>();
            if (!objectsTA.getText().equals("")) {
                String[] details = objectsTA.getText().split("\n");
                for (String detail : details) {
                    String[] split = detail.split(":");
                    String[] split1 = split[1].split("/");
                    objects.add(new Object(split[0], Integer.parseInt(split1[0].trim()), Integer.parseInt(split1[1].trim())));
                }
                ArrayList<Object> currentSolution = new ArrayList<>();
                ArrayList<Object> bestSolution = new ArrayList<>();
                double temerature = 1000;
                while (temerature > 1) {
                    ArrayList<Object> newSolution = generateNew(objects, currentSolution, max);
                    if (value(newSolution) > value(currentSolution)) {
                        currentSolution = new ArrayList<>(newSolution);
                    } else {
                        double deltaEnergie =  (value(newSolution) - value(currentSolution)*1.1);
                        if (Math.exp(-deltaEnergie / (temerature))<2)
                            currentSolution = new ArrayList<>(newSolution);
                    }
                    if (value(currentSolution) > value(bestSolution)) {
                        bestSolution = new ArrayList<>(currentSolution);
                        System.out.println(Arrays.deepToString(bestSolution.toArray()));
                    }
                    temerature = schedule(temerature);
                }
                result.setText(solution(bestSolution));
            }
        }
    }

    private String solution(ArrayList<Object> bestSolution) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bestSolution.size(); i++) {
            if ((i + 1) % 2 == 0) sb.append("\n").append(bestSolution.get(i));
            else sb.append(bestSolution.get(i));
        }
        return sb.toString();
    }

    private ArrayList<Object> generateNew(ArrayList<Object> objects, ArrayList<Object> currentSolution, int max) {
        ArrayList<Object> nonAssignedObjects = new ArrayList<>();
        for (Object object : objects) {
            if (!contains(currentSolution, object.getId())) {
                nonAssignedObjects.add(object);
            }
        }
        Collections.shuffle(nonAssignedObjects);
        if (!nonAssignedObjects.isEmpty()) {
            currentSolution.add(new Object(nonAssignedObjects.get(0)));
            int weight = weight(currentSolution);
            while (weight > max) {
                Collections.shuffle(currentSolution);
                currentSolution.remove(0);
                weight = weight(currentSolution);
            }
        }
        return currentSolution;
    }

    private int weight(ArrayList<Object> currentSolution) {
        int weight = 0;
        for (Object object : currentSolution) {
            weight += object.getWeight();
        }
        return weight;
    }

    private int value(ArrayList<Object> currentSolution) {
        int value = 0;
        for (Object object : currentSolution) {
            value += object.getValue();
        }
        return value;
    }

    private boolean contains(ArrayList<Object> currentSolution, String id) {
        for (Object object : currentSolution) {
            if (object.getId().equals(id)) return true;
        }
        return false;
    }

    private double schedule(double temp) {
        return temp * 0.997;
    }
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}