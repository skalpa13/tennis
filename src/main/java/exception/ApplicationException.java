package exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		//printStackTrace();
	}

}
