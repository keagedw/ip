package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Used to unmark a task in TaskList
 */
public class UnmarkCommand extends ToDoListCommand {
    // Messages
    private static final String NO_INDEX_MESSAGE = "You must provide an index Traveler!";
    private static final String NOT_A_NUMBER_MESSAGE = "Uhhhhhhh Traveler? I don't think that's a number...";

    // Variables
    private int index;

    /**
     * {@inheritDoc}
     *
     * Instances without index used for trigger keyword check
     * "unmark" is triggering keyword
     */
    public UnmarkCommand(String args, TaskList taskList, int index) {
        super(args, taskList);
        this.index = index;
    }

    /**
     * Creates an instance of UnmarkCommand
     *
     * Summoned from CommandFactory
     *
     * handles errors and wrong inputs
     * parses index of task to act on
     *
     * @param args arguments fed
     * @param taskList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, TaskList taskList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw new NikolausInputMismatchException(NO_INDEX_MESSAGE);
        }

        try {
            int argsIndex = Integer.parseInt(args);
            return new UnmarkCommand(args, taskList, argsIndex);
        } catch (NumberFormatException noNumberError) {
            throw new NikolausInputMismatchException(NOT_A_NUMBER_MESSAGE);
        }
    }

    /**
     * Unmarks task at specified index as complete
     */
    @Override
    public void execute() {
        taskList.unmark(index);
    }
}
