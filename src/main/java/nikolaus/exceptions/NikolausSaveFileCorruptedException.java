package nikolaus.exceptions;

import java.io.IOException;

public class NikolausSaveFileCorruptedException extends IOException {
    public NikolausSaveFileCorruptedException(String message) {
        super(message);
    }
}
