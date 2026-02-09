package nikolaus.commandhandler;

import java.util.HashMap;
import java.util.InputMismatchException;

import nikolaus.command.Command;
import nikolaus.command.DeadlineCommand;
import nikolaus.command.EchoCommand;
import nikolaus.command.EventCommand;
import nikolaus.command.FarewellCommand;
import nikolaus.command.ListCommand;
import nikolaus.command.MarkCommand;
import nikolaus.command.TaskCommand;
import nikolaus.command.ToDoCommand;
import nikolaus.command.UnmarkCommand;

import nikolaus.todolist.ToDoList;

import nikolaus.ui.Reply;

/**
 * Processes and Handles user command inputs
 * Triggers appropriate trigger
 */
public class CommandHandler {
    protected ToDoList toDoList;

    // command list
    private HashMap<String, CommandFactory> commandRegisters = new HashMap<String, CommandFactory>();

    @FunctionalInterface
    protected interface CommandFactory{
        Command create(String args) throws InputMismatchException;
    }

    /**
     * Lists all known commands to iterate through
     *
     * @param toDoList ToDoList to act on for ToDoList commands
     */
    public CommandHandler(ToDoList toDoList) {
        this.toDoList = toDoList;
        registerCommands();
    }

    /**
     * Runs chosen command
     *
     * @param input Total user input
     * @return Command run
     */
    public Command execute(String input) {
        // Split input into command and
        String[] inputArray = input.trim().split(" ", 2);
        String commandTrigger = inputArray[0];
        String args = (inputArray.length == 1) ? "" : inputArray[1];

        CommandFactory factory = commandRegisters.get(commandTrigger);

        if (factory == null) {
            Command command = new EchoCommand(input);
            command.execute();
            return command;
        }

        Command command;
        try {
            command = factory.create(args);
        } catch (InputMismatchException error) {
            Reply.sendReply(error.getMessage());
            command = new EchoCommand(input);
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

    private void registerCommands() {
        commandRegisters.put("bye", args -> FarewellCommand.parse(args));
        commandRegisters.put("list", args -> ListCommand.parse(args, toDoList));
        commandRegisters.put("mark", args -> MarkCommand.parse(args, toDoList));
        commandRegisters.put("unmark", args -> UnmarkCommand.parse(args, toDoList));
        commandRegisters.put("todo", args -> ToDoCommand.parse(args, toDoList));
        commandRegisters.put("deadline", args -> DeadlineCommand.parse(args, toDoList));
        commandRegisters.put("event", args -> EventCommand.parse(args, toDoList));
    }
}
