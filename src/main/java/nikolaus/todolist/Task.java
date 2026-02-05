package nikolaus.todolist;

public class Task {
    protected String description;
    protected boolean isComplete;

    /**
     * Task to be added to To Do List
     */
    public Task(String description) {
        this.description = description;
        isComplete = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getStatusIcon() {
        // mark done task with X
        return (isComplete ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
