package team.asd.exceptions;

public class MissingParameterException extends Exception {
	public MissingParameterException(String errorMessage) {
		super(errorMessage);
	}

	public MissingParameterException() {
		super("One or more required Parameters are missing a value.");
	}

}
