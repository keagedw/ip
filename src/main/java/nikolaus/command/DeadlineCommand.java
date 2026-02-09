package nikolaus.command;

import nikolaus.todolist.ToDoList;

public class DeadlineCommand extends TaskCommand {
    private static final int BY_LENGTH = 4;

    private String description, by;

    /**
     * {@inheritDoc}
     */
    public DeadlineCommand(String args, ToDoList toDoList, String description, String by) {
        super(args, toDoList);
        this.description = description;
        this.by = by;
    }

    /**
     * Creates an instance of ListCommand
     *
     * Summoned from CommandFactory
     *
     * Parses description and by
     *
     * @param args arguments fed
     * @param toDoList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, ToDoList toDoList) {
        return new DeadlineCommand(args, toDoList, parseDescription(args), parseBy(args));
    }

    /**
     * Adds todo to ToDoList
     */
    @Override
    public void execute() {
        toDoList.addDeadline(description, by);
    }

    /**
     * Parses description from arguments
     *
     * @param args arguments given
     * @return description
     */
    private static String parseDescription(String args) {
        int endIndex = args.indexOf("/by") - 1;
        return args.substring(0, endIndex);
    }

    /**
     * Parses from String from arguments
     *
     * @param args arguments given
     * @return from String
     */
    private static String parseBy(String args) {
        int startIndex = args.indexOf("/by") + BY_LENGTH;
        return args.substring(startIndex);
    }
}
