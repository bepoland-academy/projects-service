package pl.betse.beontime.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PROJECT_ASSIGNMENT")
public class ProjectAssignmentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ASSIGNMENT_ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "PROJECT_RATE_ID")
    private ProjectRateEntity projectRateEntity;

    @Column(name = "USER_GUID", nullable = false, unique = true)
    private String userGuid;

}
