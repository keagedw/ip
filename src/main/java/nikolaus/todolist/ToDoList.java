package nikolaus.todolist;

import java.util.ArrayList;
import java.util.Scanner;

import nikolaus.ui.Reply;
import nikolaus.ui.ReplyMode;

import nikolaus.NikolausInputMismatchException;

public class ToDoList {
    // Messages
    private static final String LIST_FULL_MESSAGE = "Apologies Traveler, the list has no more space...";
    private static final String LIST_EMPTY_MESSAGE = "Apologies Traveler, you haven't listed anything...";
    private static final String INVALID_INDEX_MESSAGE = "Traveler, that's outside of the list!";
    private static final String ALREADY_MARKED_MESSAGE = "Task already marked complete!";
    private static final String ALREADY_UNMARKED_MESSAGE = "Task hasn't been marked!!";
    private static final String MARKED_CONFIRMATION_MESSAGE = "Sure thing! I'll put this task as MARKED!\n";
    private static final String UNMARKED_CONFIRMATION_MESSAGE = "OK! The task has been UNMARKED!\n";

    // Constants
    private static final int LIST_LENGTH = 100;
    private static final int ZERO_ONE_INDEX_CONVERSION = 1;

    // Variables
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs empty To Do List
     */
    public ToDoList(Scanner scanner) {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public boolean isEmpty() {
        return taskCount == 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Adds a ToDo to list
     */
    public void addToDo(String description) throws NikolausInputMismatchException {
        // checks if full
        if (taskCount >= LIST_LENGTH) {
            throw new NikolausInputMismatchException(LIST_FULL_MESSAGE);
        }

        tasks.add(new ToDo(description));
        Reply.sendReply("Added " + tasks.get(taskCount).getDescription());
        taskCount++;
    }

    /**
     * Adds a Deadline to list
     */
    public void addDeadline(String description, String by) throws NikolausInputMismatchException {
        // checks if full
        if (taskCount >= LIST_LENGTH) {
            throw new NikolausInputMismatchException(LIST_FULL_MESSAGE);
        }

        tasks.add(new Deadline(description, by));
        Reply.sendReply("Added " + tasks.get(taskCount).getDescription());
        taskCount++;
    }

    /**
     * Adds an Event to list
     */
    public void addEvent(String description, String from, String to) throws NikolausInputMismatchException {
        // checks if full
        if (taskCount >= LIST_LENGTH) {
            throw new NikolausInputMismatchException(LIST_FULL_MESSAGE);
        }

        tasks.add(new Event(description, from, to));
        Reply.sendReply("Added " + tasks.get(taskCount).getDescription());
        taskCount++;
    }

    /**
     * Lists out items in To Do List
     */
    public void listOut() throws NikolausInputMismatchException {
        if (isEmpty()) {
            throw new NikolausInputMismatchException(LIST_EMPTY_MESSAGE);
        }

        Reply.sendReply("To Do List:", ReplyMode.TOP);
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + ZERO_ONE_INDEX_CONVERSION) + ": " + tasks.get(i).toString());
        }
        Reply.createBorder();
    }

    /**
     * Marks task as complete
     *
     * @param index Index of task in To Do List
     */
    public void mark(int index) throws NikolausInputMismatchException {
        if (index > taskCount || index < 1) {
            throw new NikolausInputMismatchException(INVALID_INDEX_MESSAGE);
        } else if (tasks.get(index - ZERO_ONE_INDEX_CONVERSION).isComplete()) {
            throw new NikolausInputMismatchException(ALREADY_MARKED_MESSAGE);
        } else {
            tasks.get(index - ZERO_ONE_INDEX_CONVERSION).setComplete(true);
            Reply.sendReply(MARKED_CONFIRMATION_MESSAGE
                    + tasks.get(index - ZERO_ONE_INDEX_CONVERSION).toString(),
                    ReplyMode.BOTH);
        }
    }

    /**
     * Marks task as incomplete
     *
     * @param index Index of task in To Do List
     */
    public void unmark(int index) throws NikolausInputMismatchException {
        if (index > taskCount || index < 1) {
            throw new NikolausInputMismatchException(INVALID_INDEX_MESSAGE);
        } else if (!tasks.get(index - ZERO_ONE_INDEX_CONVERSION).isComplete()) {
            throw new NikolausInputMismatchException(ALREADY_UNMARKED_MESSAGE);
        } else {
            tasks.get(index - ZERO_ONE_INDEX_CONVERSION).setComplete(false);
            Reply.sendReply(UNMARKED_CONFIRMATION_MESSAGE
                    + tasks.get(index - ZERO_ONE_INDEX_CONVERSION).toString(),
                    ReplyMode.BOTH);
        }
    }
}
