package pl.betse.beontime.projectservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        log.error("PROJECT DOES NOT FOUND");
    }
}
