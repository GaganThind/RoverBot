package in.gagan.roverbot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

import in.gagan.roverbot.constant.ApplicationConstants;

/**
 * Global error handler
 * 
 * @author thindgagan
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Global exception handler for IllegalArgumentException
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<?> illegalArgumentExceptionHandler(final Exception ex, WebRequest request) {
		return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Global exception handler for JsonProcessingException
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(JsonProcessingException.class)
	public final ResponseEntity<?> jsonProcessingExceptionHandler(final Exception ex, WebRequest request) {
		return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.FAILED_DEPENDENCY);
	}
	
	/**
	 * Global exception handler for Exception
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> globalExceptionHandler(final Exception ex, WebRequest request) {
		return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Get the Error Message from the exception
	 * 
	 * @param ex
	 * @return
	 */
	private String getErrorMessage(Exception ex) {
		return null != ex.getMessage() ? ex.getMessage() : ApplicationConstants.UNKNOWN_EXCEPTION;
	}

}
