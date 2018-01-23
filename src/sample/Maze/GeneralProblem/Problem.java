package sample.Maze.GeneralProblem;

import java.util.ArrayList;

public abstract class Problem<T extends Action>{
    private final Node<T> initialState ;
    public abstract  ArrayList<? extends Node<T>> successorFunction(Node<T> currentNode);
    public abstract  boolean goalTest(Node<T> currentNode);

    public Node<T> getInitialState() {
        return initialState;
    }

    protected Problem(Node<T> initialState) {
        this.initialState = initialState;
    }
}
