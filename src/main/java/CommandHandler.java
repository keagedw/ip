import java.util.Scanner;

public class CommandHandler {
    private ToDoList toDoList;
    private Scanner scanner;
    private String line;
    private Command[] commands = new Command[3];

    public CommandHandler(ToDoList toDoList, Scanner scanner) {
        this.toDoList = toDoList;
        this.scanner = scanner;
        commands[0] = new AddCommand(toDoList, scanner);
        commands[1] = new ListCommand(toDoList);
        commands[2] = new FarewellCommand();
    }

    public Command parse(String input) {
        for (Command command : commands) {
            if (command.isTriggeredBy(input)) {
                return command;
            }
        }
        return new EchoCommand(input);
    }

    public void execute(Command command) {
        command.execute();
    }

    public boolean shouldExit(Command command) {
        return command.willExit();
    }
}
