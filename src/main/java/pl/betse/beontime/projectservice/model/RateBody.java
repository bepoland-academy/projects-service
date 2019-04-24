package pl.betse.beontime.projectservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RateBody extends ResourceSupport {

    private String roleId;
    private BigDecimal rate;
    private BigDecimal onSiteRate;
    private List<String> consultants;

}
