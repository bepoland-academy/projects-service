package pl.betse.beontime.projectservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.repository.ProjectRepository;
import pl.betse.beontime.projectservice.repository.RateRepository;
import pl.betse.beontime.projectservice.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/all")
    public ResponseEntity<List<ProjectEntity>> getALL() {

        List<ProjectEntity> list = projectRepository.findAll();

        return ResponseEntity.ok(list);
    }


}
