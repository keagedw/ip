package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Routes command line input to command execution
 * Finds task with keyword in description specified in args
 */
public class FindCommand extends ToDoListCommand {
    // Constants
    private static final int MAX_WORD_COUNT = 1;
    private static final String TOKENS_REGEX = " ";

    /**
     * {@inheritDoc}
     */
    public FindCommand(String args, TaskList taskList) {
        super(args, taskList);
    }

    /**
     * Creates instance of FindCommand
     *
     * Summoned from CommandFactory
     *
     * @param args Arguments fed
     * @param taskList task list to act on
     * @return command created
     */
    public static Command parse(String args, TaskList taskList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw Ui.throwNoArgsException();
        }

        String[] tokens = args.split(TOKENS_REGEX);
        if (tokens.length > MAX_WORD_COUNT) {
            throw Ui.throwNotOneWordException();
        }

        return new FindCommand(args, taskList);
    }

    /**
     * Finds task from task list with keyword in description
     */
    @Override
    public void execute() {
        taskList.find(args);
    }
}
