package pl.betse.beontime.projectservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.betse.beontime.projectservice.exception.*;

import java.util.ArrayList;

@RestControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProjectNotFoundException.class})
    public @ResponseBody
    ResponseEntity<ExceptionInformation> sendProjectNotFoundMessage() {
        return new ResponseEntity<>(new ExceptionInformation("PROJECT NOT FOUND"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProjectAlreadyExistException.class})
    public @ResponseBody
    ResponseEntity<ExceptionInformation> sendProjectExist() {
        return new ResponseEntity<>(new ExceptionInformation("PROJECT ALREADY EXISTS"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ClientNotFoundException.class})
    public @ResponseBody
    ResponseEntity<ExceptionInformation> sendClientNotFoundMessage() {
        return new ResponseEntity<>(new ExceptionInformation("CLIENT NOT FOUND"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ClientAlreadyExistException.class})
    public @ResponseBody
    ResponseEntity<ExceptionInformation> sendClientExist() {
        return new ResponseEntity<>(new ExceptionInformation("CLIENT ALREADY EXISTS"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ProjectNoClientException.class})
    public @ResponseBody
    ResponseEntity<ExceptionInformation> sendProjectNoClientException() {
        return new ResponseEntity<>(new ExceptionInformation("CLIENT SHOULD BE ASSIGNED TO PROJECT"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TimeEntriesForProjectExists.class})
    public @ResponseBody
    ResponseEntity<ExceptionInformation> sendTimeEntriesForProjectExistsException() {
        return new ResponseEntity<>(new ExceptionInformation("PROJECT CANNOT BE DELETED BECAUSE OF EXISTING TIME ENTRIES"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ClientAssignedToProjectException.class})
    public @ResponseBody
    ResponseEntity<ExceptionInformation> sendClientAssignedToProjectExceptionException() {
        return new ResponseEntity<>(new ExceptionInformation("CLIENT CANNOT BE DELETED BECAUSE OF ASSIGNMENT TO THE PROJECT"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProjectIsActiveException.class})
    public @ResponseBody
    ResponseEntity<ExceptionInformation> sendProjectIsActiveException() {
        return new ResponseEntity<>(new ExceptionInformation("ACTIVE PROJECT CANNOT BE DELETED"), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ArrayList<ExceptionInformation> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ExceptionInformation(error.getDefaultMessage()));
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}



