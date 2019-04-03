package pl.betse.beontime.projectservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectBo {

    private String id;
    private String name;
    private String comments;
    private String departmentGuid;
    private ClientBo clientBo;
    private boolean active;
}
