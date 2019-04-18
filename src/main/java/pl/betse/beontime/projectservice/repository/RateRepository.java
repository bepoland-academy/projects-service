package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;
import pl.betse.beontime.projectservice.entity.ProjectRoleEntity;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<ProjectRateEntity, Long> {

    List<ProjectRateEntity> findByProjectEntity(ProjectEntity projectEntity);

    ProjectRateEntity findByProjectRoleEntity(ProjectRoleEntity projectRoleEntity);


    ProjectRateEntity findByProjectRoleEntityAndProjectEntity_Guid(ProjectRoleEntity projectRoleEntity, String projectGuid);
}
