package pl.betse.beontime.projectservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PROJECT")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ID")
    private Long id;

    @Column(name = "PROJECT_GUID", nullable = false, unique = true)
    private String guid;

    @Column(name = "PROJECT_NAME", nullable = false)
    private String name;

    @Length(max = 500)
    @Column(name = "COMMENTS")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity clientEntity;

    @Column(name = "DEPARTMENT_GUID")
    private String departmentGuid;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "OFF_SITE_ONLY")
    private Boolean offSiteOnly = true;

    @OneToMany(mappedBy = "projectEntity", fetch = FetchType.LAZY)
    private List<ProjectRateEntity> rates;

}
