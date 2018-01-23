package sample.Maze.search;

import sample.Maze.GeneralProblem.Action;
import sample.Maze.GeneralProblem.Node;
import sample.Maze.GeneralProblem.Problem;

import java.util.*;

public class TreeSearch < A extends Action,T extends Problem<A>> {
    public ArrayList<Node<A>> solve(T problem){
        LinkedList<Node<A>> fringe = new LinkedList<>();
        fringe.add(problem.getInitialState());
        Set<Node<A>> set = new HashSet<>();
        while (true){
            if (fringe.isEmpty())return new ArrayList<>();
            Node<A> node = fringe.pop();
            set.add(node);
            if (problem.goalTest(node)){
                ArrayDeque<Node<A>> temp = new ArrayDeque<>();
                while (node!=null){
                    temp.addFirst(node);
                    node= node.getParentNode();
                }
                ArrayList<Node<A>> arrayList = new ArrayList<>(temp);
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
