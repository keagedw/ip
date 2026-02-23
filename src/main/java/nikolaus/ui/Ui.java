package nikolaus.ui;

public class Ui {
    // Messages
    private static final String GREETING = "Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!\n"
            + "How may I be of assistance today???";

    public Ui(){}

    public void introduce() {
        Logo.display();
        Reply.sendReply(GREETING, ReplyMode.BOTTOM);
    }
}
