package nikolaus.command;

import nikolaus.todolist.TaskList;

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

public class ToDoCommand extends TaskCommand {
    /**
     * {@inheritDoc}
     */
    public ToDoCommand(String args, TaskList taskList) {
        super(args, taskList);
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
            throw Ui.throwNoArgsException();
        }
        return new ToDoCommand(args, taskList);
    }

    /**
     * Adds todo to TaskList
     */
    @Override
    public void execute() {
        taskList.addToDo(args);
    }
}
