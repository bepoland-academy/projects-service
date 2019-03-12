package pl.betse.beontime.projectservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.betse.beontime.projectservice.exception.*;

@RestControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProjectNotFoundException.class})
    public @ResponseBody
    ResponseEntity<?> sendProjectNotFoundMessage() {
        return new ResponseEntity<>(new ExceptionInformation("PROJECT NOT FOUND"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProjectAlreadyExistException.class})
    public @ResponseBody
    ResponseEntity<?> sendProjectExist() {
        return new ResponseEntity<>(new ExceptionInformation("PROJECT ALREADY EXISTS"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ClientNotFoundException.class})
    public @ResponseBody
    ResponseEntity<?> sendClientNotFoundMessage() {
        return new ResponseEntity<>(new ExceptionInformation("CLIENT NOT FOUND"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ClientAlreadyExistException.class})
    public @ResponseBody
    ResponseEntity<?> sendClientExist() {
        return new ResponseEntity<>(new ExceptionInformation("CLIENT ALREADY EXISTS"), HttpStatus.CONFLICT);
    }
}



