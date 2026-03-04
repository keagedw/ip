package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

public class FindCommand extends ToDoListCommand {
    // Constants
    private static final int MAX_WORD_COUNT = 1;
    private static final String TOKENS_REGEX = " ";

    public FindCommand(String args, TaskList taskList) {
        super(args, taskList);
    }

    public static Command parse(String args, TaskList taskList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw Ui.throwNoArgsException();
        }

        String[] tokens = args.split(TOKENS_REGEX);
        if (tokens.length > MAX_WORD_COUNT) {
            throw Ui.throwNotOneWordException();
        }

        return new FindCommand(args, taskList);
    }

    @Override
    public void execute() {
        taskList.find(args);
    }
}
