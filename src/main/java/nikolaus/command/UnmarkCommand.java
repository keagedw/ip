package nikolaus.command;

import java.util.InputMismatchException;

import nikolaus.todolist.ToDoList;

import nikolaus.NikolausInputMismatchException;

/**
 * Used to unmark a task in ToDoList
 */
public class UnmarkCommand extends ToDoListCommand {
    // index of task in list to mark, default out of list
    private int index;

    /**
     * {@inheritDoc}
     *
     * Instances without index used for trigger keyword check
     * "unmark" is triggering keyword
     */
    public UnmarkCommand(String args, ToDoList toDoList, int index) {
        super(args, toDoList);
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
     * @param toDoList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, ToDoList toDoList) {
        if (args.isEmpty()) {
            throw new NikolausInputMismatchException("You must provide an index Traveler!");
        }

        int argsIndex;
        try {
            argsIndex = Integer.parseInt(args);
        } catch (NumberFormatException noNumberError) {
            throw new NikolausInputMismatchException("Uhhhhhhh Traveler? I don't think that's a number...");
        }

        return new UnmarkCommand(args, toDoList, argsIndex);
    }

    /**
     * Unmarks task at specified index as complete
     */
    @Override
    public void execute() {
        toDoList.unmark(index);
    }
}
