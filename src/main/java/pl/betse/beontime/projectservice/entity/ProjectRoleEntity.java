package pl.betse.beontime.projectservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PROJECT_ROLE")
public class ProjectRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ROLE_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "PROJECT_ROLE_GUID")
    private String roleGuid;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "projectRoleEntity", fetch = FetchType.EAGER)
    List<ProjectRateEntity> projectRateEntities = new ArrayList<>();
}
