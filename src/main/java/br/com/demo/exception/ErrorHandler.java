package br.com.demo.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.google.gson.Gson;

@ControllerAdvice
public class ErrorHandler {

	@ResponseBody
	@ExceptionHandler(ValidateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String validateExceptionHandler(final ValidateException ex) {
		final ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
		String json = new Gson().toJson(errorDetails);
		return json.toString();
	}

	@ResponseBody
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String validateExceptionHandler(final DataIntegrityViolationException ex) {
		final ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
		String json = new Gson().toJson(errorDetails);
		return json.toString();
	}

}
