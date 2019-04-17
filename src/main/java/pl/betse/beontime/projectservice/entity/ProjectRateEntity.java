package pl.betse.beontime.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PROJECT_RATE")
public class ProjectRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_RATE_ID", unique = true, nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private ProjectEntity projectEntity;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "PROJECT_ROLE_ID")
    private ProjectRoleEntity projectRoleEntity;

    @Column(name = "RATE")
    private BigDecimal rate;

    @Column(name = "ON_SITE_RATE")
    private BigDecimal onSiteRate;

    @OneToMany(mappedBy = "projectRateEntity", fetch = FetchType.LAZY)
    private List<ProjectAssignmentsEntity> projectAssignmentsEntity = new ArrayList<>();

}
