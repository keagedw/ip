package nikolaus.command;

import nikolaus.todolist.ToDoList;

/**
 * Used to add a task to ToDoList
 */
public class TaskCommand extends ToDoListCommand {

    /**
     * {@inheritDoc}
     *
     * "add" is triggering keyword
     */
    public TaskCommand(String args, ToDoList toDoList) {
        super(args, toDoList);
    }

    public static Command parse(String args, ToDoList toDoList) {
        return new TaskCommand(args, toDoList);
    }

    /**
     * Adds task to ToDoList
     */
    @Override
    public void execute() {
        toDoList.addTask(args);
    }
}
