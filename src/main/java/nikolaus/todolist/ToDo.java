package nikolaus.todolist;

/**
 * Task that includes just description
 */
public class ToDo extends Task{
    private static final String TODO_TYPE_SIGN = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TODO_TYPE_SIGN + "]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return TODO_TYPE_SIGN + " | "
                + super.toFileFormat();
    }
}
