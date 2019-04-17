package pl.betse.beontime.projectservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class RateBody {

    private Long roleId;
    private BigDecimal rate;
    private BigDecimal onSiteRate;
    private List<String> consultants;

}
