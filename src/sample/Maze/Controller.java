package sample.Maze;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.Maze.Maze.*;
import sample.Maze.search.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ChoiceBox<Integer> startxCB;
    public ChoiceBox<Integer> startyCB;
    public ChoiceBox<Integer> endxCB;
    public ChoiceBox<Integer> endyCB;
    public GridPane maze;
    public ChoiceBox<String> typeCB;

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void find(ActionEvent actionEvent) {
        final type[][] mazeType = new type[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mazeType[i][j] = getType(i, j);
            }
        }
        Integer startx = startxCB.getValue();
        Integer starty = startyCB.getValue();
        Integer endx = endxCB.getValue();
        Integer endy = endyCB.getValue();
        ArrayList<sample.Maze.GeneralProblem.Node<Action>> solve = new ArrayList<>();
        ArrayList<MazeNodeGeedy> solve1 = new ArrayList<>();
        ArrayList<MazeNodeA> solve2 = new ArrayList<>();
        switch (typeCB.getValue()) {
            case "BFS":
                TreeSearch<Action, MazeProblem> treeSearch = new TreeSearch<>();
                solve = treeSearch.solve(new MazeProblem(new MazeNode(new MazeState(mazeType, startx, starty), null, null)));
                break;
            case "DFS":
                TreeSearchDFS<Action, MazeProblem> treeSearchdfs = new TreeSearchDFS<>();
                solve = treeSearchdfs.solve(new MazeProblem(new MazeNode(new MazeState(mazeType, startx, starty), null, null)));
                break;
            case "DLS":
                TreeSearchDLS<Action, MazeProblem> treeSearchDLS = new TreeSearchDLS<>();
                solve = treeSearchDLS.solve(new MazeProblem(new MazeNode(new MazeState(mazeType, startx, starty), null, null)));
                break;
            case "IDS":
                TreeSearchIDS<Action, MazeProblem> treeSearchIDS = new TreeSearchIDS<>();
                solve = treeSearchIDS.solve(new MazeProblem(new MazeNode(new MazeState(mazeType, startx, starty), null, null)));
                break;
            case "UCS":
                TreeSearchUCS<Action, MazeProblem> treeSearchUCS = new TreeSearchUCS<>();
                solve = treeSearchUCS.solve(new MazeProblem(new MazeNode(new MazeState(mazeType, startx, starty), null, null)));
                break;
            case "Greedy":
                MazeNodeGeedy.endx = endx;
                MazeNodeGeedy.endy = endy;
                TreeSearchGreedy treeSearchG = new TreeSearchGreedy();
                solve1 = treeSearchG.solve(new MazeProblem(new MazeNodeGeedy(new MazeState(mazeType, startx, starty), null, null)));
                break;
            case "A*":
                MazeNodeA.endx = endx;
                MazeNodeA.endy = endy;
                TreeSearchA treeSearchA = new TreeSearchA();
                solve2 = treeSearchA.solve(new MazeProblem(new MazeNodeA(new MazeState(mazeType, startx, starty), null, null)));
                break;
        }
        switch (typeCB.getValue()) {
            case "Greedy":
                if (solve1.size() == 0) System.out.println("shit");
                else
                    for (int i = 0; i < solve1.size(); i++) {
                        sample.Maze.GeneralProblem.Node<Action> actionNode = solve1.get(i);
                        int x = ((MazeState) actionNode.getCurrentState()).getX();
                        int y = ((MazeState) actionNode.getCurrentState()).getY();
                        getNodeByRowColumnIndex(x, y, maze).setText(i + "");
                        getNodeByRowColumnIndex(x, y, maze).setStyle("-fx-background-color: #336666;");
                    }
                    break;
            case "A*":
                if (solve2.size() == 0) System.out.println("shit");
                else
                    for (int i = 0; i < solve2.size(); i++) {
                        sample.Maze.GeneralProblem.Node<Action> actionNode = solve2.get(i);
                        int x = ((MazeState) actionNode.getCurrentState()).getX();
                        int y = ((MazeState) actionNode.getCurrentState()).getY();
                        getNodeByRowColumnIndex(x, y, maze).setText(i + "");
                        getNodeByRowColumnIndex(x, y, maze).setStyle("-fx-background-color: #336666;");
                    }
                break;
            default:
            if (solve.size() == 0) System.out.println("shit");
            else
                for (int i = 0; i < solve.size(); i++) {
                    sample.Maze.GeneralProblem.Node<Action> actionNode = solve.get(i);
                    int x = ((MazeState) actionNode.getCurrentState()).getX();
                    int y = ((MazeState) actionNode.getCurrentState()).getY();
                    getNodeByRowColumnIndex(x, y, maze).setText(i + "");
                    getNodeByRowColumnIndex(x, y, maze).setStyle("-fx-background-color: #336666;");
                }
        }
    }

    private type getType(int i, int j) {
        Label label = getNodeByRowColumnIndex(i, j, maze);
        String[] split = label.getStyle().split("#");
        switch (split[1]) {
            case "336666;":
                return type.end;
            case "996666;":
                return type.start;
            case "333333;":
                return type.forbidden;
            default:
                return type.ok;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeCB.getItems().addAll(FXCollections.observableArrayList("BFS", "DFS", "DLS", "IDS", "UCS", "Greedy", "A*"));
        typeCB.setValue("BFS");
        ArrayList<Integer> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i);
        }
        endxCB.getItems().addAll(FXCollections.observableArrayList(strings));
        endyCB.getItems().addAll(FXCollections.observableArrayList(strings));
        startxCB.getItems().addAll(FXCollections.observableArrayList(strings));
        startyCB.getItems().addAll(FXCollections.observableArrayList(strings));
        endxCB.setValue(9);
        endyCB.setValue(9);
        startxCB.setValue(0);
        startyCB.setValue(0);
        getNodeByRowColumnIndex(9, 9, maze).setStyle("-fx-background-color: #336666;");
        getNodeByRowColumnIndex(9, 9, maze).setText("END");
        getNodeByRowColumnIndex(0, 0, maze).setStyle("-fx-background-color: #996666;");
        getNodeByRowColumnIndex(0, 0, maze).setText("START");
        endxCB.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
            Integer endx = endxCB.getValue();
            Integer endy = endyCB.getValue();
            getNodeByRowColumnIndex((Integer) number, endy, maze).setStyle("-fx-background-color: #cccccc;");
            getNodeByRowColumnIndex((Integer) number, endy, maze).setText("");
            getNodeByRowColumnIndex((Integer) number2, endy, maze).setStyle("-fx-background-color: #336666;");
            getNodeByRowColumnIndex((Integer) number2, endy, maze).setText("END");
        });
        endyCB.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
            Integer endx = endxCB.getValue();
            Integer endy = endyCB.getValue();
            getNodeByRowColumnIndex(endx, (Integer) number, maze).setStyle("-fx-background-color: #cccccc;");
            getNodeByRowColumnIndex(endx, (Integer) number, maze).setText("");
            getNodeByRowColumnIndex(endx, (Integer) number2, maze).setStyle("-fx-background-color: #336666;");
            getNodeByRowColumnIndex(endx, (Integer) number2, maze).setText("END");
        });
        startxCB.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
            Integer endx = startxCB.getValue();
            Integer endy = startyCB.getValue();
            getNodeByRowColumnIndex((Integer) number, endy, maze).setStyle("-fx-background-color: #cccccc;");
            getNodeByRowColumnIndex((Integer) number, endy, maze).setText("");
            getNodeByRowColumnIndex((Integer) number2, endy, maze).setStyle("-fx-background-color: #996666;");
            getNodeByRowColumnIndex((Integer) number2, endy, maze).setText("START");
        });
        startyCB.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
            Integer endx = startxCB.getValue();
            Integer endy = startyCB.getValue();
            getNodeByRowColumnIndex(endx, (Integer) number, maze).setStyle("-fx-background-color: #cccccc;");
            getNodeByRowColumnIndex(endx, (Integer) number, maze).setText("");
            getNodeByRowColumnIndex(endx, (Integer) number2, maze).setStyle("-fx-background-color: #996666;");
            getNodeByRowColumnIndex(endx, (Integer) number2, maze).setText("START");
        });
    }

    public void change(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        String[] split = label.getStyle().split("#");
        if (split[1].equals("cccccc;")) {
            label.setStyle("-fx-background-color: #333333;");
        } else {
            label.setStyle("-fx-background-color: #cccccc;");
        }
    }

    public void random(ActionEvent actionEvent) {
        Integer endx = endxCB.getValue();
        Integer endy = endyCB.getValue();
        Integer startx = startxCB.getValue();
        Integer starty = startyCB.getValue();
        for (int i = 0; i < 10; i++) {
            int x = new Random().nextInt(10), y = new Random().nextInt(10);

            if ((x != endx || y != endy) && (x != startx || y != starty)) {

                Label nodeByRowColumnIndex = getNodeByRowColumnIndex(x, y, maze);
                if (nodeByRowColumnIndex != null)
                    nodeByRowColumnIndex.setStyle("-fx-background-color: #333333;");
            }
        }
    }

    public void reset(ActionEvent actionEvent) {
        maze.getChildren().forEach(node -> {
            ((Label) node).setText("");
            node.setStyle("-fx-background-color: #cccccc;");
        });

        getNodeByRowColumnIndex(endxCB.getValue(), endyCB.getValue(), maze).setStyle("-fx-background-color: #336666;");
        getNodeByRowColumnIndex(endxCB.getValue(), endyCB.getValue(), maze).setText("END");
        getNodeByRowColumnIndex(startxCB.getValue(), startyCB.getValue(), maze).setStyle("-fx-background-color: #996666;");
        getNodeByRowColumnIndex(startxCB.getValue(), startyCB.getValue(), maze).setText("START");
    }

    public Label getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) != null &&
                    GridPane.getColumnIndex(node) != null &&
                    GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return (Label) result;
    }
}

