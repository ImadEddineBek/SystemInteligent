package sample.Maze.Maze;


import sample.Maze.GeneralProblem.Node;

public class MazeNodeGeedy extends Node<Action> {
    public static int endx=9,endy=9;
    public MazeNodeGeedy(MazeState currentState, Node<Action> parentNode, Action action) {
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
        MazeNodeGeedy o1 = (MazeNodeGeedy) o;
        int x1 = ((MazeState) o1.currentState).getX();
        int t1 = ((MazeState) o1.currentState).getY();
        return x==x1 && t ==t1;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(Node<Action> o) {
        return (((MazeState) currentState).getX()-endx)*(((MazeState) currentState).getX()-endx) +(((MazeState) currentState).getY()-endy)*(((MazeState) currentState).getY()-endy) ;
    }
}
