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

import nikolaus.ui.Ui;

import nikolaus.exceptions.NikolausIOException;
import nikolaus.exceptions.NikolausSaveFileCorruptedException;

/**
 * Handles storage and file management of TaskList contents when exiting Nikolaus
 */
public class Storage {
    // Constants
    private final String LINE_SPLIT_REGEX = " \\| ";
    private final String MARK_INDICATOR = "X";
    private final String UNMARK_INDICATOR = " ";
    private final String TODO_INDICATOR = "T";
    private final String DEADLINE_INDICATOR = "D";
    private final String EVENT_INDICATOR = "E";

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks from ArrayList to text file
     *
     * @param tasks Tasks stored in ArrayList such as from TaskList
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
                    Ui.sendDirectoryCreatedMessage(dir.getPath());
                }
            }
            // Write tasks to file
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            throw Ui.throwSaveFileIssueMessage(e.getMessage());
        }
    }

    /**
     * Loads any saved to do lists from data text file
     *
     * @return ArrayList of Tasks to be inserted into To Do List
     * @throws NikolausIOException Exception caught when no data file found
     */
    public ArrayList<Task> load() throws NikolausIOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);

            // scan through file
            Scanner fileScanner = new Scanner(file);
            scanThroughFile(tasks, fileScanner);

        } catch (FileNotFoundException e) {
            throw Ui.throwNoPreviousSaveMessage();
        }
        return tasks;
    }

    /**
     * Scans through entire data file line by line
     *
     * @param tasks ArrayList to add Tasks to
     * @param fileScanner Scanner to read file
     */
    private void scanThroughFile(ArrayList<Task> tasks, Scanner fileScanner) {
        int lineNumber = 0;

        while (fileScanner.hasNextLine()) {
            lineNumber++;
            String line = fileScanner.nextLine();
            try {
                Task task = parseTaskFromFile(line);
                tasks.add(task);
            } catch (NikolausSaveFileCorruptedException e) {
                Ui.sendLineCorruptedMessage(lineNumber, e.getMessage());
            }
        }
    }

    /**
     * Parses through String for Task information
     *
     * @param line String to be parsed
     * @return Appropriate task given the line
     * @throws NikolausSaveFileCorruptedException When line is corrupted and no longer fits format
     */
    private Task parseTaskFromFile(String line) throws NikolausSaveFileCorruptedException {
        String[] parts = line.split(LINE_SPLIT_REGEX);

        if (parts.length <= 1) {
            throw Ui.throwInfoMissingMessage();
        }

        boolean isComplete;
        if (parts[1].equals(MARK_INDICATOR)) {
            isComplete = true;
        } else if (parts[1].equals(UNMARK_INDICATOR)) {
            isComplete = false;
        } else {
            throw Ui.throwMarkUnmarkSignMissingMessage();
        }

        Task task = switch (parts[0]) {
            case TODO_INDICATOR -> {
                if (parts.length == 2) {
                    throw Ui.throwToDoInfoMissingMessage();
                }
                yield new ToDo(parts[2]);
            }
            case DEADLINE_INDICATOR -> {
                if (parts.length < 4) {
                    throw Ui.throwDeadlineInfoMissingMessage();
                }
                yield new Deadline(parts[2], parts[3]);
            }
            case EVENT_INDICATOR -> {
                if (parts.length < 5) {
                    throw Ui.throwEventInfoMissingMessage();
                }
                yield new Event(parts[2], parts[3], parts[4]);
            }
            default -> throw Ui.throwTaskNotRecognisedMessage();
        };

        task.setComplete(isComplete);

        return task;
    }
}
