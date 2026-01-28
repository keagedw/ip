import java.util.Scanner;

public class ListCommand extends Command{
    private ToDoList listToDoList;

    public ListCommand(ToDoList toDoList) {
        super(new String[]{"list"});
        listToDoList = toDoList;
    }

    @Override
    public void execute() {
        listToDoList.listOut();
    }
}
