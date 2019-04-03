package pl.betse.beontime.projectservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.betse.beontime.projectservice.bo.RolesBo;
import pl.betse.beontime.projectservice.entity.ProjectRolesEntity;
import pl.betse.beontime.projectservice.exception.RoleAlreadyExistsException;
import pl.betse.beontime.projectservice.mapper.RoleMapper;
import pl.betse.beontime.projectservice.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RolesBo> allRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::mapRoleEntityToRoleBo)
                .collect(Collectors.toList());
    }

    public RolesBo addNewRole(RolesBo rolesBo) {
        if (roleRepository.findByName(rolesBo.getName()).isPresent()) {
            log.error("ROLE IS ALREADY EXISTS");
            throw new RoleAlreadyExistsException();
        }
        ProjectRolesEntity projectRolesEntity = roleRepository.save(roleMapper.mapRoleBoToRoleEntity(rolesBo));
        return roleMapper.mapRoleEntityToRoleBo(projectRolesEntity);
    }
}
