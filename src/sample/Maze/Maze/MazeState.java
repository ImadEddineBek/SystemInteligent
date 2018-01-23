package sample.Maze.Maze;

import sample.Maze.GeneralProblem.State;

public class MazeState extends State {
    private final type[][] maze;
    private int x,y;

    public MazeState(type[][] maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    public type[][] getMaze() {
        return maze;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
