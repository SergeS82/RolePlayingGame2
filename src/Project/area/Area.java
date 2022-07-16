package Project.area;

public abstract class Area implements Runnable{
    protected final String actionName;
    protected final int actionInt;

    public Area(String actionName, int actionInt) {
        this.actionName = actionName;
        this.actionInt = actionInt;
    }

    public String getActionName() {
        return actionName;
    }

    public int getActionInt() {
        return actionInt;
    }
}
