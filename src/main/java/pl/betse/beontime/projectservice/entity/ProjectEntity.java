package pl.betse.beontime.projectservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PROJECT")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ID")
    private Long id;

    @Column(name = "PROJECT_GUID", nullable = false, unique = true)
    private String guid;

    @Column(name = "PROJECT_NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "PROJECT_RATE")
    private BigDecimal rate;

    @Length(max = 500)
    @Column(name = "COMMENTS")
    private String comments;

    @ManyToMany
    @JoinTable(name = "CLIENT_PROJECT",
            joinColumns = @JoinColumn(name = "PROJECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLIENT_ID"))
    private List<ClientEntity> clientEntities;

    @Column(name = "DEPARTMENT_ID")
    private String departmentId;


}
