package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.betse.beontime.projectservice.entity.ProjectRoleEntity;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<ProjectRoleEntity, Long> {
    Optional<ProjectRoleEntity> findByName(String name);

    Optional<ProjectRoleEntity> findByRoleGuid(String guid);


}
