package pl.betse.beontime.projectservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProjectBody extends ResourceSupport {

    private String projectId;
    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @Length(max = 500, message = "Comment should contains max. 500 characters")
    private String clientGuid;
    private String comments;
    private String departmentGuid;
    private Boolean offSiteOnly;
    private Boolean active;
    private List<RateBody> rates;
}
