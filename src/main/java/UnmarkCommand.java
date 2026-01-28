public class UnmarkCommand extends Command{
    private int index;
    private ToDoList toDoList;

    public UnmarkCommand() {
        super(new String[]{"unmark"});
    }

    public UnmarkCommand(int index, ToDoList toDoList) {
        super(new String[]{"unmark"});
        this.index = index;
        this.toDoList = toDoList;
    }

    @Override
    public void execute() {
        toDoList.unmark(index);
    }
}
