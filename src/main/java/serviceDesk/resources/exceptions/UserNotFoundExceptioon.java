package serviceDesk.resources.exceptions;

public class UserNotFoundExceptioon extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UserNotFoundExceptioon(String msg) {
		super(msg);
	}
}
