package nikolaus.command;

import nikolaus.ui.Reply;
import nikolaus.ui.Ui;

/**
 * Routes command line input to command execution
 * Ends program wih farewell
 */
public class FarewellCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public FarewellCommand(String args) {
        super(args);
    }

    /**
     * Creates an instance of FarewellCommand
     *
     * Summoned from CommandFactory
     *
     * @param args arguments fed
     * @return command created
     */
    public static Command parse(String args) {
        return new FarewellCommand(args);
    }

    @Override
    public boolean willExit() {
        return true;
    }

    /**
     * Bids farewell
     */
    @Override
    public void execute() {
        Ui.sendFarewellMessage();
    }
}
