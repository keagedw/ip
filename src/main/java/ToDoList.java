public class ToDoList {
    private String[] list;
    private int index;

    public ToDoList() {
        list = new String[100];
        index = 0;
    }

    public void add(String task) {
        if (index > 99) {
            Reply.sendReply("To Do List is full");
        } else {
            list[index] = task;
            index++;
        }
    }

    public void listOut() {
        if (index == 0) {
            Reply.sendReply("To Do List is empty");
            return;
        }
        Reply.sendReply("To Do List:", 1);
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ": " + list[i]);
        }
        Reply.createBorder();
    }
}
