import java.util.Scanner;

public class Nikolaus {
    // setup for command replies
    static String line;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        introduce();
    }

    public static void introduce() {
        Logo.display();
        Reply.sendReply("Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!", 1);
        Reply.sendReply("How may I be of assistance today???", 2);
        awaitCommand();
    }

    private static void awaitCommand() {
        line = in.nextLine();
        if (!Farewell.isKeyword(line)) {
            Reply.sendReply(line);
            awaitCommand();
        } else {
            Farewell.reply();
        }
    }
}


