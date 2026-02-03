package nikolaus.commandhandler;

import nikolaus.command.AddCommand;
import nikolaus.command.Command;
import nikolaus.command.EchoCommand;
import nikolaus.command.FarewellCommand;
import nikolaus.command.ListCommand;
import nikolaus.command.MarkCommand;
import nikolaus.command.UnmarkCommand;

import nikolaus.todolist.ToDoList;

import nikolaus.ui.Reply;

/**
 * Processes and Handles user command inputs
 * Triggers appropriate trigger
 */
public class CommandHandler {
    private ToDoList toDoList;

    // parsed command and arguments from input
    private String commandWord = "";
    private String[] arguments;

    // command list
    private Command[] commands = new Command[5];

    /**
     * Lists all known commands to iterate through
     *
     * @param toDoList ToDoList to act on for ToDoList commands
     */
    public CommandHandler(ToDoList toDoList) {
        this.toDoList = toDoList;
        commands[0] = new AddCommand(toDoList);
        commands[1] = new ListCommand(toDoList);
        commands[2] = new FarewellCommand();
        commands[3] = new MarkCommand(toDoList);
        commands[4] = new UnmarkCommand(toDoList);
    }

    /**
     * Runs chosen command
     *
     * @param input Total user input
     * @return Command run
     */
    public Command execute(String input) {
        Command command = parse(input);
        if (command instanceof MarkCommand || command instanceof UnmarkCommand) {
            command = parseMarkUnmark(command, input);
        }
        command.execute();
        return command;
    }

    /**
     * Signals if Nikolaus bot should end
     *
     * @param command Last command run
     * @return If command triggers end flag
     */
    public boolean shouldExit(Command command) {
        return command.willExit();
    }

    /**
     * Parses user input to comman and arguments
     *
     * TO BE REPLACED BY CLASS
     *
     * @param input User input from scanner
     * @return Command that is chosen
     */
    private Command parse(String input) {
        // Split into array of words
        String[] inputArray = input.trim().split(" ");

        // Check if there are words
        if (inputArray[0].isEmpty()) {
            return new EchoCommand(input);
        } else {
            commandWord = inputArray[0];
        }

        // check if there are arguments
        if (inputArray.length > 1) {
            arguments = new String[inputArray.length - 1];
            System.arraycopy(inputArray, 1, arguments, 0, inputArray.length - 1);
        } else {
            arguments = new String[]{null};
        }

        // check for comman word
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

    /**
     * Processes parsed result further for Mark and Unmark commands
     *
     * TO BE REPLACED BY CLASS
     *
     * @param inputCommand Command to be parsed further
     * @param input Total user input
     * @return Mark/Unmark command chosen
     */
    private Command parseMarkUnmark(Command inputCommand, String input) {
        int index;
        if (inputCommand instanceof MarkCommand) {
            try {
                index = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                Reply.sendReply("mark command not sent with index");
                return new EchoCommand(input);
            }
            if (index > toDoList.getTaskCount() || index < 0) {
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
            if (index > toDoList.getTaskCount() || index < 0) {
                Reply.sendReply("Index not in list");
                return new EchoCommand(input);
            }
            return new UnmarkCommand(index, toDoList);
        }
    }
}
