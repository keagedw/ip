package nikolaus.todolist;

/**
 * Task that includes description, and from and to date and time
 */
public class Event extends Task{
    private static final String EVENT_TYPE_SIGN = "E";

    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start + ")"
                + " (to: " + end + ")";
    }

    @Override
    public String toFileFormat() {
        return EVENT_TYPE_SIGN + " | "
                + super.toFileFormat() + " | "
                + start + " | "
                + end;
    }
}
