package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.betse.beontime.projectservice.entity.ProjectAssignmentsEntity;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;

public interface AssignmentRepository extends JpaRepository<ProjectAssignmentsEntity, Long> {


    ProjectAssignmentsEntity findByUserGuidAndProjectRateEntity(String guid, ProjectRateEntity projectRateEntity);


    boolean existsByUserGuidAndProjectRateEntity(String guid, ProjectRateEntity projectRateEntity);
}
