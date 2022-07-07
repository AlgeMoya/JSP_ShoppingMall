package sku.mvc.exception;

/**
 * 로그인할때 인증에 실패하면 발생하는 예외종류
 * */
public class AuthenticationException extends Exception { //체크예외!!!
	public AuthenticationException() {}
	public AuthenticationException(String message) {
		super(message);
	}

}
