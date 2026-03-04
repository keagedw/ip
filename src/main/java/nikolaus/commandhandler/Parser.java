package nikolaus.commandhandler;

import java.util.HashMap;

import nikolaus.command.Command;
import nikolaus.command.DeadlineCommand;
import nikolaus.command.DeleteCommand;
import nikolaus.command.EventCommand;
import nikolaus.command.FarewellCommand;
import nikolaus.command.ListCommand;
import nikolaus.command.MarkCommand;
import nikolaus.command.ToDoCommand;
import nikolaus.command.UnmarkCommand;

import nikolaus.todolist.TaskList;

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Processes and Handles user command inputs
 * Triggers appropriate trigger
 */
public class Parser {
    protected TaskList taskList;

    // list of all commands
    private HashMap<String, CommandFactory> commandRegisters = new HashMap<String, CommandFactory>();

    /**
     * Allows commands to be built
     */
    @FunctionalInterface
    protected interface CommandFactory {
        Command create(String args) throws NikolausInputMismatchException;
    }

    /**
     * Lists all known commands to iterate through
     *
     * @param taskList TaskList to act on for TaskList commands
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        registerCommands();
    }

    /**
     * Runs correct commands
     *
     * Parses input into commands and arguments
     * Retrieves command from registry
     * Executes command with correct arguments
     *
     * @param input Total user input
     * @return exit flag boolean
     */
    public boolean execute(String input) throws NikolausInputMismatchException {
        // handles no input
        if (input.isEmpty()) {
            throw Ui.throwNoInputException();
        }

        // Split input into command and args
        String[] inputArray = input.trim().split(" ", 2);
        String commandTrigger = inputArray[0];
        String args = (inputArray.length == 1) ? "" : inputArray[1];

        // get correct CommandFactory
        CommandFactory factory = commandRegisters.get(commandTrigger);

        // handles no command match
        if (factory == null) {
            throw Ui.throwNoCommandMatchException();
        }

        // ensures correct arguments added
        Command command = factory.create(args);
        command.execute();
        return command.willExit();
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
     * Adds all commands to registry with correct CommandFactory
     */
    private void registerCommands() {
        commandRegisters.put("bye", args -> FarewellCommand.parse(args));
        commandRegisters.put("list", args -> ListCommand.parse(args, taskList));
        commandRegisters.put("mark", args -> MarkCommand.parse(args, taskList));
        commandRegisters.put("unmark", args -> UnmarkCommand.parse(args, taskList));
        commandRegisters.put("todo", args -> ToDoCommand.parse(args, taskList));
        commandRegisters.put("deadline", args -> DeadlineCommand.parse(args, taskList));
        commandRegisters.put("event", args -> EventCommand.parse(args, taskList));
        commandRegisters.put("delete", args -> DeleteCommand.parse(args, taskList));
    }
}
