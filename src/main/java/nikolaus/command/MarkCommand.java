package nikolaus.command;

import java.text.ParseException;
import java.util.InputMismatchException;

import nikolaus.todolist.ToDoList;

import nikolaus.ui.Reply;

import nikolaus.NikolausInputMismatchException;

/**
 * Used to mark a task in ToDoList as complete
 */
public class MarkCommand extends ToDoListCommand {
    // index of task in list to mark, default out of list
    private int index;

    /**
     * {@inheritDoc}
     *
     * Instances without index used for trigger keyword check
     * "mark" is triggering keyword
     */
    public MarkCommand(String args, ToDoList toDoList, int index) {
        super(args, toDoList);
        this.index = index;
    }

    /**
     * Creates an instance of MarkCommand
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
    public static Command parse(String args, ToDoList toDoList) throws InputMismatchException {
        if (args.isEmpty()) {
            throw new NikolausInputMismatchException("You must provide an index Traveler!");
        }

        int argsIndex;
        try {
            argsIndex = Integer.parseInt(args);
        } catch (NumberFormatException noNumberError) {
            throw new NikolausInputMismatchException("Uhhhhhhh Traveler? I don't think that's a number...");
        }

        return new MarkCommand(args, toDoList, argsIndex);
    }

    /**
     * Marks task at specified index as complete
     */
    @Override
    public void execute() {
        toDoList.mark(index);
    }
}
