package nikolaus.command;

import nikolaus.todolist.TaskList;

/**
 * TaskList specific commands
 */
public class ToDoListCommand extends Command {
    protected TaskList taskList;

    /**
     * {@inheritDoc}
     *
     * @param taskList TaskList for which the command acts on
     */
    public ToDoListCommand(String args, TaskList taskList) {
        super(args);
        this.taskList = taskList;
    }

    /**
     * Creates an instance of ToDoListCommand
     *
     * Summoned from CommandFactory
     *
     * @param args arguments fed
     * @param taskList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, TaskList taskList) {
        return new ToDoListCommand(args, taskList);
    }
}
