package pl.betse.beontime.projectservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.betse.beontime.projectservice.mapper.RoleMapper;
import pl.betse.beontime.projectservice.model.RoleBody;
import pl.betse.beontime.projectservice.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RoleMapper roleMapper;
    private final RoleService roleService;

    public RolesController(RoleMapper roleMapper, RoleService roleService) {
        this.roleMapper = roleMapper;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleBody>> getListOfAllRoles(){
        List<RoleBody> roles = roleService.allRoles()
                .stream()
                .map(roleMapper::mapRoleBoToRoleBody)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }


}
