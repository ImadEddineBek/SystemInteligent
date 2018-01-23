package sample.Maze.search;

import sample.Maze.GeneralProblem.Node;
import sample.Maze.GeneralProblem.Action;
import sample.Maze.GeneralProblem.Problem;

import java.util.*;

public class TreeSearchDFS< A extends Action,T extends Problem<A>> {
    public ArrayList<Node<A>> solve(T problem){
        Stack<Node<A>> fringe = new Stack<>();
        Set<Node<A>> set = new HashSet<>();
        fringe.add(problem.getInitialState());
        while (true){
            if (fringe.isEmpty())return new ArrayList<>();
            Node<A> node = fringe.pop();
            set.add(node);
            if (problem.goalTest(node)){
                ArrayList<Node<A>> arrayList = new ArrayList<>();
                ArrayDeque<Node<A>> temp = new ArrayDeque<>();
                while (node!=null){
                    temp.addFirst(node);
                    node= node.getParentNode();
                }
                arrayList.addAll(temp);
                return arrayList;
            }else {
                ArrayList<? extends Node<A>> c = problem.successorFunction(node);
                for (Node<A> aNode : c) {
                    if (set.add(aNode))
                        fringe.add(aNode);
                }

            }
        }
    }
}
