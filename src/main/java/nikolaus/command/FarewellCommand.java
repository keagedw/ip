package nikolaus.command;

import java.util.InputMismatchException;

import nikolaus.ui.Reply;

/**
 * Ends program wih farewell
 */
public class FarewellCommand extends Command {
    private static final String FAREWELL_MESSAGE = "Farewell!!! "
            + "I bid you the best of luck on your journey!!!";

    /**
     * {@inheritDoc}
     */
    public FarewellCommand(String args) {
        super(args);
    }

    public static Command parse(String args) {
        return new FarewellCommand(args);
    }

    @Override
    public boolean willExit() {
        return true;
    }

    @Override
    public void execute() {
        Reply.sendReply(FAREWELL_MESSAGE);
    }
}
