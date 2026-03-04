package nikolaus.command;

import nikolaus.todolist.TaskList;

/**
 * Routes command line input to command execution
 * Lists down linked TaskList
 */
public class ListCommand extends ToDoListCommand {
    /**
     * {@inheritDoc}
     *
     * "list" is the triggering keyword
     */
    public ListCommand(String args, TaskList taskList) {
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
        return new ListCommand(args, taskList);
    }

    /**
     * Lists out elements in TaskList
     */
    @Override
    public void execute() {
        taskList.listOut();
    }
}
