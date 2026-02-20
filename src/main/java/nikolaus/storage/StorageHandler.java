package nikolaus.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

import nikolaus.todolist.Task;
import nikolaus.todolist.ToDo;
import nikolaus.todolist.Deadline;
import nikolaus.todolist.Event;

import nikolaus.ui.Reply;

import nikolaus.exceptions.NikolausIOException;
import nikolaus.exceptions.NikolausSaveFileCorruptedException;

/**
 * Handles storage and file management of ToDoList contents when exiting Nikolaus
 */
public class StorageHandler {
    private final String filePath;

    public StorageHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks from ArrayList to text file
     *
     * @param tasks Tasks stored in ArrayList such as from ToDoList
     * @throws NikolausIOException Exception caught when permissions are denied, or disk is full, or network lost
     */
    public void save(ArrayList<Task> tasks) throws NikolausIOException {
        try {
            // Create directory if needed
            File file = new File(filePath);
            File dir = file.getParentFile();

            if (dir != null && !dir.exists()) {
                boolean created = dir.mkdirs();
                if (created) {
                    System.out.println("Created directory: " + dir.getPath());
                }
            }
            // Write tasks to file
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            throw new NikolausIOException("Apologies Traveler... I have issues saving file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);

            Scanner scanner = new Scanner(file);
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                Task task = null;
                if (!isCorrupted(task, line, lineNumber)) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            return tasks;
        }
        return tasks;
    }

    private boolean isCorrupted(Task task, String line, int lineNumber) {
        try {
            task = parseTaskFromFile(line);
        } catch (IllegalArgumentException e) {
            Reply.sendReply("Skipping corrupted line " + lineNumber + " : " + line);
            return true;
        }
        return false;
    }

    private Task parseTaskFromFile(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        Task task = new Task("");
        return task;
    }
}
