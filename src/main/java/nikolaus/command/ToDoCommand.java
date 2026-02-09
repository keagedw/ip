package nikolaus.command;

import nikolaus.todolist.ToDoList;

public class ToDoCommand extends TaskCommand {
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
