package nikolaus.command;

import nikolaus.todolist.TaskList;

/**
 * Used to add a task to TaskList
 */
public class AddTaskCommand extends ToDoListCommand {
    /**
     * {@inheritDoc}
     */
    public AddTaskCommand(String args, TaskList taskList) {
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
        return new AddTaskCommand(args, taskList);
    }
}
