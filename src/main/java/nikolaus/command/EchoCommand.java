package nikolaus.command;

import nikolaus.ui.Reply;

/**
 * Repeats back user input
 */
public class EchoCommand extends Command {
    /**
     * No triggering keyword
     *
     * @param args last user input
     */
    public EchoCommand(String args) {
        super(args);
    }

    /**
     * Creates an instance of EchoCommand
     *
     * Summoned from CommandFactory
     *
     * @param args arguments fed
     * @return command created
     */
    public static Command parse(String args) {
        return new EchoCommand(args);
    }

    /**
     * Repeats back last user input
     */
    @Override
    public void execute() {
        Reply.sendReply(args);
    }
}
