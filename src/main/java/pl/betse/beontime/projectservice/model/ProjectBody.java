package pl.betse.beontime.projectservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProjectBody extends ResourceSupport {

    private String projectId;
    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @Length(max = 500, message = "Comment should contains max. 500 characters")
    private String comments;
    private String department;
    private ClientBody client;
    private boolean active;
    @NotNull(message = "Rate can't be null")
    private BigDecimal rate;
}
