package pl.betse.beontime.projectservice.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateBo {

    private String roleId;
    private BigDecimal rate;
    private BigDecimal onSiteRate;
    private List<String> consultants;
}
