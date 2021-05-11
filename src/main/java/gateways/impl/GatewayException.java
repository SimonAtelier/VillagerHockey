package gateways.impl;

public class GatewayException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GatewayException(String message, Throwable cause) {
		super(message, cause);
	}

	public GatewayException(String message) {
		super(message);
	}

	public GatewayException(Throwable cause) {
		super(cause);
	}

}
