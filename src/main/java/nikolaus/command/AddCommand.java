package nikolaus.command;

import nikolaus.todolist.ToDoList;

/**
 * Used to add a task to ToDoList
 */
public class AddCommand extends ToDoListCommand {

    /**
     * {@inheritDoc}
     *
     * "add" is triggering keyword
     */
    public AddCommand(ToDoList toDoList) {
        super(new String[]{"add"}, toDoList);
    }

    /**
     * Adds task to ToDoList
     */
    @Override
    public void execute() {
        toDoList.add();
    }
}
