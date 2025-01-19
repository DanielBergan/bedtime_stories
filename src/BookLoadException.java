public class BookLoadException extends Exception {
	// Constructor that takes a message
	public BookLoadException(String message) {
		super(message);
	}

	// Constructor that takes a message and the line number where the error occurred
	public BookLoadException(String message, int lineNumber) {
		super(String.format("Error at line %d: %s", lineNumber, message));
	}

	// Constructor that takes a message and the original cause
	public BookLoadException(String message, Throwable cause) {
		super(message, cause);
	}
}
