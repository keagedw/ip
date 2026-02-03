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
    static boolean exitFlag = false;

    /**
     * Executes Nikolaus
     */
    public static void main(String[] args) {
        introduce();

        // keeps looping until command signals to stop
        while (!exitFlag) {
            // gets input from user
            String inputCommand = in.nextLine();

            // handler processes command; returns command run
            Command command = handler.execute(inputCommand);

            // update signal to exit Nikolaus
            exitFlag = handler.shouldExit(command);
        }
    }

    private static void introduce() {
        Logo.display();

        Reply.sendReply("Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!\n"
                + "How may I be of assistance today???");
    }
}


