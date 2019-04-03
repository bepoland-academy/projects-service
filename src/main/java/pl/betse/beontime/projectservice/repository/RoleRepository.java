package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.betse.beontime.projectservice.entity.ProjectRolesEntity;

@Repository
public interface RoleRepository extends JpaRepository<ProjectRolesEntity, Long> {
}
