package nikolaus.todolist;

import nikolaus.ui.Reply;

public class ToDoList {
    private final Task[] list;
    private int index;

    public ToDoList() {
        list = new Task[100];
        index = 0;
    }

    public int getIndex() {
        return index;
    }

    public void add(String task) {
        if (index > 99) {
            Reply.sendReply("To Do List is full");
        } else {
            list[index] = new Task(task);
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
            String tick = list[i].isComplete() ? "X" : " ";
            System.out.println((i + 1) + ": [" + tick + "] " + list[i].getName());
        }
        Reply.createBorder();
    }

    public void mark(int index) {
        if (list[index - 1].isComplete()) {
            Reply.sendReply("nikolaus.todolist.Task already marked complete!");
        } else {
            list[index - 1].setComplete(true);
            Reply.sendReply("Sure thing! I'll put this task as MARKED!", 1);
            Reply.sendReply("[X] " + list[index - 1].getName(), 2);
        }
    }

    public void unmark(int index) {
        if (!list[index - 1].isComplete()) {
            Reply.sendReply("nikolaus.todolist.Task already marked incomplete!");
        } else {
            list[index - 1].setComplete(false);
            Reply.sendReply("OK! The task has been UNMARKED!", 1);
            Reply.sendReply("[ ] " + list[index - 1].getName(), 2);
        }
    }
}
