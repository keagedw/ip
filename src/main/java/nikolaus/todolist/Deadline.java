package nikolaus.todolist;

public class Deadline extends Task{
    private static final String DEADLINE_TYPE_SIGN = "D";

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return DEADLINE_TYPE_SIGN + " | "
                + super.toFileFormat() + " | "
                + by;
    }
}
