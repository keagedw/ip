package nikolaus.exceptions;

import java.io.IOException;

/**
 * Exception to handle Scanner IO errors
 */
public class NikolausIOException extends IOException {
    public NikolausIOException(String message) {
        super(message);
    }
}
