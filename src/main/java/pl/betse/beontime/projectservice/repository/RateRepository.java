package pl.betse.beontime.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;

@Repository
public interface RateRepository extends JpaRepository<ProjectRateEntity, Long> {


}
