package pl.betse.beontime.projectservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.betse.beontime.projectservice.bo.RoleBo;
import pl.betse.beontime.projectservice.entity.ProjectRoleEntity;
import pl.betse.beontime.projectservice.exception.RoleAlreadyExistsException;
import pl.betse.beontime.projectservice.exception.RoleAssignedToRateException;
import pl.betse.beontime.projectservice.exception.RoleNotFoundException;
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

    public List<RoleBo> allRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::fromEntityToBo)
                .collect(Collectors.toList());
    }

    public RoleBo findByGuid(String roleGuid) {
        ProjectRoleEntity projectRoleEntity = roleRepository.findByRoleGuid(roleGuid).orElseThrow(RoleNotFoundException::new);
        return roleMapper.fromEntityToBo(projectRoleEntity);
    }

    public RoleBo addNewRole(RoleBo roleBo) {
        if (roleRepository.findByName(roleBo.getName()).isPresent()) {
            log.error("ROLE IS ALREADY EXISTS");
            throw new RoleAlreadyExistsException();
        }
        ProjectRoleEntity projectRoleEntity = roleRepository.save(roleMapper.fromBoToEntity(roleBo));
        return roleMapper.fromEntityToBo(projectRoleEntity);
    }

    public void editRole(String guid, RoleBo roleBo) {
        ProjectRoleEntity projectRoleEntity = roleRepository.
                findByRoleGuid(guid).
                orElseThrow(RoleNotFoundException::new);
        projectRoleEntity.setName(roleBo.getName());
        roleRepository.save(projectRoleEntity);
    }

    public void deleteRole(String guid) {
        ProjectRoleEntity projectRoleEntity = roleRepository.
                findByRoleGuid(guid).
                orElseThrow(RoleNotFoundException::new);
        if (!projectRoleEntity.getProjectRateEntities().isEmpty()) {
            throw new RoleAssignedToRateException();
        }
        roleRepository.delete(projectRoleEntity);
    }

}
