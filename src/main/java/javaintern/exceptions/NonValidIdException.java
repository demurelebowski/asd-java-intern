package javaintern.exceptions;

public class NonValidIdException extends Exception {
	public NonValidIdException(String errorMessage) {
		super(errorMessage);
	}

	public NonValidIdException() {
		super("Non-valid id.");
	}
}
