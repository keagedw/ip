import java.util.Objects;
import java.util.Scanner;

public class CommandHandler {
    private ToDoList toDoList;
    private String commandWord;
    private String[] arguments;
    private Scanner scanner;
    private String line;
    private Command[] commands = new Command[5];

    public CommandHandler(ToDoList toDoList, Scanner scanner) {
        this.toDoList = toDoList;
        this.scanner = scanner;
        commands[0] = new AddCommand(toDoList, scanner);
        commands[1] = new ListCommand(toDoList);
        commands[2] = new FarewellCommand();
        commands[3] = new MarkCommand();
        commands[4] = new UnmarkCommand();
    }

    private Command parse(String input) {
        String[] inputArray = input.trim().split(" ");
        if (inputArray[0].isEmpty()) {
            return new EchoCommand(input);
        } else {
            commandWord = inputArray[0];
        }

        if (inputArray.length > 1) {
            arguments = new String[inputArray.length - 1];
            System.arraycopy(inputArray, 1, arguments, 0, inputArray.length - 1);
        } else {
            arguments = new String[]{null};
        }

        for (Command command : commands) {
            if (inputArray[0].isEmpty()) {
                break;
            }
            if (command.isTriggeredBy(inputArray[0])) {
                return command;
            }
        }
        return new EchoCommand(input);
    }

    private Command parseMarkUnmark(Command inputCommand, String input) {
        int index;
        if (inputCommand instanceof MarkCommand) {
            try {
                index = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                Reply.sendReply("mark command not sent with index");
                return new EchoCommand(input);
            }
            if (index > toDoList.getIndex() || index < 0) {
                Reply.sendReply("Index not in list");
                return new EchoCommand(input);
            }
            return new MarkCommand(index, toDoList);
        } else {
            try {
                index = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                Reply.sendReply("unmark command not sent with index");
                return new EchoCommand(input);
            }
            if (index > toDoList.getIndex() || index < 0) {
                Reply.sendReply("Index not in list");
                return new EchoCommand(input);
            }
            return new UnmarkCommand(index, toDoList);
        }
    }

    public Command execute(String input) {
        Command command = parse(input);
        if (command instanceof MarkCommand || command instanceof UnmarkCommand) {
            command = parseMarkUnmark(command, input);
        }
        command.execute();
        return command;
    }

    public boolean shouldExit(Command command) {
        return command.willExit();
    }
}
