package nikolaus.ui;

import java.util.Scanner;

public class Ui {
    // Messages
    private static final String GREETING = "Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!\n"
            + "How may I be of assistance today???";

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public void introduce() {
        Logo.display();
        Reply.sendReply(GREETING, ReplyMode.BOTTOM);
    }

    public String nextLine() {
        return in.nextLine();
    }
}
