package nikolaus.command;

/**
 * Executes action when triggered by command from user
 */
public class Command {
    // Array of all triggering keywords
    private final String[] keywords;

    /**
     * Initialises a command triggered by keywords
     *
     * @param keywords List of trigger keywords
     */
    public Command(String[] keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if input from user triggers command
     *
     * @param input Single word input from command handler
     * @return Signals to command handler if input triggers command
     */
    public boolean isTriggeredBy(String input) {
        for (String keyword : keywords) {
            if (input.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns flag for program exit
     */
    public boolean willExit() {
        // Does not exit by default
        return false;
    }

    /**
     * Executes command action
     */
    public void execute() {
        return;
    }

}
