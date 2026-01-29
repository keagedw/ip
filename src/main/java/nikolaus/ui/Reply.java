package nikolaus.ui;

public class Reply {
    public static void sendReply(String message) {
        sendReply(message, 0);
    }

    // mode 0: top and bottom border, mode 1: only top, mode 2: only bottom
    public static void sendReply(String message, int mode) {
        if (mode < 0 | mode > 2) {
            throw new ArithmeticException("Not a legal mode. Please pick a mode between 0 and 2");
        }
        if (mode == 0 | mode == 1) {
            createBorder();
        }
        System.out.println(message);
        if (mode == 0 | mode == 2) {
            createBorder();
        }
    }

    public static void createBorder() {
        createBorder(200);
    }

    public static void createBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print('─');
        }
        System.out.println();
    }
}
