package pl.betse.beontime.projectservice.exception;

import lombok.Getter;

@Getter
public class ExceptionInformation {
    private String message;


    public ExceptionInformation(String message) {
        this.message = message;
    }
}
