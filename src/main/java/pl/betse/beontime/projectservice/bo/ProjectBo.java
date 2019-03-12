package pl.betse.beontime.projectservice.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProjectBo {

    private String id;
    private String name;
    private BigDecimal rate;
    private String comments;
    private String departmentId;
}
