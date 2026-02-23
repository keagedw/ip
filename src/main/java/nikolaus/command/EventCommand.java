package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.exceptions.NikolausInputMismatchException;

public class EventCommand extends TaskCommand {
    // Messages
    private static final String NO_FROM_INDICATOR_MESSAGE = "Traveler, you need to add \"/from\" !!!";
    private static final String NO_TO_INDICATOR_MESSAGE = "Traveler, you need to add \"/to\" !!!";
    private static final String NO_ARGS_MESSAGE = "Traveler! I can't put nothing????";

    // Constants
    private static final String FROM_INDICATOR = "/from";
    private static final String TO_INDICATOR = "/to";
    private static final int FROM_LENGTH = 6;
    private static final int TO_LENGTH = 4;
    private static final int ZERO_INDEX = 0;
    private static final int INDEXOF_NOT_FOUND_CODE = -1;
    private static final int ZERO_ONE_INDEX_CONVERSION = 1;

    // Variables
    private String description, from, to;

    /**
     * {@inheritDoc}
     */
    public EventCommand(String args, TaskList taskList, String description, String from, String to) {
        super(args, taskList);
        this.description = description;
        this.from = from;
        this.to = to;
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
    public static Command parse(String args, TaskList taskList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw new NikolausInputMismatchException(NO_ARGS_MESSAGE);
        }
        return new EventCommand(args, taskList, parseDescription(args), parseFrom(args), parseTo(args));
    }

    /**
     * Adds todo to TaskList
     */
    @Override
    public void execute() {
        taskList.addEvent(description, from, to);
    }

    /**
     * Parses description from arguments
     *
     * @param args arguments given
     * @return description
     */
    private static String parseDescription(String args) throws NikolausInputMismatchException {
        int endIndex = args.indexOf(FROM_INDICATOR);

        if (endIndex == INDEXOF_NOT_FOUND_CODE) {
            throw new NikolausInputMismatchException(NO_FROM_INDICATOR_MESSAGE);
        }

        return args.substring(ZERO_INDEX, endIndex - ZERO_ONE_INDEX_CONVERSION);
    }

    /**
     * Parses from String from arguments
     *
     * @param args arguments given
     * @return from String
     */
    private static String parseFrom(String args) throws NikolausInputMismatchException {
        int startIndex = args.indexOf(FROM_INDICATOR);

        if (startIndex == INDEXOF_NOT_FOUND_CODE) {
            throw new NikolausInputMismatchException(NO_FROM_INDICATOR_MESSAGE);
        }

        int endIndex = args.indexOf(TO_INDICATOR);

        if (endIndex == INDEXOF_NOT_FOUND_CODE) {
            throw new NikolausInputMismatchException(NO_TO_INDICATOR_MESSAGE);
        }

        return args.substring(startIndex + FROM_LENGTH, endIndex - ZERO_ONE_INDEX_CONVERSION);
    }

    /**
     * Parses to String from arguments
     *
     * @param args arguments given
     * @return to String
     */
    private static String parseTo(String args) throws NikolausInputMismatchException {
        int startIndex = args.indexOf(TO_INDICATOR);

        if (startIndex == INDEXOF_NOT_FOUND_CODE) {
            throw new NikolausInputMismatchException(NO_TO_INDICATOR_MESSAGE);
        }

        return args.substring(startIndex + TO_LENGTH);
    }
}
