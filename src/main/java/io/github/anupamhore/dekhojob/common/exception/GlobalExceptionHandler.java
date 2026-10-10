package io.github.anupamhore.dekhojob.common.exception;

import io.github.anupamhore.dekhojob.user.customexception.EmailAlreadyExistsException;
import io.github.anupamhore.dekhojob.user.customexception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailExists(EmailAlreadyExistsException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problem.setTitle("Email is already registered with DekhoJob");
        return problem;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException e){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problem.setTitle("We are unable to find any records for this user.");
        return problem;
    }

    /*
      Why this DataIntegrityViolationException, when we already have EmailAlreadyExistsException
      This is to cater for cases where two users trying to register same email same millisecond
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException e){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problem.setTitle("Data integrity violation");
        return problem;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ){
        Map<String, String> errors = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
        });
        ProblemDetail problem = e.getBody();
        problem.setTitle("Validation Failed");
        problem.setDetail("One or more fields are invalid");
        problem.setProperty("errors", errors);

        return handleExceptionInternal(e, problem, headers, statusCode, request);
    }

}
