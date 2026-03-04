package nikolaus;

import java.util.ArrayList;

import nikolaus.storage.Storage;

import nikolaus.commandhandler.Parser;

import nikolaus.todolist.Task;
import nikolaus.todolist.TaskList;

import nikolaus.ui.Reply;
import nikolaus.ui.ReplyMode;
import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausInputMismatchException;
import nikolaus.exceptions.NikolausIOException;

/**
 * Nikolaus, a multi-functional personal bot to help keep track of tasks!
 */
public class Nikolaus {
    // file storage path
    private static final String STORAGE_FILE = "./data/nikolaus.txt";

    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private boolean willExit;

    public Nikolaus(String filePath) {
        taskList = new TaskList();
        storage = new Storage(filePath);
        parser = new Parser(taskList);
        willExit = false;
    }

    /**
     * Executes Nikolaus
     */
    public static void main(String[] args) {
        new Nikolaus(STORAGE_FILE).run();
    }

    /**
     * Loads any saved to do lists from previous runs
     */
    private void loadSaves() {
        Reply.sendReply("Loading save file...\n", ReplyMode.TOP);
        try {
            ArrayList<Task> tasksArrayList = storage.load();

            taskList.setList(tasksArrayList);
            taskList.setTaskCount(tasksArrayList.size());

            Reply.sendReply("Loaded!!! Here is the last To Do List saved:", ReplyMode.TOP);
            taskList.listOut();
        } catch (NikolausIOException e) {
            Reply.sendReply(e.getMessage());
        } catch (NikolausInputMismatchException e) {
            Reply.sendReply("No previous saved To Do List!");
        }
    }

    /**
     * Saves current to do list
     */
    private void saveToDoList() {
        try {
            storage.save(taskList.getList());
        } catch (NikolausIOException e) {
            Reply.sendReply(e.getMessage());
        }
    }

    private void run() {
        loadSaves();

        Ui.introduce();

        // keeps looping until command signals to stop
        while (!willExit) {
            // gets input from user
            String inputCommand = Ui.nextLine();

            try {
                // handler processes command; returns command run
                willExit = parser.execute(inputCommand);
            } catch (NikolausInputMismatchException error) {
                Reply.sendReply(error.getMessage());
            }
        }

        // save to do list data
        Reply.sendReply("Saving To Do List...", ReplyMode.BOTTOM);
        saveToDoList();
    }
}


