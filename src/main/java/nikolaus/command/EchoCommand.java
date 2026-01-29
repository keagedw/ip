package nikolaus.command;

import nikolaus.ui.Reply;

public class EchoCommand extends Command {
    private final String echo;

    public EchoCommand(String echo) {
        super(new String[]{});
        this.echo = echo;
    }

    @Override
    public void execute() {
        Reply.sendReply(echo);
    }
}
