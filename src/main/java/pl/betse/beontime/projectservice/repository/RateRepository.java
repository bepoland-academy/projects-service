package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;
import pl.betse.beontime.projectservice.entity.ProjectRoleEntity;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<ProjectRateEntity, Long> {
    Optional<ProjectRateEntity> findByProjectRoleEntity(ProjectRoleEntity projectRoleEntity);
}
