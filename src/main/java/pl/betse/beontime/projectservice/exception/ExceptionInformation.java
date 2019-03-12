package pl.betse.beontime.projectservice.exception;

import lombok.Getter;

@Getter
public class ExceptionInformation {
    private String exceptionMessage;


    public ExceptionInformation(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
