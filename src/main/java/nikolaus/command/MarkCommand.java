package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Routes command line input to command execution
 * Marks a task in TaskList as complete
 */
public class MarkCommand extends ToDoListCommand {
    // Variables
    private int index;

    /**
     * {@inheritDoc}
     *
     * Instances without index used for trigger keyword check
     * "mark" is triggering keyword
     */
    public MarkCommand(String args, TaskList taskList, int index) {
        super(args, taskList);
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
     * @param taskList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, TaskList taskList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw Ui.throwNoIndexException();
        }

        try {
            int argsIndex = Integer.parseInt(args);
            return new MarkCommand(args, taskList, argsIndex);
        } catch (NumberFormatException noNumberError) {
            throw Ui.throwNotNumberException();
        }
    }

    /**
     * Marks task at specified index as complete
     */
    @Override
    public void execute() {
        taskList.mark(index);
    }
}
