package nikolaus.command;

public class Command {
    private final String[] keywords;

    public Command(String[] newKeywords) {
        keywords = newKeywords;
    }

    public boolean isTriggeredBy(String input) {
        String lowerCaseInput = input.toLowerCase().trim();
        for (String keyword : keywords) {
            if (lowerCaseInput.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    public boolean willExit() {
        return false;
    }

    public void execute() {
    }

}
