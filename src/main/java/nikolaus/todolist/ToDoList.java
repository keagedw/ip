package nikolaus.todolist;

import java.util.Scanner;

import nikolaus.ui.Reply;
import nikolaus.ui.ReplyMode;

public class ToDoList {
    private static final int LIST_LENGTH = 100;

    private Task[] list;
    private int taskCount;

    /**
     * Constructs empty To Do List
     */
    public ToDoList(Scanner scanner) {
        list = new Task[LIST_LENGTH];
        taskCount = 0;
    }

    public boolean isEmpty() {
        return taskCount == 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Adds a Task to list
     */
    public void addTask(String description) {
        // checks if full
        if (taskCount >= LIST_LENGTH) {
            Reply.sendReply("To Do List is full");
            return;
        }

        list[taskCount] = new Task(description);
        Reply.sendReply("Added " + list[taskCount].getDescription());
        taskCount++;
    }

    /**
     * Adds a ToDo to list
     */
    public void addToDo(String description) {
        // checks if full
        if (taskCount >= LIST_LENGTH) {
            Reply.sendReply("To Do List is full");
            return;
        }

        list[taskCount] = new ToDo(description);
        Reply.sendReply("Added " + list[taskCount].getDescription());
        taskCount++;
    }

    /**
     * Adds a Deadline to list
     */
    public void addDeadline(String description, String by) {
        // checks if full
        if (taskCount >= LIST_LENGTH) {
            Reply.sendReply("To Do List is full");
            return;
        }

        list[taskCount] = new Deadline(description, by);
        Reply.sendReply("Added " + list[taskCount].getDescription());
        taskCount++;
    }

    /**
     * Adds an Event to list
     */
    public void addEvent(String description, String from, String to) {
        // checks if full
        if (taskCount >= LIST_LENGTH) {
            Reply.sendReply("To Do List is full");
            return;
        }

        list[taskCount] = new Event(description, from, to);
        Reply.sendReply("Added " + list[taskCount].getDescription());
        taskCount++;
    }

    /**
     * Lists out items in To Do List
     */
    public void listOut() {
        if (isEmpty()) {
            Reply.sendReply("To Do List is empty");
            return;
        }

        Reply.sendReply("To Do List:", ReplyMode.TOP);
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ": " + list[i].toString());
        }
        Reply.createBorder();
    }

    /**
     * Marks task as complete
     *
     * @param index Index of task in To Do List
     */
    public void mark(int index) {
        if (index > taskCount || index < 1) {
            Reply.sendReply("Not a valid index");
        } else if (list[index - 1].isComplete()) {
            Reply.sendReply("Task already marked complete!");
        } else {
            list[index - 1].setComplete(true);
            Reply.sendReply("Sure thing! I'll put this task as MARKED!\n"
                    + list[index - 1].toString(),
                    ReplyMode.BOTH);
        }
    }

    /**
     * Marks task as incomplete
     *
     * @param index Index of task in To Do List
     */
    public void unmark(int index) {
        if (index > taskCount || index < 1) {
            Reply.sendReply("Not a valid index");
        } else if (!list[index - 1].isComplete()) {
            Reply.sendReply("Task already marked incomplete!");
        } else {
            list[index - 1].setComplete(false);
            Reply.sendReply("OK! The task has been UNMARKED!\n"
                    + list[index - 1].toString(),
                    ReplyMode.BOTH);
        }
    }
}
