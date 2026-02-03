package nikolaus.ui;

public class Reply {
    private static final int DEFAULT_LENGTH = 200;

    public static void sendReply(String message) {
        sendReply(message, ReplyMode.BOTH);
    }

    /**
     * Sends formatted reply
     *
     * @param message Nikolaus' reply
     * @param mode UPPER: only top border, LOWER: only bottom border, BOTH: both top and bottom border
     */
    public static void sendReply(String message, ReplyMode mode) {
        // create top border
        if (mode != ReplyMode.BOTTOM) {
            createBorder();
        }

        // print message
        System.out.println(message);

        // print bottom border
        if (mode != ReplyMode.TOP) {
            createBorder();
        }
    }

    public static void createBorder() {
        createBorder(DEFAULT_LENGTH);
    }

    public static void createBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print('─');
        }
        System.out.println();
    }
}
