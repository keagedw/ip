package nikolaus.ui;

import java.util.Scanner;

import nikolaus.exceptions.NikolausInputMismatchException;

public class Ui {
    // Main messages
    private static final String GREETING = "Greetings Adventurer!!! I'm Nikolaus, your friendly personal guide!!!\n"
            + "How may I be of assistance today???";

    // Command messages
    private static final String FAREWELL_MESSAGE = "Farewell!!! "
            + "I bid you the best of luck on your journey!!!";
    private static final String NO_ARGS_MESSAGE = "Traveler! I can't put nothing????";
    private static final String NO_BY_INDICATOR_MESSAGE = "Traveler, you need to add \"/by\" !!!";
    private static final String NO_FROM_INDICATOR_MESSAGE = "Traveler, you need to add \"/from\" !!!";
    private static final String NO_TO_INDICATOR_MESSAGE = "Traveler, you need to add \"/to\" !!!";
    private static final String NO_INDEX_MESSAGE = "You must provide an index Traveler!";
    private static final String NOT_A_NUMBER_MESSAGE = "Uhhhhhhh Traveler? I don't think that's a number...";

    // Parser messages
    private static final String NO_INPUT_MESSAGE = "Apologies, did you say something Traveler?";
    private static final String NO_COMMAND_MATCH_MESSAGE = "Pardon Traveler but I don't quite understand...\n"
            + "Could you repeat that???";

    private static Scanner in;

    private Ui() {
        in = new Scanner(System.in);
    }

    public static void introduce() {
        Logo.display();
        Reply.sendReply(GREETING, ReplyMode.BOTTOM);
    }

    public static String nextLine() {
        return in.nextLine();
    }

    public static void sendFarewellMessage() {
        Reply.sendReply(FAREWELL_MESSAGE);
    }

    public static NikolausInputMismatchException throwNoArgsException() {
        return new NikolausInputMismatchException(NO_ARGS_MESSAGE);
    }

    public static NikolausInputMismatchException throwNoByException() {
        return new NikolausInputMismatchException(NO_BY_INDICATOR_MESSAGE);
    }

    public static NikolausInputMismatchException throwNoFromException() {
        return new NikolausInputMismatchException(NO_FROM_INDICATOR_MESSAGE);
    }

    public static NikolausInputMismatchException throwNoToException() {
        return new NikolausInputMismatchException(NO_TO_INDICATOR_MESSAGE);
    }

    public static NikolausInputMismatchException throwNoIndexException() {
        return new NikolausInputMismatchException(NO_INDEX_MESSAGE);
    }

    public static NikolausInputMismatchException throwNotNumberException() {
        return new NikolausInputMismatchException(NOT_A_NUMBER_MESSAGE);
    }
}
