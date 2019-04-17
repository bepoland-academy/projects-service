package pl.betse.beontime.projectservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PROJECT_RATE")
public class ProjectRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_RATE_ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROJECT_ID")
    private ProjectEntity projectEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROJECT_ROLE_ID")
    private ProjectRoleEntity projectRoleEntity;

    @Column(name = "RATE")
    private BigDecimal rates;

    @Column(name = "ON_SITE_RATE")
    private BigDecimal onSiteRate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROJECT_ASSIGNMENT_ID")
    private List<ProjectAssignmentsEntity> projectAssignmentsEntity = new ArrayList<>();

}
