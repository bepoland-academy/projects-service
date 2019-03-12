package pl.betse.beontime.projectservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "CLIENT_GUID", nullable = false, unique = true)
    private String guid;

    @Column(name = "CLIENT_NAME")
    private String name;

    @ManyToMany(mappedBy = "clientEntities")
    private List<ProjectEntity> projectEntities;
}



