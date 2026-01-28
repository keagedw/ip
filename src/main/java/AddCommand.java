import java.util.Scanner;

public class AddCommand extends Command{
    private ToDoList addToDoList;
    private Scanner addScanner;

    public AddCommand(ToDoList toDoList, Scanner scanner) {
        super(new String[]{"add"});
        addToDoList = toDoList;
        addScanner = scanner;
    }

    @Override
    public void execute() {
        Reply.sendReply("What would you like to add?");
        String task = addScanner.nextLine();
        addToDoList.add(task);
        Reply.sendReply("Added " + task);
    }
}
