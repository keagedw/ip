package nikolaus.command;

import nikolaus.todolist.ToDoList;

import nikolaus.exceptions.NikolausInputMismatchException;

public class DeadlineCommand extends TaskCommand {
    // Messages
    private static final String NO_BY_INDICATOR_MESSAGE = "Traveler, you need to add \"/by\" !!!";
    private static final String NO_ARGS_MESSAGE = "Traveler! I can't put nothing????";

    // Constants
    private static final String BY_INDICATOR = "/by";
    private static final int BY_LENGTH = 4;
    private static final int ZERO_INDEX = 0;
    private static final int INDEXOF_NOT_FOUND_CODE = -1;
    private static final int ZERO_ONE_INDEX_CONVERSION = 1;

    // Variables
    private String description, by;

    /**
     * {@inheritDoc}
     */
    public DeadlineCommand(String args, ToDoList toDoList, String description, String by) {
        super(args, toDoList);
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
     * @param toDoList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, ToDoList toDoList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw new NikolausInputMismatchException(NO_ARGS_MESSAGE);
        }
        return new DeadlineCommand(args, toDoList, parseDescription(args), parseBy(args));
    }

    /**
     * Adds todo to ToDoList
     */
    @Override
    public void execute() {
        toDoList.addDeadline(description, by);
    }

    /**
     * Parses description from arguments
     *
     * @param args arguments given
     * @return description
     */
    private static String parseDescription(String args) throws NikolausInputMismatchException {
        int endIndex = args.indexOf(BY_INDICATOR);

        if (endIndex == INDEXOF_NOT_FOUND_CODE) {
            throw new NikolausInputMismatchException(NO_BY_INDICATOR_MESSAGE);
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

        if (startIndex == INDEXOF_NOT_FOUND_CODE) {
            throw new NikolausInputMismatchException(NO_BY_INDICATOR_MESSAGE);
        }

        return args.substring(startIndex + BY_LENGTH);
    }
}
