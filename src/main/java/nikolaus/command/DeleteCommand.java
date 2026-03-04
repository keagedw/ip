package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Used to delete Task from TaskList
 */
public class DeleteCommand extends ToDoListCommand {
    // Variables
    private int index;

    /**
     * {@inheritDoc}
     */
    public DeleteCommand(String args, TaskList taskList, int index) {
        super(args, taskList);
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
     * @param taskList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, TaskList taskList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw Ui.throwNoIndexException();
        }

        try {
            int argsIndex = Integer.parseInt(args);
            return new DeleteCommand(args, taskList, argsIndex);
        } catch (NumberFormatException noNumberError) {
            throw Ui.throwNotNumberException();
        }
    }

    /**
     * Marks task at specified index as complete
     */
    @Override
    public void execute() {
        taskList.delete(index);
    }
}
