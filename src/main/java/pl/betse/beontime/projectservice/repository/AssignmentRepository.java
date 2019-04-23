package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.betse.beontime.projectservice.entity.ProjectAssignmentsEntity;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;

public interface AssignmentRepository extends JpaRepository<ProjectAssignmentsEntity, Long> {


    boolean existsByUserGuidAndProjectRateEntity(String guid, ProjectRateEntity projectRateEntity);
}
