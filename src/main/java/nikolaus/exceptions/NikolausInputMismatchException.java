package nikolaus.exceptions;

import java.util.InputMismatchException;

/**
 * Exception used for unexpected or wrong inputs
 */
public class NikolausInputMismatchException extends InputMismatchException {
    public NikolausInputMismatchException(String message) {
        super(message);
    }
}
