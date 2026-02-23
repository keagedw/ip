package nikolaus;

import java.util.ArrayList;
import java.util.Scanner;

import nikolaus.storage.Storage;

import nikolaus.commandhandler.Parser;

import nikolaus.todolist.Task;
import nikolaus.todolist.TaskList;

import nikolaus.ui.Logo;
import nikolaus.ui.Ui;
import nikolaus.ui.LineMode;

import nikolaus.exceptions.NikolausInputMismatchException;
import nikolaus.exceptions.NikolausIOException;

/**
 * Nikolaus, a multi-functional personal bot to help keep track of tasks!
 */
public class Nikolaus {
    // Messages
    private static final String GREETING = "Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!\n"
            + "How may I be of assistance today???";

    // file storage path
    private static final String STORAGE_FILE = "./data/nikolaus.txt";

    // setup for command replies
    static Scanner in = new Scanner(System.in);
    static TaskList list = new TaskList(in);

    // setup for TaskList file management
    static Storage fileManager = new Storage(STORAGE_FILE);

    // setup command handler to accept commands
    static Parser handler = new Parser(list);

    // signal to exit bot
    static boolean willExit = false;

    /**
     * Executes Nikolaus
     */
    public static void main(String[] args) {
        loadSaves();
        introduce();
        run();
    }

    /**
     * Loads any saved to do lists from previous runs
     */
    private static void loadSaves() {
        Ui.sendReply("Loading save file...\n", LineMode.TOP);
        try {
            ArrayList<Task> tasksArrayList = fileManager.load();

            list.setList(tasksArrayList);
            list.setTaskCount(tasksArrayList.size());

            Ui.sendReply("Loaded!!! Here is the last To Do List saved:", LineMode.TOP);
            list.listOut();
        } catch (NikolausIOException e) {
            Ui.sendReply(e.getMessage());
        } catch (NikolausInputMismatchException e) {
            Ui.sendReply("No previous saved To Do List!");
        }
    }

    private static void introduce() {
        Logo.display();
        Ui.sendReply(GREETING, LineMode.BOTTOM);
    }

    /**
     * Saves current to do list
     */
    private static void saveToDoList() {
        try {
            fileManager.save(list.getList());
        } catch (NikolausIOException e) {
            Ui.sendReply(e.getMessage());
        }
    }

    private static void run() {
        // keeps looping until command signals to stop
        while (!willExit) {
            // gets input from user
            String inputCommand = in.nextLine();

            try {
                // handler processes command; returns command run
                willExit = handler.execute(inputCommand);
            } catch (NikolausInputMismatchException error) {
                Ui.sendReply(error.getMessage());
            }
        }

        // save to do list data
        Ui.sendReply("Saving To Do List...", LineMode.BOTTOM);
        saveToDoList();
    }
}


