package sample.Maze.GeneralProblem;

public class Action {
    protected final double cost ;

    protected Action(double cost) {
        this.cost = cost;
    }

     double getCost() {
        return cost;
    }
}
