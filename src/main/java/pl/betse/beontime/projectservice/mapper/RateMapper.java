package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import pl.betse.beontime.projectservice.bo.RateBo;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;

@Mapper(componentModel = "spring")
public abstract class RateMapper {


    public RateBo fromRateEntityToRateBo(ProjectRateEntity projectRateEntity) {
        return RateBo.builder()
                .roleId(projectRateEntity.getProjectRoleEntity().getId())
                .rate(projectRateEntity.getRate())
                .onSiteRate(projectRateEntity.getOnSiteRate())
                .build();
    }


}
