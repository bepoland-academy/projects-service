package pl.betse.beontime.projectservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    @Column(name = "CLIENT_NAME", nullable = false)
    private String name;

}



