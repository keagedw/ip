import java.util.Scanner;

public class AddCommand extends Command{
    private ToDoList toDoList;
    private Scanner scanner;

    public AddCommand(ToDoList toDoList, Scanner scanner) {
        super(new String[]{"add"});
        this.toDoList = toDoList;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Reply.sendReply("What would you like to add?");
        String task = scanner.nextLine();
        toDoList.add(task);
        Reply.sendReply("Added " + task);
    }
}
