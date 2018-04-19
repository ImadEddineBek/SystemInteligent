package sample.MapColoring;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sample.Main;
import java.io.IOException;
import java.net.URL;
import java.util.*;
public class Controller implements Initializable {
    public ColorPicker color1;
    public ColorPicker color2;
    public ColorPicker color3;
    public ChoiceBox<Integer> countries;
    public TextArea TAconstraints;
    public Label result;
    public ChoiceBox<String> type;
    public WebView webView;
    private Stage primaryStage = Main.primaryStage;
    private Map<Integer, Integer> assignment = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> constraints = new HashMap<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        color1.setValue(Color.GREEN);
        color2.setValue(Color.RED);
        color3.setValue(Color.BLUE);
        countries.valueProperty().setValue(4);
        countries.setItems(FXCollections.observableArrayList(
                4, 5, 6, 7, 9, 10,11,12,13,14,15,16)
        );
        type.valueProperty().setValue("Backtracking");
        type.setItems(FXCollections.observableArrayList("Backtracking", "FC/MRV"));
    }

    public void check() {
        Random random = new Random();
        if (color1.getValue().equals(color2.getValue())) {
            color2.setValue(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            check();
        }
        if (color1.getValue().equals(color3.getValue())) {
            color3.setValue(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            check();
        }
        if (color2.getValue().equals(color3.getValue())) {
            color3.setValue(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            check();
        }
    }

    public void solve() {
        ArrayList<ArrayList<Integer>> domains = new ArrayList<>();
        ArrayList<Integer> allColors = new ArrayList<>();
        allColors.add(1);
        allColors.add(2);
        allColors.add(3);
        for (int i = 0; i < countries.getValue(); i++) {
            constraints.put(i, new ArrayList<>());
        }
        fillConstraints();
        assignment = new HashMap<>();
        for (int i = 0; i < countries.getValue(); i++) {
            domains.add(new ArrayList<>(allColors));
        }

        result.setText("the result is :\n" + backtrack(assignment, domains));
    }

    private void fillConstraints() {
        if (TAconstraints.getText().equals("")) return;
        for (String s : TAconstraints.getText().split("\n")) {
            String[] split = s.split(":");
            int country = Integer.parseInt(split[0].trim());
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (String s1 : split[1].split(",")) {
                int neighbor = Integer.parseInt(s1.trim());
                neighbors.add(neighbor);
            }
            constraints.put(country, neighbors);
        }
    }


    private String backtrack(Map<Integer, Integer> assignment, ArrayList<ArrayList<Integer>> domains) {
        print(assignment);
        if (done(assignment)) return solution(assignment);
        int index = selectCountry(domains,assignment);// MRV
        for (Integer color : domains.get(index)) {
            assignment.put(index, color);
            domains = fixDomains(domains,assignment);// FC
            if (consistant(assignment, constraints)) {
                String result = backtrack(assignment, domains);
                if (result != null) return result;

            }
            assignment.remove(index);
        }
        return null;
    }

    private int selectCountry(ArrayList<ArrayList<Integer>> domains, Map<Integer, Integer> assignment) {
        switch (type.getValue()) {
            case "FC/MRV":
                return MRV(domains,assignment);
            default:
                return this.assignment.size();
        }
    }

    private int MRV(ArrayList<ArrayList<Integer>> domains, Map<Integer, Integer> assignment) {
        ArrayList<Integer> unassigned = new ArrayList<>();
        for (int i = 0; i < countries.getValue(); i++) {
            if (!assignment.containsKey(i)) unassigned.add(i);
        }
        int min = 100000, selected = -1;
        for (int i = 0; i < unassigned.size(); i++) {
            int country = unassigned.get(i);
            if (domains.get(country).size() < min) {
                min = domains.get(country).size();
                selected = country;
            }
        }
        return selected;
    }

    private ArrayList<ArrayList<Integer>> fixDomains(ArrayList<ArrayList<Integer>> domains, Map<Integer, Integer> assignment) {
        if (type.getValue().equals("FC/MRV")) {
            domains = new ArrayList<>();
            ArrayList<Integer> allColors = new ArrayList<>();
            allColors.add(1);
            allColors.add(2);
            allColors.add(3);
            for (int i = 0; i < countries.getValue(); i++) {
                domains.add(new ArrayList<>(allColors));
            }
            ArrayList<ArrayList<Integer>> finalDomains = domains;
            assignment.forEach((key, value) -> {
                ArrayList<Integer> coutriesToFix = constraints.getOrDefault(key, new ArrayList<>());
                for (int i = 0; i < coutriesToFix.size(); i++) {
                    int countryToFix = coutriesToFix.get(i);
                    finalDomains.get(countryToFix).remove(this.assignment.get(key));

                }
            });
        }
        return domains;
    }

    private void printDomains(ArrayList<ArrayList<Integer>> domains) {
        for (int i = 0; i < domains.size(); i++) {
            System.out.println("the country " + i + " has " + Arrays.toString(domains.get(i).toArray()));
        }
    }

    private boolean consistant(Map<Integer, Integer> assignment, Map<Integer, ArrayList<Integer>> constraints) {
        final Boolean[] isConstanat = {Boolean.TRUE};
        assignment.forEach((Integer key, Integer value) -> {
            for (Integer contraint : constraints.get(key)) {
                if (assignment.getOrDefault(contraint, -3).equals(value)) isConstanat[0] = Boolean.FALSE;
            }
        });
        return isConstanat[0];
    }

    private String solution(Map<Integer, Integer> assignment) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        StringBuilder finalSb = sb;
        assignment.forEach((key, value) -> {
            if (key%4==0 && key !=0)
            finalSb.append("\n{ ").append(key).append(" = ").append(getColorValue(value)).append(" } ,");
            else
                finalSb.append("{ ").append(key).append(" = ").append(getColorValue(value)).append(" } ,");
        });

        int index = sb.lastIndexOf(",");
        if (index != -1) {
            sb.deleteCharAt(index);
            sb.append(" ]");
        } else {
            sb = new StringBuilder();
            sb.append("no assignments yet");
        }
        return sb.toString();
    }

    private String getColorValue(Integer value) {
        switch (value) {
            case 1:
                return ColorUtils.getColorNameFromColor(color1.getValue());
            case 2:
                return ColorUtils.getColorNameFromColor(color2.getValue());
            case 3:
                return ColorUtils.getColorNameFromColor(color3.getValue());
            default:
                return "shit";
        }
    }

    private boolean done(Map<Integer, Integer> assignment) {
        return assignment.size() == countries.getValue();
    }

    private void print(Map<Integer, Integer> assignment) {
        System.out.println(solution(assignment));
//        System.out.println(afficher(constraints));
    }

    private String afficher(Map<Integer, ArrayList<Integer>> constraints) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        constraints.forEach((key, value) -> {
            sb.append("{ ").append(key).append(" = ").append(Arrays.deepToString(value.toArray())).append(" } ,");
        });
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(" ]");
        return sb.toString();
    }
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}

