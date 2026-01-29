package nikolaus.command;

import nikolaus.todolist.ToDoList;

public class ListCommand extends Command {
    private final ToDoList listToDoList;

    public ListCommand(ToDoList toDoList) {
        super(new String[]{"list"});
        listToDoList = toDoList;
    }

    @Override
    public void execute() {
        listToDoList.listOut();
    }
}
