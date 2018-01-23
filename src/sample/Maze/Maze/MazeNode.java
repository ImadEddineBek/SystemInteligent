package sample.Maze.Maze;


import sample.Maze.GeneralProblem.Node;

public class MazeNode extends Node<Action> {
    public MazeNode(MazeState currentState, Node<Action> parentNode, Action action) {
        super(currentState, parentNode, action);
    }

    @Override
    public MazeState getCurrentState() {
        return (MazeState) super.getCurrentState();
    }

    @Override
    public String toString() {
        return "MazeNode{" +
                "action=" + action +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        int x = ((MazeState) currentState).getX();
        int t = ((MazeState) currentState).getY();
        MazeNode o1 = (MazeNode) o;
        int x1 = ((MazeState) o1.currentState).getX();
        int t1 = ((MazeState) o1.currentState).getY();
        return x==x1 && t ==t1;
    }

    @Override
    public int hashCode() {
        return 0;
    }


}
