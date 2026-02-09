package nikolaus.command;

import nikolaus.todolist.ToDoList;

public class ToDoCommand extends TaskCommand {
    /**
     * {@inheritDoc}
     */
    public ToDoCommand(String args, ToDoList toDoList) {
        super(args, toDoList);
    }

    public static Command parse(String args, ToDoList toDoList) {
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
