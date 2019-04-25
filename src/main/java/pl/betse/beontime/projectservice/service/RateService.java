package pl.betse.beontime.projectservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.bo.RateBo;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.exception.ProjectNotFoundException;
import pl.betse.beontime.projectservice.mapper.RateMapper;
import pl.betse.beontime.projectservice.repository.ProjectRepository;
import pl.betse.beontime.projectservice.repository.RateRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RateService {

    private final RateMapper rateMapper;
    private final ProjectRepository projectRepository;
    private final RateRepository rateRepository;

    public RateService(RateMapper rateMapper, ProjectRepository projectRepository, RateRepository rateRepository) {
        this.rateMapper = rateMapper;
        this.projectRepository = projectRepository;
        this.rateRepository = rateRepository;
    }


    public List<RateBo> getRateListInProject(ProjectBo projectBo) {
        ProjectEntity projectEntity = projectRepository.findByGuid(projectBo.getProjectId()).orElseThrow(ProjectNotFoundException::new);
        return rateRepository.findByProjectEntity(projectEntity).stream().map(rateMapper::fromEntityToBo).collect(Collectors.toList());
    }

    public List<RateBo> listOfRates() {
        return rateRepository.findAll().stream().map(rateMapper::fromEntityToBo).collect(Collectors.toList());
    }


}