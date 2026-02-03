package nikolaus.todolist;

public class Task {
    private String description;
    private boolean isComplete;

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
}
