package nikolaus;

import java.util.Scanner;

import nikolaus.commandhandler.CommandHandler;

import nikolaus.todolist.ToDoList;

import nikolaus.ui.Logo;
import nikolaus.ui.Reply;

import nikolaus.exceptions.NikolausInputMismatchException;

/**
 * Nikolaus, a multi-functional personal bot to help keep track of tasks!
 */
public class Nikolaus {
    // Messages
    private static final String GREETING = "Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!\n"
            + "How may I be of assistance today???";

    // setup for command replies
    static Scanner in = new Scanner(System.in);
    static ToDoList list = new ToDoList(in);

    // setup command handler to accept commands
    static CommandHandler handler = new CommandHandler(list);

    // signal to exit bot
    static boolean willExit = false;

    /**
     * Executes Nikolaus
     */
    public static void main(String[] args) {
        introduce();
        run();
    }

    private static void introduce() {
        Logo.display();
        Reply.sendReply(GREETING);
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
    }
}


