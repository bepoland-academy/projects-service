package pl.betse.beontime.projectservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProjectBody extends ResourceSupport {

    @JsonIgnore
    private String projectId;
    private String name;
    private BigDecimal rate;
    private String comments;
    private String department;
}
