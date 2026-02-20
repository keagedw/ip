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

    public ArrayList<Task> load() throws NikolausIOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);

            // scan through file
            Scanner fileScanner = new Scanner(file);
            scanThroughFile(tasks, fileScanner);

        } catch (FileNotFoundException e) {
            throw new NikolausIOException("No previous saved To Do List!");
        }
        return tasks;
    }

    private void scanThroughFile(ArrayList<Task> tasks, Scanner fileScanner) {
        int lineNumber = 0;

        while (fileScanner.hasNextLine()) {
            lineNumber++;
            String line = fileScanner.nextLine();
            try {
                Task task = parseTaskFromFile(line);
                tasks.add(task);
            } catch (NikolausSaveFileCorruptedException e) {
                System.out.println("Line " + lineNumber + " corrupted: "
                        + e.getMessage() + "\n"
                        + "Skipping corrupted line...\n");
            }
        }
    }

    private Task parseTaskFromFile(String line) throws NikolausSaveFileCorruptedException {
        String[] parts = line.split(" \\| ");

        if (parts.length <= 1) {
            throw new NikolausSaveFileCorruptedException("Information missing");
        }

        boolean isComplete;
        if (parts[1].equals("X")) {
            isComplete = true;
        } else if (parts[1].equals(" ")) {
            isComplete = false;
        } else {
            throw new NikolausSaveFileCorruptedException(("Mark/Unmark sign missing"));
        }

        Task task = switch (parts[0]) {
            case "T" -> {
                if (parts.length == 2) {
                    throw new NikolausSaveFileCorruptedException("ToDo information missing");
                }
                yield new ToDo(parts[2]);
            }
            case "D" -> {
                if (parts.length < 4) {
                    throw new NikolausSaveFileCorruptedException("Deadline information missing");
                }
                yield new Deadline(parts[2], parts[3]);
            }
            case "E" -> {
                if (parts.length < 5) {
                    throw new NikolausSaveFileCorruptedException("Event information missing");
                }
                yield new Event(parts[2], parts[3], parts[4]);
            }
            default -> throw new NikolausSaveFileCorruptedException("Task not recognised");
        };

        task.setComplete(isComplete);

        return task;
    }
}
