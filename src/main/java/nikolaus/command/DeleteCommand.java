package nikolaus.command;

import nikolaus.todolist.ToDoList;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Used to delete Task from ToDoList
 */
public class DeleteCommand extends ToDoListCommand {
    // Messages
    private static final String NO_INDEX_MESSAGE = "You must provide an index Traveler!";
    private static final String NOT_A_NUMBER_MESSAGE = "Uhhhhhhh Traveler? I don't think that's a number...";

    // Variables
    private int index;

    /**
     * {@inheritDoc}
     */
    public DeleteCommand(String args, ToDoList toDoList, int index) {
        super(args, toDoList);
        this.index = index;
    }

    /**
     * Creates an instance of DeleteCommand
     *
     * Summoned from CommandFactory
     *
     * handles errors and wrong inputs
     * parses index of task to act on
     *
     * @param args arguments fed
     * @param toDoList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, ToDoList toDoList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw new NikolausInputMismatchException(NO_INDEX_MESSAGE);
        }

        try {
            int argsIndex = Integer.parseInt(args);
            return new DeleteCommand(args, toDoList, argsIndex);
        } catch (NumberFormatException noNumberError) {
            throw new NikolausInputMismatchException(NOT_A_NUMBER_MESSAGE);
        }
    }

    /**
     * Marks task at specified index as complete
     */
    @Override
    public void execute() {
        toDoList.delete(index);
    }
}
