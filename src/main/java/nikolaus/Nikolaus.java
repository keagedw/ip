package nikolaus;

import java.util.ArrayList;
import java.util.Scanner;

import nikolaus.storage.StorageHandler;

import nikolaus.commandhandler.CommandHandler;

import nikolaus.todolist.Task;
import nikolaus.todolist.ToDoList;

import nikolaus.ui.Logo;
import nikolaus.ui.Reply;
import nikolaus.ui.ReplyMode;

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
    static ToDoList list = new ToDoList(in);

    // setup for ToDoList file management
    static StorageHandler fileManager = new StorageHandler(STORAGE_FILE);

    // setup command handler to accept commands
    static CommandHandler handler = new CommandHandler(list);

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
        Reply.sendReply("Loading save file...\n", ReplyMode.TOP);
        try {
            ArrayList<Task> tasksArrayList = fileManager.load();

            list.setList(tasksArrayList);
            list.setTaskCount(tasksArrayList.size());

            Reply.sendReply("Loaded!!! Here is the last To Do List saved:", ReplyMode.TOP);
            list.listOut();
        } catch (NikolausIOException e) {
            Reply.sendReply(e.getMessage());
        } catch (NikolausInputMismatchException e) {
            Reply.sendReply("No previous saved To Do List!");
        }
    }

    private static void introduce() {
        Logo.display();
        Reply.sendReply(GREETING, ReplyMode.BOTTOM);
    }

    /**
     * Saves current to do list
     */
    private static void saveToDoList() {
        try {
            fileManager.save(list.getList());
        } catch (NikolausIOException e) {
            Reply.sendReply(e.getMessage());
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
                Reply.sendReply(error.getMessage());
            }
        }

        // save to do list data
        Reply.sendReply("Saving To Do List...", ReplyMode.BOTTOM);
        saveToDoList();
    }
}


