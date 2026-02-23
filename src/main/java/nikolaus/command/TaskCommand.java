package nikolaus.command;

import nikolaus.todolist.TaskList;

/**
 * Used to add a task to TaskList
 */
public class TaskCommand extends ToDoListCommand {
    /**
     * {@inheritDoc}
     */
    public TaskCommand(String args, TaskList taskList) {
        super(args, taskList);
    }

    /**
     * Creates an instance of ListCommand
     *
     * Summoned from CommandFactory
     *
     * @param args arguments fed
     * @param taskList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, TaskList taskList) {
        return new TaskCommand(args, taskList);
    }
}
