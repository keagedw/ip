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
        // checks if full
        if (taskCount >= LIST_LENGTH) {
            Reply.sendReply("To Do List is full");
            return;
        }

        // asks input from user
        Reply.sendReply("What would you like to add?");
        String task = scanner.nextLine();

        // prepares input string for parsing
        int firstSpaceIndex = task.indexOf(' ');

        // initialise new Task instance
        Task newTask;

        // parses between todo, deadline, or event
        // TODO: Encapsulate into individual ToDoListCommand subclasses and integrate with CommandHandler
        switch (task.substring(0, firstSpaceIndex)) {
        case "todo":
            newTask = new ToDo(task.replace("todo ", ""));
            list[taskCount] = newTask;
            taskCount++;
            Reply.sendReply("Added " + newTask.getDescription());
            break;
        case "deadline":
            int byIndex = task.indexOf('/');
            newTask = new Deadline(task.substring(0, byIndex - 1).replace("deadline ", ""),
                    task.substring(byIndex + 4));
            list[taskCount] = newTask;
            taskCount++;
            Reply.sendReply("Added " + newTask.getDescription());
            break;
        case "event":
            int fromIndex = task.indexOf('/');
            int toIndex = task.indexOf('/', fromIndex + 1);
            newTask = new Event(task.substring(0, fromIndex - 1).replace("event ", ""),
                    task.substring(fromIndex + 6, toIndex - 1),
                    task.substring(toIndex + 4));
            list[taskCount] = newTask;
            taskCount++;
            Reply.sendReply("Added " + newTask.getDescription());
            break;
        default:
            Reply.sendReply("Not able to add Task....");
            break;
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
        if (list[index - 1].isComplete()) {
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
        if (!list[index - 1].isComplete()) {
            Reply.sendReply("Task already marked incomplete!");
        } else {
            list[index - 1].setComplete(false);
            Reply.sendReply("OK! The task has been UNMARKED!\n"
                    + list[index - 1].toString(),
                    ReplyMode.BOTH);
        }
    }
}
