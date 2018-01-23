package sample.Maze.Maze;


import sample.Maze.GeneralProblem.Node;
import sample.Maze.GeneralProblem.Problem;

import java.util.ArrayList;
import java.util.Collections;


public class MazeProblem extends Problem<Action> {

    public MazeProblem(Node<Action> initialState) {
        super(initialState);
    }

    @Override
    public ArrayList<? extends Node<Action>> successorFunction(Node<Action> currentNode) {
        ArrayList<Node<Action>> successors = new ArrayList<>();
        type[][] maze = ((MazeState) currentNode.getCurrentState()).getMaze();
        int x = ((MazeState) currentNode.getCurrentState()).getX();
        int y = ((MazeState) currentNode.getCurrentState()).getY();
        try {
            if (!maze[x + 1][y].equals(type.forbidden)) {
                successors.add(new MazeNode(new MazeState(maze, x + 1, y), currentNode, new Action(ActionType.down, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x - 1][y].equals(type.forbidden)) {
                successors.add(new MazeNode(new MazeState(maze, x - 1, y), currentNode, new Action(ActionType.up, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x][y + 1].equals(type.forbidden)) {
                successors.add(new MazeNode(new MazeState(maze, x, y + 1), currentNode, new Action(ActionType.right, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x][y - 1].equals(type.forbidden)) {
                successors.add(new MazeNode(new MazeState(maze, x, y - 1), currentNode, new Action(ActionType.left, 1)));
            }
        } catch (Exception ignored) {

        }
        Collections.shuffle(successors);
        return successors;
    }
    public ArrayList<MazeNodeGeedy> successorFunctionGreedy(MazeNodeGeedy currentNode) {
        ArrayList<MazeNodeGeedy> successors = new ArrayList<>();
        type[][] maze = ((MazeState) currentNode.getCurrentState()).getMaze();
        int x = ((MazeState) currentNode.getCurrentState()).getX();
        int y = ((MazeState) currentNode.getCurrentState()).getY();
        try {
            if (!maze[x + 1][y].equals(type.forbidden)) {
                successors.add(new MazeNodeGeedy(new MazeState(maze, x + 1, y), currentNode, new Action(ActionType.down, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x - 1][y].equals(type.forbidden)) {
                successors.add(new MazeNodeGeedy(new MazeState(maze, x - 1, y), currentNode, new Action(ActionType.up, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x][y + 1].equals(type.forbidden)) {
                successors.add(new MazeNodeGeedy(new MazeState(maze, x, y + 1), currentNode, new Action(ActionType.right, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x][y - 1].equals(type.forbidden)) {
                successors.add(new MazeNodeGeedy(new MazeState(maze, x, y - 1), currentNode, new Action(ActionType.left, 1)));
            }
        } catch (Exception ignored) {

        }
        Collections.shuffle(successors);
        return successors;
    }
    public ArrayList<MazeNodeA> successorFunctionA(MazeNodeA currentNode) {
        ArrayList<MazeNodeA> successors = new ArrayList<>();
        type[][] maze = ((MazeState) currentNode.getCurrentState()).getMaze();
        int x = ((MazeState) currentNode.getCurrentState()).getX();
        int y = ((MazeState) currentNode.getCurrentState()).getY();
        try {
            if (!maze[x + 1][y].equals(type.forbidden)) {
                successors.add(new MazeNodeA(new MazeState(maze, x + 1, y), currentNode, new Action(ActionType.down, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x - 1][y].equals(type.forbidden)) {
                successors.add(new MazeNodeA(new MazeState(maze, x - 1, y), currentNode, new Action(ActionType.up, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x][y + 1].equals(type.forbidden)) {
                successors.add(new MazeNodeA(new MazeState(maze, x, y + 1), currentNode, new Action(ActionType.right, 1)));
            }
        } catch (Exception ignored) {

        }
        try {
            if (!maze[x][y - 1].equals(type.forbidden)) {
                successors.add(new MazeNodeA(new MazeState(maze, x, y - 1), currentNode, new Action(ActionType.left, 1)));
            }
        } catch (Exception ignored) {

        }
        Collections.shuffle(successors);
        return successors;
    }
    @Override
    public boolean goalTest(Node<Action> currentNode) {
        return ((MazeState) currentNode.getCurrentState()).getMaze()[((MazeState) currentNode.getCurrentState()).getX()][((MazeState) currentNode.getCurrentState()).getY()].equals(type.end);
    }

}

