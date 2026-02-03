package nikolaus.command;

import nikolaus.todolist.ToDoList;

/**
 * Used to unmark a task in ToDoList
 */
public class UnmarkCommand extends ToDoListCommand {
    // index of task in list to mark, default out of list
    private int index = -1;

    /**
     * {@inheritDoc}
     *
     * Instances without index used for trigger keyword check
     * "unmark" is triggering keyword
     */
    public UnmarkCommand(ToDoList toDoList) {
        super(new String[]{"unmark"}, toDoList);
    }

    /**
     * {@inheritDoc}
     *
     * Instances with index used to unmark tasks in lists
     * "unmark" is triggering keyword
     */
    public UnmarkCommand(int index, ToDoList toDoList) {
        super(new String[]{"unmark"}, toDoList);
        this.index = index;
    }

    /**
     * Unmarks task at specified index as complete
     */
    @Override
    public void execute() {
        toDoList.unmark(index);
    }
}
