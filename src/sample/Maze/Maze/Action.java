package sample.Maze.Maze;

enum ActionType {
    up, down, left, right
}

public class Action extends sample.Maze.GeneralProblem.Action {
    private ActionType actionType;

    public Action(ActionType actionType, int cost) {
        super(cost);
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionType=" + actionType +
                ", cost=" + cost +
                '}';
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
