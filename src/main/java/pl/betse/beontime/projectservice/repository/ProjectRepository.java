package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.betse.beontime.projectservice.entity.ProjectEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    Optional<ProjectEntity> findByGuid(String guid);

    Optional<List<ProjectEntity>> findProjectByDepartmentGuid(String departmentGuid);
}
