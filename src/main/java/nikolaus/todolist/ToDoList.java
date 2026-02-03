package nikolaus.todolist;

import java.util.Scanner;

import nikolaus.ui.Reply;
import nikolaus.ui.ReplyMode;

public class ToDoList {
    private static final int LIST_LENGTH = 100;

    private Task[] list;
    private int taskCount;

    private Scanner scanner;

    /**
     * Constructs empty To Do List
     */
    public ToDoList(Scanner scanner) {
        list = new Task[LIST_LENGTH];
        taskCount = 0;
        this.scanner = scanner;
    }

    public boolean isEmpty() {
        return taskCount == 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Adds task to To Do List
     */
    public void add() {
        Reply.sendReply("What would you like to add?");
        String task = scanner.nextLine();
        if (taskCount >= LIST_LENGTH) {
            Reply.sendReply("To Do List is full");
        } else {
            list[taskCount] = new Task(task);
            taskCount++;
            Reply.sendReply("Added " + task);
        }
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
            String tick = list[i].isComplete() ? "X" : " ";
            System.out.println((i + 1) + ": [" + tick + "] " + list[i].getDescription());
        }
        Reply.createBorder();
    }

    /**
     * Marks task as complete
     *
     * @param index Index of task in To Do List
     */
    public void mark(int index) {
        if (list[index - 1].isComplete()) {
            Reply.sendReply("Task already marked complete!");
        } else {
            list[index - 1].setComplete(true);
            Reply.sendReply("Sure thing! I'll put this task as MARKED!\n"
                                    + "[X] " + list[index - 1].getDescription(),
                            ReplyMode.BOTH);
        }
    }

    /**
     * Marks task as incomplete
     *
     * @param index Index of task in To Do List
     */
    public void unmark(int index) {
        if (!list[index - 1].isComplete()) {
            Reply.sendReply("Task already marked incomplete!");
        } else {
            list[index - 1].setComplete(false);
            Reply.sendReply("OK! The task has been UNMARKED!\n"
                                    + "[ ] " + list[index - 1].getDescription(),
                            ReplyMode.BOTH);
        }
    }
}
