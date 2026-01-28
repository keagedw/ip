import java.util.Scanner;

public class Nikolaus {
    // setup for command replies
    static Scanner in = new Scanner(System.in);
    static ToDoList list = new ToDoList();
    static CommandHandler handler = new CommandHandler(list, in);
    static boolean exitFlag = false;

    public static void main(String[] args) {
        introduce();
        while (!exitFlag) {
            String line = in.nextLine();
            Command command = handler.parse(line);
            handler.execute(command);
            exitFlag = handler.shouldExit(command);
        }
    }

    public static void introduce() {
        Logo.display();
        Reply.sendReply("Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!", 1);
        Reply.sendReply("How may I be of assistance today???", 2);
    }
}


