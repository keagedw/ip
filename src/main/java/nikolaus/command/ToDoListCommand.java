package nikolaus.command;

import nikolaus.todolist.ToDoList;

/**
 * ToDoList specific commands
 */
public class ToDoListCommand extends Command{
    protected ToDoList toDoList;

    /**
     * {@inheritDoc}
     *
     * @param toDoList ToDoList for which the command acts on
     */
    public ToDoListCommand(String args, ToDoList toDoList) {
        super(args);
        this.toDoList = toDoList;
    }

    public static Command parse(String args, ToDoList toDoList) {
        return new ToDoListCommand(args, toDoList);
    }
}
