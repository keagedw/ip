package nikolaus.command;

import nikolaus.todolist.ToDoList;

/**
 * Used to add a task to ToDoList
 */
public class TaskCommand extends ToDoListCommand {
    /**
     * {@inheritDoc}
     */
    public TaskCommand(String args, ToDoList toDoList) {
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
        return new TaskCommand(args, toDoList);
    }
}
