package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Routes command line input to command execution
 * Adds a deadline to TaskList
 */
public class AddDeadlineCommand extends AddTaskCommand {
    // Constants
    private static final String BY_INDICATOR = "/by";
    private static final int BY_LENGTH = 4;
    private static final int ZERO_INDEX = 0;
    private static final int INDEX_NOT_FOUND_CODE = -1;
    private static final int ZERO_ONE_INDEX_CONVERSION = 1;

    // Variables
    private String description, by;

    /**
     * {@inheritDoc}
     */
    public AddDeadlineCommand(String args, TaskList taskList, String description, String by) {
        super(args, taskList);
        this.description = description;
        this.by = by;
    }

    /**
     * Creates an instance of ListCommand
     *
     * Summoned from CommandFactory
     *
     * Parses description and by
     *
     * @param args arguments fed
     * @param taskList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, TaskList taskList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw Ui.throwNoArgsException();
        }
        return new AddDeadlineCommand(args, taskList, parseDescription(args), parseBy(args));
    }

    /**
     * Adds deadline to TaskList
     */
    @Override
    public void execute() {
        taskList.addDeadline(description, by);
    }

    /**
     * Parses description from arguments
     *
     * @param args arguments given
     * @return description
     */
    private static String parseDescription(String args) throws NikolausInputMismatchException {
        int endIndex = args.indexOf(BY_INDICATOR);

        if (endIndex == INDEX_NOT_FOUND_CODE) {
            throw Ui.throwNoByException();
        }

        return args.substring(ZERO_INDEX, endIndex - ZERO_ONE_INDEX_CONVERSION);
    }

    /**
     * Parses from String from arguments
     *
     * @param args arguments given
     * @return from String
     */
    private static String parseBy(String args) throws NikolausInputMismatchException {
        int startIndex = args.indexOf(BY_INDICATOR);

        if (startIndex == INDEX_NOT_FOUND_CODE) {
            throw Ui.throwNoByException();
        }

        return args.substring(startIndex + BY_LENGTH);
    }
}
