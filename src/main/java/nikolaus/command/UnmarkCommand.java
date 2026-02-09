package nikolaus.command;

import java.util.InputMismatchException;

import nikolaus.todolist.ToDoList;

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

    public static Command parse(String args, ToDoList toDoList) {
        if (args.isEmpty()) {
            throw new InputMismatchException("Index must be provided");
        }

        int argsIndex;
        try {
            argsIndex = Integer.parseInt(args);
        } catch (NumberFormatException noNumberError) {
            throw new InputMismatchException("Index must be an integer");
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
