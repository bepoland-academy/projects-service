package pl.betse.beontime.projectservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleAssignedToRateException extends RuntimeException {
    public RoleAssignedToRateException() {
        log.error("Role cannot be deleted because is assigned to rates");
    }
}
