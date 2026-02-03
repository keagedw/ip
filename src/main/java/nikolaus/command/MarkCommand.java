package nikolaus.command;

import nikolaus.todolist.ToDoList;

/**
 * Used to mark a task in ToDoList as complete
 */
public class MarkCommand extends ToDoListCommand {
    // index of task in list to mark, default out of list
    private int index = -1;

    /**
     * {@inheritDoc}
     *
     * Instances without index used for trigger keyword check
     * "mark" is triggering keyword
     */
    public MarkCommand(ToDoList toDoList) {
        super(new String[]{"mark"}, toDoList);
    }

    /**
     * {@inheritDoc}
     *
     * Instances with index used to mark tasks in lists
     * "mark" is triggering keyword
     */
    public MarkCommand(int index, ToDoList toDoList) {
        super(new String[]{"mark"}, toDoList);
        this.index = index;
    }

    /**
     * Marks task at specified index as complete
     */
    @Override
    public void execute() {
        if (index == -1) {
            return;
        }
        toDoList.mark(index);
    }
}
