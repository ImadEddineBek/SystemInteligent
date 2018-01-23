package sample.Maze.GeneralProblem;

public abstract class Node<T extends Action> implements Comparable<Node<T>>{
    protected final State currentState;
    private final Node<T> parentNode;
    protected final T action;
    public final double cost;
    public final int depth;

    protected Node(State currentState, Node<T> parentNode, T action)  {
        this.currentState = currentState;
        this.parentNode = parentNode;
        this.action = action;
        if (parentNode != null) {
            this.cost = parentNode.cost + action.getCost();
            this.depth = parentNode.depth + 1;
        }else {
            this.cost = 0;
            this.depth = 0;
        }
    }

    public Node<T> getParentNode() {
        return parentNode;
    }

    public State getCurrentState() {
        return currentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return currentState.equals(node.currentState);
    }

    @Override
    public abstract int hashCode() ;

    @Override
    public int compareTo(Node<T> o) {
        return (int) (cost-o.cost);
    }
}
