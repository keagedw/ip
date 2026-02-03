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
    public ListCommand(ToDoList toDoList) {
        super(new String[]{"list"}, toDoList);
    }

    /**
     * Lists out elements in ToDoList
     */
    @Override
    public void execute() {
        toDoList.listOut();
    }
}
