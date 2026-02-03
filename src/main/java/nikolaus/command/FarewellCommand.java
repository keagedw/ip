package nikolaus.command;

import nikolaus.ui.Reply;

/**
 * Ends program wih farewell
 */
public class FarewellCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * "bye" is triggering keyword
     */
    public FarewellCommand() {
        super(new String[]{"bye"});
    }

    @Override
    public boolean willExit() {
        return true;
    }

    @Override
    public void execute() {
        Reply.sendReply("Farewell!!! I bid you the best of luck on your journey!!!");
    }
}
