package nikolaus.todolist;

import java.util.ArrayList;
import java.util.Scanner;

import nikolaus.ui.Reply;
import nikolaus.ui.ReplyMode;
import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Stores task list and methods associated with task list
 */
public class TaskList {
    // Messages
    private static final String MARKED_CONFIRMATION_MESSAGE = "Sure thing! I'll put this task as MARKED!\n";
    private static final String UNMARKED_CONFIRMATION_MESSAGE = "OK! The task has been UNMARKED!\n";
    private static final String REMOVED_TASK_CONFIRMATION_MESSAGE = "Good to know Traveler! "
            + "I've removed the following:\n";
    private static final String REMOVE_UPDATE_COUNT_MESSAGE_PART_A = "You now have ";
    private static final String REMOVE_UPDATE_COUNT_MESSAGE_PART_B = " tasks left";

    // Constants
    private static final int ZERO_ONE_INDEX_CONVERSION = 1;

    // Variables
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs empty To Do List
     */
    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public boolean isEmpty() {
        return taskCount == 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public void setList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    /**
     * Adds a ToDo to list
     */
    public void addToDo(String description) throws NikolausInputMismatchException {
        tasks.add(new ToDo(description));
        Reply.sendReply("Added " + tasks.get(taskCount).getDescription());
        taskCount++;
    }

    /**
     * Adds a Deadline to list
     */
    public void addDeadline(String description, String by) throws NikolausInputMismatchException {
        tasks.add(new Deadline(description, by));
        Reply.sendReply("Added " + tasks.get(taskCount).getDescription());
        taskCount++;
    }

    /**
     * Adds an Event to list
     */
    public void addEvent(String description, String from, String to) throws NikolausInputMismatchException {
        tasks.add(new Event(description, from, to));
        Reply.sendReply("Added " + tasks.get(taskCount).getDescription());
        taskCount++;
    }

    /**
     * Lists out items in To Do List
     */
    public void listOut() throws NikolausInputMismatchException {
        if (isEmpty()) {
            throw Ui.throwListEmptyException();
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
            throw Ui.throwInvalidIndexException();
        } else if (tasks.get(index - ZERO_ONE_INDEX_CONVERSION).isComplete()) {
            throw Ui.throwAlreadyMarkedException();
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
            throw Ui.throwInvalidIndexException();
        } else if (!tasks.get(index - ZERO_ONE_INDEX_CONVERSION).isComplete()) {
            throw Ui.throwAlreadyUnmarkedException();
        } else {
            tasks.get(index - ZERO_ONE_INDEX_CONVERSION).setComplete(false);
            Reply.sendReply(UNMARKED_CONFIRMATION_MESSAGE
                    + tasks.get(index - ZERO_ONE_INDEX_CONVERSION).toString(),
                    ReplyMode.BOTH);
        }
    }

    /**
     * Deletes task from to do list
     *
     * @param index Index in List to be deleted
     */
    public void delete(int index) throws NikolausInputMismatchException {
        if (index > taskCount || index < 1) {
            throw Ui.throwInvalidIndexException();
        } else {
            Task taskRemoved = tasks.remove(index - ZERO_ONE_INDEX_CONVERSION);
            taskCount--;
            Reply.sendReply(REMOVED_TASK_CONFIRMATION_MESSAGE
                    + "    " + taskRemoved.toString() + "\n"
                    + REMOVE_UPDATE_COUNT_MESSAGE_PART_A + taskCount + REMOVE_UPDATE_COUNT_MESSAGE_PART_B,
                    ReplyMode.BOTH);
        }
    }

    /**
     * Find tasks from task list with keyword in description
     *
     * @param keyword Keyword to look for in description
     */
    public void find(String keyword) throws NikolausInputMismatchException {
        ArrayList<Integer> taskIndices = new ArrayList<Integer>();

        for (int i = 0; i < taskCount; i++) {
            String[] tokens = tasks.get(i).description.split(" ");
            for (String token : tokens) {
                if (token.equals(keyword)) {
                    taskIndices.add(i);
                    break;
                }
            }
        }

        if (taskIndices.isEmpty()) {
            Ui.sendNoMatchingTasksMessage();
            return;
        }

        Ui.sendMatchingTasksMessage();
        for (int index : taskIndices) {
            System.out.println((index + ZERO_ONE_INDEX_CONVERSION) + ": " + tasks.get(index).toString());
        }
        Reply.createBorder();
    }
}
