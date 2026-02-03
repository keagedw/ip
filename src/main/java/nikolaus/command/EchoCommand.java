package nikolaus.command;

import nikolaus.ui.Reply;

/**
 * Repeats back user input
 */
public class EchoCommand extends Command {
    private String echo;

    /**
     * No triggering keyword
     *
     * @param echo last user input
     */
    public EchoCommand(String echo) {
        super(new String[]{});
        this.echo = echo;
    }

    /**
     * Repeats back last user input
     */
    @Override
    public void execute() {
        Reply.sendReply(echo);
    }
}
