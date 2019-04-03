package pl.betse.beontime.projectservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectAlreadyExistException extends RuntimeException {
    public ProjectAlreadyExistException() {
    log.error("PROJECT ALREADY EXISTS");
    }
}
