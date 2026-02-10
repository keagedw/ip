package nikolaus.command;

import nikolaus.todolist.ToDoList;

/**
 * Used to list down linked ToDoList
 */
public class ListCommand extends ToDoListCommand {
    /**
     * {@inheritDoc}
     *
     * "list" is the triggering keyword
     */
    public ListCommand(String args, ToDoList toDoList) {
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
    public static Command parse(String args, ToDoList toDoList) {
        return new ListCommand(args, toDoList);
    }

    /**
     * Lists out elements in ToDoList
     */
    @Override
    public void execute() {
        toDoList.listOut();
    }
}
