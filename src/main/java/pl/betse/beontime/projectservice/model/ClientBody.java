package pl.betse.beontime.projectservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClientBody extends ResourceSupport {

    private String clientId;
    private String name;
}
