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
    public ToDoListCommand(String[] keywords, ToDoList toDoList) {
        super(keywords);
        this.toDoList = toDoList;
    }
}
