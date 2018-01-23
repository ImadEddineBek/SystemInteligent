package sample.Maze.search;

import sample.Maze.Maze.MazeNodeA;
import sample.Maze.Maze.MazeProblem;

import java.util.*;

public class TreeSearchA {
    public ArrayList<MazeNodeA> solve(MazeProblem problem){
        PriorityQueue<MazeNodeA> fringe = new PriorityQueue<>();
        fringe.add((MazeNodeA) problem.getInitialState());
        Set<MazeNodeA> set = new HashSet<>();
        while (true){
            if (fringe.isEmpty())return new ArrayList<>();
            MazeNodeA node = fringe.poll();
            set.add(node);
            if (problem.goalTest(node)){
                ArrayDeque<MazeNodeA> temp = new ArrayDeque<>();
                while (node!=null){
                    temp.addFirst(node);
                    node= (MazeNodeA) node.getParentNode();
                }
                return new ArrayList<MazeNodeA>(temp);
            }
            ArrayList<MazeNodeA> c = ((MazeProblem) problem).successorFunctionA(((MazeNodeA) node));
            for (MazeNodeA aNode : c) {
                if (set.add(aNode))
                    fringe.add(aNode);
            }
        }
    }
}