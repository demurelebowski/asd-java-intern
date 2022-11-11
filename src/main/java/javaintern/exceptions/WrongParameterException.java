package javaintern.exceptions;

public class WrongParameterException extends Exception {
	public WrongParameterException(String errorMessage) {
		super(errorMessage);
	}

	public WrongParameterException() {
		super("One or more required Parameters is invalid.");
	}

}
