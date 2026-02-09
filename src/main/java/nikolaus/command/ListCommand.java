package nikolaus.command;

import nikolaus.todolist.ToDo;
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
