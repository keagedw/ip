public class MarkCommand extends Command{
    private int index;
    private ToDoList toDoList;

    public MarkCommand() {
        super(new String[]{"mark"});
    }

    public MarkCommand(int index, ToDoList toDoList) {
        super(new String[]{"mark"});
        this.index = index;
        this.toDoList = toDoList;
    }

    @Override
    public void execute() {
        toDoList.mark(index);
    }
}
