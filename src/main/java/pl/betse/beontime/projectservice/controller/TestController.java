package pl.betse.beontime.projectservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.mapper.ProjectMapper;
import pl.betse.beontime.projectservice.repository.ProjectRepository;
import pl.betse.beontime.projectservice.repository.RateRepository;
import pl.betse.beontime.projectservice.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProjectEntity>> getALL() {

        List<ProjectEntity> list = projectRepository.findAll();

        List<ProjectBo> bos = list.stream().map(projectMapper::fromEntityToBo).collect(Collectors.toList());

        return ResponseEntity.ok(bos.stream().map(projectMapper::fromBoToEntity).collect(Collectors.toList()));
    }

    @GetMapping("/all/2")
    public ResponseEntity<List<ProjectBo>> getALL2() {

        List<ProjectEntity> list = projectRepository.findAll();


        return ResponseEntity.ok(list.stream().map(projectMapper::fromEntityToBo).collect(Collectors.toList()));
    }


}
