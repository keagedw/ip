package nikolaus.command;

import nikolaus.todolist.ToDoList;

public class DeadlineCommand extends TaskCommand {
    private static final int BY_LENGTH = 4;

    /**
     * {@inheritDoc}
     */
    public DeadlineCommand(String args, ToDoList toDoList) {
        super(args, toDoList);
    }

    public static Command parse(String args, ToDoList toDoList) {
        return new DeadlineCommand(args, toDoList);
    }

    /**
     * Adds todo to ToDoList
     */
    @Override
    public void execute() {
        toDoList.addDeadline(parseDescription(args), parseBy(args));
    }

    private String parseDescription(String args) {
        int endIndex = args.indexOf("/by") - 1;
        return args.substring(0, endIndex);
    }

    private String parseBy(String args) {
        int startIndex = args.indexOf("/by") + BY_LENGTH;
        return args.substring(startIndex);
    }
}
