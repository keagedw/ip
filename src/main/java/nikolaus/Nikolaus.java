package nikolaus;

import java.util.Scanner;

import nikolaus.command.Command;

import nikolaus.commandhandler.CommandHandler;

import nikolaus.todolist.ToDoList;

import nikolaus.ui.Logo;
import nikolaus.ui.Reply;

/**
 * Nikolaus, a multi-functional personal bot to help keep track of tasks!
 */
public class Nikolaus {
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

    private static void introduce() {
        Logo.display();

        Reply.sendReply("Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!\n"
                + "How may I be of assistance today???");
    }
}


