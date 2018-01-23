package sample.Maze.search;

import sample.Maze.GeneralProblem.Node;
import sample.Maze.Maze.Action;
import sample.Maze.Maze.MazeNodeGeedy;
import sample.Maze.Maze.MazeProblem;

import java.util.*;

public class TreeSearchGreedy {
    public ArrayList<MazeNodeGeedy> solve(MazeProblem problem){
        PriorityQueue<MazeNodeGeedy> fringe = new PriorityQueue<>();
        fringe.add((MazeNodeGeedy) problem.getInitialState());
        Set<MazeNodeGeedy> set = new HashSet<>();
        while (true){
            if (fringe.isEmpty())return new ArrayList<>();
            MazeNodeGeedy node = fringe.poll();
            set.add(node);
            if (problem.goalTest(node)){
                ArrayDeque<MazeNodeGeedy> temp = new ArrayDeque<>();
                while (node!=null){
                    temp.addFirst(node);
                    node= (MazeNodeGeedy) node.getParentNode();
                }
                return new ArrayList<MazeNodeGeedy>(temp);
            }
            ArrayList<MazeNodeGeedy> c = ((MazeProblem) problem).successorFunctionGreedy(((MazeNodeGeedy) node));
            for (MazeNodeGeedy aNode : c) {
                if (set.add(aNode))
                    fringe.add(aNode);
            }
        }
    }
}