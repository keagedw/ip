package nikolaus.ui;

import java.util.Scanner;

import nikolaus.exceptions.NikolausIOException;
import nikolaus.exceptions.NikolausInputMismatchException;
import nikolaus.exceptions.NikolausSaveFileCorruptedException;

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

    // Storage messages
    private static final String DIRECTORY_CREATED_MESSAGE = "Created directory: ";
    private static final String SAVE_FILE_ISSUE_MESSAGE = "Apologies Traveler... I have issues saving file: ";
    private static final String NO_PREVIOUS_SAVE_MESSAGE = "No previous saved To Do List!";
    private static final String LINE_CORRUPTED_MESSAGE_A = "Line ";
    private static final String LINE_CORRUPTED_MESSAGE_B = " corrupted: ";
    private static final String LINE_CORRUPTED_MESSAGE_C = "\nSkipping corrupted line...\n";
    private static final String INFO_MISSING_MESSAGE = "Information missing";
    private static final String MARK_UNMARK_SIGN_MISSING_MESSAGE = "Mark/Unmark sign missing";
    private static final String TODO_INFO_MISSING = "ToDo information missing";
    private static final String DEADLINE_INFO_MISSING = "Deadline information missing";
    private static final String EVENT_INFO_MISSING = "Event information missing";
    private static final String TASK_NOT_RECOGNISED_MESSAGE = "Task not recognised";

    private static Scanner in = new Scanner(System.in);

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

    public static void sendDirectoryCreatedMessage(String path) {
        System.out.println(DIRECTORY_CREATED_MESSAGE + path);
    }

    public static void sendLineCorruptedMessage(int lineNumber, String message) {
        System.out.println(LINE_CORRUPTED_MESSAGE_A + lineNumber
                + LINE_CORRUPTED_MESSAGE_B + message
                + LINE_CORRUPTED_MESSAGE_C);
    }

    public static NikolausIOException throwSaveFileIssueMessage(String message) {
        return new NikolausIOException(SAVE_FILE_ISSUE_MESSAGE + message);
    }

    public static NikolausIOException throwNoPreviousSaveMessage() {
        return new NikolausIOException(NO_PREVIOUS_SAVE_MESSAGE);
    }

    public static NikolausSaveFileCorruptedException throwInfoMissingMessage() {
        return new NikolausSaveFileCorruptedException(INFO_MISSING_MESSAGE);
    }

    public static NikolausSaveFileCorruptedException throwMarkUnmarkSignMissingMessage() {
        return new NikolausSaveFileCorruptedException(MARK_UNMARK_SIGN_MISSING_MESSAGE);
    }

    public static NikolausSaveFileCorruptedException throwToDoInfoMissingMessage() {
        return new NikolausSaveFileCorruptedException(TODO_INFO_MISSING);
    }

    public static NikolausSaveFileCorruptedException throwDeadlineInfoMissingMessage() {
        return new NikolausSaveFileCorruptedException(DEADLINE_INFO_MISSING);
    }

    public static NikolausSaveFileCorruptedException throwEventInfoMissingMessage() {
        return new NikolausSaveFileCorruptedException(EVENT_INFO_MISSING);
    }

    public static NikolausSaveFileCorruptedException throwTaskNotRecognisedMessage() {
        return new NikolausSaveFileCorruptedException(TASK_NOT_RECOGNISED_MESSAGE);
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

    public static NikolausInputMismatchException throwNoInputException() {
        return new NikolausInputMismatchException(NO_INPUT_MESSAGE);
    }

    public static NikolausInputMismatchException throwNoCommandMatchException() {
        return new NikolausInputMismatchException(NO_COMMAND_MATCH_MESSAGE);
    }
}
