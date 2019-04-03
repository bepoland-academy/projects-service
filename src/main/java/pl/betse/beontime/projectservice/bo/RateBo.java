package pl.betse.beontime.projectservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RateBo {

    private ProjectBo projectBo;
    private RoleBo roleBo;
    private BigDecimal rate;
    private boolean onSite;
}
