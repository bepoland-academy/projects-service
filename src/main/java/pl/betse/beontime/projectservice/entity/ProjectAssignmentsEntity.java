package pl.betse.beontime.projectservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PROJECT_ASSIGNMENTS")
public class ProjectAssignmentsEntity {


    private ProjectRateEntity projectRateEntity;

    @Column(name = "USER_GUID",nullable = false, unique = true)
    private String userGuid;

}
