package pl.betse.beontime.projectservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PROJECT_ASSIGNMENT")
public class ProjectAssignmentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ASSIGNMENT_ID")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROJECT_RATE_ID")
    private ProjectRateEntity projectRateEntity;

    @Column(name = "USER_GUID", nullable = false, unique = true)
    private String userGuid;

}
