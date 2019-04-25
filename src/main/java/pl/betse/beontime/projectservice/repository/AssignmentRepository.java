package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.betse.beontime.projectservice.entity.ProjectAssignmentsEntity;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<ProjectAssignmentsEntity, Long> {


    boolean existsByUserGuidAndProjectRateEntity(String guid, ProjectRateEntity projectRateEntity);

    Optional<ProjectAssignmentsEntity> findByUserGuid(String userGuid);
}
