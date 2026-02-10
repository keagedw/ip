package nikolaus.command;

import nikolaus.todolist.ToDoList;

import nikolaus.NikolausInputMismatchException;

public class ToDoCommand extends TaskCommand {
    // Messages
    private static final String NO_ARGS_MESSAGE = "Traveler! I can't put nothing????";

    /**
     * {@inheritDoc}
     */
    public ToDoCommand(String args, ToDoList toDoList) {
        super(args, toDoList);
    }

    /**
     * Creates an instance of ListCommand
     *
     * Summoned from CommandFactory
     *
     * @param args arguments fed
     * @param toDoList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, ToDoList toDoList) throws NikolausInputMismatchException {
        if (args.isEmpty()) {
            throw new NikolausInputMismatchException(NO_ARGS_MESSAGE);
        }
        return new ToDoCommand(args, toDoList);
    }

    /**
     * Adds todo to ToDoList
     */
    @Override
    public void execute() {
        toDoList.addToDo(args);
    }
}
