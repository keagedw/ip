package nikolaus.command;

import java.util.Scanner;

import nikolaus.todolist.ToDoList;

import nikolaus.ui.Reply;

public class AddCommand extends Command {
    private final ToDoList toDoList;
    private final Scanner scanner;

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
