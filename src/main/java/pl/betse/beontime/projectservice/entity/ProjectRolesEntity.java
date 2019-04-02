package pl.betse.beontime.projectservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PROJECT_ROLES")
public class ProjectRolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ROLE_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
