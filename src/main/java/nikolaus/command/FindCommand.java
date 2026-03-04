package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

public class FindCommand extends ToDoListCommand {
    public FindCommand(String args, TaskList taskList) {
        super(args, taskList);
    }

    public static Command parse(String args, TaskList taskList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw Ui.throwNoArgsException();
        }

        String[] tokens = args.split(" ");
        if (tokens.length > 1) {
            throw new NikolausInputMismatchException("I can only handle 1 word Traveler!");
        }

        return new FindCommand(args, taskList);
    }

    @Override
    public void execute() {
        taskList.find(args);
    }
}
