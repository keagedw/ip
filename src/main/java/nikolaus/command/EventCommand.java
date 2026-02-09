package nikolaus.command;

import nikolaus.todolist.ToDoList;

public class EventCommand extends TaskCommand {
    private static final int FROM_LENGTH = 6;
    private static final int TO_LENGTH = 4;

    private String description, from, to;

    /**
     * {@inheritDoc}
     */
    public EventCommand(String args, ToDoList toDoList, String description, String from, String to) {
        super(args, toDoList);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an instance of ListCommand
     *
     * Summoned from CommandFactory
     *
     * @param args arguments fed
     * @param toDoList To Do List to act on
     * @return command created
     */
    public static Command parse(String args, ToDoList toDoList) {
        return new EventCommand(args, toDoList, parseDescription(args), parseFrom(args), parseTo(args));
    }

    /**
     * Adds todo to ToDoList
     */
    @Override
    public void execute() {
        toDoList.addEvent(description, from, to);
    }

    /**
     * Parses description from arguments
     *
     * @param args arguments given
     * @return description
     */
    private static String parseDescription(String args) {
        int endIndex = args.indexOf("/from") - 1;
        return args.substring(0, endIndex);
    }

    /**
     * Parses from String from arguments
     *
     * @param args arguments given
     * @return from String
     */
    private static String parseFrom(String args) {
        int startIndex = args.indexOf("/from") + FROM_LENGTH;
        int endIndex = args.indexOf("/to") - 1;
        return args.substring(startIndex, endIndex);
    }

    /**
     * Parses to String from arguments
     *
     * @param args arguments given
     * @return to String
     */
    private static String parseTo(String args) {
        int startIndex = args.indexOf("/to") + TO_LENGTH;
        return args.substring(startIndex);
    }
}
