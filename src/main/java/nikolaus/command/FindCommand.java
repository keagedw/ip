package nikolaus.command;

import nikolaus.todolist.TaskList;

public class FindCommand extends ToDoListCommand {
    public FindCommand(String args, TaskList taskList) {
        super(args, taskList);
    }
}
