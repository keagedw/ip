package nikolaus.command;

/**
 * Executes action when triggered by command from user
 */
public class Command {
    protected String args;
    /**
     * Initialises a command triggered by keywords
     *
     * @param args arguments to be parsed
     */
    public Command(String args) {
        this.args = args;
    }

    public static Command parse(String args){
        return new Command(args);
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
