package pl.betse.beontime.projectservice.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectBo {

    private String projectId;
    private String name;
    private String comments;
    private String departmentGuid;
    private String clientGuid;
    private Boolean offSiteOnly;
    private Boolean active;
    private List<RateBo> rates;
}
