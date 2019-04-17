package pl.betse.beontime.projectservice.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleBo {

    private String roleId;

    private String name;

    private boolean projects;

}
