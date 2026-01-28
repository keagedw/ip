public class Farewell {
    // selection of keywords to activate command
    static String[] keywords = {
        "goodbye",
        "bye",
        "farewell"
    };

    public static boolean isKeyword(String inputCommand) {
        String lowerCaseInput = inputCommand.toLowerCase();
        for (String keyword : keywords) {
            if (lowerCaseInput.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    public static void reply() {
        Reply.sendReply("Farewell!!! I bid you the best of luck on your journey!!!");
    }
}
