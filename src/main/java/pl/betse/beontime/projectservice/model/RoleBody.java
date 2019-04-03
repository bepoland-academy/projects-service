package pl.betse.beontime.projectservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleBody extends ResourceSupport {

    private String roleId;

    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Name can't be empty")
    private String name;
}
