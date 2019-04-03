package pl.betse.beontime.projectservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        log.error("ROLE DOES NOT FOUND");
    }
}
