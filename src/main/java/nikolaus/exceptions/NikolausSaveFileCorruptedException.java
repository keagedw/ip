package nikolaus.exceptions;

import java.io.IOException;

/**
 * Exception used for when save file being read from is corrupted
 */
public class NikolausSaveFileCorruptedException extends IOException {
    public NikolausSaveFileCorruptedException(String message) {
        super(message);
    }
}
