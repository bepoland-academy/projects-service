package pl.betse.beontime.projectservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private ProjectRolesEntity projectRolesEntity;

    @Column(name = "RATE")
    private BigDecimal rate;

    @Column(name = "ON_SITE")
    private boolean onSite;


}
