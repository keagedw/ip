package nikolaus.command;

import nikolaus.todolist.ToDoList;

public class EventCommand extends TaskCommand {
    private static final int FROM_LENGTH = 6;
    private static final int TO_LENGTH = 4;

    /**
     * {@inheritDoc}
     */
    public EventCommand(String args, ToDoList toDoList) {
        super(args, toDoList);
    }

    public static Command parse(String args, ToDoList toDoList) {
        return new EventCommand(args, toDoList);
    }

    /**
     * Adds todo to ToDoList
     */
    @Override
    public void execute() {
        toDoList.addEvent(parseDescription(args), parseFrom(args), parseTo(args));
    }

    private String parseDescription(String args) {
        int endIndex = args.indexOf("/from") - 1;
        return args.substring(0, endIndex);
    }

    private String parseFrom(String args) {
        int startIndex = args.indexOf("/from") + FROM_LENGTH;
        int endIndex = args.indexOf("/to") - 1;
        return args.substring(startIndex, endIndex);
    }

    private String parseTo(String args) {
        int startIndex = args.indexOf("/to") + TO_LENGTH;
        return args.substring(startIndex);
    }
}
