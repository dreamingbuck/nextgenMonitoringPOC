package edu.stanford.sumonitorspring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that indicates a resource is not found - a 404 error.
 * 
 * <p>
 * Spring automatically converts this into the default 404 page
 * </p>
 * 
 * @author pradtke
 * 
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a 404 error.
	 * 
	 * @param message
	 *            The message - may be shown to users.
	 * @param throwable
	 *            The root cause
	 */
	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * Create a 404 error.
	 * 
	 * @param message
	 *            The message - may be shown to users.
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

}